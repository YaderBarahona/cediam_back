package com.app.cediamback.repository;

import com.app.cediamback.model.Voluntariado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface VoluntariadoRepository extends JpaRepository<Voluntariado, Long> {

    //Optional<Voluntariado> findByIdAndEstadoAprobado(Long id, Boolean estadoAprobado);
    // Método para buscar voluntariados por estado aprobado
    List<Voluntariado> findByEstadoAprobado(Boolean estadoAprobado);

    // Método para contar voluntariados por estado aprobado
    long countByEstadoAprobado(Boolean estadoAprobado);

    Optional<Voluntariado> findByIdAndEstadoAprobado(Long id, Boolean estadoAprobado);

}
