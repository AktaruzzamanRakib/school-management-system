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

    /**
     * POST /api/grades : Create a new grade.
     */
    @PostMapping
    public ResponseEntity<GradeDTO> createGrade(
            @RequestBody @Valid GradeCreateDTO createDTO) {
        GradeDTO dto = gradeService.createGrade(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    /**
     * GET /api/grades/{id} : Retrieve a grade by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable Long id) {
        GradeDTO dto = gradeService.getGradeById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * GET /api/grades : Retrieve all grades, optionally filtering by studentId or courseId.
     */
    @GetMapping
    public ResponseEntity<List<GradeDTO>> getAllGrades(
            @RequestParam(value = "studentId", required = false) Long studentId,
            @RequestParam(value = "courseId",  required = false) Long courseId) {

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

    /**
     * PUT /api/grades/{id} : Update an existing grade.
     */
    @PutMapping("/{id}")
    public ResponseEntity<GradeDTO> updateGrade(
            @PathVariable Long id,
            @RequestBody @Valid GradeUpdateDTO updateDTO) {

        GradeDTO updated = gradeService.updateGrade(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/grades/{id} : Delete a grade by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
