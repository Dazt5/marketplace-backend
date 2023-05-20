package com.dazt.products.persistence.crud;

import com.dazt.products.persistence.entity.Category;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoryRepository.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
public interface CategoryCrudRepository extends JpaRepository<Category, BigInteger> {

    /**
     * Return a category queried by category code
     *
     * @param categoryCode {@link String}
     * @return category {@link Category}
     * */
    Optional<Category> findByCategoryCode(String categoryCode);
}