package com.bookinfo;

import java.io.Serializable;

public class Review implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int productId;
	private String reviewer;
	private String text;
	
	private Rating rating;

	public Review(int id, int productId, String reviewer, String text) {
		super();
		this.id = id;
		this.productId = productId;
		this.reviewer = reviewer;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	public Review rating(Rating rating) {
		this.rating = rating;
		return this;
	}
	
}
