package com.tigger.team.examen.model;

import com.tigger.team.examen.entity.RegistroAlumnoToExamen;

import java.util.List;

public class RegistrarRespuestasRequest {

    private RegistroAlumnoToExamen informacionExamen;
    private List<Respuesta> respuestas;

    public RegistrarRespuestasRequest() {
    }

    public RegistroAlumnoToExamen getInformacionExamen() {
        return informacionExamen;
    }

    public void setInformacionExamen(RegistroAlumnoToExamen informacionExamen) {
        this.informacionExamen = informacionExamen;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
