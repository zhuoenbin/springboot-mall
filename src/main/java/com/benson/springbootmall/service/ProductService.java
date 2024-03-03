package com.benson.springbootmall.service;

import com.benson.springbootmall.dto.ProductRequest;
import com.benson.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);
}
