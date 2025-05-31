package com.bjit.schoolmanagementsystem.mapper;

import com.bjit.schoolmanagementsystem.dto.GradeDTO;
import com.bjit.schoolmanagementsystem.dto.GradeUpdateDTO;
import com.bjit.schoolmanagementsystem.entity.Grade;

import java.time.LocalDateTime;


public class GradeMapper {

    public static GradeDTO toDTO(Grade entity) {
        return GradeDTO.builder()
                .id(entity.getId())
                .studentId(entity.getStudentId())
                .courseId(entity.getCourseId())
                .letter(entity.getLetter())
                .score(entity.getScore())
                .dateAssigned(entity.getDateAssigned())
                .build();
    }

    public static void applyUpdate(Grade entity, GradeUpdateDTO dto) {
        entity.setLetter(dto.getLetter());
        entity.setScore(dto.getScore());
        entity.setDateAssigned(LocalDateTime.now());
    }
}
