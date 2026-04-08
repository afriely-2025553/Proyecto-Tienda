package com.example.Ejemplo.Service;

import com.example.Ejemplo.Entity.Ventas;
import com.example.Ejemplo.Repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServiceImplements implements VentasService {

    private final VentasRepository ventasRepository;

    public VentasServiceImplements(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<Ventas> getAllVentas() {
        return ventasRepository.findAll();
    }

    @Override
    public Ventas getVentasById(Integer id) {
        Ventas venta = ventasRepository.findById(id).orElse(null);
        if (venta == null) {
            throw new IllegalArgumentException("Venta no encontrada");
        }
        return ventasRepository.findById(id).orElse(null);
    }

    @Override
    public Ventas saveVentas(Ventas ventas) throws RuntimeException {
        return ventasRepository.save(ventas);
    }

    @Override
    public Ventas updateVentas(Integer id, Ventas ventas) {
        Ventas venta = ventasRepository.findById(id).orElse(null);
        if (venta != null) {
            venta.setFecha_venta(ventas.getFecha_venta());
            venta.setTotal(ventas.getTotal());
            venta.setEstado(ventas.getEstado());
            venta.setClientes_dpi_cliente(ventas.getClientes_dpi_cliente());
            venta.setUsuarios_codigo_usuario(ventas.getUsuarios_codigo_usuario());
        } else {
            throw new IllegalArgumentException("Venta no encontrada");
        }
        return ventasRepository.save(venta);
    }

    @Override
    public void deleteVentas(Integer id) {
        Ventas venta = ventasRepository.findById(id).orElse(null);
        if (venta == null) {
            throw new IllegalArgumentException("Venta no encontrada");
        }
        ventasRepository.deleteById(id);
    }
}