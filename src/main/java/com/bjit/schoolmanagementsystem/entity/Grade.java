package com.bjit.schoolmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "grades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 5)
    private String letter;

    @Column
    private Double score;

    @Column(name = "date_assigned", nullable = false)
    private LocalDateTime dateAssigned;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @PrePersist
    public void onPrePersist() {
        if (dateAssigned == null) {
            dateAssigned = LocalDateTime.now();
        }
    }
}
