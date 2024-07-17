package com.myProject.practice.Digital_Library_Project.Repository;

import com.myProject.practice.Digital_Library_Project.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
