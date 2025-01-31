package org.dmitry2025.authservice.repositories;

import org.dmitry2025.authservice.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(value = "u-authorities")
    Optional<User> findByUsername(String username);
}
