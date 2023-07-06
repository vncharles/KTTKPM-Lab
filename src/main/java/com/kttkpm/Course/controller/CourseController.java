package com.kttkpm.Course.controller;

import com.kttkpm.Course.entity.Course;
import com.kttkpm.Course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable("id")Long id) {
        return ResponseEntity.ok(courseService.getDetail(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Course course) {
        courseService.create(course);

        return ResponseEntity.ok("Create course is success");
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
