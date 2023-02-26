/*
 * @(#)TestProductController.java
 *
 * Copyright (c) BANCO DE CHILE (Chile). All rights reserved.
 *
 * All rights to this product are owned by BANCO DE CHILE and may only
 * be used under the terms of its associated license document. You may NOT
 * copy, modify, sublicense, or distribute this source file or portions of
 * it unless previously authorized in writing by BANCO DE CHILE.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package com.dazt.products.controllers;

import com.dazt.ms.products.dto.CategoryDto;
import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.fixtures.CategoryFixtures;
import com.dazt.products.fixtures.ProductFixtures;
import com.dazt.products.services.impl.CategoryServiceImpl;
import com.dazt.products.services.impl.ProductServiceImpl;
import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * TestProductController.
 *
 * @author David Alvarez.
 * @version 1.0.0, 14-11-2022
 */
class TestProductController {

    /** service*/
    @Mock
    private ProductServiceImpl service;
    /** instance. */
    @InjectMocks
    private ProductController instance;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Mockito.when(this.service.getAll()).thenReturn(List.of(ProductFixtures.getSingleProductDto()));
        final var rs = this.instance.getAll();
        Assertions.assertNotNull(rs);
        Assertions.assertEquals(1,rs.size());
        Assertions.assertEquals(BigInteger.ONE,rs.get(0).getId());
        Mockito.verify(this.service).getAll();
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void testGetById() {
        Mockito.when(this.service.getById(ArgumentMatchers.anyString()))
            .thenReturn(ProductFixtures.getSingleProductDto());
        final var rs = this.instance.getId("1");
        Assertions.assertNotNull(rs);
        Assertions.assertEquals(BigInteger.ONE,rs.getId());
        Mockito.verify(this.service).getById(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void testSave() {
        Mockito.when(this.service.save(ArgumentMatchers.any(ProductDto.class)))
            .thenReturn(ProductFixtures.getSingleProductDto());
        final var rs = this.instance.save(ProductFixtures.getSingleProductDto());
        Assertions.assertNotNull(rs);
        Assertions.assertEquals(BigInteger.ONE,rs.getId());
        Mockito.verify(this.service).save(ArgumentMatchers.any(ProductDto.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void testUpdate() {
        Mockito.when(this.service.update(ArgumentMatchers.anyString(), ArgumentMatchers.any(ProductDto.class)))
            .thenReturn(ProductFixtures.getSingleProductDto());
        final var rs = this.instance.update("1", ProductFixtures.getSingleProductDto());
        Assertions.assertNotNull(rs);
        Assertions.assertEquals(BigInteger.ONE,rs.getId());
        Mockito.verify(this.service).update(ArgumentMatchers.anyString(), ArgumentMatchers.any(ProductDto.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void testDelete() {
        Mockito.when(this.service.delete(ArgumentMatchers.anyString()))
            .thenReturn(true);
        final var rs = this.instance.delete("1");
        Assertions.assertNotNull(rs);
        Assertions.assertTrue(rs);
        Mockito.verify(this.service).delete(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void testDeleteProductDoesntExist() {
        Mockito.when(this.service.delete(ArgumentMatchers.anyString()))
            .thenReturn(false);
        final var rs = this.instance.delete("1");
        Assertions.assertNotNull(rs);
        Assertions.assertFalse(rs);
        Mockito.verify(this.service).delete(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(this.service);
    }

}