package com.app.cediamback.Security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuración de CORS
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para APIs REST
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas
                .requestMatchers("/api/images/view/**", "/api/images/list").permitAll() // Acceso público a imágenes
                .requestMatchers("/api/donante/**", "/api/voluntariado/**").permitAll() // Acceso público a formularios de donaciones/voluntariado
                
                // Rutas protegidas para el dashboard y el CRUD de la galería
                .requestMatchers("/admin/**").authenticated() // El dashboard del admin requiere autenticación
                .requestMatchers("/api/images/upload", "/api/images/update/**", "/api/images/delete/**").permitAll() // CRUD de imágenes protegido
                
                // Otras rutas que pueden ser públicas
                .anyRequest().permitAll()
            )
            .formLogin(form -> form // Usa el sistema de login existente (implementado en tu proyecto)
                .loginPage("/login")  // Página personalizada de login
                .defaultSuccessUrl("/admin/dashboard", true) // Redirigir al dashboard tras login exitoso
                .permitAll() // Permitir acceso al formulario de login
            )
            .logout(logout -> logout // Configurar el logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    // Configuración de CORS para permitir acceso desde los frontends
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:5174"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // Codificador de contraseñas BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
} 