package org.dmitry2025.courseservice.responses;

import org.dmitry2025.courseservice.entities.ENROLLMENT_TYPE;

public record CourseResponse (
    String courseName,
    String courseDescription,
    ENROLLMENT_TYPE enrollmentType,
    Boolean owner
){}
