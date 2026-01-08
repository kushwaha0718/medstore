package com.nitish.medstore.controller;

import com.nitish.medstore.dto.product.AdminRequestDto;
import com.nitish.medstore.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

//    @GetMapping("/get-verification")
//    public ResponseEntity<?> getVerification(@RequestBody AdminRequestDto adminRequestDto){
//
//    }
}
