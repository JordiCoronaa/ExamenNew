package com.tigger.team.examen.controller;

import com.tigger.team.examen.entity.Estudiante;
import com.tigger.team.examen.entity.RegistroAlumnoToExamen;
import com.tigger.team.examen.model.*;
import com.tigger.team.examen.service.EstudianteService;
import com.tigger.team.examen.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExamenController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private ExamenService examenService;

    @PostMapping("/crearAlumno")
    public ResponseEntity<Estudiante> crearAlumno(@RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteService.guardarEstudiante(estudiante);
        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }


    @PostMapping("/crearExamen")
    public ResponseEntity<?> crearExamen(@RequestBody RequestCreateExam request) {
        return new ResponseEntity<>(examenService.guardarExamen(request), HttpStatus.CREATED);
    }

    @PostMapping("/agendarExamen")
    public ResponseEntity<?> agendarExamen(@RequestBody RegistroAlumnoToExamen request) {
        return new ResponseEntity<>(examenService.agendarExamen(request), HttpStatus.CREATED);
    }

    @PostMapping("/registrarRespuestas")
    public ResponseEntity<?> registrarRespuestas(@RequestBody RegistrarRespuestasRequest request){
        return new ResponseEntity<>(examenService.registrarRespuestas(request), HttpStatus.CREATED);
    }

}
