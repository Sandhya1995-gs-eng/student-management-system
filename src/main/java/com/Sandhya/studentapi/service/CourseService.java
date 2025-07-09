package com.Sandhya.studentapi.service;

import com.Sandhya.studentapi.dto.CourseDTO;
import com.Sandhya.studentapi.entity.Course;
import com.Sandhya.studentapi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course addCourse(CourseDTO course) {
        Course newCourse = new Course();
        newCourse.setCourseId(course.getCourseId());
        newCourse.setCourseName(course.getCourseName());
        return courseRepository.save(newCourse);
    }
}
