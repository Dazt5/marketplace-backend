package com.dazt.products.mappers;

import com.dazt.ms.products.dto.CategoryDto;
import com.dazt.products.entity.Category;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

/**
 * CategoryMapper.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
@Mapper
public interface CategoryMapper {

    /**
     * @param categoryDto {@link Category}
     * @return {@link Category}
     * */
    Category categoryToEntity(CategoryDto categoryDto);

    @InheritInverseConfiguration
    CategoryDto categoryToDto(Category categoryEntity);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<CategoryDto> toDtoList(List<Category> productList);

}