package com.dazt.products.persistence.repositories.impl;

import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.domain.repository.ProductRepository;
import com.dazt.products.persistence.mappers.ProductMapper;
import com.dazt.products.persistence.crud.ProductCrudRepository;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepositoryImpl.
 *
 * @author David Alvarez.
 * @version 1.0.0, 06-02-2023
 */
@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    /** productMapper. */
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    /** crudRepository. */
    private final ProductCrudRepository crudRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductDto> getAll() {
        return this.productMapper.toDtoList(this.crudRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<ProductDto>> getByCategory(final int categoryId) {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<ProductDto>> getScarseProducts(final int quantity) {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProductDto> getProductById(final String id) {
        return this.crudRepository.findById(new BigInteger(id)).map(productMapper::productToDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDto save(final ProductDto product) {
        return this.productMapper.productToDto(this.crudRepository.save(this.productMapper.productToEntity(product)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(final String id) {
        final var productId = new BigInteger(id);
        final var savedProductOp = this.crudRepository.findById(productId);
        if (savedProductOp.isEmpty()) {
            return false;
        }
        try {
            this.crudRepository.delete(savedProductOp.get());
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}