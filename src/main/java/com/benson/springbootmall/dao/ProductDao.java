package com.benson.springbootmall.dao;

import com.benson.springbootmall.constant.ProductCategory;
import com.benson.springbootmall.dto.ProductRequest;
import com.benson.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category,String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
