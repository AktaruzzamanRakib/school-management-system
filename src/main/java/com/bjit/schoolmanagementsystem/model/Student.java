package com.bjit.schoolmanagementsystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Parent;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// Student.java
@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Student ID is required")
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{6}$", message = "Student ID must be 6 digits")
    private String studentId;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address must be less than 200 characters")
    private String address;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String contactNumber;

    @NotNull(message = "Admission date is required")
    @PastOrPresent(message = "Admission date must be in the past or present")
    private LocalDate admissionDate;


}
