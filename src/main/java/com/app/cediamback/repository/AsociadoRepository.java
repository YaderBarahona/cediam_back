package com.app.cediamback.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.cediamback.model.Asociado;

public interface AsociadoRepository extends JpaRepository<Asociado, Long> {

    Optional<Asociado> findByIdAndEstadoAprobado(Long id, Boolean estadoAprobado);


}
