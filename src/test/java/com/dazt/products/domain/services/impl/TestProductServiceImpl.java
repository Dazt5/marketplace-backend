package com.dazt.products.domain.services.impl;

import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.domain.repository.ProductRepository;
import com.dazt.products.domain.services.CategoryService;
import com.dazt.products.fixtures.CategoryFixtures;
import com.dazt.products.fixtures.ProductFixtures;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * TestProductServiceImpl.
 *
 * @author David Alvarez.
 * @version 1.0.0, 10-10-2022
 */
class TestProductServiceImpl {

    @Mock
    private ProductRepository repository;
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private ProductServiceImpl instance;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_get_all_OK() {
        Mockito.when(this.repository.getAll()).thenReturn(ProductFixtures.getProductDtoList());
        Assertions.assertNotNull(this.instance.getAll());
        Mockito.verify(this.repository).getAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_get_by_id_OK() {
        Mockito.when(this.repository.getProductById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(ProductFixtures.getSingleProductDto()));
        Assertions.assertNotNull(this.instance.getById("1"));
        Mockito.verify(this.repository).getProductById(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_get_by_id_NOK_product_doesnt_exist() {
        Mockito.when(this.repository.getProductById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.instance.getById("1"));
        Mockito.verify(this.repository).getProductById(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_get_by_category_code() {
        Assertions.assertNull(this.instance.getByCategory("categoryCode"));
    }

    @Test
    void test_save() {
        Mockito.when(this.repository.save(ArgumentMatchers.any(ProductDto.class)))
                .thenReturn(ProductFixtures.getSingleProductDto());
        Mockito.when(this.categoryService.getById(ArgumentMatchers.anyString()))
                .thenReturn(CategoryFixtures.getCategoryDto());
        Assertions.assertNotNull(this.instance.save(ProductFixtures.getSingleProductDto()));
        Mockito.verify(this.repository).save(ArgumentMatchers.any(ProductDto.class));
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_update() {
        Mockito.when(this.repository.getProductById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(ProductFixtures.getSingleProductDto()));
        Mockito.when(this.repository.save(ArgumentMatchers.any(ProductDto.class)))
                .thenReturn((ProductFixtures.getSingleProductDto()));
        Assertions.assertNotNull(this.instance.update("1", ProductFixtures.getSingleProductDto()));
        Mockito.verify(this.repository).getProductById(ArgumentMatchers.anyString());
        Mockito.verify(this.repository).save(ArgumentMatchers.any(ProductDto.class));
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_update_NOK_product_doesnt_exist() {
        final var rq = ProductFixtures.getSingleProductDto();
        Mockito.when(this.repository.getProductById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.instance.update("1", rq));
        Mockito.verify(this.repository).getProductById(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_delete_OK() {
        Mockito.when(this.repository.getProductById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(ProductFixtures.getSingleProductDto()));
        Mockito.when(this.repository.delete(ArgumentMatchers.anyString()))
                .thenReturn(true);
        Assertions.assertTrue(this.instance.delete("1"));
        Mockito.verify(this.repository).delete(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_delete_NOK() {
        Mockito.when(this.repository.getProductById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(this.repository.delete(ArgumentMatchers.anyString()))
                .thenReturn(false);
        Assertions.assertFalse(this.instance.delete("1"));
        Mockito.verify(this.repository).delete(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(this.repository);
    }

}