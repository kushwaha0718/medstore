package com.nitish.medstore.repository;

import com.nitish.medstore.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Long> {
}
