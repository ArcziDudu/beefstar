package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Product save(ProductDTO productDTO);

    List<Product> fetchAllProducts();
    Page<Product> fetchAllProducts(Pageable pageable);
    Page<Product> findByProductName(String key1, String key2, Pageable pageable);
    void deleteProductDetailsById(Integer productId);

   Optional<Product> fetchProductDetailsById(Integer productId);
}
