package com.kt.AssetSearcher.model;

public class Thumbnail {
	private String src;
	private String href;
	
	public Thumbnail(String src, String href) {
		this.src = src;
		this.href = href;
	}

	@Override
	public String toString() {
		return "[src: " + src + ", href: " + href + "]";
	}

	public String getSrc() {
		return src;
	}

	public String getHref() {
		return href;
	}
}
