package com.kt.AssetSearcher.model.website;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.kt.AssetSearcher.model.Thumbnail;
import com.kt.AssetSearcher.thread.ThreadWithValue;

abstract class Scraper {
	protected ThreadWithValue<List<Thumbnail>> get(String url, String selector) {
		return new ThreadWithValue<List<Thumbnail>>(()->{
			try {
				return Jsoup
					.connect(url)
					.get()
					.select(selector)
					.stream()
					.map(this::parse)
					.collect(Collectors.toList());
			}catch(IOException e) {
				e.printStackTrace();
				return null;
			}
		});
	}
	
	abstract protected Thumbnail parse(Element elem);
}
