package com.tigger.team.examen.service;

import com.tigger.team.examen.entity.Estudiante;
import com.tigger.team.examen.repository.EstudianteRepository;
import com.tigger.team.examen.service.EstudianteService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EstudianteServiceTest {

    @InjectMocks
    private EstudianteService estudianteService;

    @Mock
    private EstudianteRepository estudianteRepository;

    @Test
    public void testGuardarEstudiante() {
        // Crear un estudiante de ejemplo
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("John Doe");

        // Configurar el comportamiento simulado del repositorio
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);

        // Llamar al método que se está probando
        Estudiante resultado = estudianteService.guardarEstudiante(estudiante);

        // Verificar que el método del repositorio fue llamado con el estudiante adecuado
        verify(estudianteRepository, times(1)).save(estudiante);

        // Verificar que el resultado sea el mismo estudiante devuelto por el repositorio
        Assertions.assertEquals(estudiante, resultado);
    }
}
