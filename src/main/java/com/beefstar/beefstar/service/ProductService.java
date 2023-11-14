package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.ProductDao;
import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    @Transactional
    public Product addNewProduct(ProductDTO productDTO) {
        return productDao.save(productDTO);
    }

    public Page<Product> fetchAllProducts(int pageNumber, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        if(searchKey.isEmpty()){
            return productDao.fetchAllProducts(pageable);
        }else {
          return productDao.findByProductName(searchKey,searchKey, pageable);
        }

    }

    public void deleteProductDetails(Integer productId) {
        productDao.deleteProductDetailsById(productId);
    }

    public Product fetchProductDetails(Integer productId) {
        return productDao.fetchProductDetailsById(productId).orElseThrow();
    }

    public List<Product> fetchProductDetails(Boolean isProductCheckout, Integer productId) {
        List<Product> list = new ArrayList<>();
        if (isProductCheckout) {
            list.add(productDao.fetchProductDetailsById(productId).orElseThrow());
            return list;
        }
        return new ArrayList<>();

    }
}

