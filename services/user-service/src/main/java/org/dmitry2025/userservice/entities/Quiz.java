package org.dmitry2025.userservice.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    private String name;
    
    @OneToMany(mappedBy = "quiz")
    private Set<Question> questions;
    
    @ManyToOne
    @JoinColumn(
            name = "course_name",
            foreignKey = @ForeignKey(name = "fk_course_name"),
            referencedColumnName = "name"
    )
    private Course course;
}
