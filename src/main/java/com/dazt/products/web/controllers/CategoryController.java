package com.dazt.products.web.controllers;

import com.dazt.ms.products.dto.CategoryDto;
import com.dazt.products.persistence.entity.Product;
import com.dazt.products.domain.services.CategoryService;
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
 * CategoryController.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories/")
public class CategoryController {

    /** service. */
    private final CategoryService service;

    /**
     * Obtain a list of products.
     *
     * @return list {@link Product}
     * */
    @GetMapping
    public List<CategoryDto> getAll(){
        return service.getAll();
    }

    /**
     * Obtain a specific category by id.
     *
     * @return list {@link Boolean}
     * */
    @GetMapping("/{id}")
    public CategoryDto getId(@PathVariable String id){
        return service.getById(id);
    }

    /**
     * Save a category.
     *
     * @return product {@link Product}
     * */
    @PostMapping
    public CategoryDto save(@RequestBody CategoryDto product){
        return service.save(product);
    }

    /**
     * Update a category.
     *
     * @return product {@link Product}
     * */
    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable final String id, @RequestBody CategoryDto product){
        return service.update(id,product);
    }

    /**
     * Delete a category.
     *
     * @return product {@link Product}
     * */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable final String id){
        return service.delete(id);
    }

}