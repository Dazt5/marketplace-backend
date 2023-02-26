package com.dazt.products.services.impl;

import com.dazt.ms.products.dto.CategoryDto;
import com.dazt.products.mappers.CategoryMapper;
import com.dazt.products.repositories.CategoryRepository;
import com.dazt.products.services.CategoryService;
import java.math.BigInteger;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CategoryServiceImpl.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    /** repository. */
    private final CategoryRepository repository;
    /** mapper. */
    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    /**
     * {@inheritDoc}
     * */
    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAll() {
        return this.mapper.toDtoList(this.repository.findAll());
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    @Transactional(readOnly = true)
    public CategoryDto getById(final String id) {
        return this.mapper.categoryToDto(this.repository.findById(new BigInteger(id)).orElse(null));
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    @Transactional
    public CategoryDto save(final CategoryDto categoryDto) {
        final var categoryEntity = this.mapper.categoryToEntity(categoryDto);
        return this.mapper.categoryToDto(this.repository.save(categoryEntity));
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    @Transactional
    public CategoryDto update(final String id, final CategoryDto categoryDto) {
        final var categoryEntity= this.repository.findById(new BigInteger(id)).orElse(null);
        if (null == categoryEntity){
            throw new IllegalArgumentException("Category doesn't exists.");
        }
        categoryEntity.setName(categoryEntity.getName());
        categoryEntity.setDescription(categoryDto.getDescription());
        categoryEntity.setCategoryCode(categoryDto.getCategoryCode());
        return this.save(this.mapper.categoryToDto(categoryEntity));
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    @Transactional
    public Boolean delete(final String id) {
        final var category = this.repository.findById(new BigInteger(id)).orElse(null);
        if (null == category){
            return false;
        }
        this.repository.delete(category);
        return true;
    }

}