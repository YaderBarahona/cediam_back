package com.app.cediamback.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cediamback.model.Admin;
import com.app.cediamback.model.PasswordResetToken;
import com.app.cediamback.repository.AdminRepository;
import com.app.cediamback.repository.PasswordResetTokenRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class ResetPasswordController {
    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /* 
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> requestBody) {
    String token = requestBody.get("token");
    String newPassword = requestBody.get("newPassword");

    Optional<PasswordResetToken> resetTokenOptional = tokenRepository.findByToken(token);

    if (resetTokenOptional.isPresent() && resetTokenOptional.get().getExpirationDate().isAfter(LocalDateTime.now())) {
        PasswordResetToken resetToken = resetTokenOptional.get();
        Optional<Admin> adminOptional = adminRepository.findByEmail(resetToken.getEmail());

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setPassword(passwordEncoder.encode(newPassword));
            adminRepository.save(admin);
            tokenRepository.delete(resetToken);
            return ResponseEntity.ok("Contraseña actualizada con éxito.");
        }
    }

    return ResponseEntity.status(400).body("Token inválido o expirado.");
 } */

 @PostMapping("/reset-password")
public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> requestBody) {
    String token = requestBody.get("token");
    String newPassword = requestBody.get("newPassword");

    Optional<PasswordResetToken> resetTokenOptional = tokenRepository.findByToken(token);

    if (resetTokenOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido.");
    }

    PasswordResetToken resetToken = resetTokenOptional.get();

    // Verifica si el token ha expirado
    if (resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El token ha expirado.");
    }

    // Buscar el administrador asociado al token
    Optional<Admin> adminOptional = adminRepository.findByEmail(resetToken.getEmail());

    if (adminOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró un usuario asociado al token.");
    }

    Admin admin = adminOptional.get();
    admin.setPassword(passwordEncoder.encode(newPassword));
    adminRepository.save(admin);

    // Elimina el token para evitar su reutilización
    tokenRepository.delete(resetToken);

    return ResponseEntity.ok("Contraseña actualizada con éxito.");
}


}
