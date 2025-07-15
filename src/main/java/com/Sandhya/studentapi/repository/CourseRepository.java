package com.Sandhya.studentapi.repository;

import com.Sandhya.studentapi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByCourseName(String courseName);

    Optional<Course> findByCourseNameIgnoreCase(String specification);
}
