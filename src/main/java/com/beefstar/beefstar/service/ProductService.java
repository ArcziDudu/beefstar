package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.CartDao;
import com.beefstar.beefstar.dao.ProductDao;
import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.configuration.security.JwtAuthenticationFilter;
import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "products")
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final UserService userService;
    private final CartDao cartDao;

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
        Pageable pageable = PageRequest.of(pageNumber, 6);
        if (searchKey.isEmpty()) {

            return productDao.fetchAllProducts(pageable);
        } else {
            return productDao.findByProductName(searchKey, searchKey, searchKey, pageable);
        }
    }

    @Cacheable
    public Product fetchProductDetails(Integer productId) {
        return productDao.fetchProductDetailsById(productId).orElseThrow();
    }

    public List<Product> fetchProductDetails(Boolean isProductCheckout, Integer productId) {
        List<Product> list = new ArrayList<>();
        UserInfo user = userService.findUserById(JwtAuthenticationFilter.CURRENT_USER);
        if (isProductCheckout && productId != 0) {
            list.add(productDao.fetchProductDetailsById(productId).orElseThrow());
            return list;
        } else {

            List<Cart> cartDetails = cartDao.findCartByUser(user);
            return cartDetails.stream().map(Cart::getProduct).toList();
        }
    }
}

