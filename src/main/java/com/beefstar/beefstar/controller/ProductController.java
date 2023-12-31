package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.ImageModel;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.service.ImageService;
import com.beefstar.beefstar.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ImageService imageService;
    public static final String ADD_PRODUCT = "/product/add";
    public static final String ALL_PRODUCT = "/product/all";
    public static final String ONE_PRODUCT_BY_ID = "/product/details/{productId}";
    public static final String ONE_PRODUCT_BY_ID_CHECKOUT = "/product/order/{isSingleProductCheckout}/{productId}";

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
    public ResponseEntity<List<Product>> getAllProduct(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String searchKey) {
        Page<Product> products = productService.fetchAllProducts(pageNumber, searchKey);

        return ResponseEntity.ok(products.getContent());
    }

    @GetMapping(ONE_PRODUCT_BY_ID)
    public ResponseEntity<Product> getProductDetailsById(@PathVariable("productId") Integer productId) {
        return ResponseEntity.ok(productService.fetchProductDetails(productId));
    }


    @GetMapping(ONE_PRODUCT_BY_ID_CHECKOUT)
    public ResponseEntity<List<Product>> getProductDetailsCheckout(
            @PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
            @PathVariable(name = "productId") Integer productId) {
        return ResponseEntity.ok(productService.fetchProductDetails(isSingleProductCheckout, productId));
    }
}
