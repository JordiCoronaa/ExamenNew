package com.examen.mx.examen.service;

import com.examen.mx.examen.entity.Cliente;
import com.examen.mx.examen.entity.Habitacion;
import com.examen.mx.examen.entity.Reserva;
import com.examen.mx.examen.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    public void testObtenerTodasLasReservas() {
        // Datos de prueba
        Cliente cliente = new Cliente(1L, "Nombre", "Apellido", "email@example.com", "123456789", LocalDate.now(), null);
        Habitacion habitacion = new Habitacion(1L, "101", null, "Descripción", 1, new BigDecimal("50.00"), null);
        Reserva reserva1 = new Reserva(1L, cliente, habitacion, LocalDate.now(), LocalDate.now().plusDays(3), new BigDecimal("150.00"), "Confirmada");
        Reserva reserva2 = new Reserva(2L, cliente, habitacion, LocalDate.now().plusDays(5), LocalDate.now().plusDays(7), new BigDecimal("200.00"), "Pendiente");
        List<Reserva> reservas = List.of(reserva1, reserva2);

        // Configuración del mock
        when(reservaRepository.findAll()).thenReturn(reservas);

        // Llamada al servicio
        List<Reserva> resultado = reservaService.obtenerTodasLasReservas();

        // Verificación
        assertEquals(reservas, resultado);
    }
}
