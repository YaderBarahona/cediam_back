package com.app.cediamback.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "voluntariado")
public class Voluntariado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String cedula;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String telefono;

    private String direccion;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String comentarios;

    @Column(name = "estado_usuario", nullable = false)
    private Boolean estadoUsuario = false;

    @Column(name = "estado_aprobado", nullable = true)
    private Boolean estadoAprobado = null;
}
