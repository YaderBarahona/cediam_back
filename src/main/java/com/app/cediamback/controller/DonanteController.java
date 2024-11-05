package com.app.cediamback.controller;

import com.app.cediamback.model.Donante;
import com.app.cediamback.service.DonanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donante")
@CrossOrigin(origins = "*")
public class DonanteController {

    @Autowired
    private DonanteService donanteService;

    // Crear un nuevo donante
    @PostMapping
    public ResponseEntity<Donante> createDonante(@RequestBody Donante donante) {
        Donante newDonante = donanteService.createDonante(donante);
        return ResponseEntity.ok(newDonante);
    }

    // Obtener un donante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Donante> getDonanteById(@PathVariable Long id) {
        Donante donante = donanteService.getDonanteById(id);
        return ResponseEntity.ok(donante);
    }

    // Obtener todos los donantes
    @GetMapping
    public ResponseEntity<List<Donante>> getAllDonantes() {
        List<Donante> donantes = donanteService.getAllDonantes();
        return ResponseEntity.ok(donantes);
    }

    // Actualizar un donante
    @PutMapping("/{id}")
    public ResponseEntity<Donante> updateDonante(@PathVariable Long id, @RequestBody Donante donante) {
        Donante updatedDonante = donanteService.updateDonante(id, donante);
        return ResponseEntity.ok(updatedDonante);
    }

    // Eliminar un donante por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonante(@PathVariable Long id) {
        donanteService.deleteDonante(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activo/{id}")
    public ResponseEntity<Boolean> verificarEstadoActivo(@PathVariable Long id) {
        boolean isActivo = donanteService.isDonanteActivo(id);
        return ResponseEntity.ok(isActivo);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> updateEstadoDonante(@PathVariable Long id, @RequestParam boolean nuevoEstado) {
        donanteService.updateEstadoDonante(id, nuevoEstado);
        return ResponseEntity.ok().build(); // Devolvemos un código 200 OK si todo va bien
    }

    @PutMapping("/{id}/activo")
    public ResponseEntity<Void> updateActivoDonante(@PathVariable Long id, @RequestParam boolean nuevoActivo) {
        donanteService.updateActivoDonante(id, nuevoActivo);
        return ResponseEntity.ok().build(); // Devolvemos un código 200 OK si todo va bien
    }
}
