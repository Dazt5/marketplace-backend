package com.dazt.products.domain.repository;

import com.dazt.ms.products.dto.CategoryDto;
import com.dazt.products.persistence.entity.Category;
import com.dazt.products.persistence.entity.Product;
import java.util.List;
import java.util.Optional;

/**
 * CategoryRepository.
 *
 * @author David Alvarez.
 * @version 1.0.0, 26-02-2023
 */
public interface CategoryRepository {

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
    Optional<CategoryDto> getById(String id);

    /**
     * Return a category queried by id.
     *
     * @return product {@link Category}
     * */
    Optional<CategoryDto> getByCategoryCode(String categoryCode);

    /**
     * Save a new or update an existing category.
     *
     * @return list {@link Product}
     * */
    CategoryDto save(CategoryDto product);

    /**
     * Delete a category
     *
     * @return list {@link Boolean}
     * */
    Boolean delete(String id);

}