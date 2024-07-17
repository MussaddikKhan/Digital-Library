package com.myProject.practice.Digital_Library_Project.Services;

import com.myProject.practice.Digital_Library_Project.Entity.Admin;
import com.myProject.practice.Digital_Library_Project.Entity.User;
import com.myProject.practice.Digital_Library_Project.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Value("${admin.authorities}")
    String authorities;

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserService userService;

    public void create(Admin admin) {
        User user = User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .authorities(this.authorities)
                .build();
        
        userService.create(user);
        admin.setUser(user);
        adminRepository.save(admin);
    }
}
