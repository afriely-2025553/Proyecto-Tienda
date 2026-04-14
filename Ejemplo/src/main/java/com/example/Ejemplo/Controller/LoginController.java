package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.Usuarios;
import com.example.Ejemplo.Service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UsuariosService usuariosService;

    // LOGIN
    @GetMapping("/usuario")
    public String login() {
        return "usuario";
    }

    @PostMapping("/login")
    public String validar(@RequestParam ("username")String usuario,
                          @RequestParam ("pass")String pass,
                          Model model) {

        Usuarios usuarios = usuariosService.login(usuario, pass);

        if (usuarios != null) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    // REGISTRO
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String guardar(@RequestParam String usuario,
                          @RequestParam String password,
                          Model model) {

        Usuarios usuarios = usuariosService.registrar(usuario, password);

        if (usuarios == null) {
            model.addAttribute("error", "Usuario ya existe");
            return "registro";
        }

        return "redirect:/usuario";
    }


    @GetMapping("/lista")
    public String listar(Model model) {
        List<Usuarios> lista = usuariosService.getAllUsuarios();
        model.addAttribute("usuarios", lista);
        return "lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        usuariosService.deleteUsuarios(id);
        return "redirect:/lista";
    }

    @GetMapping("/")
    public String redirigir(){
        return"redirect:/acceder";
    }


    @GetMapping("/acceder")
    public String mostrarLogin() {
        return "login";
    }

}
