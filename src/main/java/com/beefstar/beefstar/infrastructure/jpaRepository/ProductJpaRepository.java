package com.beefstar.beefstar.infrastructure.jpaRepository;

import com.beefstar.beefstar.infrastructure.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCaseOrProductCategoryContainingIgnoreCase(String key1,
                                                                                                                              String key2,
                                                                                                                              String key3,
                                                                                                                              Pageable pageable);

}
