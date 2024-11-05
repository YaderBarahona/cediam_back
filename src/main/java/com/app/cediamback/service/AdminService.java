package com.app.cediamback.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.cediamback.model.Admin;
import com.app.cediamback.repository.AdminRepository;

@Service
public class AdminService {


    
     @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public Optional<Admin> authenticate(String email, String rawPassword) {
        // Cambiar a findByEmail para buscar al usuario por correo electrónico
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent() && passwordEncoder.matches(rawPassword, admin.get().getPassword())) {
            return admin;
        }
        return Optional.empty();
    }

}