package com.dazt.products.services.impl;

import com.dazt.products.entity.Category;
import com.dazt.products.fixtures.CategoryFixtures;
import com.dazt.products.repositories.CategoryRepository;
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

    /** repository. */
    @Mock
    private CategoryRepository repository;
    /** instance. */
    @InjectMocks
    private CategoryServiceImpl instance;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll(){
        Mockito.when(this.repository.findAll())
            .thenReturn(CategoryFixtures.getListCategory());
        Assertions.assertNotNull(this.instance.getAll());
    }

    @Test
    void testGetById(){
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.of(CategoryFixtures.getCategory()));
        Assertions.assertNotNull(this.instance.getById("1"));
    }

    @Test
    void testSave(){
        Mockito.when(this.repository.save(ArgumentMatchers.any(Category.class)))
            .thenReturn(CategoryFixtures.getCategory());
        Assertions.assertNotNull(this.instance.save(CategoryFixtures.getCategoryDto()));
    }

    @Test
    void testUpdate(){
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.of(CategoryFixtures.getCategory()));
        Mockito.when(this.repository.save(ArgumentMatchers.any(Category.class)))
            .thenReturn(CategoryFixtures.getCategory());
        Assertions.assertNotNull(this.instance.update("1", CategoryFixtures.getCategoryDto()));
    }

    @Test
    void testUpdateCategoryDoesntExists(){
        final var rq = CategoryFixtures.getCategoryDto();
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.empty());
        Mockito.when(this.repository.save(ArgumentMatchers.any(Category.class)))
            .thenReturn(CategoryFixtures.getCategory());
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            this.instance.update("1", rq));
    }

    @Test
    void testDelete(){
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.of(CategoryFixtures.getCategory()));
        Mockito.doNothing().when(this.repository).delete(ArgumentMatchers.any(Category.class));
        Assertions.assertTrue(this.instance.delete("1"));
    }

    @Test
    void testDeleteCategoryDoesntExist(){
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.empty());
        Mockito.doNothing().when(this.repository).delete(ArgumentMatchers.any(Category.class));
        Assertions.assertFalse(this.instance.delete("1"));
    }

}