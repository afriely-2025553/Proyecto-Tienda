package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.Ventas;
import com.example.Ejemplo.Service.VentasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public List<Ventas> getAllVentas() {
        return ventasService.getAllVentas();
    }

    @PostMapping
    public ResponseEntity<Object> createVentas(@Valid @RequestBody Ventas ventas) {
        try {
            Ventas createVentas = ventasService.saveVentas(ventas);
            return new ResponseEntity<>(createVentas, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVentas(@PathVariable Integer id, @Valid @RequestBody Ventas ventas) {
        try {
            Ventas updateVentas = ventasService.updateVentas(id, ventas);
            return new ResponseEntity<>(updateVentas, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVentasById(@PathVariable Integer id) {
        try {
            Ventas searchVentas = ventasService.getVentasById(id);
            return new ResponseEntity<>(searchVentas, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVentas(@PathVariable Integer id) {
        try {
            ventasService.deleteVentas(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}