package com.dazt.products.persistence.repositories.impl;

import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.domain.repository.ProductRepository;
import com.dazt.products.persistence.mappers.ProductMapper;
import com.dazt.products.persistence.repositories.ProductCrudRepository;
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

    /** crudRepository. */
    private final ProductCrudRepository crudRepository;
    /** productMapper. */
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<ProductDto> getAll() {
        return this.productMapper.toDtoList(this.crudRepository.findAll());
    }

    @Override
    public Optional<List<ProductDto>> getByCategory(final int categoryId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<ProductDto>> getScarseProducts(final int quantity) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductDto> getProductById(final BigInteger productId) {
        return this.crudRepository.findById(productId).map(productMapper::productToDto);
    }

    @Override
    public ProductDto save(final ProductDto product) {
        final var productToSave = this.productMapper.productToEntity(product);
        return this.productMapper.productToDto(this.crudRepository.save(productToSave));
    }

    @Override
    public void delete(final BigInteger productId) {
        this.crudRepository.deleteById(productId);
    }

}