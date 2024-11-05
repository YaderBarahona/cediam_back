package com.app.cediamback.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "adulto_mayor")
public class registroAdultoMayor {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String email;

    private String telefono;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento_adulto")
    private LocalDate fechaNacimiento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

   private String genero;

   private String patologías;

   private String medicamento;

   private String dosis;

   private String cedulaEncargado;

   private String nombreEncargado;

   private String apellido1Encargado;

   private String apellido2Encargado;

   private String emailEncargado;

   private String telefonoEncargado;

   @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento_encargado")
    private LocalDate fechaNacimientoEncargado;

  

    private String generoEncargado;

    @Column(name = "estado_usuario", nullable = false)
    private Boolean estadoUsuario = false;


    @Column(name = "estado_aprobado", nullable = true)
    private Boolean estadoAprobado = null;
}
