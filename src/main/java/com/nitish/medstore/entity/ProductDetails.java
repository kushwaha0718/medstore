package com.nitish.medstore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String productDescription;
    private Double productPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate productUploadDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "hh-mm-ss")
    @Column(columnDefinition = "TIME(0)")
    private LocalTime productUploadTime;
    private String productUnit;
    private String productManufacturer;
    private String productStrength;

    private String productImageName;
    private String productImageType;

    @Lob
    private byte[] productImageData;
}
