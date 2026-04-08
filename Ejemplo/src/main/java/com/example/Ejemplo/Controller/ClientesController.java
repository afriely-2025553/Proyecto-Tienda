package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.Clientes;
import com.example.Ejemplo.Service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public List<Clientes> getAllClientes() {
        return clientesService.getAllClientes();
    }

    @PostMapping
    public ResponseEntity<Object> createClientes(@Valid @RequestBody Clientes clientes) {
        try {
            Clientes createClientes = clientesService.saveClientes(clientes);
            return new ResponseEntity<>(createClientes, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClientes(@PathVariable Integer id, @Valid @RequestBody Clientes clientes) {
        try {
            Clientes updateClientes = clientesService.updateClientes(id, clientes);
            return new ResponseEntity<>(updateClientes, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientesById(@PathVariable Integer id) {
        try {
            Clientes searchClientes = clientesService.getClientesById(id);
            return new ResponseEntity<>(searchClientes, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClientes(@PathVariable Integer id) {
        try {
            clientesService.deleteClientes(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
