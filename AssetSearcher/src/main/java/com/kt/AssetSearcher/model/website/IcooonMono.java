package com.kt.AssetSearcher.model.website;

import java.util.List;

import org.jsoup.nodes.Element;

import com.kt.AssetSearcher.model.Thumbnail;
import com.kt.AssetSearcher.thread.ThreadWithValue;

public class IcooonMono extends Scraper {
	public ThreadWithValue<List<Thumbnail>> getIcon(String q) {
		return get("https://icooon-mono.com/?s=" + q, "#topMaincolumn > ul > li > a");
	}
	
	@Override
	protected Thumbnail parse(Element elem){
		String src = "https://icooon-mono.com" + elem.select("img").attr("src");
		String href = elem.attr("href");
		return new Thumbnail(src, href);
	}
}
