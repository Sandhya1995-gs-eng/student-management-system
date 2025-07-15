package com.Sandhya.studentapi.controller;

import com.Sandhya.studentapi.dto.StudentRequestDTO;
import com.Sandhya.studentapi.entity.Student;
import com.Sandhya.studentapi.service.CourseService;
import com.Sandhya.studentapi.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/students")
    public ResponseEntity<String> createStudent(@RequestBody StudentRequestDTO student) {
        try {
            Student saved = studentService.createStudent(student);
            ObjectMapper mapper = new ObjectMapper();
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.writeValueAsString(saved));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
    @PutMapping("/students/{name}")
    public ResponseEntity<String> updateStudent(@PathVariable String name, @RequestBody StudentRequestDTO student){
        try {
            Student updatedStudent = studentService.updateStudent(name, student);
            ObjectMapper mapper = new ObjectMapper();
            String updatedStudentJson = mapper.writeValueAsString(updatedStudent);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedStudentJson);
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
         catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    }

