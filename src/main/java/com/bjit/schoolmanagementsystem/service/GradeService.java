package com.bjit.schoolmanagementsystem.service;

import com.bjit.schoolmanagementsystem.dto.GradeCreateDTO;
import com.bjit.schoolmanagementsystem.dto.GradeDTO;
import com.bjit.schoolmanagementsystem.dto.GradeUpdateDTO;

import java.util.List;

public interface GradeService {

    GradeDTO createGrade(GradeCreateDTO dto);

    GradeDTO getGradeById(Long id);

    List<GradeDTO> getGradesByStudent(String studentId);

    List<GradeDTO> getGradesByCourse(Long courseId);

    GradeDTO updateGrade(Long id, GradeUpdateDTO dto);

    void deleteGrade(Long id);

    List<GradeDTO> getAllGrades();
}
