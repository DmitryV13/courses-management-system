package org.dmitry2025.courseservice.controllers;

import jakarta.transaction.Transactional;
import org.dmitry2025.courseservice.requests.ChangeEnrollmentRequest;
import org.dmitry2025.courseservice.requests.CourseRequest;
import org.dmitry2025.courseservice.responses.CourseResponse;
import org.dmitry2025.courseservice.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    
    private final CourseService courseService;
    
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    @GetMapping("/user-courses")
    ResponseEntity<List<CourseResponse>> getUserCourses()
    {
        return ResponseEntity.ok(courseService.getUserCourses());
    }
//
//    @GetMapping("/enroll")
//    ResponseEntity<String> enrollOnCourse(
//            @RequestParam("course-name") String name
//    )
//    {
//        return ResponseEntity.ok(courseService.enrollOnCourse(name));
//    }
//
//    @GetMapping("/discard-e")
//    ResponseEntity<String> discardEnrollment(
//            @RequestParam("course-name") String name
//    )
//    {
//        return ResponseEntity.ok(courseService.discardEnrollment(name));
//    }
//
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @PostMapping("/create")
    ResponseEntity<String> createCourse(
            @RequestBody CourseRequest request
    )
    {
        return ResponseEntity.ok(courseService.createCourse(request));
    }

    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @PutMapping("/update")
    ResponseEntity<String> updateCourse(
            @RequestBody CourseRequest request
    )
    {
        return ResponseEntity.ok(courseService.updateCourse(request));
    }

    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @PatchMapping("/change-etype")
    ResponseEntity<String> changeEnrollmentType(
            @RequestBody ChangeEnrollmentRequest request
    )
    {
        return ResponseEntity.ok(courseService.changeEnrollmentType(request));
    }

    @Transactional
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @DeleteMapping("/delete")
    ResponseEntity<String> deleteCourse(
            @RequestParam("course-name") String name
    )
    {
        return ResponseEntity.ok(courseService.deleteCourse(name));
    }
}
