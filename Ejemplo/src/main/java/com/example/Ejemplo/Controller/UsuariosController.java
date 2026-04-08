package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.Usuarios;
import com.example.Ejemplo.Service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public List<Usuarios> getAllUsuarios() {
        return usuariosService.getAllUsuarios();
    }

    @PostMapping
    public ResponseEntity<Object> createUsuarios(@Valid @RequestBody Usuarios usuarios) {
        try {
            Usuarios createUsuarios = usuariosService.saveUsuarios(usuarios);
            return new ResponseEntity<>(createUsuarios, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuarios(@PathVariable Integer id, @Valid @RequestBody Usuarios usuarios) {
        try {
            Usuarios updateUsuarios = usuariosService.updateUsuarios(id, usuarios);
            return new ResponseEntity<>(updateUsuarios, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuariosById(@PathVariable Integer id) {
        try {
            Usuarios searchUsuarios = usuariosService.getUsuariosById(id);
            return new ResponseEntity<>(searchUsuarios, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuarios(@PathVariable Integer id) {
        try {
            usuariosService.deleteUsuarios(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}