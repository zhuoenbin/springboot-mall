package com.benson.springbootmall.dto;

import com.benson.springbootmall.constant.ProductCategory;

public class ProductQueryParams {
    //對應controller的getProducts方法參數
    private ProductCategory category;
    private String search;
    private String orderBy;  //排序10-12
    private String sort;     //排序10-12
    private Integer limit;   //分頁10-13
    private Integer offset;  //分頁10-13

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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
