package com.benson.springbootmall.service;

import com.benson.springbootmall.dto.ProductQueryParams;
import com.benson.springbootmall.dto.ProductRequest;
import com.benson.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    //計算商品總筆數的方法
    Integer countProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
