package com.app.cediamback.service;

import java.util.List;

import com.app.cediamback.model.registroAdultoMayor;

public interface registroAdultoMayorService {

     registroAdultoMayor createregistroAdultoMayor(registroAdultoMayor registroadultomayor);

    registroAdultoMayor updateregistroAdultoMayor(Long id, registroAdultoMayor registroadultomayor);

    registroAdultoMayor getregistroAdultoMayorById(Long id);

    List<registroAdultoMayor> getAllregistroAdultoMayores();

    void deleteregistroAdultoMayor(Long id);

    boolean isregistroAdultoMayorActivo(Long id);

    void updateEstadoRegistroAdultoMayor(Long id, boolean nuevoEstado);

    void updateActivoRegistroAdultoMayor(Long id, boolean nuevoActivo);
    
} 
