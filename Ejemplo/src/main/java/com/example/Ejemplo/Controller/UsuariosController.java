package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.Usuarios;
import com.example.Ejemplo.Service.UsuariosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {

        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/acceder";
        }

        model.addAttribute("usuarios", usuariosService.getAllUsuarios());
        model.addAttribute("usuario", usuario); // 🔥 para Thymeleaf

        return "usuarios";
    }

    @GetMapping("/register")
    public String registro(HttpSession session) {

        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");

        if (u == null || !"ADMIN".equals(u.getRol())) {
            return "redirect:/usuarios";
        }

        return "register";
    }

    @PostMapping("/registro")
    public String guardar(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email,
                          @RequestParam String rol,
                          HttpSession session,
                          Model model) {

        Usuarios admin = (Usuarios) session.getAttribute("usuarioLogueado");

        if (admin == null || !"ADMIN".equals(admin.getRol())) {
            return "redirect:/usuarios";
        }

        Usuarios u = usuariosService.registrar(username, password, email, rol);

        if (u == null) {
            model.addAttribute("error", "Usuario ya existe");
            return "register";
        }

        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, HttpSession session) {

        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");

        if (u == null || !"ADMIN".equals(u.getRol())) {
            return "redirect:/usuarios";
        }

        Usuarios usuario = usuariosService.getUsuariosById(id);

        if (usuario == null) return "redirect:/usuarios";

        model.addAttribute("usuario", usuario);
        return "editar_usuario";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Usuarios usuario, HttpSession session) {

        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");

        if (u == null || !"ADMIN".equals(u.getRol())) {
            return "redirect:/usuarios";
        }

        usuariosService.saveUsuarios(usuario);
        return "redirect:/usuarios";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, HttpSession session) {

        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");

        if (u == null || !"ADMIN".equals(u.getRol())) {
            return "redirect:/usuarios";
        }

        usuariosService.deleteUsuarios(id);
        return "redirect:/usuarios";
    }
}