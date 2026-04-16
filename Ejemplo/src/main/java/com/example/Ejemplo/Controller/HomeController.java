package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.Usuarios;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {

        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/acceder";
        }

        model.addAttribute("usuario", usuario);

        return "home";
    }

    @GetMapping("/detalleVenta")
    public String detalleVenta(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/acceder";
        }
        return "detalleVenta";
    }

    @GetMapping("/productos")
    public String productos(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/acceder";
        }
        return "productos";
    }

    @GetMapping("/ventas")
    public String ventas(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/acceder";
        }
        return "ventas";
    }
}