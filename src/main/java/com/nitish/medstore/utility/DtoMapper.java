package com.nitish.medstore.utility;

import com.nitish.medstore.dto.product.AdminResponseDto;
import com.nitish.medstore.dto.product.ProductRequestDto;
import com.nitish.medstore.dto.product.ProductResponseDto;
import com.nitish.medstore.entity.AdminData;
import com.nitish.medstore.entity.ProductCategory;
import com.nitish.medstore.entity.ProductDetails;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class DtoMapper {
    private DtoMapper() {}

    public static ProductDetails convertToProductDetails(ProductRequestDto dto) {

        ProductDetails product = new ProductDetails();
        product.setProductName(dto.getProductName());
        product.setProductDescription(dto.getProductDescription());
        product.setProductPrice(dto.getProductPrice());
        product.setProductUploadDate(LocalDate.now());
        product.setProductUploadTime(LocalTime.now());
        product.setProductUnit(dto.getProductUnit());
        product.setProductManufacturer(dto.getProductManufacturer());
        product.setProductStrength(dto.getProductStrength());
        product.setProductMarkedStar(false);

        // ✅ Map categories
        if (dto.getCategories() != null) {
            List<ProductCategory> categoryEntities = dto.getCategories()
                    .stream()
                    .map(cat -> new ProductCategory(null, product, cat))
                    .toList();
            product.setCategories(categoryEntities);
        }

        return product;
    }


    public static ProductResponseDto convertToProductResponseDto(ProductDetails product) {

        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setProductDescription(product.getProductDescription());
        dto.setProductPrice(product.getProductPrice().toString());
        dto.setProductUploadDate(product.getProductUploadDate());
        dto.setProductUploadTime(product.getProductUploadTime());
        dto.setProductImageData(product.getProductImageData());
        dto.setProductUnit(product.getProductUnit());
        dto.setProductManufacturer(product.getProductManufacturer());
        dto.setProductStrength(product.getProductStrength());
        dto.setProductMarkedStar(product.isProductMarkedStar());

        // ✅ Convert entity → List<Integer>
        List<Integer> categories = product.getCategories()
                .stream()
                .map(ProductCategory::getCategory)
                .toList();
        dto.setCategories(categories);

        return dto;
    }


    public static AdminResponseDto convertToAdminResponseDto(AdminData admin) {
        AdminResponseDto adminResponseDto = new AdminResponseDto();
        adminResponseDto.setAdminName(admin.getAdminName());
        adminResponseDto.setAdminEmail(admin.getAdminEmail());
        adminResponseDto.setAdminUserName(admin.getAdminUserName());

        return adminResponseDto;
    }
}
