package com.app.cediamback.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.app.cediamback.model.Asociado;
import com.app.cediamback.repository.AsociadoRepository;
import com.app.cediamback.service.AsociadoService;

import java.util.List;


@Service
public class AsociadoServiceImpl implements AsociadoService{

    @Autowired
    private AsociadoRepository asociadoRepository;

    @Override
    public Asociado createAsociado(Asociado asociado) {
        return asociadoRepository.save(asociado);
    }

    @Override
    public Asociado updateAsociado(Long id, Asociado asociado) {
        
        Optional<Asociado> optionalAsociado = asociadoRepository.findById(id);

        if (optionalAsociado.isPresent()) {
            Asociado existingAsociado = optionalAsociado.get();
            existingAsociado.setNombre(asociado.getNombre());
            existingAsociado.setApellido1(asociado.getApellido1());
            existingAsociado.setApellido2(asociado.getApellido2());
            existingAsociado.setCedula(asociado.getCedula());
            existingAsociado.setEmail(asociado.getEmail());
            existingAsociado.setDireccion(asociado.getDireccion());
            existingAsociado.setOcupacion(asociado.getOcupacion());
            existingAsociado.setFecha(asociado.getFecha());
            existingAsociado.setTelefono(asociado.getTelefono());
            existingAsociado.setObservaciones(asociado.getObservaciones());
            existingAsociado.setEstadoAprobado(asociado.getEstadoUsuario());
            existingAsociado.setEstadoAprobado(asociado.getEstadoAprobado());

            return asociadoRepository.save(existingAsociado);
        } else {
            throw new RuntimeException("No se encontró el donante con ID: " + id);
        }
    }

    @Override
    public Asociado getAsociadoById(Long id) {
        return asociadoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el donante con ID: " + id));
    }

    @Override
    public List<Asociado> getAllAsociados() {
        return asociadoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public void deleteAsociado(Long id) {
        
        Asociado asociado = asociadoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el donante con ID: " + id));
asociadoRepository.delete(asociado);
    }

    @Override
    public boolean isAsociadoActivo(Long id) {
        return asociadoRepository.findByIdAndEstadoAprobado(id, true).isPresent();
    }

    @Override
    public void updateEstadoAsociado(Long id, boolean nuevoEstado) {
        Asociado asociado = asociadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el asociado con ID: " + id));
        asociado.setEstadoAprobado(nuevoEstado);
        asociadoRepository.save(asociado);
    }

    @Override
    public void updateActivoAsociado(Long id, boolean nuevoActivo) {
        Asociado asociado = asociadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el asociado con ID: " + id));
        asociado.setEstadoUsuario(nuevoActivo);
        asociadoRepository.save(asociado);
    }


}
