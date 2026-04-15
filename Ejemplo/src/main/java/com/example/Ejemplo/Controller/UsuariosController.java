package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.Usuarios;
import com.example.Ejemplo.Service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public String listar(Model model) {
        List<Usuarios> lista = usuariosService.getAllUsuarios();
        model.addAttribute("usuarios", lista);
        return "usuarios";
    }


    @GetMapping("/register")
    public String registro() {
        return "register";
    }

    @PostMapping("/registro")
    public String guardar(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("email") String email,
                          Model model) {

        Usuarios u = usuariosService.registrar(username, password, email);

        if (u == null) {
            model.addAttribute("error", "Usuario ya existe");
            return "register";
        }

        return "redirect:/acceder";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Usuarios usuario = usuariosService.getUsuariosById(id);

        if (usuario == null) {
            return "redirect:/usuarios"; // evita error si no existe
        }

        model.addAttribute("usuario", usuario);
        return "editar_usuario";
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getUsuariosById(@PathVariable Integer id) {
//        try {
//            Usuarios searchUsuarios = usuariosService.getUsuariosById(id);
//            return new ResponseEntity<>(searchUsuarios, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuariosService.deleteUsuarios(id);
        return "redirect:/usuarios";
    }
}