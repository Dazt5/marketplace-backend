package com.dazt.products.domain.repository;

import java.util.List;
import java.util.Optional;

/**
 * ProductRepository.
 *
 * @author David Alvarez.
 * @version 1.0.0, 06-02-2023
 */
public interface ProductRepository {

    /**
     * Return a list of categories.
     *
     * @return list {@link ProductDto}
     * */
    List<ProductDto> getAll();

    /**
     * Return a list of product queried by category.
     *
     * @return list {@link ProductDto}
     * */
    Optional<List<ProductDto>> getByCategory(int categoryId);

    /**
     * Return a list of product queried by scarse quantity.
     *
     * @return list {@link ProductDto}
     * */
    Optional<List<ProductDto>> getScarseProducts(int quantity);

    /**
     * Return a Product queried by id.
     *
     * @return list {@link ProductDto}
     * */
    Optional<ProductDto> getProductById(String productId);

    /**
     * Save a product in the database
     *
     * @return list {@link ProductDto}
     * */
    ProductDto save(ProductDto product);

    /**
     * Return a Product queried by id.
     *
     * */
    boolean delete(String productId);

}