package com.nitish.medstore.controller.product;

import com.nitish.medstore.dto.product.ProductRequestDto;
import com.nitish.medstore.dto.product.ProductResponseDto;
import com.nitish.medstore.exceptions.NoProductFoundException;
import com.nitish.medstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        try{
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-product-by-id/{productId}")
    public  ResponseEntity<?> getProductById(@PathVariable("productId") Long productID) {
        try{
            return new ResponseEntity<>(productService.getProductById(productID),HttpStatus.OK);
        }catch (NoProductFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@ModelAttribute ProductRequestDto productRequestDto,
                                        @RequestPart MultipartFile productImage) {
        try{
            return new ResponseEntity<>(productService.addProduct(productRequestDto,productImage),
                    HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update-product/{productId}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long productId,
            @ModelAttribute ProductRequestDto productRequestDto,
            @RequestPart(required = false) MultipartFile productImage
    ) {
        try {
            return ResponseEntity.ok(productService.updateProduct(productId,productRequestDto, productImage));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok(Map.of("message","Product Deleted Successfully"));
        } catch (NoProductFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message",e.getMessage()));
        }
    }

     @GetMapping("/get-all-marked-products")
    public ResponseEntity<List<ProductResponseDto>> getAllMarkedProducts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAllMarkedProducts());
    }

    @PutMapping("/toggle-marked")
    public ResponseEntity<?> toggleMarkedProduct(@RequestParam Long productId) {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("message",productService.toggleMarked(productId)));
        }catch (NoProductFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message",e.getMessage()));
        }
    }
}
