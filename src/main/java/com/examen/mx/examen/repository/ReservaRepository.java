package com.examen.mx.examen.repository;

import com.examen.mx.examen.entity.Cliente;
import com.examen.mx.examen.entity.Habitacion;
import com.examen.mx.examen.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByCliente(Cliente cliente);
    List<Reserva> findByHabitacion(Habitacion habitacion);
}