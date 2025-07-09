package com.Sandhya.studentapi.dto;

import com.Sandhya.studentapi.entity.Course;
import com.Sandhya.studentapi.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentResponseDTO {
    private Student student;
    private String message;
    private List<Course> courseList;

}
