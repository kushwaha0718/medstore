package com.nitish.medstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unitId;
    private String unitName;
}
