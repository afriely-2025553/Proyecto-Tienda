package com.example.Ejemplo.Service;

import com.example.Ejemplo.Entity.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {

    List<DetalleVenta> getAllDetalleVenta();

    DetalleVenta getDetalleVentaById(Integer id);

    DetalleVenta saveDetalleVenta(DetalleVenta detalleVenta) throws RuntimeException;

    DetalleVenta updateDetalleVenta(Integer id, DetalleVenta detalleVenta);

    void deleteDetalleVenta(Integer id);
}
