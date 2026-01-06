package com.nitish.medstore.service;

import com.nitish.medstore.entity.UnitProduct;
import com.nitish.medstore.repository.UnitProductRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitProductService {

    private final UnitProductRepo unitProductRepo;

    @Autowired
    public UnitProductService(UnitProductRepo unitProductRepo) {
        this.unitProductRepo = unitProductRepo;
    }

    public List<UnitProduct> getAllUnits() {
        return unitProductRepo.findAll();
    }

    @Transactional
    public void deleteUnit(int unitId) {
        unitProductRepo.deleteById(unitId);
    }

    @Transactional
    public UnitProduct addUnit(UnitProduct unitProduct) {
        return unitProductRepo.save(unitProduct);
    }

    @Transactional
    public UnitProduct updateUnit(UnitProduct unitProduct) {
        UnitProduct existing = unitProductRepo.findById(unitProduct.getUnitId())
                .orElseThrow(() -> new EntityNotFoundException("Unit not found with ID: " + unitProduct.getUnitId()));

        existing.setUnitName(unitProduct.getUnitName());
        return unitProductRepo.save(existing);
    }

}
