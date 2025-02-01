package org.dmitry2025.courseservice.services;

import org.dmitry2025.courseservice.entities.Course;
import org.dmitry2025.courseservice.entities.User;
import org.dmitry2025.courseservice.entities.UserCourse;
import org.dmitry2025.courseservice.entities.UserCourseK;
import org.dmitry2025.courseservice.other.Mapper;
import org.dmitry2025.courseservice.repositories.CourseRepository;
import org.dmitry2025.courseservice.repositories.UserCourseRepository;
import org.dmitry2025.courseservice.repositories.UserRepository;
import org.dmitry2025.courseservice.requests.ChangeEnrollmentRequest;
import org.dmitry2025.courseservice.requests.CourseRequest;
import org.dmitry2025.courseservice.responses.CourseResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;
    
    public CourseService(
            UserRepository userRepository,
            CourseRepository courseRepository,
            UserCourseRepository userCourseRepository
    ) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.userCourseRepository = userCourseRepository;
    }
    
    public String createCourse(CourseRequest request) {
        var username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Teacher not found!"));
        
        var course = Mapper.mapToCourse(request);
        courseRepository.save(course);
        
        UserCourse userCourse = new UserCourse();
        userCourse.setId(new UserCourseK(user.getId(), course.getName()));
        userCourse.setUser(user);
        userCourse.setCourse(course);
        userCourse.setOwner(true);
        userCourseRepository.save(userCourse);
        
        return "Course created successfully!";
    }
    
    public List<CourseResponse> getUserCourses() {
        var username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        var user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found!"));
        
        List<CourseResponse> courseResponses = new ArrayList<>();
        var userCourses = userCourseRepository.findByUserId(user.getId());
        for(UserCourse connector: userCourses){
            var course = courseRepository.findByName(connector.getId().getCourseName())
                            .orElseThrow(()-> new RuntimeException("Course not found!"));
            courseResponses.add(new CourseResponse(
                    course.getName(),
                    course.getDescription(),
                    course.getEnrollmentType(),
                    connector.getOwner())
            );
        }
        return courseResponses;
    }
    
    public String updateCourse(CourseRequest request) {
        var course = confirmOwner(request.name())
                .orElseThrow(()-> new RuntimeException("You are not the owner of this course!"));
        
        course.setDescription(request.description());
        course.setEnrollmentType(request.enrollmentType());
        courseRepository.save(course);
        return "Course updated successfully!";
    }
    
    public String changeEnrollmentType(ChangeEnrollmentRequest request) {
        var course = confirmOwner(request.name())
                .orElseThrow(()-> new RuntimeException("You are not the owner of this course!"));
        
        course.setEnrollmentType(request.enrollmentType());
        courseRepository.save(course);
        return "Course updated successfully!";
    }
    
    public String deleteCourse(String name) {
        var course = confirmOwner(name)
                .orElseThrow(()-> new RuntimeException("You are not the owner of this course!"));
        courseRepository.delete(course);
        return "Course deleted successfully!";
    }
    
    //returns course only if current user is the owner
    private Optional<Course> confirmOwner(String courseName){
        var username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        var user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found!"));
        var course = courseRepository.findByName(courseName)
                .orElseThrow(()-> new RuntimeException("Course not found!"));
        var connector = userCourseRepository.findByUserIdAndCourseName(user.getId(), courseName)
                .orElseThrow(()-> new RuntimeException("Course not found!"));
        return connector.getOwner() ? Optional.of(course) : Optional.empty();
    }
}
