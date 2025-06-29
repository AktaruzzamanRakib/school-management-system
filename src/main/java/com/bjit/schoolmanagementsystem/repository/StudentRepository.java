package com.bjit.schoolmanagementsystem.repository;

import com.bjit.schoolmanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByStudentId(String studentId);
    Optional<Student> findByStudentId(String studentId);
}
