package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.ProductDao;
import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.configuration.security.JwtRequestFilter;
import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "products")
public class ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;
    @CacheEvict(value = "products", key = "#productId")
    public void evictProductCache(Integer productId) {
    }
    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public Product addNewProduct(ProductDTO productDTO) {
        return productDao.save(productDTO);
    }
    @Cacheable("products")
    public Page<Product> fetchAllProducts(int pageNumber, String searchKey) {
        System.err.println("wywołuje metode przy odswiezaniu strony");
        Pageable pageable = PageRequest.of(pageNumber, 6);
        if(searchKey.isEmpty()){

            return productDao.fetchAllProducts(pageable);
        }else {
            System.err.println("wywołuje metode przy pobieraniu po słowie  strony");
            return productDao.findByProductName(searchKey,searchKey,searchKey, pageable);
        }
    }

    @Cacheable
    public Product fetchProductDetails(Integer productId) {
        System.err.println("wywołuje product detail");
        return productDao.fetchProductDetailsById(productId).orElseThrow();
    }

    public List<Product> fetchProductDetails(Boolean isProductCheckout, Integer productId) {
        List<Product> list = new ArrayList<>();
        if (isProductCheckout && productId != 0) {
            list.add(productDao.fetchProductDetailsById(productId).orElseThrow());
            return list;
        }else {
            List<Cart> cartDetails = cartService.getCartDetails();
            return cartDetails.stream().map(Cart::getProduct).toList();
        }
    }
}

