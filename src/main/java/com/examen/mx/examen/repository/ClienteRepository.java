package com.examen.mx.examen.repository;

import com.examen.mx.examen.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByApellido(String apellido);
}
