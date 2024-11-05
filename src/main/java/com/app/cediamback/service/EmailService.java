package com.app.cediamback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String token) {
        try {
            String resetUrl = "http://localhost:5173/reset-password?token=" + token; // URL del frontend para restablecer la contraseña
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Restablecimiento de Contraseña");
            message.setText("Para restablecer tu contraseña, haz clic en el siguiente enlace:\n" + resetUrl);
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo de restablecimiento de contraseña: " + e.getMessage());
        }
    }
} 

