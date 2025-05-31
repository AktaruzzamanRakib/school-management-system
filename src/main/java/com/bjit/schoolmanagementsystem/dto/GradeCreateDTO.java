package com.bjit.schoolmanagementsystem.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GradeCreateDTO {
    @NotNull
    private String studentId;

    @NotNull
    private Long courseId;

    @NotBlank
    private String letter;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double score;
}
