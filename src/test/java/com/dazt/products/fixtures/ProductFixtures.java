package com.dazt.products.fixtures;

import com.dazt.products.persistence.entity.Product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * ProductFixtures.
 *
 * @author David Alvarez.
 * @version 1.0.0, 10-10-2022
 */
public class ProductFixtures {

    public static Product getSingleProduct(){
        return Product.builder()
                .id(BigInteger.ONE)
                .name("product")
                .description("a random description")
                .price(BigDecimal.ONE)
                .build();
    }

    public static ProductDto getSingleProductDto(){
        return ProductDto.builder()
                .id(BigInteger.ONE)
                .name("product")
                .description("a random description")
                .price(BigDecimal.ONE)
                .category(CategoryFixtures.getCategoryDto())
                .build();
    }

    public static List<Product> getProductList(){
        return List.of(getSingleProduct());
    }

    public static List<ProductDto> getProductDtoList(){
        return List.of(getSingleProductDto());
    }

}