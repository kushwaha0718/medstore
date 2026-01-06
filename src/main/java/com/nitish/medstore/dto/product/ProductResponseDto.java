package com.nitish.medstore.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ProductResponseDto {
    private String productName;
    private String productDescription;
    private String productPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDate productUploadDate;
    private String productUnit;
    private String productManufacturer;
    private String productStrength;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "hh-mm-ss")
    private LocalTime productUploadTime;

    private byte[] productImageData;
}

