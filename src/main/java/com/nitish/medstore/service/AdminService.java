package com.nitish.medstore.service;

import com.nitish.medstore.dto.product.AdminRequestDto;
import com.nitish.medstore.dto.product.AdminResponseDto;
import com.nitish.medstore.entity.AdminData;
import com.nitish.medstore.exceptions.NoAdminFound;
import com.nitish.medstore.repository.AdminRepository;
import com.nitish.medstore.utility.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminResponseDto verifyAdmin(AdminRequestDto adminRequestDto) {
        AdminData adminData = adminRepository.findByadminUserName(adminRequestDto.getAdminUsername());
        if (adminData == null) {
            throw new NoAdminFound("No admin found with username " + adminRequestDto.getAdminUsername());
        }
        if (adminData.getAdminPassword().equals(adminRequestDto.getAdminPassword())) {
            return DtoMapper.convertToAdminResponseDto(adminData);
        }
        return null;
    }
}
