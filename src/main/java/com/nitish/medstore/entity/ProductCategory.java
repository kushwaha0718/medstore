package com.nitish.medstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // ✅ PRIMARY KEY

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductDetails product;

    @Column(nullable = false)
    private Integer category;
}
