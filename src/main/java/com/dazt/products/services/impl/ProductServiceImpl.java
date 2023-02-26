/*
 * @(#)ProductServiceImpl.java
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
package com.dazt.products.services.impl;

import com.dazt.ms.products.entity.Product;
import com.dazt.products.repositories.ProductRepository;
import com.dazt.products.services.ProductService;
import java.math.BigInteger;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl.
 *
 * @author David Alvarez.
 * @version 1.0.0, 21-09-2022
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(final String id) {
        return repository.findById(new BigInteger(id)).orElse(null);
    }

    @Override
    public Product save(final Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(final String id, final Product product) {
        final var existingProduct = repository.findById(new BigInteger(id)).orElse(null);
        if (null == existingProduct){
            throw new IllegalArgumentException("El producto no existe");
        }
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }

    @Override
    public Boolean delete(final String id) {
        final var product =  this.getById(id);
        if (null != this.getById((id))){
            repository.delete(product);
            return true;
        }
        return false;
    }
}