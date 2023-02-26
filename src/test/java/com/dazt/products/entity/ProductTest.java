package com.dazt.products.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * ProductTest.
 *
 * @author David Alvarez.
 * @version 1.0.0, 20-09-2022
 */
class ProductTest {

    @Test
    void testGetters(){
        final var product = new Product();
        product.setId(BigInteger.ONE);
        product.setName("productName");
        product.setDescription("productDescription");
        product.setStock(1);
        product.setPrice(BigDecimal.ONE);
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        Assertions.assertNotNull(product.getId());
        Assertions.assertNotNull(product.getName());
        Assertions.assertNotNull(product.getDescription());
        Assertions.assertNotNull(product.getStock());
        Assertions.assertNotNull(product.getPrice());
        Assertions.assertNotNull(product.getCreateTime());
        Assertions.assertNotNull(product.getUpdateTime());
    }

    @Test
    void testBuilder(){
        final var product = Product.builder()
            .id(BigInteger.ONE)
            .name("productName")
            .description("productDescription")
            .stock(1)
            .price(BigDecimal.ONE)
            .createTime(new Date())
            .updateTime(new Date())
            .build();
        Assertions.assertNotNull(product.getId());
        Assertions.assertNotNull(product.getName());
        Assertions.assertNotNull(product.getDescription());
        Assertions.assertNotNull(product.getStock());
        Assertions.assertNotNull(product.getPrice());
        Assertions.assertNotNull(product.getCreateTime());
        Assertions.assertNotNull(product.getUpdateTime());
    }

}