package org.dmitry2025.userservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String text;
    
    @ManyToOne
    @JoinColumn(
            name = "course_name",
            foreignKey = @ForeignKey(name = "fk_course_name"),
            referencedColumnName = "name"
    )
    private Course course;
}
