package com.dazt.products.services.impl;

import com.dazt.products.entity.Product;
import com.dazt.products.fixtures.ProductFixtures;
import com.dazt.products.repositories.ProductRepository;
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
import org.springframework.util.Assert;

/**
 * TestProductServiceImpl.
 *
 * @author David Alvarez.
 * @version 1.0.0, 10-10-2022
 */
class TestProductServiceImpl {

    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductServiceImpl instance;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_get_all_OK(){
        Mockito.when(repository.findAll()).thenReturn(ProductFixtures.getProductList());
        Assertions.assertNotNull(instance.getAll());
        Mockito.verify(repository).findAll();
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void test_get_by_id_OK(){
        Mockito.when(repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.of(ProductFixtures.getSingleProduct()));
        Assertions.assertNotNull(instance.getById("1"));
        Mockito.verify(repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void test_get_by_id_NOK_product_doesnt_exist(){
        Mockito.when(repository.findById(ArgumentMatchers.any(BigInteger.class))).thenReturn(Optional.empty());
        Assertions.assertNull(instance.getById("1"));
        Mockito.verify(repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void test_save(){
        Mockito.when(repository.save(ArgumentMatchers.any(Product.class))).thenReturn(ProductFixtures.getSingleProduct());
        Assertions.assertNotNull(instance.save(ProductFixtures.getSingleProduct()));
        Mockito.verify(repository).save(ArgumentMatchers.any(Product.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void test_update(){
        Mockito.when(repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.of(ProductFixtures.getSingleProduct()));
        Mockito.when(repository.save(ArgumentMatchers.any(Product.class)))
            .thenReturn((ProductFixtures.getSingleProduct()));
        Assertions.assertNotNull(instance.update("1",ProductFixtures.getSingleProduct()));
        Mockito.verify(repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verify(repository).save(ArgumentMatchers.any(Product.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void test_update_NOK_product_doesnt_exist(){
        final var rq = ProductFixtures.getSingleProduct();
        Mockito.when(repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> instance.update("1",rq));
        Mockito.verify(repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void test_delete_OK(){
        Mockito.when(repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.of(ProductFixtures.getSingleProduct()));
        Mockito.doNothing().when(repository).delete(ArgumentMatchers.any(Product.class));
        Assertions.assertTrue(instance.delete("1"));
        Mockito.verify(repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verify(repository).delete(ArgumentMatchers.any(Product.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void test_delete_NOK(){
        Mockito.when(repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.empty());
        Mockito.doNothing().when(repository).delete(ArgumentMatchers.any(Product.class));
        Assertions.assertFalse(instance.delete("1"));
        Mockito.verify(repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

}