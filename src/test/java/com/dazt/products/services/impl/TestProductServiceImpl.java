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

/**
 * TestProductServiceImpl.
 *
 * @author David Alvarez.
 * @version 1.0.0, 10-10-2022
 */
class TestProductServiceImpl {

    /** repository .*/
    @Mock
    private ProductRepository repository;
    /** instance .*/
    @InjectMocks
    private ProductServiceImpl instance;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_get_all_OK(){
        Mockito.when(this.repository.findAll()).thenReturn(ProductFixtures.getProductList());
        Assertions.assertNotNull(this.instance.getAll());
        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_get_by_id_OK(){
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.of(ProductFixtures.getSingleProduct()));
        Assertions.assertNotNull(this.instance.getById("1"));
        Mockito.verify(this.repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_get_by_id_NOK_product_doesnt_exist(){
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class))).thenReturn(Optional.empty());
        Assertions.assertNull(this.instance.getById("1"));
        Mockito.verify(this.repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_save(){
        Mockito.when(this.repository.save(ArgumentMatchers.any(Product.class))).thenReturn(ProductFixtures.getSingleProduct());
        Assertions.assertNotNull(this.instance.save(ProductFixtures.getSingleProductDto()));
        Mockito.verify(this.repository).save(ArgumentMatchers.any(Product.class));
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_update(){
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.of(ProductFixtures.getSingleProduct()));
        Mockito.when(this.repository.save(ArgumentMatchers.any(Product.class)))
            .thenReturn((ProductFixtures.getSingleProduct()));
        Assertions.assertNotNull(this.instance.update("1",ProductFixtures.getSingleProductDto()));
        Mockito.verify(this.repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verify(this.repository).save(ArgumentMatchers.any(Product.class));
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_update_NOK_product_doesnt_exist(){
        final var rq = ProductFixtures.getSingleProductDto();
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> this.instance.update("1",rq));
        Mockito.verify(this.repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_delete_OK(){
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.of(ProductFixtures.getSingleProduct()));
        Mockito.doNothing().when(this.repository).delete(ArgumentMatchers.any(Product.class));
        Assertions.assertTrue(this.instance.delete("1"));
        Mockito.verify(this.repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verify(this.repository).delete(ArgumentMatchers.any(Product.class));
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void test_delete_NOK(){
        Mockito.when(this.repository.findById(ArgumentMatchers.any(BigInteger.class)))
            .thenReturn(Optional.empty());
        Mockito.doNothing().when(this.repository).delete(ArgumentMatchers.any(Product.class));
        Assertions.assertFalse(this.instance.delete("1"));
        Mockito.verify(this.repository).findById(ArgumentMatchers.any(BigInteger.class));
        Mockito.verifyNoMoreInteractions(this.repository);
    }

}