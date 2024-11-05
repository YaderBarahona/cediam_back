package com.app.cediamback.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.cediamback.model.registroAdultoMayor;
import com.app.cediamback.repository.registroAdultoMayorRepository;
import com.app.cediamback.service.registroAdultoMayorService;

@Service
public class registroAdultoMayorServiceImpl implements registroAdultoMayorService {

    @Autowired
    private registroAdultoMayorRepository registroadultomayorRepository;

    @Override
    public registroAdultoMayor createregistroAdultoMayor(registroAdultoMayor registroadultomayor) {
       
        return registroadultomayorRepository.save(registroadultomayor);
    }

    @Override
    public registroAdultoMayor updateregistroAdultoMayor(Long id, registroAdultoMayor registroadultomayor) {
        Optional<registroAdultoMayor> optionalregistroAdultoMayor = registroadultomayorRepository.findById(id);

        if (optionalregistroAdultoMayor.isPresent()) {
            registroAdultoMayor existingregistroAdultoMayor = optionalregistroAdultoMayor.get();
            existingregistroAdultoMayor.setCedula(registroadultomayor.getCedula());
            existingregistroAdultoMayor.setNombre(registroadultomayor.getNombre());
            existingregistroAdultoMayor.setApellido1(registroadultomayor.getApellido1());
            existingregistroAdultoMayor.setApellido2(registroadultomayor.getApellido2());
            existingregistroAdultoMayor.setEmail(registroadultomayor.getEmail());
            existingregistroAdultoMayor.setTelefono(registroadultomayor.getTelefono());
            existingregistroAdultoMayor.setFechaNacimiento(registroadultomayor.getFechaNacimiento());
            existingregistroAdultoMayor.setFechaRegistro(registroadultomayor.getFechaRegistro());
            existingregistroAdultoMayor.setGenero(registroadultomayor.getGenero());
            existingregistroAdultoMayor.setPatologías(registroadultomayor.getPatologías());
            existingregistroAdultoMayor.setMedicamento(registroadultomayor.getMedicamento());
            existingregistroAdultoMayor.setDosis(registroadultomayor.getDosis());

            existingregistroAdultoMayor.setCedulaEncargado(registroadultomayor.getCedulaEncargado());
            existingregistroAdultoMayor.setNombreEncargado(registroadultomayor.getNombreEncargado());
            existingregistroAdultoMayor.setApellido1Encargado(registroadultomayor.getApellido1Encargado());
            existingregistroAdultoMayor.setApellido2Encargado(registroadultomayor.getApellido2Encargado());
            existingregistroAdultoMayor.setEmailEncargado(registroadultomayor.getEmailEncargado());
            existingregistroAdultoMayor.setTelefonoEncargado(registroadultomayor.getTelefonoEncargado());
            existingregistroAdultoMayor.setFechaNacimientoEncargado(registroadultomayor.getFechaNacimientoEncargado());
            existingregistroAdultoMayor.setGeneroEncargado(registroadultomayor.getGeneroEncargado());

            existingregistroAdultoMayor.setEstadoUsuario(registroadultomayor.getEstadoUsuario());
            existingregistroAdultoMayor.setEstadoAprobado(registroadultomayor.getEstadoAprobado());

            return registroadultomayorRepository.save(existingregistroAdultoMayor);
        } else {
            throw new RuntimeException("No se encontró el adulto mayor con ID: " + id);
        }
    }

    @Override
    public registroAdultoMayor getregistroAdultoMayorById(Long id) {
        return registroadultomayorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el adulto mayor con ID: " + id));
    }

    @Override
    public List<registroAdultoMayor> getAllregistroAdultoMayores() {
        
        return registroadultomayorRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public void deleteregistroAdultoMayor(Long id) {
       
         registroAdultoMayor registroadultomayor = registroadultomayorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el adulto mayor con ID: " + id));
        registroadultomayorRepository.delete(registroadultomayor);
    }

    @Override
    public boolean isregistroAdultoMayorActivo(Long id) {
       
        return registroadultomayorRepository.findByIdAndEstadoAprobado(id, true).isPresent();
    }

    @Override
    public void updateEstadoRegistroAdultoMayor(Long id, boolean nuevoEstado) {
        registroAdultoMayor registroadultomayor = registroadultomayorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el adulto mayor con ID: " + id));
        registroadultomayor.setEstadoAprobado(nuevoEstado);
        registroadultomayorRepository.save(registroadultomayor);
    }

    @Override
    public void updateActivoRegistroAdultoMayor(Long id, boolean nuevoActivo) {
        registroAdultoMayor registroadultomayor = registroadultomayorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el adulto mayor con ID: " + id));
        registroadultomayor.setEstadoUsuario(nuevoActivo);
        registroadultomayorRepository.save(registroadultomayor);
    }
}
