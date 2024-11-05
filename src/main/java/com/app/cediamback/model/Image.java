package com.app.cediamback.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // El nombre original de la imagen

    @Column(nullable = false)
    private String fileName;  // El nombre del archivo almacenado, debe tener valor

    private String filePath;  // La ruta del archivo en el sistema de archivos

    @Column(nullable = false)
    private LocalDateTime uploadTime;  // Añadir este campo

    private String description;  // Nueva campo para la descripción

    // Constructor vacío
    public Image() {
    }

    // Constructor con todos los campos obligatorios
    public Image(String fileName, String filePath, LocalDateTime uploadTime) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadTime = uploadTime;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}