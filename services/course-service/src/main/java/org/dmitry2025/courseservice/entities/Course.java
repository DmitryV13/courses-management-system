package org.dmitry2025.courseservice.entities;

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
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCourse> usersCourses;
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public ENROLLMENT_TYPE getEnrollmentType() {
        return enrollment_type;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setEnrollment_type(ENROLLMENT_TYPE enrollment_type) {
        this.enrollment_type = enrollment_type;
    }
}
