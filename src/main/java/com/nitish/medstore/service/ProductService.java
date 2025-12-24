package com.nitish.medstore.service;

import com.nitish.medstore.dto.product.ProductRequestDto;
import com.nitish.medstore.dto.product.ProductResponseDto;
import com.nitish.medstore.entity.ProductDetails;
import com.nitish.medstore.exceptions.InvalidInputException;
import com.nitish.medstore.repository.ProductDetailsRepo;
import com.nitish.medstore.utility.DtoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductDetailsRepo productDetailsRepo;

    @Autowired
    public ProductService(ProductDetailsRepo productDetailsRepo) {
        this.productDetailsRepo = productDetailsRepo;
    }


    public List<ProductResponseDto> getAllProducts() {
        List<ProductDetails> allProducts = productDetailsRepo.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (ProductDetails productDetails : allProducts) {
            ProductResponseDto productResponseDto = DtoMapper
                    .convertToProductResponseDto(productDetails);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    private boolean validityCheck(ProductRequestDto productRequestDto) {
        return !productRequestDto.getProductName().isEmpty() &&
                !productRequestDto.getProductDescription().isEmpty() &&
                (productRequestDto.getProductPrice() != null && productRequestDto.getProductPrice()>0.0);
    }

    @Transactional
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        if(validityCheck(productRequestDto)) {
            ProductDetails productDetails = DtoMapper.convertToProductDetails(productRequestDto);
            productDetailsRepo.save(productDetails);
            return DtoMapper.convertToProductResponseDto(productDetails);
        }else{
            throw new InvalidInputException("Some Fields are empty");
        }
    }
}
