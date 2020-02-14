package com.bookinfo.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class Details implements Serializable {
    private int id;
    private String author;
    private int year;
    private String type;
    private int pages;
    private String publisher;
    private String language;
    private String ISBN_10;
    private String ISBN_13;
}
