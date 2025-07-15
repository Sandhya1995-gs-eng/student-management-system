package com.Sandhya.studentapi.controller;

import com.Sandhya.studentapi.dto.CourseDTO;
import com.Sandhya.studentapi.entity.Course;
import com.Sandhya.studentapi.service.CourseService;
import com.Sandhya.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;
    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDTO course) {
        Course saved=courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


}
