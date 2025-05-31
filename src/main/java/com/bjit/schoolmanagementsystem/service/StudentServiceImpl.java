package com.bjit.schoolmanagementsystem.service;

import com.bjit.schoolmanagementsystem.dto.StudentRequest;
import com.bjit.schoolmanagementsystem.dto.StudentResponse;
import com.bjit.schoolmanagementsystem.exception.DuplicateStudentIdException;
import com.bjit.schoolmanagementsystem.exception.IllegalOperationException;
import com.bjit.schoolmanagementsystem.exception.StudentNotFoundException;
import com.bjit.schoolmanagementsystem.mapper.StudentMapper;
import com.bjit.schoolmanagementsystem.model.Student;
import com.bjit.schoolmanagementsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        log.info("Attempting to create new student with ID: {}", studentRequest.studentId());

        if (studentRepository.existsByStudentId(studentRequest.studentId())) {
            log.warn("Student creation failed - ID already exists: {}", studentRequest.studentId());
            throw new DuplicateStudentIdException(studentRequest.studentId());
        }

        Student student = studentMapper.toEntity(studentRequest);
        Student savedStudent = studentRepository.save(student);

        log.info("Successfully created student with ID: {}", savedStudent.getStudentId());
        return studentMapper.toResponse(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toResponse)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse getStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId)
                .map(studentMapper::toResponse)
                .orElseThrow(() -> new StudentNotFoundException(Long.parseLong(studentId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        if (!existingStudent.getStudentId().equals(studentRequest.studentId())) {
            throw new IllegalOperationException("Student ID cannot be changed");
        }

        studentMapper.updateEntity(studentRequest, existingStudent);
        Student updatedStudent = studentRepository.save(existingStudent);

        log.info("Updated student with ID: {}", updatedStudent.getStudentId());
        return studentMapper.toResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
        log.info("Deleted student with ID: {}", id);
    }
}
