package com.padmapg.codefellowship.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    public ApplicationUser findByUsername(String username);
    public Optional<ApplicationUser> findById(Long id);
}
