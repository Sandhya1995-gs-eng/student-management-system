package com.Sandhya.studentapi.dto;

import com.Sandhya.studentapi.entity.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO {

    private String name;
    private String email;
    private int courseId;
    private int mathMarks;
    private int scienceMarks;
    private int socialMarks;
}
