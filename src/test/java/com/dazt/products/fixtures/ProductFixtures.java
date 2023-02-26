package com.dazt.products.fixtures;

import com.dazt.ms.products.dto.ProductDto;
import com.dazt.products.entity.Product;
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
        return new ProductDto();
    }

    public static List<Product> getProductList(){
        return List.of(getSingleProduct());
    }

}