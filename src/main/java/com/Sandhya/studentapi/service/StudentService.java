package com.Sandhya.studentapi.service;

import com.Sandhya.studentapi.dto.CourseDTO;
import com.Sandhya.studentapi.dto.StudentRequestDTO;
import com.Sandhya.studentapi.entity.Course;
import com.Sandhya.studentapi.entity.Student;
import com.Sandhya.studentapi.repository.CourseRepository;
import com.Sandhya.studentapi.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private static final int BIOLOGY_COURSE_ID = 1;
    private static final int COMPUTERSCIENCE_COURSE_ID = 2;
    private static final int STATICS_COURSE_ID = 3;
    private static final int ELECTRONICS_COURSE_ID = 4;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public Student createStudent(StudentRequestDTO student) {

        Optional<Course> course = courseRepository.findById(student.getCourseId());
        if(!course.isPresent()) {
            throw new EntityNotFoundException("Course not found with ID " + student.getCourseId());
        }
        Student newStudent = new Student();
        newStudent.setStudentName(student.getName());
        newStudent.setStudentEmail(student.getEmail());
        newStudent.setMathMarks(student.getMathMarks());
        newStudent.setScienceMarks(student.getScienceMarks());
        newStudent.setSocialMarks(student.getSocialMarks());

        float total = newStudent.getMathMarks() + newStudent.getScienceMarks() + newStudent.getSocialMarks();
        newStudent.setTotal(total);
        newStudent.setPercentage(total / 3.0f);
        float percentage = newStudent.getPercentage();
        int courseId = student.getCourseId();
        if (courseId == ELECTRONICS_COURSE_ID && percentage < 75) {
            throw new IllegalArgumentException("Your percentage is "+percentage+ " ,Student percentage must be greater than 75 to opt for Electrics, so choose other course");
        }
        if (courseId == STATICS_COURSE_ID && percentage < 80) {
            throw new IllegalArgumentException("Your percentage is " +percentage+ " ,Student percentage must be greater than 85 to opt for Statics, so choose other course");
        }
        if (courseId == COMPUTERSCIENCE_COURSE_ID && percentage < 85) {
            throw new IllegalArgumentException("Your percentage is " +percentage+ " ,Student percentage must be greater than 85 to opt for Computerscience, so choose other course");

        }
        if (courseId == BIOLOGY_COURSE_ID && percentage < 95) {
            IllegalArgumentException ex=new IllegalArgumentException("Your percentage is " +percentage+ " ,Student percentage must be greater than 95 to opt for Biology, so choose other course");
            throw ex;
        }
        newStudent.setCourse(course.get());
        return studentRepository.save(newStudent);

    }

}
