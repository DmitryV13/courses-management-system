package org.dmitry2025.userservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserCourseK implements Serializable {
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "course_name")
    private String courseName;
    
    // Конструкторы
    public UserCourseK() {}
    
    public UserCourseK(Long userId, String courseName) {
        this.userId = userId;
        this.courseName = courseName;
    }
}
