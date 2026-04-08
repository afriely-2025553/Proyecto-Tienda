package com.example.Ejemplo.Repository;

import com.example.Ejemplo.Entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentasRepository extends JpaRepository<Ventas, Integer> {
}
