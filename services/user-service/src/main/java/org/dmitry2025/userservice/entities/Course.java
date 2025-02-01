package org.dmitry2025.userservice.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    private String name;
    
    @Column(columnDefinition = "VARCHAR(255)")
    private String description;
    
    @Column(nullable = false)
    private ENROLLMENT_TYPE enrollment_type;
    
    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCourse> usersCourses;
    
    @OneToMany(mappedBy = "course")
    private Set<Quiz> quizzes;
    
    @OneToMany(mappedBy = "course")
    private Set<Review> reviews;
    
    @OneToMany(mappedBy = "course")
    private Set<Material> materials;
}
