package com.kt.AssetSearcher.model.website;

import java.util.List;

import org.jsoup.nodes.Element;

import com.kt.AssetSearcher.model.Thumbnail;
import com.kt.AssetSearcher.thread.ThreadWithValue;

public class IconIcons extends Scraper{
	public ThreadWithValue<List<Thumbnail>> getIcon(String q) {
		return get("https://icon-icons.com/search/icons/?filtro=" + q, ".imagen-pinta-resultados > a");
	}
	
	@Override
	protected Thumbnail parse(Element elem){
		String src = elem.select("img").attr("data-original");
		String href = "https://icon-icons.com/" + elem.attr("href");
		return new Thumbnail(src, href);
	}
}
