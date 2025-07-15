package com.Sandhya.studentapi.service;

import com.Sandhya.studentapi.entity.Course;
import com.Sandhya.studentapi.exception.CourseNotFoundException;
import com.Sandhya.studentapi.repository.CourseRepository;
import com.Sandhya.studentapi.repository.StudentRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseAssignment {
    private static final int BIOLOGY_COURSE_ID = 1;
    private static final int COMPUTERSCIENCE_COURSE_ID = 2;
    private static final int STATICS_COURSE_ID = 3;
    private static final int ELECTRONICS_COURSE_ID = 4;
    @Autowired
    public StudentRepository studentRepository;
    @Autowired
    public CourseRepository courseRepository;

    public Course assignCourseBasedOnPercentage(int courseId, float percentage) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            if (courseId == ELECTRONICS_COURSE_ID && percentage < 75) {
                throw new IllegalArgumentException("Your percentage is " + percentage + " ,Student percentage must be greater than 75 to opt for Electrics, so choose other course");
            }
            if (courseId == STATICS_COURSE_ID && percentage < 80) {
                throw new IllegalArgumentException("Your percentage is " + percentage + " ,Student percentage must be greater than 85 to opt for Statics, so choose other course");
            }
            if (courseId == COMPUTERSCIENCE_COURSE_ID && percentage < 85) {
                throw new IllegalArgumentException("Your percentage is " + percentage + " ,Student percentage must be greater than 85 to opt for Computerscience, so choose other course");

            }
            if (courseId == BIOLOGY_COURSE_ID && percentage < 95) {
                IllegalArgumentException ex = new IllegalArgumentException("Your percentage is " + percentage + " ,Student percentage must be greater than 95 to opt for Biology, so choose other course");
                throw ex;

            }

            return course;
        } else {
            throw new CourseNotFoundException("Course with" +courseId+ "not found");
        }


    }
}
