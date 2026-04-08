package com.example.Ejemplo.Repository;

import com.example.Ejemplo.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {

}
