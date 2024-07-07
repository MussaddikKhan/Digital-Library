package com.myProject.practice.Digital_Library_Project.Dto;

import com.myProject.practice.Digital_Library_Project.Entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateTransactionRequest {


       @Positive
       Integer bookId;

       @Positive
       Integer studentId;

       @NonNull
       TransactionType transactionType;
}
