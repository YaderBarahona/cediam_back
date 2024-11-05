package com.app.cediamback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.cediamback.dto.ImageDTO;
import com.app.cediamback.model.Image;
import com.app.cediamback.repository.ImageRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileStorageService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private ImageRepository imageRepository;

    public ImageDTO saveImage(MultipartFile file, String description) throws IOException {
        try {
            // Lógica para guardar la imagen
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, uniqueFilename);
            Files.write(filePath, file.getBytes());

            System.out.println("Archivo guardado en: " + filePath.toString());

            Image image = new Image();
            image.setFileName(uniqueFilename);  // Nombre único generado
            image.setFilePath(filePath.toString());  // Ruta del archivo
            image.setUploadTime(LocalDateTime.now());  // Fecha de subida
            image.setName(file.getOriginalFilename());  // Nombre original del archivo
            image.setDescription(description);  // Descripción

            Image savedImage = imageRepository.save(image);
            return new ImageDTO(savedImage.getId(), savedImage.getFileName(), savedImage.getName(), savedImage.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error al guardar la imagen en la base de datos: " + e.getMessage(), e);
        }
    }

    public List<ImageDTO> listAllImages() {
        return imageRepository.findAll().stream()
            .map(image -> new ImageDTO(image.getId(), image.getFileName(), image.getName(), image.getDescription()))
            .collect(Collectors.toList());
    }

    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }

    public void deleteImage(Long id) {
        Optional<Image> imageOpt = imageRepository.findById(id);
        if (imageOpt.isPresent()) {
            Image image = imageOpt.get();
            File file = new File(image.getFilePath());
            if (file.exists()) {
                file.delete();
            }
            imageRepository.deleteById(id);
        }
    }

    // Nuevo método para actualizar la descripción de una imagen
    public ImageDTO updateImageDescription(Long id, String newDescription) {
        Optional<Image> imageOpt = imageRepository.findById(id);
        if (imageOpt.isPresent()) {
            Image image = imageOpt.get();
            image.setDescription(newDescription);
            Image updatedImage = imageRepository.save(image);
            return new ImageDTO(updatedImage.getId(), updatedImage.getFileName(), updatedImage.getName(), updatedImage.getDescription());
        } else {
            throw new RuntimeException("Imagen no encontrada con id: " + id);
        }
    }
}
