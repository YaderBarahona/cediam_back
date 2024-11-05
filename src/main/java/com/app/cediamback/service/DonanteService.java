package com.app.cediamback.service;

import java.util.List;

import com.app.cediamback.model.Donante;


public interface DonanteService {

     Donante createDonante(Donante donante);

    Donante updateDonante(Long id, Donante donante);

    Donante getDonanteById(Long id);

    List<Donante> getAllDonantes();

    void deleteDonante(Long id);

    boolean isDonanteActivo(Long id);

    void updateEstadoDonante(Long id, boolean nuevoEstado);

    void updateActivoDonante(Long id, boolean nuevoActivo);

     // Métodos adicionales sugeridos para optimizar el dashboard
    List<Donante> getDonantesByEstado(Boolean estadoAprobado);

    long getTotalDonantesAprobados();

    long getTotalDonantesPendientes();
}
