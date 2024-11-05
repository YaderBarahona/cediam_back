package com.app.cediamback.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.cediamback.service.AsociadoService;
import com.app.cediamback.model.Asociado;



@RestController
@RequestMapping("/api/asociado")
@CrossOrigin(origins = "*")
public class AsociadoController {

     @Autowired
    private AsociadoService asociadoService;

    // Crear un nuevo donante
    @PostMapping
    public ResponseEntity<Asociado> createAsociado(@RequestBody Asociado asociado) {
        Asociado newAsociado = asociadoService.createAsociado(asociado);
        return ResponseEntity.ok(newAsociado);
    }

    // Obtener un donante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Asociado> getAsociadoById(@PathVariable Long id) {
        Asociado asociado = asociadoService.getAsociadoById(id);
        return ResponseEntity.ok(asociado);
    }

    // Obtener todos los donantes
    @GetMapping
    public ResponseEntity<List<Asociado>> getAllAsociados() {
        List<Asociado> asociados = asociadoService.getAllAsociados();
        return ResponseEntity.ok(asociados);
    }

    // Actualizar un donante
    @PutMapping("/{id}")
    public ResponseEntity<Asociado> updateAsociado(@PathVariable Long id, @RequestBody Asociado asociado) {
        Asociado updatedAsociado = asociadoService.updateAsociado(id, asociado);
        return ResponseEntity.ok(updatedAsociado);
    }

    // Eliminar un donante por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsociado(@PathVariable Long id) {
        asociadoService.deleteAsociado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activo/{id}")
    public ResponseEntity<Boolean> verificarEstadoActivo(@PathVariable Long id) {
        boolean isActivo = asociadoService.isAsociadoActivo(id);
        return ResponseEntity.ok(isActivo);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> updateEstadoAsociado(@PathVariable Long id, @RequestParam boolean nuevoEstado) {
        asociadoService.updateEstadoAsociado(id, nuevoEstado);
        return ResponseEntity.ok().build(); // Devolvemos un código 200 OK si todo va bien
    }

    @PutMapping("/{id}/activo")
    public ResponseEntity<Void> updateActivoAsociado(@PathVariable Long id, @RequestParam boolean nuevoActivo) {
        asociadoService.updateActivoAsociado(id, nuevoActivo);
        return ResponseEntity.ok().build(); // Devolvemos un código 200 OK si todo va bien
    }
}
