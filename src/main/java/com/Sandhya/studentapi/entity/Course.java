package com.Sandhya.studentapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;
    @ManyToOne
    @JoinColumn(name="teacher_Job_id")
    private Teacher teacher;



}

