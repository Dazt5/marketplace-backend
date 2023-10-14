package com.dazt.products.persistence.repositories.impl;

import com.dazt.products.domain.repository.CategoryRepository;
import com.dazt.products.persistence.mappers.CategoryMapper;
import com.dazt.products.persistence.crud.CategoryCrudRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * CategoryRepositoryImpl.
 *
 * @author David Alvarez.
 * @version 1.0.0, 26-02-2023
 */
@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryRepositoryImpl.class);
    private final CategoryCrudRepository repository;
    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAll() {
        return mapper.toDtoList(this.repository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryDto> getById(final String id) {
        return this.repository.findById(new BigInteger(id))
                .map(this.mapper::categoryToDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryDto> getByCategoryCode(final String categoryCode) {
        return this.repository.findByCategoryCode(categoryCode)
                .map(this.mapper::categoryToDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CategoryDto save(final CategoryDto category) {
        return this.mapper.categoryToDto(this.repository.save(this.mapper.categoryToEntity(category)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Boolean delete(final String id) {
        final var categoryId = new BigInteger(id);
        final var savedCategoryOp = this.repository.findById(categoryId);
        if (savedCategoryOp.isEmpty()) {
            return false;
        }
        try {
            this.repository.deleteById(categoryId);
        } catch (Exception e) {
            CategoryRepositoryImpl.LOG.error("Error occurred deleting a category.", e);
            return false;
        }
        return true;
    }

}