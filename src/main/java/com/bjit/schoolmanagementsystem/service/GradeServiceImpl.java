package com.bjit.schoolmanagementsystem.service;


import com.bjit.schoolmanagementsystem.dto.GradeCreateDTO;
import com.bjit.schoolmanagementsystem.dto.GradeDTO;
import com.bjit.schoolmanagementsystem.dto.GradeMapper;
import com.bjit.schoolmanagementsystem.dto.GradeUpdateDTO;
import com.bjit.schoolmanagementsystem.entity.Grade;
import com.bjit.schoolmanagementsystem.exception.GradeNotFoundException;
import com.bjit.schoolmanagementsystem.exception.ValidationException;
import com.bjit.schoolmanagementsystem.repository.GradeRepository;
import com.bjit.schoolmanagementsystem.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Standard service implementation for Grade CRUD.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    @Override
    public GradeDTO createGrade(GradeCreateDTO dto) {

        boolean isStudentExist = studentRepository.existsByStudentId(dto.getStudentId());
        if (!isStudentExist) {
            throw new ValidationException(List.of("Student with ID " + dto.getStudentId() + " does not exist"));
        }
        // 1. Prevent duplicate grade for same student+course
        List<Grade> existing = gradeRepository
                .findByStudentIdAndCourseId(dto.getStudentId(), dto.getCourseId());
        if (!existing.isEmpty()) {
            throw new ValidationException(List.of(
                    "Student " + dto.getStudentId() +
                            " already has a grade for course " + dto.getCourseId()));
        }

        // 2. Build new entity
        Grade entity = Grade.builder()
                .studentId(dto.getStudentId())
                .courseId(dto.getCourseId())
                .letter(dto.getLetter())
                .score(dto.getScore())
                .dateAssigned(LocalDateTime.now())
                .build();

        // 3. Save
        Grade saved = gradeRepository.save(entity);
        return GradeMapper.toDTO(saved);
    }

    @Override
    public GradeDTO getGradeById(Long id) {
        Grade entity = gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException(id));
        return GradeMapper.toDTO(entity);
    }

    @Override
    public List<GradeDTO> getGradesByStudent(String studentId) {
        List<Grade> list = gradeRepository.findByStudentId(studentId);
        return list.stream()
                .map(GradeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<GradeDTO> getGradesByCourse(Long courseId) {
        List<Grade> list = gradeRepository.findByCourseId(courseId);
        return list.stream()
                .map(GradeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GradeDTO updateGrade(Long id, GradeUpdateDTO dto) {
        Grade existing = gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException(id));

        GradeMapper.applyUpdate(existing, dto);

        Grade updated = gradeRepository.save(existing);
        return GradeMapper.toDTO(updated);
    }

    @Override
    public void deleteGrade(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new GradeNotFoundException(id);
        }
        gradeRepository.deleteById(id);
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll()
                .stream()
                .map(GradeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
