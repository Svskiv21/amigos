package com.springframework.amigos.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // data access layer
public interface StudentRepository extends JpaRepository<Student, Long> {
}
