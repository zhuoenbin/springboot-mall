package com.benson.springbootmall.dao.impl;

import com.benson.springbootmall.dao.ProductDao;
import com.benson.springbootmall.dto.ProductQueryParams;
import com.benson.springbootmall.dto.ProductRequest;
import com.benson.springbootmall.model.Product;
import com.benson.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override  //11-14分頁+總比數
    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql = "SELECT count(*) FROM product WHERE 1=1";
        Map<String, Object> map = new HashMap<>();
        //查詢條件
        sql = addFilteringSql(sql, map, productQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "select product_id,product_name, category, image_url, price, stock," +
                "description,created_date, last_modified_date from product where 1=1";
        Map<String, Object> map = new HashMap<>();
        //查詢條件
        sql = addFilteringSql(sql, map, productQueryParams);

        //排序(SQL語句要記得留空格)
        sql = sql + " order by " + productQueryParams.getOrderBy() + " " +productQueryParams.getSort(); //排序10-12
        //分頁(因為在controller有設定預設值，所以不用用判斷式處理)
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",productQueryParams.getLimit());
        map.put("offset",productQueryParams.getOffset());

        List<Product> producList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return producList;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "select product_id,product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date " +
                "from product where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);

        List<Product> producList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        //!producList.isEmpty()可以換成這個
        if(producList.size()>0){
            return producList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "insert into product(product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date) " +
                "values (:productName, :category, :imageUrl, :price, :stock, :description, " +
                ":createdDate, :lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("productName",productRequest.getProductName());
        map.put(("category"), productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override   //在商品更新的時候也要一併修改商品更新的最後時間
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "update product set product_name = :productName,category = :category," +
                "image_url = :imageUrl,price = :price,stock = :stock,description = :description," +
                "last_modified_date = :lastModifiedDate where product_id =:productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("productName",productRequest.getProductName());
        map.put(("category"), productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        map.put("lastModifiedDate",new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql ="delete from product where product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);
        namedParameterJdbcTemplate.update(sql,map);
    }

    //11-15(提煉)
    private String addFilteringSql(String sql, Map<String,Object> map, ProductQueryParams productQueryParams){
        if(productQueryParams.getCategory() !=null){
            sql = sql + " and category = :category";
            map.put("category", productQueryParams.getCategory().name());
        }
        //關鍵字查詢
        if(productQueryParams.getSearch() != null){
            sql = sql + " and product_name like :search";
            map.put("search","%" + productQueryParams.getSearch() + "%");
        }
        return sql;
    }

}
