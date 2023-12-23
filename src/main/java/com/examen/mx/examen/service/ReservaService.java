package com.examen.mx.examen.service;
import com.examen.mx.examen.entity.Cliente;
import com.examen.mx.examen.entity.Habitacion;
import com.examen.mx.examen.entity.Reserva;
import com.examen.mx.examen.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<Reserva> obtenerReservasPorCliente(Cliente cliente) {
        return reservaRepository.findByCliente(cliente);
    }

    public List<Reserva> obtenerReservasPorHabitacion(Habitacion habitacion) {
        return reservaRepository.findByHabitacion(habitacion);
    }
}
