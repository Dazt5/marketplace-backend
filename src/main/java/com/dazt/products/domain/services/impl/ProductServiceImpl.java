package com.dazt.products.domain.services.impl;

import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.domain.services.CategoryService;
import com.dazt.products.domain.services.ProductService;
import com.dazt.products.persistence.mappers.CategoryMapper;
import com.dazt.products.persistence.mappers.ProductMapper;
import com.dazt.products.persistence.repositories.ProductCrudRepository;
import java.math.BigInteger;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
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

    /** repository.*/
    private final ProductCrudRepository repository;
    /** categoryRepository.*/
    private final CategoryService categoryService;
    /** productMapper.*/
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    /** categoryMapper.*/
    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Override
    public List<ProductDto> getAll() {
        return this.productMapper.toDtoList(this.repository.findAll());
    }

    @Override
    public ProductDto getById(final String id) {
        return this.productMapper.productToDto(this.repository.findById(new BigInteger(id)).orElse(null));
    }

    /**
     * {@inheritDoc}
     * */
    @Transactional(readOnly = true)
    public List<ProductDto> getByCategory(final String categoryCode){
        return null;
    }

    @Override
    public ProductDto save(final ProductDto product) {
       final var productEntity = this.productMapper.productToEntity(product);
       final var category = this.categoryService.getByCategoryCode(product.getCategory().getCategoryCode());
        productEntity.setCategory(this.categoryMapper.categoryToEntity(category));
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