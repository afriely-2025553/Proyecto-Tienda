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
    public Usuarios updateUsuarios(Integer id, Usuarios usuarios) {
        Usuarios usuario = usuariosRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setUsername(usuarios.getUsername());
            usuario.setPassword(usuarios.getPassword());
            usuario.setEmail(usuarios.getEmail());
            usuario.setRol(usuarios.getRol());
            usuario.setEstado(usuarios.getEstado());
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        return usuariosRepository.save(usuario);
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