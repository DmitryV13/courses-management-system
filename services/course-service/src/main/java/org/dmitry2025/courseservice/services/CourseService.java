package org.dmitry2025.courseservice.services;

import org.dmitry2025.courseservice.entities.Course;
import org.dmitry2025.courseservice.entities.User;
import org.dmitry2025.courseservice.entities.UserCourse;
import org.dmitry2025.courseservice.entities.UserCourseK;
import org.dmitry2025.courseservice.other.Mapper;
import org.dmitry2025.courseservice.repositories.CourseRepository;
import org.dmitry2025.courseservice.repositories.UserCourseRepository;
import org.dmitry2025.courseservice.repositories.UserRepository;
import org.dmitry2025.courseservice.requests.CourseRequest;
import org.dmitry2025.courseservice.responses.CourseResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        User user = userRepository.findByUsername(username)
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
}
