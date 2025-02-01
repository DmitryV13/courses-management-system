package org.dmitry2025.userservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users_courses")
public class UserCourse {
    @EmbeddedId
    private UserCourseK id;
    
    @ManyToOne
    @MapsId("userId") // uses userId from compose key
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;
    
    @ManyToOne
    @MapsId("courseName") // uses courseName from compose key
    @JoinColumn(name = "course_name", foreignKey = @ForeignKey(name = "fk_course_name"))
    private Course course;
    
    @Column(nullable = false)
    private Boolean owner;
}
