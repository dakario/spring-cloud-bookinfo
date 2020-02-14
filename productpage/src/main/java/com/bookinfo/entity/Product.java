package com.bookinfo.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Product {
    private int id;
    private String title;
    private String descriptionHtml;
}
