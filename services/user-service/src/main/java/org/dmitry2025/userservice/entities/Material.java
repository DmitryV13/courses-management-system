package org.dmitry2025.userservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String topic;
    
    @Column(nullable = false)
    private String text_ref;
    
    @Column(nullable = false)
    private String video_ref;
    
    @ManyToOne
    @JoinColumn(
            name = "course_name",
            foreignKey = @ForeignKey(name = "fk_course_name"),
            referencedColumnName = "name"
    )
    private Course course;
}
