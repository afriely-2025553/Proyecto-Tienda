package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.Usuarios;
import com.example.Ejemplo.Service.UsuariosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/acceder")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String validar(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {

        Usuarios u = usuariosService.login(username, password);

        if (u != null) {
            session.setAttribute("usuarioLogueado", u); 
            return "redirect:/home";
        }

        model.addAttribute("error", "Credenciales incorrectas");
        return "login";
    }

    @GetMapping("/")
    public String redirigir() {
        return "redirect:/acceder";
    }
}