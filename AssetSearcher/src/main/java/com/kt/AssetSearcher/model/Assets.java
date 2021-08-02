package com.kt.AssetSearcher.model;

import java.util.Arrays;
import java.util.List;

import com.kt.AssetSearcher.model.website.AC;
import com.kt.AssetSearcher.model.website.IconRainbow;
import com.kt.AssetSearcher.model.website.IcooonMono;
import com.kt.AssetSearcher.model.website.PIXTA;
import com.kt.AssetSearcher.thread.ThreadWithValue;

public class Assets {
	public static List<ThreadWithValue<List<Thumbnail>>> getPhoto(String q) {
		return Arrays.asList(
			new AC().getPhoto(q),
			new PIXTA().getPhoto(q)
		);
	}
	
	public static List<ThreadWithValue<List<Thumbnail>>> getIllust(String q) {
		return Arrays.asList(
			new AC().getIllust(q),
			new PIXTA().getIllust(q)
		);
	}
	
	public static List<ThreadWithValue<List<Thumbnail>>> getIcon(String q) {
		return Arrays.asList(
			new IcooonMono().getIcon(q),
			new IconRainbow().getIcon(q)
		);
	}
}
