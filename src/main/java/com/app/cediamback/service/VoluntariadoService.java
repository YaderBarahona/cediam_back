package com.app.cediamback.service;

import com.app.cediamback.model.Voluntariado;

import java.util.List;

public interface VoluntariadoService {

    Voluntariado createVoluntariado(Voluntariado voluntariado);

    Voluntariado updateVoluntariado(Long id, Voluntariado voluntariado);

    Voluntariado getVoluntariadoById(Long id);

    List<Voluntariado> getAllVoluntariados();

    void deleteVoluntariado(Long id);

    boolean isVoluntariadoActivo(Long id);

    // Cambia la firma del método para aceptar el nuevoEstado como parámetro
    void updateEstadoVoluntariado(Long id, boolean nuevoEstado);

    // Cambia la firma del método para aceptar el nuevo activo como parámetro
    void updateActivoVoluntariado(Long id, boolean nuevoActivo);

    // Métodos adicionales sugeridos para optimizar el dashboard
    List<Voluntariado> getVoluntariadosByEstado(Boolean estadoAprobado);

    long getTotalVoluntariadosAprobados();

    long getTotalVoluntariadosPendientes();

}
