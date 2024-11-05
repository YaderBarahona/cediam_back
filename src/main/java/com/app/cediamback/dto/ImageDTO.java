package com.app.cediamback.dto;

public class ImageDTO {
    private Long id;
    private String fileName;  // Nombre del archivo para construir la URL
    private String name;      // Nombre de la imagen
    private String description; // Descripción de la imagen

    public ImageDTO(Long id, String fileName, String name, String description) {
        this.id = id;
        this.fileName = fileName;
        this.name = name;
        this.description = description;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}