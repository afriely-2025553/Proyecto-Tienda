package com.example.Ejemplo.Service;

import com.example.Ejemplo.Entity.DetalleVenta;
import com.example.Ejemplo.Repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImplements implements DetalleVentaService{

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImplements(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> getAllDetalleVenta() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta getDetalleVentaById(Integer id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElse(null);
        if (detalleVenta == null) {
            throw new IllegalArgumentException("Detalle de venta no encontrado");
        }
        return detalleVentaRepository.findById(id).orElse(null);
    }

    @Override
    public DetalleVenta saveDetalleVenta(DetalleVenta detalleVenta) throws RuntimeException {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public DetalleVenta updateDetalleVenta(Integer id, DetalleVenta detalleVenta) {
        DetalleVenta detalle = detalleVentaRepository.findById(id).orElse(null);
        if (detalle != null) {
            detalle.setCantidad(detalleVenta.getCantidad());
            detalle.setPrecio_unitario(detalleVenta.getPrecio_unitario());
            detalle.setSubtotal(detalleVenta.getSubtotal());
            detalle.setProducto(detalleVenta.getProducto());
            detalle.setVenta(detalleVenta.getVenta());
        } else {
            throw new IllegalArgumentException("Detalle de venta no encontrado");
        }
        return detalleVentaRepository.save(detalle);
    }

    @Override
    public void deleteDetalleVenta(Integer id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElse(null);
        if (detalleVenta == null) {
            throw new IllegalArgumentException("Detalle de venta no encontrado");
        }
        detalleVentaRepository.deleteById(id);
    }
}