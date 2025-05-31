package com.bjit.schoolmanagementsystem.mapper;

import com.bjit.schoolmanagementsystem.dto.StudentRequest;
import com.bjit.schoolmanagementsystem.dto.StudentResponse;
import com.bjit.schoolmanagementsystem.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parents", ignore = true)
    Student toEntity(StudentRequest studentRequest);

    @Mapping(source = "parents", target = "parents")
    StudentResponse toResponse(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "studentId", ignore = true)
    @Mapping(target = "parents", ignore = true)
    void updateEntity(StudentRequest studentRequest, @MappingTarget Student student);
}


