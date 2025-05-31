package com.bjit.schoolmanagementsystem.dto;

import com.bjit.schoolmanagementsystem.entity.Grade;

import java.time.LocalDateTime;


public class GradeMapper {

    public static Grade toEntity(GradeCreateDTO dto) {
        return Grade.builder()
                .studentId(dto.getStudentId())
                .courseId(dto.getCourseId())
                .letter(dto.getLetter())
                .score(dto.getScore())
                .dateAssigned(LocalDateTime.now())
                .build();
    }

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
