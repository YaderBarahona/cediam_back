package com.app.cediamback.service.impl;
/* 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.cediamback.model.Voluntariado;
import com.app.cediamback.repository.VoluntariadoRepository;
import com.app.cediamback.service.VoluntariadoService;

@Service
public class VoluntariadoServiceImpl implements VoluntariadoService {

    @Autowired
    private VoluntariadoRepository voluntariadoRepository;

    @Override
    public Voluntariado createVoluntariado(Voluntariado voluntariado) {
        return voluntariadoRepository.save(voluntariado);
    }

    @Override
    public Voluntariado updateVoluntariado(Long id, Voluntariado voluntariado) {
        Optional<Voluntariado> optionalVoluntariado = voluntariadoRepository.findById(id);

        if (optionalVoluntariado.isPresent()) {
            Voluntariado existingVoluntariado = optionalVoluntariado.get();
            existingVoluntariado.setNombre(voluntariado.getNombre());
            existingVoluntariado.setCedula(voluntariado.getCedula());
            existingVoluntariado.setEmail(voluntariado.getEmail());
            existingVoluntariado.setFechaNacimiento(voluntariado.getFechaNacimiento());
            existingVoluntariado.setTelefono(voluntariado.getTelefono());
            existingVoluntariado.setDireccion(voluntariado.getDireccion());
            existingVoluntariado.setComentarios(voluntariado.getComentarios());
            existingVoluntariado.setEstadoAprobado(voluntariado.getEstadoAprobado());

            return voluntariadoRepository.save(existingVoluntariado);
        } else {
            throw new RuntimeException("No se encontró el voluntario con ID: " + id);
        }
    }                  //ResponseStatusException(HttpStatus.NOT_FOUND,

    @Override
    public Voluntariado getVoluntariadoById(Long id) {
        return voluntariadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el voluntario con ID: " + id));
    }

    @Override
    public List<Voluntariado> getAllVoluntariados() {
        return voluntariadoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public boolean isVoluntariadoActivo(Long id) {
        return voluntariadoRepository.findByIdAndEstadoAprobado(id, true).isPresent();
    }

    @Override
    public void updateEstadoVoluntariado(Long id) {
        Voluntariado voluntariado = voluntariadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el voluntario con ID: " + id));

        voluntariado.setEstadoAprobado(true);
        voluntariadoRepository.save(voluntariado);
    }

    @Override
    public void deleteVoluntariado(Long id) {
        Voluntariado voluntariado = voluntariadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el voluntario con ID: " + id));
        voluntariadoRepository.delete(voluntariado);
    }
}
*/



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.app.cediamback.model.Voluntariado;
import com.app.cediamback.repository.VoluntariadoRepository;
import com.app.cediamback.service.VoluntariadoService;

@Service
public class VoluntariadoServiceImpl implements VoluntariadoService {

    @Autowired
    private VoluntariadoRepository voluntariadoRepository;

    @Override
    public Voluntariado createVoluntariado(Voluntariado voluntariado) {
        return voluntariadoRepository.save(voluntariado);
    }

    @Override
    public Voluntariado updateVoluntariado(Long id, Voluntariado voluntariado) {
        Optional<Voluntariado> optionalVoluntariado = voluntariadoRepository.findById(id);

        if (optionalVoluntariado.isPresent()) {
            Voluntariado existingVoluntariado = optionalVoluntariado.get();
            existingVoluntariado.setNombre(voluntariado.getNombre());
            existingVoluntariado.setCedula(voluntariado.getCedula());
            existingVoluntariado.setEmail(voluntariado.getEmail());
            existingVoluntariado.setFechaNacimiento(voluntariado.getFechaNacimiento());
            existingVoluntariado.setTelefono(voluntariado.getTelefono());
            existingVoluntariado.setDireccion(voluntariado.getDireccion());
            existingVoluntariado.setComentarios(voluntariado.getComentarios());
            existingVoluntariado.setEstadoAprobado(voluntariado.getEstadoUsuario());
            existingVoluntariado.setEstadoAprobado(voluntariado.getEstadoAprobado());

            return voluntariadoRepository.save(existingVoluntariado);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el voluntario con ID: " + id);
        }
    }

    @Override
    public Voluntariado getVoluntariadoById(Long id) {
        return voluntariadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el voluntario con ID: " + id));
    }

    @Override
    public List<Voluntariado> getAllVoluntariados() {
        return voluntariadoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public boolean isVoluntariadoActivo(Long id) {
        return voluntariadoRepository.findByIdAndEstadoAprobado(id, true).isPresent();
    }

    @Override
    public void updateEstadoVoluntariado(Long id, boolean nuevoEstado) {
        Voluntariado voluntariado = voluntariadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el voluntario con ID: " + id));

        voluntariado.setEstadoAprobado(nuevoEstado);
        voluntariadoRepository.save(voluntariado);
    }

    @Override
    public void updateActivoVoluntariado(Long id, boolean nuevoActivo) {
        Voluntariado voluntariado = voluntariadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el voluntario con ID: " + id));
        voluntariado.setEstadoUsuario(nuevoActivo);
        voluntariadoRepository.save(voluntariado);
    }

    @Override
    public void deleteVoluntariado(Long id) {
        Voluntariado voluntariado = voluntariadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el voluntario con ID: " + id));
        voluntariadoRepository.delete(voluntariado);
    }

    @Override
    public List<Voluntariado> getVoluntariadosByEstado(Boolean estadoAprobado) {
        return voluntariadoRepository.findByEstadoAprobado(estadoAprobado);
    }

    @Override
    public long getTotalVoluntariadosAprobados() {
        return voluntariadoRepository.countByEstadoAprobado(true);
    }

    @Override
    public long getTotalVoluntariadosPendientes() {
        return voluntariadoRepository.countByEstadoAprobado(false);
    }
}
