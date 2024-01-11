package com.myProject.practice.Digital_Library_Project.Services;

import com.myProject.practice.Digital_Library_Project.Entity.Student;
import com.myProject.practice.Digital_Library_Project.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    public ResponseEntity<Optional<Student>> findById(Integer id){
        Optional<Student> s =  studentRepository.findById(id);
        if(s.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
    }

    public ResponseEntity<Optional<Student>> deleteStudent(Integer id) throws Exception {
        Optional<Student> s = studentRepository.findById(id);
        if (s.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentRepository.deleteById(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
