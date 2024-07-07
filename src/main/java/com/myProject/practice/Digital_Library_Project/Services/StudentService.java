package com.myProject.practice.Digital_Library_Project.Services;

import com.myProject.practice.Digital_Library_Project.Dto.*;
import com.myProject.practice.Digital_Library_Project.Entity.Book;
import com.myProject.practice.Digital_Library_Project.Entity.Student;
import com.myProject.practice.Digital_Library_Project.Entity.Transaction;
import com.myProject.practice.Digital_Library_Project.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public ReturnStudentResponse createStudent(CreateStudentRequest student){
        Student s = student.to();
        studentRepository.save(s);
        return ReturnStudentResponse.From(s);
    }
    public ReturnStudentResponse getByRollNumber(String rollNo){
        Student s =  studentRepository.findByRollNo(rollNo);
        if(s == null) throw new RuntimeException("Student Not Found ");
        return  ReturnStudentResponse.From(s);
    }

    public Student getById(Integer studentId) {
        return studentRepository.findById(studentId).orElse(null); 
    }

    public List<ReturnBookResponse> findBookByStudent(String rollNo) {
        Student student = studentRepository.findByRollNo(rollNo);
        List<Book> bookList   =  null;
        if(student != null){
            bookList = student.getBookList();
        }
//        return bookList.stream().map(ReturnBookResponse::from).collect(Collectors.toList());
          return bookList.stream()
                .map(book -> ReturnBookResponse.from(book))  // Using a lambda expression here
                .collect(Collectors.toList());
    }

    public List<ReturnTransactionResponse> getAllTransaction(String rollNo) {
        return studentRepository.findByRollNo(rollNo)
                .getTransactions().stream()
                .map(txn -> ReturnTransactionResponse.From(txn))
                .collect(Collectors.toList());
    }

    public String updateStudent(String rollNo, UpdateStudentRequest updateStudentRequest) {
        studentRepository.updateStudent(rollNo , updateStudentRequest);
        return "update Successfully !";
    }
}
