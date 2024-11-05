package com.app.cediamback.service;
import java.util.List;

import com.app.cediamback.model.Asociado;


public interface AsociadoService {

     Asociado createAsociado(Asociado asociado);

    Asociado updateAsociado(Long id, Asociado asociado);

    Asociado getAsociadoById(Long id);

    List<Asociado> getAllAsociados();

    void deleteAsociado(Long id);

    boolean isAsociadoActivo(Long id);

    void updateEstadoAsociado(Long id, boolean nuevoEstado);

    void updateActivoAsociado(Long id, boolean nuevoActivo);
}
