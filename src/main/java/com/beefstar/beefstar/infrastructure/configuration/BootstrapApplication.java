package com.beefstar.beefstar.infrastructure.configuration;

import com.beefstar.beefstar.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;




@Component
@AllArgsConstructor
public class BootstrapApplication implements ApplicationListener<ContextRefreshedEvent> {
    private final ProductService productService;
    private final ProductUtil productUtil;


    @Transactional
    @Override
    public void onApplicationEvent(final @NonNull ContextRefreshedEvent event) {

        productUtil.getProducts().forEach(productService::addNewProduct);


    }
}
