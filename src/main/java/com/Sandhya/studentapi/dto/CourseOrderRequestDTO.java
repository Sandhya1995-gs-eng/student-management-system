package com.Sandhya.studentapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseOrderRequestDTO {
    private String courseName;
    private float amount;
    private String studentName;
    private String studentEmail;

}
