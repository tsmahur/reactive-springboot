package com.tsm.reactive.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsm.reactive.DTO.ProductDto;
import com.tsm.reactive.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class AppUtils {
//    @Autowired
//    ObjectMapper objectMapper;


    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto); //both class have same fields/attributes
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
