package com.examen.mx.examen.service;

import com.examen.mx.examen.entity.Cliente;
import com.examen.mx.examen.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void testObtenerTodosLosClientes() {
        // Datos de prueba
        Cliente cliente1 = new Cliente(1L, "Nombre1", "Apellido1", "email1@example.com", "123456789", LocalDate.now(), null);
        Cliente cliente2 = new Cliente(2L, "Nombre2", "Apellido2", "email2@example.com", "987654321", LocalDate.now(), null);
        List<Cliente> clientes = List.of(cliente1, cliente2);

        // Configuración del mock
        when(clienteRepository.findAll()).thenReturn(clientes);

        // Llamada al servicio
        List<Cliente> resultado = clienteService.obtenerTodosLosClientes();

        // Verificación
        assertEquals(clientes, resultado);
    }
}
