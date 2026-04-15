package com.example.Ejemplo.Service;

import com.example.Ejemplo.Entity.Usuarios;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UsuariosService {

    List<Usuarios> getAllUsuarios();
    Usuarios getUsuariosById(Integer id);
    Usuarios saveUsuarios(Usuarios usuarios) throws RuntimeException;
    Usuarios login(String usuarios, String password);
    Usuarios registrar(String usuario, String password, String email);
    Usuarios updateUsuarios(Integer id, Usuarios usuarios);
    void deleteUsuarios(Integer id);
}
