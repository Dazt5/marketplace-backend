package com.dazt.products.services.impl;

import com.dazt.products.entity.Product;
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
        if (null != product){
            repository.delete(product);
            return true;
        }
        return false;
    }
}