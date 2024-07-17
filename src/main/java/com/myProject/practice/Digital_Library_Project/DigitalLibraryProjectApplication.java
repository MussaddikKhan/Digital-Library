package com.myProject.practice.Digital_Library_Project;

import com.myProject.practice.Digital_Library_Project.Entity.Admin;
import com.myProject.practice.Digital_Library_Project.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DigitalLibraryProjectApplication implements CommandLineRunner {

	@Autowired
	private  AdminService adminService;
	public static void main(String[] args) {
		SpringApplication.run(DigitalLibraryProjectApplication.class, args);
	}

	//Run Only Once While Creating Admin 
	@Override
	public void run(String... args) throws Exception {
//		Admin admin = Admin.builder()
//				.username("Musaddik")
//				.password(new BCryptPasswordEncoder().encode("Musaddik123"))
//				.build();
//		adminService.create(admin);
		
	}
}
