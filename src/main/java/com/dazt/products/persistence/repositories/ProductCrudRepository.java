package com.dazt.products.persistence.repositories;

import com.dazt.products.persistence.entity.Category;
import com.dazt.products.persistence.entity.Product;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductRepository.
 *
 * @author David Alvarez.
 * @version 1.0.0, 21-09-2022
 */
public interface ProductCrudRepository extends JpaRepository<Product, BigInteger> {

    /**
     * find a list of product queried by category code
     *
     * @param category {@link String}
     * @return list {@link List}
     * */
    List<Product> findByCategory(Category category);
}