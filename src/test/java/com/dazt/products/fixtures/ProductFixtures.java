package com.dazt.products.fixtures;

import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.persistence.entity.Product;
import java.math.BigInteger;
import java.util.List;

/**
 * ProductFixtures.
 *
 * @author David Alvarez.
 * @version 1.0.0, 10-10-2022
 */
public class ProductFixtures {


    public static Product getSingleProduct(){
        return new Product();
    }

    public static ProductDto getSingleProductDto(){
        return ProductDto.builder().id(BigInteger.ONE).build();
    }

    public static List<Product> getProductList(){
        return List.of(getSingleProduct());
    }

}