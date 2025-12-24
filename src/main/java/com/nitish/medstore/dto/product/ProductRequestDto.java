package com.nitish.medstore.dto.product;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String productName;
    private String productDescription;
    private Double productPrice;
}
