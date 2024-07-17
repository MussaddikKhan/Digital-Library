package com.myProject.practice.Digital_Library_Project.Repository;

import com.myProject.practice.Digital_Library_Project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
