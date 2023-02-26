package com.dazt.products.services.impl;

import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.mappers.ProductMapper;
import com.dazt.products.repositories.ProductRepository;
import com.dazt.products.services.ProductService;
import java.math.BigInteger;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl.
 *
 * @author David Alvarez.
 * @version 1.0.0, 21-09-2022
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    /** repository.*/
    private final ProductRepository repository;
    /** productMapper.*/
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<ProductDto> getAll() {
        return this.productMapper.toDtoList(this.repository.findAll());
    }

    @Override
    public ProductDto getById(final String id) {
        return this.productMapper.productToDto(this.repository.findById(new BigInteger(id)).orElse(null));
    }

    @Override
    public ProductDto save(final ProductDto product) {
       final var productEntity = this.productMapper.productToEntity(product);
        return this.productMapper.productToDto(this.repository.save(productEntity));
    }

    @Override
    public ProductDto update(final String id, final ProductDto product) {
        final var existingProduct = this.repository.findById(new BigInteger(id)).orElse(null);
        if (null == existingProduct){
            throw new IllegalArgumentException("El producto no existe");
        }
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());
        return this.productMapper.productToDto(this.repository.save(existingProduct));
    }

    @Override
    public Boolean delete(final String id) {
        final var product =  this.getById(id);
        if (null != product){
            this.repository.delete(this.productMapper.productToEntity(product));
            return true;
        }
        return false;
    }
}