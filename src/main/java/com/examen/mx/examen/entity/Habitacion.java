package com.examen.mx.examen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitacion;
    private String numero;
    private TipoHabitacion tipo;
    private String descripcion;
    private Integer capacidad;
    private BigDecimal precioPorNoche;

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public enum TipoHabitacion {
        INDIVIDUAL,
        DOBLE,
        SUITE
    }
}