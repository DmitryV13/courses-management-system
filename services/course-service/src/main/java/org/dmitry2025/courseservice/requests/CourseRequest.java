package org.dmitry2025.courseservice.requests;

import org.dmitry2025.courseservice.entities.ENROLLMENT_TYPE;

public record CourseRequest(
        String name,
        String description,
        ENROLLMENT_TYPE enrollmentType
){}
