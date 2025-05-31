package com.bjit.schoolmanagementsystem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GradeNotFoundException extends RuntimeException {
    private final Long gradeId;

    public GradeNotFoundException(Long gradeId) {
        super("Grade not found with ID: " + gradeId);
        this.gradeId = gradeId;
    }
}
