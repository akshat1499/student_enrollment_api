package com.example.student_enrollment.repositories;

import com.example.student_enrollment.entities.User;
import com.example.student_enrollment.utillities.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findUserByRoleEquals(UserRole role, Pageable paging);

}
