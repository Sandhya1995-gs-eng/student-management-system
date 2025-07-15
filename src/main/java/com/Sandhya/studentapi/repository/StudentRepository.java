package com.Sandhya.studentapi.repository;

import com.Sandhya.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {


      Optional<Student> findByStudentName(String name);


}
