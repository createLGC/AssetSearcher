package com.kt.AssetSearcher.model.website;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;

import com.kt.AssetSearcher.model.Thumbnail;
import com.kt.AssetSearcher.thread.ThreadWithValue;

public class AC extends Scraper {
	public ThreadWithValue<List<Thumbnail>> getPhoto(String q) {
		return get("https://www.photo-ac.com/main/search?q=" + q, ".pinterest-save-lg > a");
    }
	
	public ThreadWithValue<List<Thumbnail>> getIllust(String q) {
		return get("https://www.ac-illust.com/main/search_result.php?search_word=" + q, "a.btn-pinterest");
	}
	
	@Override
	protected Thumbnail parse(Element elem){
    	String queryString = elem.attr("href");
    	String[] query = queryString.substring(queryString.indexOf("?") + 1).split("&");
    	Map<String, String> params = new HashMap<>();
    	for(String q: query) {
    		int equalIndex = q.indexOf("=");
    		String name = q.substring(0, equalIndex);
    		String value = q.substring(equalIndex + 1);
    		params.put(name, value);
    	}
    	String src = params.get("media");
    	String href = params.get("url");
    	return new Thumbnail(src, href);
	}
}
