package com.dazt.products.domain.services.impl;

import com.dazt.products.domain.repository.CategoryRepository;
import com.dazt.products.fixtures.CategoryFixtures;

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
 * TestCategoryServiceImpl.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
class TestCategoryServiceImpl {

    /**
     * repository.
     */
    @Mock
    private CategoryRepository repository;
    /**
     * instance.
     */
    @InjectMocks
    private CategoryServiceImpl instance;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_get_all() {
        Mockito.when(this.repository.getAll())
                .thenReturn(CategoryFixtures.getListCategoryDto());
        Assertions.assertNotNull(this.instance.getAll());
    }

    @Test
    void test_get_by_id() {
        Mockito.when(this.repository.getById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CategoryFixtures.getCategoryDto()));
        Assertions.assertNotNull(this.instance.getById("1"));
    }

    @Test
    void test_get_by_category_code() {
        Mockito.when(this.repository.getByCategoryCode(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CategoryFixtures.getCategoryDto()));
        Assertions.assertNotNull(this.instance.getByCategoryCode("categoryCode"));
        Mockito.verify(this.repository).getByCategoryCode(ArgumentMatchers.anyString());
    }

    @Test
    void test_get_by_category_code_NOK_category_code_doesnt_exists() {
        Mockito.when(this.repository.getByCategoryCode(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.instance.getByCategoryCode("categoryCode"));
        Mockito.verify(this.repository).getByCategoryCode(ArgumentMatchers.anyString());
    }

    @Test
    void test_save() {
        Mockito.when(this.repository.getByCategoryCode(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(this.repository.save(ArgumentMatchers.any(CategoryDto.class)))
                .thenReturn(CategoryFixtures.getCategoryDto());
        Assertions.assertNotNull(this.instance.save(CategoryFixtures.getCategoryDto()));
    }

    @Test
    void test_save_category_already_exists() {
        Mockito.when(this.repository.getByCategoryCode(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CategoryFixtures.getCategoryDto()));
        Mockito.when(this.repository.save(ArgumentMatchers.any(CategoryDto.class)))
                .thenReturn(CategoryFixtures.getCategoryDto());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.instance.save(CategoryFixtures.getCategoryDto()));
        Mockito.verify(this.repository).getByCategoryCode(ArgumentMatchers.anyString());
        Mockito.verify(this.repository, Mockito.never()).save(ArgumentMatchers.any(CategoryDto.class));
    }

    @Test
    void test_update() {
        final var rq = CategoryFixtures.getCategoryDto();
        Mockito.when(this.repository.getById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CategoryFixtures.getCategoryDto()));
        Mockito.when(this.repository.save(ArgumentMatchers.any(CategoryDto.class)))
                .thenReturn(CategoryFixtures.getCategoryDto());
        Assertions.assertNotNull(this.instance.update("1", rq));
    }

    @Test
    void test_update_category_doesnt_exists() {
        final var rq = CategoryFixtures.getCategoryDto();
        Mockito.when(this.repository.getById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(this.repository.save(ArgumentMatchers.any(CategoryDto.class)))
                .thenReturn(CategoryFixtures.getCategoryDto());
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                this.instance.update("1", rq));
    }

    @Test
    void test_delete() {
        Mockito.when(this.repository.getById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CategoryFixtures.getCategoryDto()));
        Mockito.when(this.repository.delete(ArgumentMatchers.anyString()))
                .thenReturn(true);
        Assertions.assertTrue(this.instance.delete("1"));
    }

}