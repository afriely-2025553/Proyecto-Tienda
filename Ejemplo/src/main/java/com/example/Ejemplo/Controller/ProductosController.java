package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.Productos;
import com.example.Ejemplo.Service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {

    private final ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public List<Productos> getAllProductos() {
        return productosService.getAllProductos();
    }

    @PostMapping
    public ResponseEntity<Object> createProductos(@Valid @RequestBody Productos productos) {
        try {
            Productos createProductos = productosService.saveProductos(productos);
            return new ResponseEntity<>(createProductos, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductos(@PathVariable Integer id, @Valid @RequestBody Productos productos) {
        try {
            Productos updateProductos = productosService.updateProductos(id, productos);
            return new ResponseEntity<>(updateProductos, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductosById(@PathVariable Integer id) {
        try {
            Productos searchProductos = productosService.getProductosById(id);
            return new ResponseEntity<>(searchProductos, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductos(@PathVariable Integer id) {
        try {
            productosService.deleteProductos(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}