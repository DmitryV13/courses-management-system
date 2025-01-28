package org.dmitry2025.userservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;


@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    private String name;
    
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}
