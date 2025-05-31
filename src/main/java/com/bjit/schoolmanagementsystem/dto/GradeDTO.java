package com.bjit.schoolmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response payload for grade details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeDTO {
    private Long id;
    private String studentId;
    private Long courseId;
    private String letter;
    private Double score;
    private LocalDateTime dateAssigned;
}
