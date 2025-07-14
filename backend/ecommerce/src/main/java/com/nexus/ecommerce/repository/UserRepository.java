package com.nexus.ecommerce.repository;

import com.nexus.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA will automatically implement this method based on its name
    Optional<User> findByUsername(String username);
}
