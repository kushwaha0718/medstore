package com.nitish.medstore.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDto {
    private String adminUserName;
    private String adminName;
    private String adminEmail;
}
