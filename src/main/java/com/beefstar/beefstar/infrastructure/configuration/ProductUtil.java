package com.beefstar.beefstar.infrastructure.configuration;

import com.beefstar.beefstar.domain.ProductDTO;
import com.beefstar.beefstar.infrastructure.entity.ImageModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
@AllArgsConstructor
@Component
public class ProductUtil {

    private final ResourceLoader resourceLoader;


    private  List<byte[]> images() {
        List<String> paths = new ArrayList<>();
        String imagePath1 = "classpath:productImages/product1 jpg1";
        paths.add(imagePath1);
        String imagePath2 = "classpath:productImages/product1 jpg2";
        paths.add(imagePath2);
        String imagePath3 = "classpath:productImages/product2 img1";
        paths.add(imagePath3);
        String imagePath4 = "classpath:productImages/product2 img2";
        paths.add(imagePath4);
        String imagePath5 = "classpath:productImages/product3 img1";
        paths.add(imagePath5);
        String imagePath6 = "classpath:productImages/product3 img2";
        paths.add(imagePath6);
        String imagePath7 = "classpath:productImages/product3 img3";
        paths.add(imagePath7);
        String imagePath8 = "classpath:productImages/product4 img1";
        paths.add(imagePath8);
        String imagePath9 = "classpath:productImages/product4 img2";
        paths.add(imagePath9);
        String imagePath10 = "classpath:productImages/product5 img1";
        paths.add(imagePath10);
        String imagePath11 = "classpath:productImages/product5 img2";
        paths.add(imagePath11);
        String imagePath12 = "classpath:productImages/product6 img1";
        paths.add(imagePath12);
        String imagePath13 = "classpath:productImages/product7 img1";
        paths.add(imagePath13);
        String imagePath14 = "classpath:productImages/product8 img1";
        paths.add(imagePath14);
        String imagePath15 = "classpath:productImages/product9 img1";
        paths.add(imagePath15);

        return paths.stream().map(a -> {
            try {
                return Base64.getDecoder().decode(resourceLoader.getResource(a).getContentAsByteArray());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public  List<ProductDTO> getProducts() {

        List<ProductDTO> productsBootstrap = new ArrayList<>();
        List<Set<ImageModel>> images = new ArrayList<>();
        Set<ImageModel> imagesProduct = new HashSet<>();
        Set<ImageModel> imagesProduct2 = new HashSet<>();
        Set<ImageModel> imagesProduct3 = new HashSet<>();
        Set<ImageModel> imagesProduct4 = new HashSet<>();
        Set<ImageModel> imagesProduct5 = new HashSet<>();
        Set<ImageModel> imagesProduct6 = new HashSet<>();
        Set<ImageModel> imagesProduct7 = new HashSet<>();
        Set<ImageModel> imagesProduct8 = new HashSet<>();
        Set<ImageModel> imagesProduct9 = new HashSet<>();

        images.add(imagesProduct);
        images.add(imagesProduct2);
        images.add(imagesProduct3);
        images.add(imagesProduct4);
        images.add(imagesProduct5);
        images.add(imagesProduct6);
        images.add(imagesProduct7);
        images.add(imagesProduct8);
        images.add(imagesProduct9);

        imagesProduct.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product1 jpg1")
                .picByte(images().get(0)).build());
        imagesProduct.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product1 jpg2")
                .picByte(images().get(1)).build());

        imagesProduct2.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product2 jpg1")
                .picByte(images().get(2)).build());
        imagesProduct2.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product2 jpg1")
                .picByte(images().get(3)).build());

        imagesProduct3.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product3 jpg1")
                .picByte(images().get(4)).build());
        imagesProduct3.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product3 jpg2")
                .picByte(images().get(5)).build());
        imagesProduct3.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product3 jpg3")
                .picByte(images().get(6)).build());

        imagesProduct4.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product4 jpg1")
                .picByte(images().get(7)).build());
        imagesProduct4.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product4 jpg2")
                .picByte(images().get(8)).build());

        imagesProduct5.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product5 jpg1")
                .picByte(images().get(9)).build());
        imagesProduct5.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product5 jpg2")
                .picByte(images().get(10)).build());

        imagesProduct6.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product6 jpg1")
                .picByte(images().get(11)).build());

        imagesProduct7.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product7 jpg1")
                .picByte(images().get(12)).build());


        imagesProduct8.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product8 jpg1")
                .picByte(images().get(13)).build());

        imagesProduct9.add(ImageModel.builder()
                .type("image/jpeg")
                .name("productImages/product9 jpg1")
                .picByte(images().get(14)).build());

        productsBootstrap.add(ProductDTO.builder()
                .productActualPrice(new BigDecimal("10.00"))
                .productCategory("cold-meat")
                .productName("Old-Fashioned Bacon")
                .productImages(images.get(0))
                .build());

        productsBootstrap.add(ProductDTO.builder()
                .productActualPrice(new BigDecimal("15.00"))
                .productCategory("cold-meat")
                .productName("Smoked Tenderloin")
                .productImages(images.get(1))
                .build());


        productsBootstrap.add(ProductDTO.builder()
                .productActualPrice(new BigDecimal("8.00"))
                .productCategory("cold-meat")
                .productName("Cabanos")
                .productImages(images.get(2))
                .build());

        productsBootstrap.add(ProductDTO.builder()
                .productActualPrice(new BigDecimal("13.00"))
                .productCategory("cold-meat")
                .productName("smoked rib")
                .productImages(images.get(3))
                .build());

        productsBootstrap.add(ProductDTO.builder()
                .productActualPrice(new BigDecimal("17.00"))
                .productCategory("sea-food")
                .productName("oysters")
                .productImages(images.get(4))
                .build());
        productsBootstrap.add(ProductDTO.builder()
                .productActualPrice(new BigDecimal("17.00"))
                .productCategory("sea-food")
                .productName("Canadian lobster")
                .productImages(images.get(5))
                .build());
        productsBootstrap.add(ProductDTO.builder()
                .productActualPrice(new BigDecimal("4.00"))
                .productCategory("sea-food")
                .productName("Chicken filet")
                .productImages(images.get(6))
                .build());
        productsBootstrap.add(ProductDTO.builder()
                .productActualPrice(new BigDecimal("20.00"))
                .productCategory("sea-food")
                .productName("Entrecote steak")
                .productImages(images.get(7))
                .build());
        productsBootstrap.add(ProductDTO.builder()
                .productActualPrice(new BigDecimal("18.00"))
                .productCategory("sea-food")
                .productName("Beef")
                .productImages(images.get(8))
                .build());
        return productsBootstrap;


    }

}
