package com.myProject.practice.Digital_Library_Project.Dto;

import com.myProject.practice.Digital_Library_Project.Entity.Transaction;
import com.myProject.practice.Digital_Library_Project.Entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnStudentWithOverDueResponse {
    private String name;
    private  Integer age;
    private  String email;
    private  String rollNo;
    private String phone_Number;
    private  String bookName;
    private  Double fine;

    public  static  ReturnStudentWithOverDueResponse From(Transaction transaction){
                return ReturnStudentWithOverDueResponse.builder()
                        .name(transaction.getStudent().getName())
                        .age(transaction.getStudent().getAge())
                        .email(transaction.getStudent().getEmail())
                        .rollNo(transaction.getStudent().getRollNo())
                        .phone_Number(transaction.getStudent().getPhone_Number())
                        .bookName(transaction.getBook().getName())
                        .fine(transaction.getFine())
                        .build(); 
    }
}
