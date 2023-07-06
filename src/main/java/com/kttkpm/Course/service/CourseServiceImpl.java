package com.kttkpm.Course.service;

import com.kttkpm.Course.entity.Course;
import com.kttkpm.Course.repository.CoursseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CoursseRepository coursseRepository;

    @Override
    public List<Course> getAll() {
        return coursseRepository.findAll();
    }

    @Override
    public Course getDetail(Long id) {
        Course course = coursseRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Course is not exist!");
        });

        return course;
    }

    @Override
    public void create(Course course) {
        coursseRepository.save(course);
    }

    @Override
    public void update(Long id, Course courseUpdate) {
        Course course = coursseRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Course is not exist!");
        });

        if(courseUpdate.getTitle()!=null) {
            course.setTitle(courseUpdate.getTitle());
        }
        if(courseUpdate.getDescription()!=null) {
            course.setDescription(courseUpdate.getDescription());
        }
        if(courseUpdate.getPriceSale()!=null) {
            course.setPriceSale(courseUpdate.getPriceSale());
        }
        if(courseUpdate.getListPrice()!=null) {
            course.setListPrice(courseUpdate.getListPrice());
        }
        if(courseUpdate.getTeacher()!=null) {
            course.setTeacher(courseUpdate.getTeacher());
        }

        coursseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        Course course = coursseRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Course is not exist!");
        });

        coursseRepository.deleteById(id);
    }
}
