package com.kt.AssetSearcher.model.website;

import java.util.List;

import org.jsoup.nodes.Element;

import com.kt.AssetSearcher.model.Thumbnail;
import com.kt.AssetSearcher.thread.ThreadWithValue;

public class IconRainbow extends Scraper {
	public ThreadWithValue<List<Thumbnail>> getIcon(String q) {
		return get("https://icon-rainbow.com/?s=" + q, "li.icon > div > a");
	}
	
	@Override
	protected Thumbnail parse(Element elem){
		String src = elem.select("img").attr("src");
		String href = elem.attr("href");
		return new Thumbnail(src, href);
	}
}
