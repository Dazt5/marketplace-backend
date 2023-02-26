package com.dazt.products.domain.services;

import com.dazt.ms.products.dto.CategoryDto;
import com.dazt.products.persistence.entity.Category;
import com.dazt.products.persistence.entity.Product;
import java.util.List;

/**
 * CategoryRepository.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
public interface CategoryService {

    /**
     * Return a list of categories.
     *
     * @return list {@link CategoryDto}
     * */
    List<CategoryDto> getAll();

    /**
     * Return a category queried by id.
     *
     * @return product {@link CategoryDto}
     * */
    CategoryDto getById(final String id);

    /**
     * Return a category queried by id.
     *
     * @return product {@link Category}
     * */
    CategoryDto getByCategoryCode(String categoryCode);

    /**
     * Save a new category.
     *
     * @return list {@link Product}
     * */
    CategoryDto save(final CategoryDto product);

    /**
     * Update an existing category
     *
     * @return list {@link Product}
     * */
    CategoryDto update(final String id, final CategoryDto product);

    /**
     * Delete a category
     *
     * @return list {@link Boolean}
     * */
    Boolean delete(final String id);

}