package com.kttkpm.Course.controller;

import com.kttkpm.Course.entity.Course;
import com.kttkpm.Course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.slf4j.Logger.*;

@RestController
@RequestMapping("/api/course")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Cacheable("courses")
    @GetMapping("")
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @Cacheable(value = "course", key = "#courseId")
    @GetMapping("/{courseId}")
    public Course getDetail(@PathVariable("courseId")Long courseId) {
        log.info("Getting user with ID {}.", courseId);
        return courseService.getDetail(courseId);
    }

    @PostMapping("/create")
    public Course create(@RequestBody Course course) {
        Course courseNew = courseService.create(course);
        System.out.println("GET ID CREATE: " + courseNew.getId());
        return courseNew;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id,
                                    @RequestBody Course course) {
        courseService.update(id, course);

        return ResponseEntity.ok("Update course is success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        courseService.delete(id);

        return ResponseEntity.ok("Delete course is success");
    }
}
