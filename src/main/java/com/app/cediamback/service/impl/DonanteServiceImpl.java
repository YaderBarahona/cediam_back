package com.app.cediamback.service.impl;


import com.app.cediamback.model.Donante;
import com.app.cediamback.repository.DonanteRepository;
import com.app.cediamback.service.DonanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;


@Service
public class DonanteServiceImpl implements DonanteService {

    @Autowired
    private DonanteRepository donanteRepository;

    @Override
    public Donante createDonante(Donante donante) {
        return donanteRepository.save(donante);
    }
    
    @Override
    public Donante updateDonante(Long id, Donante donante) {
        Optional<Donante> optionalDonante = donanteRepository.findById(id);

        if (optionalDonante.isPresent()) {
            Donante existingDonante = optionalDonante.get();
            existingDonante.setNombre(donante.getNombre());
            existingDonante.setCedula(donante.getCedula());
            existingDonante.setEmail(donante.getEmail());
            existingDonante.setFechaDonacion(donante.getFechaDonacion());
            existingDonante.setTelefono(donante.getTelefono());
            existingDonante.setTipoDonacion(donante.getTipoDonacion());
            existingDonante.setMedioDonar(donante.getMedioDonar());
            existingDonante.setMontoDonacion(donante.getMontoDonacion());
            existingDonante.setDescripDonacion(donante.getDescripDonacion());
            existingDonante.setEstadoAprobado(donante.getEstadoUsuario());
            existingDonante.setEstadoAprobado(donante.getEstadoAprobado());

            return donanteRepository.save(existingDonante);
        } else {
            throw new RuntimeException("No se encontró el donante con ID: " + id);
        }
    }

    @Override
    public Donante getDonanteById(Long id) {
        return donanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el donante con ID: " + id));
    }

    @Override
    public List<Donante> getAllDonantes() {
        return donanteRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public boolean isDonanteActivo(Long id) {
        return donanteRepository.findByIdAndEstadoAprobado(id, true).isPresent();
    }

    @Override
    public void updateEstadoDonante(Long id, boolean nuevoEstado) {
        Donante donante = donanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el donante con ID: " + id));
        donante.setEstadoAprobado(nuevoEstado);
        donanteRepository.save(donante);
    }

    @Override
    public void updateActivoDonante(Long id, boolean nuevoActivo) {
        Donante donante = donanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el donante con ID: " + id));
        donante.setEstadoUsuario(nuevoActivo);
        donanteRepository.save(donante);
    }

    @Override
    public void deleteDonante(Long id) {
        Donante donante = donanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el donante con ID: " + id));
        donanteRepository.delete(donante);
    }
     @Override
    public List<Donante> getDonantesByEstado(Boolean estadoAprobado) {
        return donanteRepository.findByEstadoAprobado(estadoAprobado);
    }

    @Override
    public long getTotalDonantesAprobados() {
        return donanteRepository.countByEstadoAprobado(true);
    }

    @Override
    public long getTotalDonantesPendientes() {
        return donanteRepository.countByEstadoAprobado(false);
    }
}
