package com.nitish.medstore.controller;

import com.nitish.medstore.dto.product.AdminRequestDto;
import com.nitish.medstore.dto.product.AdminResponseDto;
import com.nitish.medstore.exceptions.NoAdminFound;
import com.nitish.medstore.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/verification")
    public ResponseEntity<?> verifyAdmin(@RequestBody AdminRequestDto adminRequestDto) {
        try {
            AdminResponseDto result = adminService.verifyAdmin(adminRequestDto);
            if (result != null) {
                return ResponseEntity.ok().body(result);
            }
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Incorrect password"
            ));
        } catch (NoAdminFound e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", e.getMessage()
            ));
        }
    }
}
