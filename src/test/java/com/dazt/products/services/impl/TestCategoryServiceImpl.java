package com.dazt.products.services.impl;

import com.dazt.ms.products.dto.CategoryDto;
import com.dazt.products.domain.repository.CategoryRepository;
import com.dazt.products.domain.services.impl.CategoryServiceImpl;
import com.dazt.products.persistence.entity.Category;
import com.dazt.products.fixtures.CategoryFixtures;
import com.dazt.products.persistence.repositories.CategoryCrudRepository;

import java.math.BigInteger;
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
    void testGetAll() {
        Mockito.when(this.repository.getAll())
                .thenReturn(CategoryFixtures.getListCategoryDto());
        Assertions.assertNotNull(this.instance.getAll());
    }

    @Test
    void testGetById() {
        Mockito.when(this.repository.getById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CategoryFixtures.getCategoryDto()));
        Assertions.assertNotNull(this.instance.getById("1"));
    }

    @Test
    void testSave() {
        Mockito.when(this.repository.save(ArgumentMatchers.any(CategoryDto.class)))
                .thenReturn(CategoryFixtures.getCategoryDto());
        Assertions.assertNotNull(this.instance.save(CategoryFixtures.getCategoryDto()));
    }

    @Test
    void testUpdate() {
        Mockito.when(this.repository.getById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CategoryFixtures.getCategoryDto()));
        Mockito.when(this.repository.save(ArgumentMatchers.any(CategoryDto.class)))
                .thenReturn(CategoryFixtures.getCategoryDto());
        Assertions.assertNotNull(this.instance.update("1", CategoryFixtures.getCategoryDto()));
    }

    @Test
    void testUpdateCategoryDoesntExists() {
        final var rq = CategoryFixtures.getCategoryDto();
        Mockito.when(this.repository.getById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(this.repository.save(ArgumentMatchers.any(CategoryDto.class)))
                .thenReturn(CategoryFixtures.getCategoryDto());
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                this.instance.update("1", rq));
    }

    @Test
    void testDelete() {
        Mockito.when(this.repository.getById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CategoryFixtures.getCategoryDto()));
        Mockito.when(this.repository.delete(ArgumentMatchers.anyString()))
                .thenReturn(true);
        Assertions.assertTrue(this.instance.delete("1"));
    }

}