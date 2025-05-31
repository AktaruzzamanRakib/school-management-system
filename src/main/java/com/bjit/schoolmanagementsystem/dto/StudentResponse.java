package com.bjit.schoolmanagementsystem.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record StudentResponse(
        Long id,
        String name,
        String studentId,
        String address,
        String contactNumber,
        LocalDate admissionDate
        //List<ParentResponse> parents
) {

}
