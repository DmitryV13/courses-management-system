package org.dmitry2025.courseservice.other;

import org.dmitry2025.courseservice.entities.Course;
import org.dmitry2025.courseservice.requests.CourseRequest;

public class Mapper {
    public static Course mapToCourse(CourseRequest request){
        Course course = new Course();
        course.setName(request.name());
        course.setDescription(request.description());
        course.setEnrollmentType(request.enrollmentType());
        return course;
    }
}
