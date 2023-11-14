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
import org.springframework.web.bind.annotation.*;
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
    public static final String ALL_PRODUCT = "/product/all";
    public static final String ONE_PRODUCT_BY_ID = "/product/details/{productId}";
    public static final String ONE_PRODUCT_BY_ID_CHECKOUT = "/product/order/{isSingleProductCheckout}/{productId}";
    public static final String DELETE_PRODUCT = "/product/delete/{productId}";

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
    @GetMapping(ALL_PRODUCT)
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.fetchAllProducts());
    }
    @GetMapping(ONE_PRODUCT_BY_ID)
    public ResponseEntity<Product> getProductDetailsById(@PathVariable("productId") Integer productId){
        return ResponseEntity.ok(productService.fetchProductDetails(productId));
    }
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping(DELETE_PRODUCT)
    public void deleteProductDetails(@PathVariable("productId") Integer productId){
        productService.deleteProductDetails(productId);
    }
    @PreAuthorize("hasRole('User')")
    @GetMapping(ONE_PRODUCT_BY_ID_CHECKOUT)
    public ResponseEntity<List<Product>> getProductDetailsCheckout(
            @PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
            @PathVariable(name = "productId") Integer productId){
        return ResponseEntity.ok(productService.fetchProductDetails(isSingleProductCheckout,productId));
    }
}
