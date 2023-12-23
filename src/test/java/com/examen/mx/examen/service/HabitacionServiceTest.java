package com.examen.mx.examen.service;

import com.examen.mx.examen.entity.Habitacion;
import com.examen.mx.examen.repository.HabitacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HabitacionServiceTest {

    @Mock
    private HabitacionRepository habitacionRepository;

    @InjectMocks
    private HabitacionService habitacionService;

    @Test
    public void testObtenerTodasLasHabitaciones() {
        // Datos de prueba
        Habitacion habitacion1 = new Habitacion(1L, "101", null, "Descripción1", 1, new BigDecimal("50.00"), null);
        Habitacion habitacion2 = new Habitacion(2L, "201", null, "Descripción2", 2, new BigDecimal("80.00"), null);
        List<Habitacion> habitaciones = List.of(habitacion1, habitacion2);

        // Configuración del mock
        when(habitacionRepository.findAll()).thenReturn(habitaciones);

        // Llamada al servicio
        List<Habitacion> resultado = habitacionService.obtenerTodasLasHabitaciones();

        // Verificación
        assertEquals(habitaciones, resultado);
    }
}
