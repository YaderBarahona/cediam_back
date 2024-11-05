package com.app.cediamback.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.cediamback.model.Admin;
import com.app.cediamback.service.AdminService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin admin) {
        Admin newAdmin = adminService.registerAdmin(admin);
        return ResponseEntity.ok(newAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        Optional<Admin> authenticatedAdmin = adminService.authenticate(admin.getEmail(), admin.getPassword());
        if (authenticatedAdmin.isPresent()) {
            // Aquí generaríamos un JWT para el usuario autenticado
            return ResponseEntity.ok("JWT_TOKEN_GENERATED");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}

