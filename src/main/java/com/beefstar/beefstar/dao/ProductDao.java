package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.Product;

public interface ProductDao {
    Product save(ProductDTO productDTO);
}
