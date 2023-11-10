package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    public static final String ADD_PRODUCT = "/product/add";

    @PostMapping(ADD_PRODUCT)
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO product){
        return ResponseEntity.ok(productService.addNewProduct(product));
    }
}
