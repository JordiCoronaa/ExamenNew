package com.tigger.team.examen.repository;

import com.tigger.team.examen.entity.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpcionRepository extends  JpaRepository<Opcion, Long> {
    List<Opcion> findByIdPregunta(Long id);
}