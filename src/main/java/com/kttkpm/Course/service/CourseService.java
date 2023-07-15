package com.kttkpm.Course.service;

import com.kttkpm.Course.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<Course> getAll();

    Course getDetail(Long id);

    Course create(Course course);

    void update(Long id, Course course);

    void delete(Long id);
}
