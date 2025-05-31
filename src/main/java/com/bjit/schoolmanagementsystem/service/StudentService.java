package com.bjit.schoolmanagementsystem.service;

import com.bjit.schoolmanagementsystem.dto.StudentRequest;
import com.bjit.schoolmanagementsystem.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest studentRequest);

    StudentResponse getStudentById(Long id);

    StudentResponse getStudentByStudentId(String studentId);

    List<StudentResponse> getAllStudents();

    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    void deleteStudent(Long id);
}


