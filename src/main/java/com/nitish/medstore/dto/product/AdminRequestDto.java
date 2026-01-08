package com.nitish.medstore.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDto {
    private String adminUsername;
    private String adminPassword;
}
