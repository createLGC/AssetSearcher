package com.kt.AssetSearcher.model.website;

import java.util.List;

import org.jsoup.nodes.Element;

import com.kt.AssetSearcher.model.Thumbnail;
import com.kt.AssetSearcher.thread.ThreadWithValue;

public class PIXTA extends Scraper {
	private static enum PIXTASearchType {
    	Photo(1), Illust(2);
    	
    	private int id;
    	
    	private PIXTASearchType(int id) {
    		this.id = id;
    	}
    	
    	public int getValue() {
    		return this.id;
    	}
    }
	
	public ThreadWithValue<List<Thumbnail>> getPhoto(String q) {
		return getThumbnailList(PIXTASearchType.Photo, q);
	}
	
	public ThreadWithValue<List<Thumbnail>> getIllust(String q) {
		return getThumbnailList(PIXTASearchType.Illust, q);
	}
    
    private ThreadWithValue<List<Thumbnail>> getThumbnailList(PIXTASearchType type, String q) {
    	return get("https://pixta.jp/tags/" + q + "?search_type=" + type.getValue(), ".item-list--large__image > a");
    }
    
    @Override
    protected Thumbnail parse(Element elem){
    	String url = elem.attr("href");
    	String id = url.split("/")[2];
    	StringBuilder sb = new StringBuilder();
    	sb.append("0" + id);
    	sb.insert(3, "/");
    	sb.insert(7, "/");
    	sb.append("/1/" + id + ".jpg");
    	String src = "https://t.pimg.jp/" + sb.toString();
    	String href = "https://pixta.jp" + url;
    	return new Thumbnail(src, href);
    }
}
