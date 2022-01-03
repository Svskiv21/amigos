package com.springframework.amigos.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // makes this class to serve endpoints
@RequestMapping(path = "api/v1/students")
public class StudentController { // class will have all resources for our API

    private final StudentService studentService; // reference

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){ // jak damy liste jak tutaj to zwróci nam jsona
        return studentService.getStudents();
    }
}
