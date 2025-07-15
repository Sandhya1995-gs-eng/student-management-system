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
    @Autowired
    private CourseAssignment courseAssignment;

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
        CourseAssignment courseAssignment = new CourseAssignment();
        courseAssignment.assignCourseBasedOnPercentage(courseId,percentage);
        return newStudent;
    }

    public Student updateStudent(String name, StudentRequestDTO student) {
        Optional<Student> optionalStudent=studentRepository.findByStudentName(name);
        if(optionalStudent.isPresent()) {
            Student updatedStudent=optionalStudent.get();
            updatedStudent.setStudentName(student.getName());
            updatedStudent.setStudentEmail(student.getEmail());
            updatedStudent.setMathMarks(student.getMathMarks());
            updatedStudent.setScienceMarks(student.getScienceMarks());
            updatedStudent.setSocialMarks(student.getSocialMarks());
            float total = updatedStudent.getMathMarks() + updatedStudent.getScienceMarks() + updatedStudent.getSocialMarks();
            updatedStudent.setTotal(total);
            updatedStudent.setPercentage(total / 3.0f);
            int courseId = student.getCourseId();
            courseAssignment.assignCourseBasedOnPercentage(courseId,total);
            studentRepository.save(updatedStudent);
            return updatedStudent;
        }else {
            throw new EntityNotFoundException("Student not found with name " + name);
        }


    }

}
