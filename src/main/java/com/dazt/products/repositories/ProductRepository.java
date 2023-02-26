package com.dazt.products.repositories;

import com.dazt.products.entity.Product;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductRepository.
 *
 * @author David Alvarez.
 * @version 1.0.0, 21-09-2022
 */
public interface ProductRepository extends JpaRepository<Product, BigInteger> {
}