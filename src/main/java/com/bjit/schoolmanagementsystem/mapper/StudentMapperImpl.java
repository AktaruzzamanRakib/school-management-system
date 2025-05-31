package com.bjit.schoolmanagementsystem.mapper;

import com.bjit.schoolmanagementsystem.dto.StudentRequest;
import com.bjit.schoolmanagementsystem.dto.StudentResponse;
import com.bjit.schoolmanagementsystem.model.Student;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student toEntity(StudentRequest studentRequest) {
        return Student.builder()
                .name(studentRequest.name())
                .studentId(studentRequest.studentId())
                .address(studentRequest.address())
                .contactNumber(studentRequest.contactNumber())
                .admissionDate(studentRequest.admissionDate())
                //.guardians(new HashSet<>())
                .build();
    }

    @Override
    public StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getStudentId(),
                student.getAddress(),
                student.getContactNumber(),
                student.getAdmissionDate()
                //Collections.emptyList() // Or map guardians if needed
        );
    }

    @Override
    public void updateEntity(StudentRequest studentRequest, Student student) {
        student.setName(studentRequest.name());
        student.setAddress(studentRequest.address());
        student.setContactNumber(studentRequest.contactNumber());
        student.setAdmissionDate(studentRequest.admissionDate());
    }
}
