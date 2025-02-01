package org.dmitry2025.courseservice.requests;

import org.dmitry2025.courseservice.entities.ENROLLMENT_TYPE;

public record ChangeEnrollmentRequest (
        String name,
        ENROLLMENT_TYPE enrollmentType
){}
