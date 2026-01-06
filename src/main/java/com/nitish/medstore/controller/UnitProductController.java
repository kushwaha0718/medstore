package com.nitish.medstore.controller;

import com.nitish.medstore.entity.UnitProduct;
import com.nitish.medstore.service.UnitProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unit")
public class UnitProductController {

    private final UnitProductService  unitProductService;

    @Autowired
    public UnitProductController(UnitProductService unitProductService) {
        this.unitProductService = unitProductService;
    }

    @GetMapping("/get-all-units")
    public List<UnitProduct> getAllUnits(){
        return unitProductService.getAllUnits();
    }

    @DeleteMapping("/delete-unit/{unitId}")
    public ResponseEntity<?> deleteUnit(@PathVariable int unitId){
        try {
            unitProductService.deleteUnit(unitId);
            return new ResponseEntity<>(Map.of("message","Unit deleted successfully with ID: " + unitId)
                    ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message",e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-unit")
    public ResponseEntity<?> addUnit(@RequestBody UnitProduct unitProduct){
        try {
            UnitProduct saved = unitProductService.addUnit(unitProduct);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message",e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-unit")
    public ResponseEntity<?> updateUnit(@RequestBody UnitProduct unitProduct){
        try {
            return new ResponseEntity<>(unitProductService.updateUnit(unitProduct),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message",e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
