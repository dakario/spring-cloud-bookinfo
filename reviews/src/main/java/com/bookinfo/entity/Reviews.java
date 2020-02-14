package com.bookinfo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class Reviews implements Serializable {
    private int idProduct;
    private int idReviewer;
    private String text;
    private String reviewer;
    private Object rating;
    private String color;
}
