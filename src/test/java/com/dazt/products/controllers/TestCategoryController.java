package com.dazt.products.controllers;

import com.dazt.ms.products.dto.CategoryDto;
import com.dazt.products.fixtures.CategoryFixtures;
import com.dazt.products.domain.services.impl.CategoryServiceImpl;
import com.dazt.products.web.controllers.CategoryController;
import java.math.BigInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * TestCategoryController.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
class TestCategoryController {

   /** service*/
   @Mock
   private CategoryServiceImpl service;
   /** instance. */
   @InjectMocks
   private CategoryController instance;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Mockito.when(this.service.getAll()).thenReturn(CategoryFixtures.getListCategoryDto());
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
            .thenReturn(CategoryFixtures.getCategoryDto());
        final var rs = this.instance.getId("1");
        Assertions.assertNotNull(rs);
        Assertions.assertEquals(BigInteger.ONE,rs.getId());
        Mockito.verify(this.service).getById(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void testSave() {
        Mockito.when(this.service.save(ArgumentMatchers.any(CategoryDto.class)))
            .thenReturn(CategoryFixtures.getCategoryDto());
        final var rs = this.instance.save(CategoryFixtures.getCategoryDto());
        Assertions.assertNotNull(rs);
        Assertions.assertEquals(BigInteger.ONE,rs.getId());
        Mockito.verify(this.service).save(ArgumentMatchers.any(CategoryDto.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void testUpdate() {
        Mockito.when(this.service.update(ArgumentMatchers.anyString(), ArgumentMatchers.any(CategoryDto.class)))
            .thenReturn(CategoryFixtures.getCategoryDto());
        final var rs = this.instance.update("1", CategoryFixtures.getCategoryDto());
        Assertions.assertNotNull(rs);
        Assertions.assertEquals(BigInteger.ONE,rs.getId());
        Mockito.verify(this.service).update(ArgumentMatchers.anyString(), ArgumentMatchers.any(CategoryDto.class));
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