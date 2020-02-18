package com.bookinfo.entity;

import java.io.Serializable;


public class Reviews implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idProduct;
    private int idReviewer;
    private String text;
    private String reviewer;
    private Object rating;
    private String color;
    
    public Reviews idProduct(int idProduct) {
    	this.idProduct = idProduct;
    	return this;
    }
    public Reviews idReviewer(int idReviewer) {
    	this.idReviewer = idReviewer;
    	return this;
    }

    public Reviews text(String text) {
    	this.text = text;
    	return this;
    }

    public Reviews reviewer(String reviewer) {
    	this.reviewer = reviewer;
    	return this;
    }
    public Reviews rating(Object rating) {
    	this.rating = rating;
    	return this;
    }
    public Reviews color(String color) {
    	this.color = color;
    	return this;
    }
    
    public int getIdReviewer() {
    	return idReviewer;
    }
    
    public void setRating(Object rating) {
    	this.rating = rating;
    }

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Object getRating() {
		return rating;
	}

	public void setIdReviewer(int idReviewer) {
		this.idReviewer = idReviewer;
	}
    
}
