package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.Product;

import java.util.List;

public interface ProductDao {
    Product save(ProductDTO productDTO);

    List<Product> fetchAllProducts();
}
