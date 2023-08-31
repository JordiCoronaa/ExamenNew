package com.tigger.team.examen.repository;

import com.tigger.team.examen.entity.RegistroAlumnoToExamen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoToExamRepository extends  JpaRepository<RegistroAlumnoToExamen, Long> {
}