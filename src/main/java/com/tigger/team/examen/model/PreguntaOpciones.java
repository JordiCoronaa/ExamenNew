package com.tigger.team.examen.model;

import com.tigger.team.examen.entity.Opcion;

import java.util.List;

public class PreguntaOpciones {

    private String enunciado;
    private List<Opcion> opciones;

    public PreguntaOpciones() {
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }
}
