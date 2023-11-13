package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.ProductDao;
import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    @Transactional
    public Product addNewProduct(ProductDTO productDTO) {
        return productDao.save(productDTO);
    }

    public List<Product> fetchAllProducts(){
        return productDao.fetchAllProducts();
    }

    public void deleteProductDetails(Integer productId){
        productDao.deleteProductDetailsById(productId);
    }

    public Product fetchProductDetails(Integer productId) {
     return productDao.fetchProductDetailsById(productId).orElseThrow();
    }
}

