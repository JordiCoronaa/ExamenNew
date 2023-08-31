package com.tigger.team.examen.service;

import com.tigger.team.examen.entity.*;
import com.tigger.team.examen.model.PreguntaOpciones;
import com.tigger.team.examen.model.RegistrarRespuestasRequest;
import com.tigger.team.examen.model.RequestCreateExam;
import com.tigger.team.examen.model.Respuesta;
import com.tigger.team.examen.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private OpcionRepository opcionRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private AlumnoToExamRepository alumnoToExamRepository;

    @Autowired
    private ResultadosRepository resultadosRepository;

    public ResponseEntity<?> guardarExamen(RequestCreateExam request) {

        try {
            //GUARDAMOS ID EXAMEN / NOMBRE EXAMEN
            // TODO: AGREGAR VALIDACIONES DE QUE SI EXSITE PERO CAMBIA EL NUMERO DE PREGUNTAS
            if (!examenRepository.existsByMateria(request.getMateria())) {
                Examen examen = new Examen();
                examen.setMateria(request.getMateria());
                examenRepository.save(examen);

                Long idExamen = examenRepository.findIdByMateria(request.getMateria());

                for (PreguntaOpciones pregunta : request.getPreguntas()) {
                    Pregunta preguntaToSave = new Pregunta();
                    preguntaToSave.setIdExamen(idExamen);
                    preguntaToSave.setEnunciado(pregunta.getEnunciado());
                    preguntaRepository.save(preguntaToSave);

                    Long idPregunta = preguntaRepository.findIdByEnunciado(pregunta.getEnunciado());
                    for (Opcion op : pregunta.getOpciones()) {
                        Opcion opcionToSave = new Opcion();
                        opcionToSave.setOp(op.getOp());
                        opcionToSave.setIdPregunta(idPregunta);
                        opcionToSave.setTexto(op.getTexto());
                        opcionToSave.setEsCorrecta(op.getEsCorrecta());

                        opcionRepository.save(opcionToSave);
                    }
                }

                return ResponseEntity.status(HttpStatus.CREATED).body("Examen guardado correctamente");
            } else {
                return ResponseEntity.ok("El examen ya existe");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getCause().toString());
        }

    }

    public ResponseEntity<?> agendarExamen(RegistroAlumnoToExamen request) {

        ///TODO AGREGAR MAS VALIDACIONE COMO VALIDAR FECHA VALIDA DE ECAMEN, REGION, GRUPOS GRADOS
        try {
            if (estudianteRepository.existsById(request.getMatriculaAlumno())) {
                if (examenRepository.existsById(request.getIdentificadorExamen())) {
                    alumnoToExamRepository.save(request);
                    return ResponseEntity.status(HttpStatus.CREATED).body("Examen agendado correctamente");
                } else {
                    return ResponseEntity.badRequest().body("el identificador del examen es icorrecto o no existe");
                }
            } else {
                return ResponseEntity.badRequest().body("La mtricula del alumno es incorrecta o no existe");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getCause().toString());
        }
    }

    public ResponseEntity<?> registrarRespuestas(RegistrarRespuestasRequest request) {

        //VALIDAR SI EL ALUMNO TIENE EL EXAMNE AGENDADO
        if (estudianteRepository.existsById(request.getInformacionExamen().getMatriculaAlumno())) {
            if (examenRepository.existsById(request.getInformacionExamen().getIdentificadorExamen())) {

                //VALIDAR RESPUESTAS CON NUMERO DE PREGUNTAS DEL EXAMEN
                List<Pregunta> totalPreguntas = preguntaRepository.findByIdExamen(request.getInformacionExamen().getIdentificadorExamen());
                Integer respuestasCorrectas = 0;
                if (totalPreguntas.size() == request.getRespuestas().size()) {
                    for (Respuesta respuesta : request.getRespuestas()) {

                        List<Opcion> opcionsToQuestion = opcionRepository.findByIdPregunta(respuesta.getIdPregunta());

                        for (Opcion op : opcionsToQuestion) {
                            if (respuesta.getOpcion().equals(op.getOp())) {
                                if (op.getEsCorrecta()) {
                                    //VALIDAR LA CANTIDAD DE RESPUESTA CORRECTAS
                                    respuestasCorrectas++;

                                }
                            }
                        }
                    }

                    //CALCULAR CALIFICACION EN BASE AL NUMERO DE PREGUNTAS Y EL NUMERO DE ASIERTOS
                    Double calificacion = Double.valueOf((respuestasCorrectas / totalPreguntas.size() ) * 100);

                    String materia = examenRepository.findMateriaById(request.getInformacionExamen().getIdentificadorExamen());
                    String nombre = estudianteRepository.findNombreById(request.getInformacionExamen().getMatriculaAlumno());
                    Resultados resultados = new Resultados();


                    resultados.setMatriculaAlumno(request.getInformacionExamen().getMatriculaAlumno());
                    resultados.setNombreAlumno(nombre);
                    resultados.setIdentificadorExamen(request.getInformacionExamen().getIdentificadorExamen());
                    resultados.setMateria(materia);
                    resultados.setAciertos(respuestasCorrectas);
                    resultados.setCalificacion(calificacion);
                    //REGISTRAR RESULTADOS DEL EXAMEN


                    resultadosRepository.save(resultados);
                    return new ResponseEntity<>(resultados, HttpStatus.CREATED) ;
                } else {
                    return ResponseEntity.badRequest().body("La cantidad de respuestas no concide con la cantidad de preguntas ");
                }
            } else {
                return ResponseEntity.badRequest().body("el identificador del examen es icorrecto o no existe");
            }
        } else {
            return ResponseEntity.badRequest().body("La mtricula del alumno es incorrecta o no existe");
        }

    }
}