package org.dmitry2025.courseservice.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(
            columnDefinition = "VARCHAR(50)",
            unique = true,
            nullable = false
    )
    private String username;
    
    @Column(
            columnDefinition = "VARCHAR(60)",
            nullable = false
    )
    private String password;
    
    @Column(
            columnDefinition = "VARCHAR(60)",
            nullable = false
    )
    private String email;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCourse> usersCourses;
    
    public Long getId() {
        return id;
    }
}
