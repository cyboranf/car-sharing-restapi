package com.example.carental.repository;

import com.example.carental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByFirstName(String userName);

    boolean existsByFirstName(String firstName);
    boolean existsByLastName(String lastName);

    boolean existsByEmail(String email);
}
