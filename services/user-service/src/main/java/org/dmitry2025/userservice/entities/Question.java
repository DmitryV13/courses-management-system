package org.dmitry2025.userservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String question;
    
    @ManyToOne
    @JoinColumn(
            name = "quiz_name",
            foreignKey = @ForeignKey(name = "fk_quiz_name"),
            referencedColumnName = "name"
    )
    private Quiz quiz;
}
