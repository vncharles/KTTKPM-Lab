package com.kttkpm.Course.repository;

import com.kttkpm.Course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursseRepository extends JpaRepository<Course, Long> {
}
