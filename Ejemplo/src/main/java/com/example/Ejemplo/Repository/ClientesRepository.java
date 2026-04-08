package com.example.Ejemplo.Repository;

import com.example.Ejemplo.Entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
}
