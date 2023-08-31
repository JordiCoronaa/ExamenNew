package com.tigger.team.examen.repository;

import com.tigger.team.examen.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreguntaRepository extends  JpaRepository<Pregunta, Long> {

    @Query("SELECT e.id FROM Pregunta e WHERE e.enunciado = :enunciado")
    Long findIdByEnunciado(@Param("enunciado") String enunciado);

    List<Pregunta> findByIdExamen(Long idExamen);

}