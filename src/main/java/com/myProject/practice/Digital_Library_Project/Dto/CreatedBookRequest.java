package com.myProject.practice.Digital_Library_Project.Dto;

import com.myProject.practice.Digital_Library_Project.Entity.Author;
import com.myProject.practice.Digital_Library_Project.Entity.Book;
import com.myProject.practice.Digital_Library_Project.Entity.Genre;
import jakarta.validation.constraints.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreatedBookRequest {

    @NotBlank
    private String name;

    private String language;
    
    @NonNull
    private Genre genre;


    @NotBlank
    private String  authorName ;

    @Email
    private String authorEmail;
    private  String authorCountry ;

    public Book to(){
        return Book.builder()
                .name(this.name)
                .language((this.language))
                .genre(this.genre)
                .author(
                    Author.builder()
                            .name(this.authorName)
                            .email(this.authorEmail)
                            .country(this.authorCountry)
                            .build()
                )
        .build();
    }
}
