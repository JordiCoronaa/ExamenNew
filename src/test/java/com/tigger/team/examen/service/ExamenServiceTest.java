package com.tigger.team.examen.service;

import com.tigger.team.examen.entity.*;
import com.tigger.team.examen.model.PreguntaOpciones;
import com.tigger.team.examen.model.RegistrarRespuestasRequest;
import com.tigger.team.examen.model.RequestCreateExam;
import com.tigger.team.examen.model.Respuesta;
import com.tigger.team.examen.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamenServiceTest {

    @InjectMocks
    private ExamenService examenService;

    @Mock
    private EstudianteRepository estudianteRepository;

    @Mock
    private ExamenRepository examenRepository;

    @Mock
    private OpcionRepository opcionRepository;

    @Mock
    private PreguntaRepository preguntaRepository;

    @Mock
    private AlumnoToExamRepository alumnoToExamRepository;

    @Mock
    private ResultadosRepository resultadosRepository;


    @Test
    public void testAgendarExamen_ExamAndStudentExist_ReturnsCreatedResponse() {
        // Arrange
        RegistroAlumnoToExamen request = new RegistroAlumnoToExamen();
        request.setMatriculaAlumno(123456L);
        request.setIdentificadorExamen(1L);

        when(estudianteRepository.existsById(request.getMatriculaAlumno())).thenReturn(true);
        when(examenRepository.existsById(request.getIdentificadorExamen())).thenReturn(true);

        // Act
        ResponseEntity<?> response = examenService.agendarExamen(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Examen agendado correctamente", response.getBody());
        verify(alumnoToExamRepository, times(1)).save(request);
    }

    @Test
    public void testAgendarExamen_ExamDoesNotExist_ReturnsBadRequest() {
        // Arrange
        RegistroAlumnoToExamen request = new RegistroAlumnoToExamen();
        request.setMatriculaAlumno(123456L);
        request.setIdentificadorExamen(1L);

        when(estudianteRepository.existsById(request.getMatriculaAlumno())).thenReturn(true);
        when(examenRepository.existsById(request.getIdentificadorExamen())).thenReturn(false);

        // Act
        ResponseEntity<?> response = examenService.agendarExamen(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("el identificador del examen es icorrecto o no existe", response.getBody());
        verify(alumnoToExamRepository, never()).save(request);
    }

    @Test
    public void testAgendarExamen_StudentDoesNotExist_ReturnsBadRequest() {
        // Arrange
        RegistroAlumnoToExamen request = new RegistroAlumnoToExamen();
        request.setMatriculaAlumno(123456L);
        request.setIdentificadorExamen(1L);

        when(estudianteRepository.existsById(request.getMatriculaAlumno())).thenReturn(false);

        // Act
        ResponseEntity<?> response = examenService.agendarExamen(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("La mtricula del alumno es incorrecta o no existe", response.getBody());
        verify(examenRepository, never()).existsById(anyLong());
        verify(alumnoToExamRepository, never()).save(request);
    }

    @Test
    void testRegistrarRespuestas() {
        // Mocking data
        RegistrarRespuestasRequest request = createMockRequest();
        List<Pregunta> mockPreguntas = createMockPreguntas();
        List<Opcion> mockOpciones = createMockOpciones();

        // Mocking repository behavior
        when(estudianteRepository.existsById(anyLong())).thenReturn(true);
        when(examenRepository.existsById(anyLong())).thenReturn(true);
        when(preguntaRepository.findByIdExamen(anyLong())).thenReturn(mockPreguntas);
        when(opcionRepository.findByIdPregunta(anyLong())).thenReturn(mockOpciones);

        // Perform the test
        ResponseEntity<?> response = examenService.registrarRespuestas(request);

        // Assertions
        verify(resultadosRepository, times(1)).save(any(Resultados.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    // Helper methods to create mock data
// Helper methods to create mock data
    private RegistrarRespuestasRequest createMockRequest() {
        RegistrarRespuestasRequest request = new RegistrarRespuestasRequest();

        // Set properties of the request
        RegistroAlumnoToExamen informacionExamen = new RegistroAlumnoToExamen();
        informacionExamen.setMatriculaAlumno(123456L);
        informacionExamen.setIdentificadorExamen(789L);
        // Set other properties of informacionExamen

        List<Respuesta> respuestas = new ArrayList<>();
        // Create and add mock respuestas to the list
        Respuesta respuesta1 = new Respuesta();
        respuesta1.setIdPregunta(1L);
        respuesta1.setOpcion("A");
        // Set other properties of respuesta1
        respuestas.add(respuesta1);

        Respuesta respuesta2 = new Respuesta();
        respuesta2.setIdPregunta(2L);
        respuesta2.setOpcion("C");
        // Set other properties of respuesta2
        respuestas.add(respuesta2);

        // Set respuestas list in the request
        request.setInformacionExamen(informacionExamen);
        request.setRespuestas(respuestas);

        return request;
    }

    private List<Pregunta> createMockPreguntas() {
        List<Pregunta> preguntas = new ArrayList<>();

        // Create and add mock preguntas to the list
        Pregunta pregunta1 = new Pregunta();
        pregunta1.setIdExamen(789L);
        pregunta1.setId(1L);
        // Set other properties of pregunta1
        preguntas.add(pregunta1);

        Pregunta pregunta2 = new Pregunta();
        pregunta2.setIdExamen(789L);
        pregunta2.setId(2L);
        // Set other properties of pregunta2
        preguntas.add(pregunta2);

        return preguntas;
    }

    private List<Opcion> createMockOpciones() {
        List<Opcion> opciones = new ArrayList<>();

        // Create and add mock opciones to the list
        Opcion opcion1 = new Opcion();
        opcion1.setIdPregunta(1L);
        opcion1.setOp("A");
        opcion1.setEsCorrecta(true);
        // Set other properties of opcion1
        opciones.add(opcion1);

        Opcion opcion2 = new Opcion();
        opcion2.setIdPregunta(1L);
        opcion2.setOp("B");
        opcion2.setEsCorrecta(false);
        // Set other properties of opcion2
        opciones.add(opcion2);

        Opcion opcion3 = new Opcion();
        opcion3.setIdPregunta(2L);
        opcion3.setOp("C");
        opcion3.setEsCorrecta(true);
        // Set other properties of opcion3
        opciones.add(opcion3);

        // Add more mock opciones

        return opciones;
    }
}
