package com.nitish.medstore.utility;

import com.nitish.medstore.entity.AdminData;
import com.nitish.medstore.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminStartupRunner implements CommandLineRunner {

    private final AdminRepository adminRepository;

    public AdminStartupRunner(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) {

        if (!adminRepository.existsByadminUserName("admin@nitish")) {
            AdminData admin = new AdminData();
            admin.setAdminUserName("admin@nitish");
            admin.setAdminPassword("admin@nitish");
            admin.setAdminEmail("admin@gmail.com");
            admin.setAdminName("Nitish Kushwaha");
            adminRepository.save(admin);
        }
    }
}

