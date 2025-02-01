package org.dmitry2025.courseservice.controllers;

import org.dmitry2025.courseservice.requests.ChangeEnrollmentRequest;
import org.dmitry2025.courseservice.requests.CourseRequest;
import org.dmitry2025.courseservice.responses.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    
    @GetMapping("/user-courses")
    ResponseEntity<List<CourseResponse>> getUserCourses()
    {
        return ResponseEntity.ok(new LinkedList<CourseResponse>());
    }
    
    @GetMapping("/enroll")
    ResponseEntity<String> enrollOnCourse(
            @RequestParam("course-name") String name
    )
    {
        return ResponseEntity.ok("SUCCESSFULLY ENROLLED");
    }
    
    @GetMapping("/discard-e")
    ResponseEntity<String> discardEnrollment(
            @RequestParam("course-name") String name
    )
    {
        return ResponseEntity.ok("SUCCESSFULLY ENROLLED");
    }
    
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @PostMapping("/create")
    ResponseEntity<String> createCourse(
            @RequestBody CourseRequest request
    )
    {
        return ResponseEntity.ok("SUCCESSFULLY ENROLLED");
    }
    
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @PutMapping("/update")
    ResponseEntity<String> updateCourse(
            @RequestBody CourseRequest request
    )
    {
        return ResponseEntity.ok("SUCCESSFULLY ENROLLED");
    }
    
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @PatchMapping("/change-etype")
    ResponseEntity<String> changeEnrollmentType(
            @RequestBody ChangeEnrollmentRequest request
    )
    {
        return ResponseEntity.ok("SUCCESSFULLY ENROLLED");
    }
    
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @DeleteMapping("/delete")
    ResponseEntity<String> deleteCourse(
            @RequestParam("course-name") String name
    )
    {
        return ResponseEntity.ok("SUCCESSFULLY ENROLLED");
    }
}
