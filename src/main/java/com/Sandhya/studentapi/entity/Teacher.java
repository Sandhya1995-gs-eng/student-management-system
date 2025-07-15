package com.Sandhya.studentapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Teacher {
    @Id
    @Column(name = "teacher_job_id")
    private int teacherJobId;
    private String teacherName;
    private String phoneNumber;
    private String courseName;
    private String specifications;
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
}

