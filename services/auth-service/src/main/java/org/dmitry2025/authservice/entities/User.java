package org.dmitry2025.authservice.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedEntityGraph(
        name = "u-authorities",
        attributeNodes = {
                @NamedAttributeNode("authorities")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    
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
    private Set<Authority> authorities = new HashSet<>();;
    
    public User() {
    }
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public Set<Authority> getAuthorities() {
        return authorities;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
