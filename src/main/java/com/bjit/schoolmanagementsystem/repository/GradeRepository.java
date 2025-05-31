package com.bjit.schoolmanagementsystem.repository;

import com.bjit.schoolmanagementsystem.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByStudentId(Long studentId);

    List<Grade> findByCourseId(Long courseId);

    @Query("""
            SELECT g
              FROM Grade g
             WHERE g.studentId = :studentId
               AND g.courseId = :courseId
            """)
    List<Grade> findByStudentIdAndCourseId(
            @Param("studentId") Long studentId,
            @Param("courseId") Long courseId);
}
