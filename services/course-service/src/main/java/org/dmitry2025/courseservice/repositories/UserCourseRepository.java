package org.dmitry2025.courseservice.repositories;

import org.dmitry2025.courseservice.entities.UserCourse;
import org.dmitry2025.courseservice.entities.UserCourseK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourse, UserCourseK> {
}
