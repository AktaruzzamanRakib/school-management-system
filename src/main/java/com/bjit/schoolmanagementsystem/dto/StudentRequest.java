package com.bjit.schoolmanagementsystem.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record StudentRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Pattern(regexp = "^[0-9]{6}$") String studentId,
        @NotBlank @Size(max = 200) String address,
        @NotBlank @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$") String contactNumber,
        @NotNull @PastOrPresent LocalDate admissionDate
) {}
