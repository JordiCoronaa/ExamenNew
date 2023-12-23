package com.examen.mx.examen.controller;

import com.examen.mx.examen.entity.Habitacion;
import com.examen.mx.examen.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;

    @PostMapping
    public ResponseEntity<Habitacion> crearHabitacion(@RequestBody Habitacion habitacion) {
        Habitacion nuevaHabitacion = habitacionService.crearHabitacion(habitacion);
        return new ResponseEntity<>(nuevaHabitacion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Habitacion>> obtenerTodasLasHabitaciones() {
        List<Habitacion> habitaciones = habitacionService.obtenerTodasLasHabitaciones();
        return new ResponseEntity<>(habitaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtenerHabitacionPorId(@PathVariable Long id) {
        Habitacion habitacion = habitacionService.obtenerHabitacionPorId(id);
        if (habitacion != null) {
            return new ResponseEntity<>(habitacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabitacion(@PathVariable Long id) {
        habitacionService.eliminarHabitacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
