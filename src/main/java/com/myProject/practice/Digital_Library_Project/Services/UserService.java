package com.myProject.practice.Digital_Library_Project.Services;

import com.myProject.practice.Digital_Library_Project.Entity.User;
import com.myProject.practice.Digital_Library_Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User is not found");
        }
        return user;
    }
    public void create(User user){
        userRepository.save(user);
    }
}
