package com.myProject.practice.Digital_Library_Project.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    private  final  String GLOBAL_DELIMITER = "::";
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  int id;

    @NotBlank
    @Column(unique = true)
    private  String username;

    private  String password;

    private  String authorities;

    @OneToOne(mappedBy = "user")
    private  Student student;

    @OneToOne(mappedBy = "user")
    private  Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String [] authoritiesList = authorities.split(GLOBAL_DELIMITER);
        return Arrays.stream(authoritiesList)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
