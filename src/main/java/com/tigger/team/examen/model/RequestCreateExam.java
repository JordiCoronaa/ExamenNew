package com.tigger.team.examen.model;

import java.util.List;

public class RequestCreateExam {
    private String materia;
    private List<PreguntaOpciones> preguntas;

    public RequestCreateExam() {
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public List<PreguntaOpciones> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaOpciones> preguntas) {
        this.preguntas = preguntas;
    }
}
