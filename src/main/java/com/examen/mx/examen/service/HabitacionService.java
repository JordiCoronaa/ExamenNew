package com.examen.mx.examen.service;
import com.examen.mx.examen.entity.Habitacion;
import com.examen.mx.examen.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepository;

    public Habitacion crearHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public List<Habitacion> obtenerTodasLasHabitaciones() {
        return habitacionRepository.findAll();
    }

    public Habitacion obtenerHabitacionPorId(Long id) {
        return habitacionRepository.findById(id).orElse(null);
    }

    public void eliminarHabitacion(Long id) {
        habitacionRepository.deleteById(id);
    }
}
