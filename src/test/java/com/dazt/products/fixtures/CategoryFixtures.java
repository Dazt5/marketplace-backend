package com.dazt.products.fixtures;

import com.dazt.ms.products.dto.CategoryDto;
import com.dazt.products.persistence.entity.Category;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * CategoryFixtures.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
public class CategoryFixtures {

    /**
     * Return a CategoryDto object.
     *
     * @return category {@link CategoryDto}
     * */
    public static CategoryDto getCategoryDto(){
        return CategoryDto.builder()
            .id(BigInteger.ONE)
            .categoryCode("code")
            .name("category")
            .description("category description")
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .build();
    }

    /**
     * Return a List of CategoryDto objects.
     *
     * @return list {@link List}
     * */
    public static List<CategoryDto> getListCategoryDto(){
        return List.of(getCategoryDto());
    }

    /**
     * Return a Category entity object.
     *
     * @return category {@link Category}
     * */
    public static Category getCategory(){
        return Category.builder()
            .id(BigInteger.ONE)
            .categoryCode("code")
            .name("category")
            .description("category description")
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .build();
    }

    /**
     * Return a List of Category entities objects.
     *
     * @return list {@link List}
     * */
    public static List<Category> getListCategory(){
        return List.of(getCategory());
    }

}