package com.bjit.schoolmanagementsystem.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeUpdateDTO {

    @NotBlank
    private String letter;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double score;
}
