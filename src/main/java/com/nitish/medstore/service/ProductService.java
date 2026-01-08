package com.nitish.medstore.service;

import com.nitish.medstore.dto.product.ProductRequestDto;
import com.nitish.medstore.dto.product.ProductResponseDto;
import com.nitish.medstore.entity.ProductDetails;
import com.nitish.medstore.exceptions.InvalidInputException;
import com.nitish.medstore.exceptions.NoProductFoundException;
import com.nitish.medstore.repository.ProductDetailsRepo;
import com.nitish.medstore.utility.DtoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto, MultipartFile productImage) throws IOException {
        if(validityCheck(productRequestDto)) {
            ProductDetails productDetails = DtoMapper.convertToProductDetails(productRequestDto);
            productDetails.setProductImageName(productImage.getOriginalFilename());
            productDetails.setProductImageType(productImage.getContentType());
            productDetails.setProductImageData(productImage.getBytes());
            productDetailsRepo.save(productDetails);
            return DtoMapper.convertToProductResponseDto(productDetails);
        }else{
            throw new InvalidInputException("Some Fields are empty");
        }
    }

//    @Transactional
//    public void updateProduct(Long productId) {
//        ProductDetails productDetails = productDetailsRepo.findById(productId).orElse(null);
//        if(productDetails != null) {
//
//        }
//    }

    public ProductResponseDto getProductById(Long productID) {
        ProductDetails productDetails = productDetailsRepo.findById(productID).orElse(null);
        if(productDetails != null)
            return DtoMapper.convertToProductResponseDto(productDetails);
        else
            throw new NoProductFoundException("Product with id " + productID + " not found");
    }

    @Transactional
    public boolean deleteProduct(Long productId) {
        if (productDetailsRepo.existsById(productId)){
            productDetailsRepo.deleteById(productId);
            return true;
        }else
            throw new NoProductFoundException("Product with id " + productId + " not found");
    }

    public List<ProductResponseDto> getAllMarkedProducts() {
        List<ProductDetails> allProducts = productDetailsRepo.findAll();
        List<ProductResponseDto> markedProducts = new ArrayList<>();
        if (!allProducts.isEmpty()){
            for (ProductDetails productDetails : allProducts) {
                if(productDetails.isProductMarkedStar())
                    markedProducts.add(DtoMapper.convertToProductResponseDto(productDetails));
            }
        }
        return markedProducts;
    }

    @Transactional
    public boolean toggleMarked(Long productId) {
        ProductDetails product = productDetailsRepo.findById(productId).orElse(null);
        if (product != null) {
            product.setProductMarkedStar(!product.isProductMarkedStar());
            productDetailsRepo.save(product);
            return product.isProductMarkedStar();
        }else {
            throw new NoProductFoundException("Product with id " + productId + " not found");
        }
    }
}
