package com.bookinfo.entity;


public class Product {
    private int id;
    private String title;
    private String descriptionHtml;
    
    public int getId() {return id;}
    
    public static Product builder(){
    	return new Product();
    }
    
    public Product id(int id) {
    	this.id=id;
    	return this;
    }
    public Product title(String title) {
    	this.title=title;
    	return this;
    }
    public Product descriptionHtml(String descriptionHtml) {
    	this.descriptionHtml=descriptionHtml;
    	return this;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescriptionHtml() {
		return descriptionHtml;
	}

	public void setDescriptionHtml(String descriptionHtml) {
		this.descriptionHtml = descriptionHtml;
	}

	public void setId(int id) {
		this.id = id;
	}
    
}
