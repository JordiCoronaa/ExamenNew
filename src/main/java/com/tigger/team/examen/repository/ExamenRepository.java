package com.tigger.team.examen.repository;

import com.tigger.team.examen.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    boolean existsById(Long id);

    @Query("SELECT e.id FROM Examen e WHERE e.materia = :materia")
    Long findIdByMateria(@Param("materia") String materia);

    @Query("SELECT e.materia FROM Examen e WHERE e.id = :id")
    String findMateriaById(@Param("id") Long id);

    boolean existsByMateria(String materia);
}