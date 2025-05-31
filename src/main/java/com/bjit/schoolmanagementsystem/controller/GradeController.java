package com.bjit.schoolmanagementsystem.controller;

import com.bjit.schoolmanagementsystem.dto.GradeCreateDTO;
import com.bjit.schoolmanagementsystem.dto.GradeDTO;
import com.bjit.schoolmanagementsystem.dto.GradeUpdateDTO;
import com.bjit.schoolmanagementsystem.service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<GradeDTO> createGrade(
            @RequestBody @Valid GradeCreateDTO createDTO) {
        GradeDTO dto = gradeService.createGrade(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable Long id) {
        GradeDTO dto = gradeService.getGradeById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<GradeDTO>> getAllGrades(
            @RequestParam(value = "studentId", required = false) String studentId,
            @RequestParam(value = "courseId", required = false) Long courseId) {

        List<GradeDTO> result;
        if (studentId != null) {
            result = gradeService.getGradesByStudent(studentId);
        } else if (courseId != null) {
            result = gradeService.getGradesByCourse(courseId);
        } else {
            result = gradeService.getAllGrades();
        }
        return ResponseEntity.ok(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<GradeDTO> updateGrade(
            @PathVariable Long id,
            @RequestBody @Valid GradeUpdateDTO updateDTO) {

        GradeDTO updated = gradeService.updateGrade(id, updateDTO);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
