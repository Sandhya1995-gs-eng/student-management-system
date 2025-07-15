package com.Sandhya.studentapi.controller;

import com.Sandhya.studentapi.dto.TeacherRequestDTO;
import com.Sandhya.studentapi.entity.Teacher;
import com.Sandhya.studentapi.service.TeacherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping

public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("/teachers")
    public ResponseEntity<String> addTeacher(@RequestBody TeacherRequestDTO teacher) {
        try {
            Teacher newteacher = teacherService.addTeacher(teacher);
            ObjectMapper mapper = new ObjectMapper();
            String jsonTeacher=mapper.writeValueAsString(newteacher);
            return ResponseEntity.status(HttpStatus.CREATED).body(jsonTeacher);
        }catch(IllegalArgumentException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/teacher/{teachersJobId}")
    public ResponseEntity<String> updateTeacher(@PathVariable int teachersJobId, @RequestBody TeacherRequestDTO teacher) throws JsonProcessingException {
        try {
            Teacher updatedTeacher = teacherService.updateTeacher(teachersJobId, teacher);
            ObjectMapper mapper = new ObjectMapper();
            String jsonUpdatedTeacher = mapper.writeValueAsString(updatedTeacher);
            return ResponseEntity.status(HttpStatus.OK).body(jsonUpdatedTeacher);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }



}
