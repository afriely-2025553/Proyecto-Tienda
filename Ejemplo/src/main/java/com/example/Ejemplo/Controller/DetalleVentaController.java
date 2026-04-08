package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.DetalleVenta;
import com.example.Ejemplo.Service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalleVenta")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public List<DetalleVenta> getAllDetalleVenta() {
        return detalleVentaService.getAllDetalleVenta();
    }

    @PostMapping
    public ResponseEntity<Object> createDetalleVenta(@Valid @RequestBody DetalleVenta detalleVenta) {
        try {
            DetalleVenta createDetalleVenta = detalleVentaService.saveDetalleVenta(detalleVenta);
            return new ResponseEntity<>(createDetalleVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDetalleVenta(@PathVariable Integer id, @Valid @RequestBody DetalleVenta detalleVenta) {
        try {
            DetalleVenta updateDetalleVenta = detalleVentaService.updateDetalleVenta(id, detalleVenta);
            return new ResponseEntity<>(updateDetalleVenta, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDetalleVentaById(@PathVariable Integer id) {
        try {
            DetalleVenta searchDetalleVenta = detalleVentaService.getDetalleVentaById(id);
            return new ResponseEntity<>(searchDetalleVenta, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDetalleVenta(@PathVariable Integer id) {
        try {
            detalleVentaService.deleteDetalleVenta(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}