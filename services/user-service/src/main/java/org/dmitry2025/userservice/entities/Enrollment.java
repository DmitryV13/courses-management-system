package org.dmitry2025.userservice.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private ENROLLMENT_TYPE enrollmentType;
    
    @Column(nullable = false)
    private Date enrollmentStartDate;
    
    @Column(nullable = false)
    private Date enrollmentEndDate;
    
    @OneToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "fk_user_id")
    )
    private User user;
}
