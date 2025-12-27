package com.nitish.medstore.utility;

import com.nitish.medstore.dto.product.ProductRequestDto;
import com.nitish.medstore.dto.product.ProductResponseDto;
import com.nitish.medstore.entity.ProductDetails;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DtoMapper {
    private DtoMapper() {}

    public static ProductDetails convertToProductDetails(ProductRequestDto productRequestDto) {
        ProductDetails product = new ProductDetails();
        product.setProductName(productRequestDto.getProductName());
        product.setProductDescription(productRequestDto.getProductDescription());
        product.setProductPrice(productRequestDto.getProductPrice());
        product.setProductUploadDate(LocalDate.now());
        product.setProductUploadTime(LocalTime.now());

        return product;
    }

    public static ProductResponseDto convertToProductResponseDto(ProductDetails productDetails) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductName(productDetails.getProductName());
        productResponseDto.setProductDescription(productDetails.getProductDescription());
        productResponseDto.setProductPrice(productDetails.getProductPrice().toString());
        productResponseDto.setProductUploadDate(productDetails.getProductUploadDate());
        productResponseDto.setProductUploadTime(productDetails.getProductUploadTime());
        productResponseDto.setProductImageData(productDetails.getProductImageData());

        return productResponseDto;
    }
}
