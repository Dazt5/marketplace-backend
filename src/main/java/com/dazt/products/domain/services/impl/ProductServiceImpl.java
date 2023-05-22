package com.dazt.products.domain.services.impl;

import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.domain.repository.ProductRepository;
import com.dazt.products.domain.services.CategoryService;
import com.dazt.products.domain.services.ProductService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProductServiceImpl.
 *
 * @author David Alvarez.
 * @version 1.0.0, 21-09-2022
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductDto> getAll() {
        return this.repository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDto getById(final String id) {
        return this.repository.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product doesn't exists"));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<ProductDto> getByCategory(final String categoryCode) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDto save(final ProductDto product) {
        final var category = this.categoryService.getById(product.getCategory().getId().toString());
        product.setCategory(category);
        return this.repository.save(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDto update(final String id, final ProductDto product) {
        final var existingProduct = this.getById(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());
        return this.repository.save(existingProduct);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(final String id) {
        return this.repository.delete(id);
    }

}