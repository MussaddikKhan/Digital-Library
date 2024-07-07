package com.myProject.practice.Digital_Library_Project.Dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnAuthorResponse {
    private  String name;
    private  String email;
    private  String country;
    private Date createdOn;
    private Date updatedOn;
}
