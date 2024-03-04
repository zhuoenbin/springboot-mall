package com.benson.springbootmall.dao;

import com.benson.springbootmall.dto.ProductQueryParams;
import com.benson.springbootmall.dto.ProductRequest;
import com.benson.springbootmall.model.Product;
import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
