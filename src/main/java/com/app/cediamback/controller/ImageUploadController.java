package com.app.cediamback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.cediamback.dto.ImageDTO;
import com.app.cediamback.model.Image;
import com.app.cediamback.service.FileStorageService;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description) {
        try {
            ImageDTO savedImage = fileStorageService.saveImage(file, description);
            return new ResponseEntity<>(savedImage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al subir la imagen: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<ImageDTO>> listImages() {
        List<ImageDTO> images = fileStorageService.listAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        Optional<Image> image = fileStorageService.getImageById(id);
        return image.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        fileStorageService.deleteImage(id);
        return new ResponseEntity<>("Imagen eliminada exitosamente.", HttpStatus.OK);
    }

    // Nuevo endpoint para actualizar la descripción de una imagen
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateImageDescription(
            @PathVariable Long id,
            @RequestParam("description") String newDescription) {
        try {
            ImageDTO updatedImage = fileStorageService.updateImageDescription(id, newDescription);
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }
}
