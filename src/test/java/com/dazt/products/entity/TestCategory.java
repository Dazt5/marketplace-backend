package com.dazt.products.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * CategoryTest.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
class TestCategory {

    @Test
    void testGetters(){
        final var product = new Category();
        product.setId(BigInteger.ONE);
        product.setName("productName");
        product.setCategoryCode("categoryCode");
        product.setDescription("productDescription");
        product.setCreateTime(LocalDateTime.now(ZoneId.systemDefault()));
        product.setUpdateTime(LocalDateTime.now(ZoneId.systemDefault()));
        Assertions.assertNotNull(product.getId());
        Assertions.assertNotNull(product.getCategoryCode());
        Assertions.assertNotNull(product.getName());
        Assertions.assertNotNull(product.getDescription());
        Assertions.assertNotNull(product.getCreateTime());
        Assertions.assertNotNull(product.getUpdateTime());
    }

    @Test
    void testConstructors(){
        final var product = new Category();
        Assertions.assertNotNull(product);
    }

    @Test
    void testBuilder(){
        final var product = Category.builder()
            .id(BigInteger.ONE)
            .categoryCode("productName")
            .name("categoryCode")
            .description("productDescription")
            .createTime(LocalDateTime.now(ZoneId.systemDefault()))
            .updateTime(LocalDateTime.now(ZoneId.systemDefault()))
            .build();
        Assertions.assertNotNull(product.getId());
        Assertions.assertNotNull(product.getCategoryCode());
        Assertions.assertNotNull(product.getName());
        Assertions.assertNotNull(product.getDescription());
        Assertions.assertNotNull(product.getCreateTime());
        Assertions.assertNotNull(product.getUpdateTime());
    }

}