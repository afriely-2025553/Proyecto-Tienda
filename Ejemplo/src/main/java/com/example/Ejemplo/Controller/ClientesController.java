package com.example.Ejemplo.Controller;

import com.example.Ejemplo.Entity.Clientes;
import com.example.Ejemplo.Entity.Usuarios;
import com.example.Ejemplo.Service.ClientesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {

        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogueado");

        if (usuario == null) return "redirect:/acceder";

        model.addAttribute("clientes", clientesService.getAllClientes());
        model.addAttribute("usuario", usuario);

        return "clientes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model, HttpSession session) {

        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");

        if (u == null || !"ADMIN".equals(u.getRol())) {
            return "redirect:/clientes";
        }

        model.addAttribute("cliente", new Clientes());

        return "register_clientes";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Clientes c, HttpSession session) {

        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");

        if (u == null || !"ADMIN".equals(u.getRol())) {
            return "redirect:/clientes";
        }

        clientesService.saveClientes(c);

        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, HttpSession session) {

        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");

        if (u == null || !"ADMIN".equals(u.getRol())) {
            return "redirect:/clientes";
        }

        Clientes cliente = clientesService.getClientesById(id);

        if (cliente == null) {
            return "redirect:/clientes";
        }

        model.addAttribute("cliente", cliente);

        return "editar_clientes";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Clientes c, HttpSession session) {

        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");

        if (u == null || !"ADMIN".equals(u.getRol())) {
            return "redirect:/clientes";
        }

        Clientes existente = clientesService.getClientesById(c.getDpi_cliente());

        if (existente == null) return "redirect:/clientes";

        existente.setNombre_cliente(c.getNombre_cliente());
        existente.setApellido_cliente(c.getApellido_cliente());
        existente.setDireccion(c.getDireccion());
        existente.setEstado(c.getEstado());

        clientesService.saveClientes(existente);

        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, HttpSession session) {

        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");

        if (u == null || !"ADMIN".equals(u.getRol())) {
            return "redirect:/clientes";
        }

        clientesService.deleteClientes(id);

        return "redirect:/clientes";
    }
}