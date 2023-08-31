package com.tigger.team.examen.repository;

import com.tigger.team.examen.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstudianteRepository extends  JpaRepository<Estudiante, Long> {

    boolean existsById(Long id);

    @Query("SELECT e.nombre FROM Estudiante e WHERE e.id = :id")
    String findNombreById(@Param("id") Long id);
}