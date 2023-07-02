package com.dazt.products.domain.services.impl;

import com.dazt.ms.products.dto.CategoryDto;
import com.dazt.products.domain.repository.CategoryRepository;
import com.dazt.products.domain.services.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

    /**
     * {@inheritDoc}
     * */
    @Override
    public List<CategoryDto> getAll() {
        return this.repository.getAll();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public CategoryDto getById(final String id) {
        final var categoryOp = this.repository.getById(id);
        if (categoryOp.isEmpty()){
            throw new IllegalArgumentException("Category Doesn't exists");
        }
        return categoryOp.get();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    @Transactional(readOnly = true)
    public CategoryDto getByCategoryCode(final String categoryCode) {
        final var categoryOp =  this.repository.getByCategoryCode(categoryCode);
        if (categoryOp.isEmpty()){
            throw new IllegalArgumentException("Category Doesn't exists");
        }
        return categoryOp.get();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public CategoryDto save(final CategoryDto categoryDto) {
        final var categoryAlreadyExist = this.repository.getByCategoryCode(categoryDto.getCategoryCode())
            .isPresent();
        if (categoryAlreadyExist){
            throw new IllegalArgumentException("Category already exist with the code " + categoryDto.getCategoryCode());
        }
        return this.repository.save(categoryDto);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public CategoryDto update(final String id, final CategoryDto categoryDto) {
        final var savedCategoryOp = this.getById(id);
        savedCategoryOp.setCategoryCode(categoryDto.getCategoryCode());
        savedCategoryOp.setName(categoryDto.getName());
        savedCategoryOp.setDescription(categoryDto.getDescription());
        return this.repository.save(savedCategoryOp);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Boolean delete(final String id) {
        return this.repository.delete(id);
    }

}