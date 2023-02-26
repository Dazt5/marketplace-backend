package com.dazt.products.mappers;

import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.entity.Product;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;

/**
 * ProductoMapper.
 *
 * @author David Alvarez.
 * @version 1.0.0, 02-11-2022
 */
@Mapper
public interface ProductMapper {

    Product productToEntity(ProductDto productDto);

    @InheritInverseConfiguration
    ProductDto productToDto(Product productEntity);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<ProductDto> toDtoList(List<Product> productList);
}