package com.myProject.practice.Digital_Library_Project.Dto;


import com.myProject.practice.Digital_Library_Project.Entity.Book;
import com.myProject.practice.Digital_Library_Project.Entity.Genre;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnBookResponse implements Serializable {
    private String name;
    private String language;
    private Genre genre;
    private ReturnAuthorResponse author;
    private Date createdOn;
    private  Date updatedOn;

    public static ReturnBookResponse from(Book b){
        return ReturnBookResponse.builder()
                .name(b.getName())
                .genre(b.getGenre())
                .language(b.getLanguage())
                .author(ReturnAuthorResponse.builder()
                        .name(b.getAuthor().getName())
                        .email(b.getAuthor().getEmail())
                        .country(b.getAuthor().getCountry())
                        .createdOn(b.getAuthor().getCreatedOn())
                        .updatedOn(b.getAuthor().getUpdatedOn())
                        .build())
                .createdOn(b.getCreatedOn())
                .updatedOn(b.getUpdatedOn())
                .build();
    }
}
