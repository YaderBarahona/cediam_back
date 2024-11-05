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

import com.app.cediamback.model.registroAdultoMayor;

import com.app.cediamback.service.registroAdultoMayorService;


@RestController
@RequestMapping("/api/adultomayor")
@CrossOrigin(origins = "*")
public class registroAdultoMayorController {

     @Autowired
    private registroAdultoMayorService registroadultomayorService;

    // Crear un nuevo donante
    @PostMapping
    public ResponseEntity<registroAdultoMayor> createregistroAdultoMayor(@RequestBody registroAdultoMayor registroadultomayor) {
        registroAdultoMayor newregistroAdultoMayor = registroadultomayorService.createregistroAdultoMayor(registroadultomayor);
        return ResponseEntity.ok(newregistroAdultoMayor);
    }

    // Obtener un adulto por ID
    @GetMapping("/{id}")
    public ResponseEntity<registroAdultoMayor> getregistroAdultoMayorById(@PathVariable Long id) {
        registroAdultoMayor registroadultomayor = registroadultomayorService.getregistroAdultoMayorById(id);
        return ResponseEntity.ok(registroadultomayor);
    }

    // Obtener todos los adultos
    @GetMapping
    public ResponseEntity<List<registroAdultoMayor>> getAllregistroAdultoMayores() {
        List<registroAdultoMayor> registroadultomayores = registroadultomayorService.getAllregistroAdultoMayores();
        return ResponseEntity.ok(registroadultomayores);
    }

    // Actualizar un adulto
    @PutMapping("/{id}")
    public ResponseEntity<registroAdultoMayor> updateregistroAdultoMayor(@PathVariable Long id, @RequestBody registroAdultoMayor registroadultomayor) {
        registroAdultoMayor updatedregistroAdultoMayor = registroadultomayorService.updateregistroAdultoMayor(id, registroadultomayor);
        return ResponseEntity.ok(updatedregistroAdultoMayor);
    }

    // Eliminar un adulto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteregistroAdultoMayor(@PathVariable Long id) {
        registroadultomayorService.deleteregistroAdultoMayor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activo/{id}")
    public ResponseEntity<Boolean> verificarEstadoActivo(@PathVariable Long id) {
        boolean isActivo = registroadultomayorService.isregistroAdultoMayorActivo(id);
        return ResponseEntity.ok(isActivo);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> updateEstadoRegistroAdultoMayor(@PathVariable Long id, @RequestParam boolean nuevoEstado) {
        registroadultomayorService.updateEstadoRegistroAdultoMayor(id, nuevoEstado);
        return ResponseEntity.ok().build(); // Devolvemos un código 200 OK si todo va bien
    }

    @PutMapping("/{id}/activo")
    public ResponseEntity<Void> updateActivoRegistroAdultoMayor(@PathVariable Long id, @RequestParam boolean nuevoActivo) {
        registroadultomayorService.updateActivoRegistroAdultoMayor(id, nuevoActivo);
        return ResponseEntity.ok().build(); // Devolvemos un código 200 OK si todo va bien
    }
}
