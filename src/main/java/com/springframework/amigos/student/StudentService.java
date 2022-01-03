package com.springframework.amigos.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service                      // this annotation makes this class a bean, class may be instantiated from now
public class StudentService { // Business Layer

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){ // jak damy liste jak tutaj to zwróci nam jsona
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        // studentRepository.findById(studentId);
        boolean exists = studentRepository.existsById(studentId);
        if (exists){
            studentRepository.deleteById(studentId);
        } else {
            throw new IllegalStateException("student with id " + studentId + " doesn't exist in the base.");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("Student with id" + studentId + " doesn't exist in the base.")
        );
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }

//    @Transactional
//    public void updateStudent(Student student, String name, String email){
//        Optional<Student> studentOptional = studentRepository.findById(student.getId());
//        if (studentOptional.isPresent()){
//            student.setName(name);
//            student.setEmail(email);
//        } else {
//            throw new IllegalStateException("student with id " + student.getId() + " doesn't exist in the base.");
//        }
//    }
    
}
