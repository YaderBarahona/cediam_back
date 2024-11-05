package com.app.cediamback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.cediamback.model.Donante;
import java.util.List;


@Repository
public interface DonanteRepository extends JpaRepository<Donante, Long>{

     List<Donante> findByEstadoAprobado(Boolean estadoAprobado);

    // Método para contar voluntariados por estado aprobado
    long countByEstadoAprobado(Boolean estadoAprobado);


    Optional<Donante> findByIdAndEstadoAprobado(Long id, Boolean estadoAprobado);

}
