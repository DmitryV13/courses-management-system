package org.dmitry2025.userservice.entities;

import jakarta.persistence.*;

import java.util.Locale;
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
    
    @Column(columnDefinition = "VARCHAR(60)")
    private String firstName;
    
    @Column(columnDefinition = "VARCHAR(60)")
    private String lastName;
    
    
    @ManyToMany
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    foreignKey = @ForeignKey(name = "fk_user_id"),
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "authority_name",
                    foreignKey = @ForeignKey(name = "fk_authority_name"),
                    referencedColumnName = "name"
            )
    )
    private Set<Authority> authorities;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCourse> usersCourses;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Enrollment enrollment;
}
