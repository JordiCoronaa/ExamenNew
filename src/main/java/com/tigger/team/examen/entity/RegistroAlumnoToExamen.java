package com.tigger.team.examen.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class RegistroAlumnoToExamen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fechaExamen;
    private Long matriculaAlumno;
    private Long identificadorExamen;
    private String tipoExamen;
    private String grado;

    public RegistroAlumnoToExamen() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public Long getMatriculaAlumno() {
        return matriculaAlumno;
    }

    public void setMatriculaAlumno(Long matriculaAlumno) {
        this.matriculaAlumno = matriculaAlumno;
    }

    public Long getIdentificadorExamen() {
        return identificadorExamen;
    }

    public void setIdentificadorExamen(Long identificadorExamen) {
        this.identificadorExamen = identificadorExamen;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
}
