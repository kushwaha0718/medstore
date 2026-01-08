package com.nitish.medstore.repository;

import com.nitish.medstore.entity.AdminData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminData, Integer> {
    public boolean existsByadminUserName(String adminUserName);
}
