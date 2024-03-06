package com.benson.springbootmall.controller;

import com.benson.springbootmall.constant.ProductCategory;
import com.benson.springbootmall.dto.ProductQueryParams;
import com.benson.springbootmall.dto.ProductRequest;
import com.benson.springbootmall.model.Product;
import com.benson.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated  //要加上這個註解，@Max跟@Min才會生效
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")   //關鍵字查詢跟類別查詢
    public ResponseEntity<List<Product>> getProducts(
            //查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category,  //required = false前端傳的參數是可選的
            @RequestParam(required = false) String search,  //關鍵字搜尋參數
            //排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy,    //排序功能參數10-12(預設值=>defaultValue)
            @RequestParam(defaultValue = "desc") String sort,        //排序功能參數10-12(預設值)
            //分頁 Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,    //分頁功能參數10-13(defaultValue = "5" =>只先存取5筆)，前端傳來的數據最多1000
            @RequestParam(defaultValue = "0") @Min(0) Integer offset      //分頁功能參數10-13
    ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy); //排序10-12
        productQueryParams.setSort(sort);       //排序10-12
        productQueryParams.setLimit(limit);     //分頁10-13
        productQueryParams.setOffset(offset);   //分頁10-13

        List<Product> productList =  productService.getProducts(productQueryParams);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")   //有加上@NotNull的就要加@Valid
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){
        //檢查 product 是否存在
        Product product = productService.getProductById(productId);
        if(product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // 修改商品的數據
        productService.updateProduct(productId,productRequest);
        Product updatedProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
