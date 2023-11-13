package com.beefstar.beefstar.infrastructure.JpaImpl;

import com.beefstar.beefstar.dao.ProductDao;
import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.infrastructure.jpaRepository.ProductJpaRepository;
import com.beefstar.beefstar.infrastructure.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductJpaImpl implements ProductDao {
    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product save(ProductDTO product) {
        return productJpaRepository.save(productMapper.mapFromDto(product));
    }

    @Override
    public List<Product> fetchAllProducts() {
        return productJpaRepository
                .findAll();
    }

    @Override
    public void deleteProductDetailsById(Integer productId) {
        productJpaRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> fetchProductDetailsById(Integer productId) {
        return productJpaRepository.findById(productId);
    }


}
