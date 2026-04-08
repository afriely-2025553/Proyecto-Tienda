package com.example.Ejemplo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @GetMapping("/")
    public String redirigir(){
        return"redirect:/acceder";
    }

    // Mostrar login
    @GetMapping("/acceder")
    public String mostrarLogin() {
        return "login";
    }

    // Procesar login
    @PostMapping("/login")
    public String login(@RequestParam("user") String username,
                        @RequestParam("pass") String password) {

        if ("admin".equals(username) && "1234".equals(password)) {
            return "redirect:/home";
        } else {
            return "login";
        }
    }
}
