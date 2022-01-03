package com.springframework.amigos.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // this annotation makes this class a bean, class may be instantiated from now
public class StudentService { // Business Layer

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){ // jak damy liste jak tutaj to zwróci nam jsona
        return studentRepository.findAll();
    }
}
