package org.dmitry2025.courseservice.repositories;

import org.dmitry2025.courseservice.entities.UserCourse;
import org.dmitry2025.courseservice.entities.UserCourseK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCourseRepository extends JpaRepository<UserCourse, UserCourseK> {
    List<UserCourse> findByUserId(Long id);
}
