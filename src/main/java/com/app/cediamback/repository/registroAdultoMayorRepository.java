package com.app.cediamback.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.app.cediamback.model.registroAdultoMayor;

@Repository
public interface registroAdultoMayorRepository extends JpaRepository<registroAdultoMayor, Long>{

    Optional<registroAdultoMayor> findByIdAndEstadoAprobado(Long id, Boolean estadoAprobado);
}