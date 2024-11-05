package com.app.cediamback.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cediamback.model.PasswordResetToken;
import com.app.cediamback.repository.AdminRepository;
import com.app.cediamback.repository.PasswordResetTokenRepository;
import com.app.cediamback.service.EmailService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class PasswordResetController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    /*@PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        var adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setEmail(email);
            resetToken.setExpirationDate(LocalDateTime.now().plusHours(1)); // Token válido por 1 hora
            tokenRepository.save(resetToken);
            
            emailService.sendPasswordResetEmail(email, token);
            return ResponseEntity.ok("Correo de restablecimiento de contraseña enviado.");
        } else {
            return ResponseEntity.status(404).body("No se encontró una cuenta con ese correo electrónico.");
        }
    }*/
    /* 
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestBody) {
    String email = requestBody.get("email");
    if (email == null) {
        return ResponseEntity.status(400).body("El campo de correo electrónico es obligatorio.");
    }
    var adminOptional = adminRepository.findByEmail(email);
    if (adminOptional.isPresent()) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setExpirationDate(LocalDateTime.now().plusHours(1)); // Token válido por 1 hora
        tokenRepository.save(resetToken);

        emailService.sendPasswordResetEmail(email, token);
        return ResponseEntity.ok("Correo de restablecimiento de contraseña enviado.");
    } else {
        return ResponseEntity.status(404).body("No se encontró una cuenta con ese correo electrónico.");
    }}
} */

/* 
@PostMapping("/forgot-password")
public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestBody) {
    String email = requestBody.get("email");
    if (email == null) {
        return ResponseEntity.status(400).body("El campo de correo electrónico es obligatorio.");
    }

    var adminOptional = adminRepository.findByEmail(email);
    if (adminOptional.isPresent()) {
        String token = UUID.randomUUID().toString();
        System.out.println("Token generado: " + token); // Log para verificar el token

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setExpirationDate(LocalDateTime.now().plusHours(1)); // Token válido por 1 hora
        
        tokenRepository.save(resetToken);
        System.out.println("Token almacenado en la base de datos: " + resetToken.getToken()); // Log para verificar el token almacenado

        emailService.sendPasswordResetEmail(email, token);
        System.out.println("Token enviado por correo: " + token); // Log para verificar el token enviado

        return ResponseEntity.ok("Correo de restablecimiento de contraseña enviado.");
    } else {
        return ResponseEntity.status(404).body("No se encontró una cuenta con ese correo electrónico.");
    }
}
} */




@PostMapping("/forgot-password")
public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestBody) {
    String email = requestBody.get("email");
    if (email == null) {
        return ResponseEntity.status(400).body("El campo de correo electrónico es obligatorio.");
    }

    var adminOptional = adminRepository.findByEmail(email);
    if (adminOptional.isPresent()) {
        String token = UUID.randomUUID().toString();
        System.out.println("Token generado: " + token); // Log para verificar el token generado

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setExpirationDate(LocalDateTime.now().plusHours(1)); // Token válido por 1 hora

        tokenRepository.save(resetToken);
        System.out.println("Token almacenado en la base de datos: " + resetToken.getToken()); // Log para verificar el token almacenado

        emailService.sendPasswordResetEmail(email, token);
        System.out.println("Token enviado por correo: " + token); // Log para verificar el token enviado

        return ResponseEntity.ok("Correo de restablecimiento de contraseña enviado.");
    } else {
        return ResponseEntity.status(404).body("No se encontró una cuenta con ese correo electrónico.");
    }
}
} 


