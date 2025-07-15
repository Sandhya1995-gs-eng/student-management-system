package com.Sandhya.studentapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestDTO {
    private int teacherJobId;
    private String teacherName;
    private String phoneNumber;
    private String specifications;
}
