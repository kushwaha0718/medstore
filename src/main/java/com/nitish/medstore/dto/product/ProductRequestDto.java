package com.nitish.medstore.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String productUnit;
    private String productManufacturer;
    private String productStrength;
    private List<Integer> categories;
}
