package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.ImageModel;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.service.ImageService;
import com.beefstar.beefstar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    public static final String ADD_PRODUCT = "/product/add";

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = ADD_PRODUCT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Product> addNewProduct(@RequestPart("product") ProductDTO product,
                                                 @RequestPart("imageFile") MultipartFile[] file) {
        try {
            Set<ImageModel> images = imageService.uploadImage(file);
            return ResponseEntity.ok(productService.addNewProduct(product.withProductImages(images)));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @GetMapping("/product/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.fetchAllProducts());
    }

}
