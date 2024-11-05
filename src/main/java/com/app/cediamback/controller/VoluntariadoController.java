package com.app.cediamback.controller;

import com.app.cediamback.model.Voluntariado;
import com.app.cediamback.service.VoluntariadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voluntariado")
@CrossOrigin(origins = "*")
public class VoluntariadoController {

    @Autowired
    private VoluntariadoService voluntariadoService;

    // Crear un nuevo voluntariado
    @PostMapping
    public ResponseEntity<Voluntariado> createVoluntariado(@RequestBody Voluntariado voluntariado) {
        Voluntariado newVoluntariado = voluntariadoService.createVoluntariado(voluntariado);
        return ResponseEntity.ok(newVoluntariado);
    }

    // Obtener un voluntariado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Voluntariado> getVoluntariadoById(@PathVariable Long id) {
        Voluntariado voluntariado = voluntariadoService.getVoluntariadoById(id);
        return ResponseEntity.ok(voluntariado);
    }

    // Obtener todos los voluntariados
    @GetMapping
    public ResponseEntity<List<Voluntariado>> getAllVoluntariados() {
        List<Voluntariado> voluntariados = voluntariadoService.getAllVoluntariados();
        return ResponseEntity.ok(voluntariados);
    }

    // Actualizar un voluntariado
    @PutMapping("/{id}")
    public ResponseEntity<Voluntariado> updateVoluntariado(@PathVariable Long id, @RequestBody Voluntariado voluntariado) {
        Voluntariado updatedVoluntariado = voluntariadoService.updateVoluntariado(id, voluntariado);
        return ResponseEntity.ok(updatedVoluntariado);
    }

    // Eliminar un voluntariado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoluntariado(@PathVariable Long id) {
        voluntariadoService.deleteVoluntariado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activo/{id}")
    public ResponseEntity<Boolean> verificarEstadoActivo(@PathVariable Long id) {
        boolean isActivo = voluntariadoService.isVoluntariadoActivo(id);
        return ResponseEntity.ok(isActivo);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> updateEstadoVoluntariado(@PathVariable Long id, @RequestParam boolean nuevoEstado) {
        voluntariadoService.updateEstadoVoluntariado(id,nuevoEstado);
        return ResponseEntity.ok().build(); // Devolvemos un código 200 OK si todo va bien
    }

    @PutMapping("/{id}/activo")
    public ResponseEntity<Void> updateActivoVoluntariado(@PathVariable Long id, @RequestParam boolean nuevoActivo) {
        voluntariadoService.updateActivoVoluntariado(id, nuevoActivo);
        return ResponseEntity.ok().build(); // Devolvemos un código 200 OK si todo va bien
    }
}
 