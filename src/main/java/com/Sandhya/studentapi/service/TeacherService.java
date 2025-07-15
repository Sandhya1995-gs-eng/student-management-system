package com.Sandhya.studentapi.service;

import com.Sandhya.studentapi.dto.TeacherRequestDTO;
import com.Sandhya.studentapi.entity.Course;
import com.Sandhya.studentapi.entity.Teacher;
import com.Sandhya.studentapi.repository.CourseRepository;
import com.Sandhya.studentapi.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    public Teacher addTeacher(TeacherRequestDTO teacher) {
        Teacher newTeacher = new Teacher();
        newTeacher.setTeacherJobId(teacher.getTeacherJobId());
        newTeacher.setTeacherName(teacher.getTeacherName());
        newTeacher.setSpecifications(teacher.getSpecifications());
        newTeacher.setPhoneNumber(teacher.getPhoneNumber());
        //getting the specification to match for the course
        String specification = newTeacher.getSpecifications();
        teacherRepository.save(newTeacher);
        //finding the course that matches existing course
        Optional<Course> optionalCourse = courseRepository.findByCourseNameIgnoreCase(specification);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            newTeacher.setCourseName(course.getCourseName());
            if (course.getTeacher() == null) {
                course.setTeacher(newTeacher);
                courseRepository.save(course);
            } else {
                throw new IllegalArgumentException("Teacher already assigned to course");
            }


        }
        return newTeacher;

    }

    public Teacher updateTeacher(int id, TeacherRequestDTO teacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (!teacherOptional.isPresent()) {
            throw new IllegalArgumentException("Teacher not found, please enter the correct JobId");
        } else {
            Teacher newTeacher = teacherOptional.get();
            newTeacher.setTeacherJobId(teacher.getTeacherJobId());
            newTeacher.setTeacherName(teacher.getTeacherName());
            newTeacher.setSpecifications(teacher.getSpecifications());
            newTeacher.setPhoneNumber(teacher.getPhoneNumber());
            String specification = newTeacher.getSpecifications();
            teacherRepository.save(newTeacher);
            Optional<Course> optionalCourse = courseRepository.findByCourseNameIgnoreCase(specification);
            if (optionalCourse.isPresent()) {
                Course course = optionalCourse.get();
                newTeacher.setCourseName(course.getCourseName());
                course.setTeacher(newTeacher);
                courseRepository.save(course);

            }
            return newTeacher;
        }

    }

}
