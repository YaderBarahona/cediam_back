package com.app.cediamback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.cediamback.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}

