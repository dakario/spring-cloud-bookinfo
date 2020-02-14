package com.bookinfo;

import java.io.Serializable;

public class Detail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String author;
	private int year;
	private String type;
	private int pages;
	private String publisher;
	private String language;
	private String ISBN_10;
	private String ISBN_13;
	public Detail(int id, String author, int year, String type, int pages, String publisher, String language,
			String iSBN_10, String iSBN_13) {
		super();
		this.id = id;
		this.author = author;
		this.year = year;
		this.type = type;
		this.pages = pages;
		this.publisher = publisher;
		this.language = language;
		ISBN_10 = iSBN_10;
		ISBN_13 = iSBN_13;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getISBN_10() {
		return ISBN_10;
	}
	public void setISBN_10(String iSBN_10) {
		ISBN_10 = iSBN_10;
	}
	public String getISBN_13() {
		return ISBN_13;
	}
	public void setISBN_13(String iSBN_13) {
		ISBN_13 = iSBN_13;
	}
	
	
}
