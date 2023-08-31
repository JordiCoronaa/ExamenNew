package com.tigger.team.examen.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Resultados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matriculaAlumno;
    private String nombreAlumno;
    private Long identificadorExamen;
    private String materia;
    private Integer aciertos;
    private Double calificacion;


    public Resultados() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatriculaAlumno() {
        return matriculaAlumno;
    }

    public void setMatriculaAlumno(Long matriculaAlumno) {
        this.matriculaAlumno = matriculaAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public Long getIdentificadorExamen() {
        return identificadorExamen;
    }

    public void setIdentificadorExamen(Long identificadorExamen) {
        this.identificadorExamen = identificadorExamen;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Integer getAciertos() {
        return aciertos;
    }

    public void setAciertos(Integer aciertos) {
        this.aciertos = aciertos;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
}
