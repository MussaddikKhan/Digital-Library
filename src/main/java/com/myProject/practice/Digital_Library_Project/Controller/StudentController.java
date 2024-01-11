package com.myProject.practice.Digital_Library_Project.Controller;

import com.myProject.practice.Digital_Library_Project.Entity.Student;
import com.myProject.practice.Digital_Library_Project.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Student>>findById(@PathVariable Integer id){
        return studentService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Student>> deleteStudent(@PathVariable Integer id) throws Exception {
        return studentService.deleteStudent(id);
    }
}

