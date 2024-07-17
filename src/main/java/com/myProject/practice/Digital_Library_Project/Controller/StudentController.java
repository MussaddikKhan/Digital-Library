package com.myProject.practice.Digital_Library_Project.Controller;

import com.myProject.practice.Digital_Library_Project.Dto.*;
import com.myProject.practice.Digital_Library_Project.Entity.Student;
import com.myProject.practice.Digital_Library_Project.Entity.User;
import com.myProject.practice.Digital_Library_Project.Services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ReturnStudentResponse createStudent(@Valid @RequestBody CreateStudentRequest student){
        return studentService.createStudent(student);
    }
    @GetMapping("/find/{rollNo}")
    public  ReturnStudentResponse findStudentByRollNo(@PathVariable String rollNo){
        return studentService.getByRollNumber(rollNo);
    }
    @GetMapping("/get")
    public Student getStudent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User securedUser = (User) authentication.getPrincipal();
        int studentId = securedUser.getStudent().getId();
        return studentService.getById(studentId);
    }

    @GetMapping("/books")
    public List<ReturnBookResponse> findBookByStudent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User securedUser = (User) authentication.getPrincipal();
        String rollNo = securedUser.getStudent().getRollNo();
        return studentService.findBookByStudent(rollNo);
    }
    @GetMapping("/transactions")
    public  List<ReturnTransactionResponse> getAllTransaction(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User securedUser = (User) authentication.getPrincipal();
        String rollNo = securedUser.getStudent().getRollNo();
        return studentService.getAllTransaction(rollNo);
    }
    @PatchMapping("/update")
    public  String updateStudent( @RequestBody UpdateStudentRequest updateStudentRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User securedUser = (User) authentication.getPrincipal();
        String rollNo = securedUser.getStudent().getRollNo();
        return studentService.updateStudent(rollNo , updateStudentRequest);
    }
}

