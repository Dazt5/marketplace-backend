package com.dazt.products.controllers;

import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.entity.Product;
import com.dazt.products.services.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * ProductController.
 *
 * @author David Alvarez.
 * @version 1.0.0, 20-09-2022
 */
@RestController
@RequestMapping("/v1/products/")
@RequiredArgsConstructor
public class ProductController {

    /** service. */
    private final ProductService service;

    /**
     * Obtain a list of products.
     *
     * @return list {@link Product}
     * */
    @GetMapping
    public List<ProductDto> getAll(){
        return service.getAll();
    }

    /**
     * Obtain a specific product by id.
     *
     * @return list {@link Boolean}
     * */
    @GetMapping("/{id}")
    public ProductDto getId(@PathVariable String id){
        return service.getById(id);
    }

    /**
     * Save a product.
     *
     * @return product {@link Product}
     * */
    @PostMapping
    public ProductDto save(@RequestBody ProductDto product){
        return service.save(product);
    }

    /**
     * Update a product.
     *
     * @return product {@link Product}
     * */
    @PutMapping("/{id}")
    public ProductDto update(@PathVariable final String id, @RequestBody ProductDto product){
        return service.update(id,product);
    }

    /**
     * Delete a product.
     *
     * @return product {@link Product}
     * */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable final String id){
        return service.delete(id);
    }

}