package com.beefstar.beefstar.infrastructure.JpaImpl;

import com.beefstar.beefstar.dao.ProductDao;
import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.infrastructure.jpaRepository.ProductJpaRepository;
import com.beefstar.beefstar.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductJpaImpl implements ProductDao {

    private final ProductJpaRepository productJpaRepository;


    private final ProductMapper productMapper;

    @Override
    public Product save(ProductDTO product) {
        return productJpaRepository.save(productMapper.mapFromDto(product));
    }


    @Override
    public Page<Product> fetchAllProducts(Pageable pageable) {
        return productJpaRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByProductName(String key1, String key2, String key3, Pageable pageable) {
        return productJpaRepository
                .findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCaseOrProductCategoryContainingIgnoreCase(
                        key1, key2, key3, pageable);
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
