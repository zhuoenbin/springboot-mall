package com.benson.springbootmall.dto;

import com.benson.springbootmall.constant.ProductCategory;

public class ProductQueryParams {
    //對應controller的getProducts方法參數
    private ProductCategory category;
    private String search;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
