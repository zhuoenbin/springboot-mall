package com.benson.springbootmall.dao;

import com.benson.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
