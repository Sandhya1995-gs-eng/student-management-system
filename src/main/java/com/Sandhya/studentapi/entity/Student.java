package com.Sandhya.studentapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String studentName;
    private String studentEmail;
    private int mathMarks;
    private int scienceMarks;
    private int socialMarks;
    private float total;
    private float percentage;
    @ManyToOne
    @JoinColumn(name="courseId")
    private Course course;
}
