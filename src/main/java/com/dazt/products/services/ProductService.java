/*
 * @(#)ProductService.java
 *
 * Copyright (c) BANCO DE CHILE (Chile). All rights reserved.
 *
 * All rights to this product are owned by BANCO DE CHILE and may only
 * be used under the terms of its associated license document. You may NOT
 * copy, modify, sublicense, or distribute this source file or portions of
 * it unless previously authorized in writing by BANCO DE CHILE.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package com.dazt.products.services;

import com.dazt.ms.products.entity.Product;
import java.util.List;

/**
 * ProductService.
 *
 * @author David Alvarez.
 * @version 1.0.0, 21-09-2022
 */
public interface ProductService {

    /**
     * Return a list of products.
     *
     * @return list {@link Product}
     * */
    List<Product> getAll();

    /**
     * Return a product queried by id.
     *
     * @return product {@link Product}
     * */
    Product getById(final String id);

    /**
     * Save a new product.
     *
     * @return list {@link Product}
     * */
    Product save(final Product product);

    /**
     * Update a existing product
     *
     * @return list {@link Product}
     * */
    Product update(final String id, final Product product);

    /**
     * Delete a product
     *
     * @return list {@link Boolean}
     * */
    Boolean delete(final String id);
}