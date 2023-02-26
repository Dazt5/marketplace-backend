package com.dazt.products.repositories;

import com.dazt.products.entity.Category;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoryRepository.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
public interface CategoryRepository extends JpaRepository<Category, BigInteger> {
}