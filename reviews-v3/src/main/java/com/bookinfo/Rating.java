package com.bookinfo;

import java.io.Serializable;

public class Rating implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int stars;
	private final String star_color;
	public Rating(int stars, String star_color) {
		super();
		this.stars = stars;
		this.star_color = "red";
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	
	public String getStar_color() {
		return star_color;
	}
	
	
}
