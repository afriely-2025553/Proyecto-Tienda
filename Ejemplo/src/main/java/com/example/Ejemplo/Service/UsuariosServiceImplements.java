package com.example.Ejemplo.Service;

import com.example.Ejemplo.Entity.Usuarios;
import com.example.Ejemplo.Repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceImplements implements UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosServiceImplements(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public List<Usuarios> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    @Override
    public Usuarios getUsuariosById(Integer id) {
        Usuarios usuario = usuariosRepository.findById(id).orElse(null);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        return usuariosRepository.findById(id).orElse(null);
    }

    @Override
    public Usuarios saveUsuarios(Usuarios usuarios) throws RuntimeException {
        return usuariosRepository.save(usuarios);
    }

    @Override
    public Usuarios login(String usuario, String password) {
        Usuarios u = usuariosRepository.findByUsername(usuario);

        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    @Override
    public Usuarios registrar(String usuario, String password, String email) {

        if (usuariosRepository.findByUsername(usuario) != null) {
            return null;
        }

        Usuarios u = new Usuarios();
        u.setUsername(usuario);
        u.setPassword(password);
        u.setEmail(email);
        u.setEstado(1);

        return usuariosRepository.save(u);
    }

    @Override
    public Usuarios updateUsuarios(Integer id, Usuarios datos) {
        Usuarios u = usuariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        u.setUsername(datos.getUsername());
        u.setEmail(datos.getEmail());
        u.setPassword(datos.getPassword());
        u.setRol(datos.getRol());
        u.setEstado(datos.getEstado());

        return usuariosRepository.save(u);
    }

    @Override
    public void deleteUsuarios(Integer id) {
        Usuarios usuario = usuariosRepository.findById(id).orElse(null);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuariosRepository.deleteById(id);
    }
}