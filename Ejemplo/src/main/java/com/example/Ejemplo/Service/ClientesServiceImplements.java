package com.example.Ejemplo.Service;

import com.example.Ejemplo.Entity.Clientes;
import com.example.Ejemplo.Repository.ClientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServiceImplements implements ClientesService {

    private final ClientesRepository clientesRepository;

    public ClientesServiceImplements(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public List<Clientes> getAllClientes() {
        return clientesRepository.findAll();
    }

    @Override
    public Clientes getClientesById(Integer id) {
        Clientes cliente = clientesRepository.findById(id).orElse(null);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        return clientesRepository.findById(id).orElse(null);
    }

    @Override
    public Clientes saveClientes(Clientes clientes) throws RuntimeException {
        return clientesRepository.save(clientes);
    }

    @Override
    public Clientes updateClientes(Integer id, Clientes clientes) {
        Clientes cliente = clientesRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setNombre_cliente(clientes.getNombre_cliente());
            cliente.setApellido_cliente(clientes.getApellido_cliente());
            cliente.setDireccion(clientes.getDireccion());
            cliente.setEstado(clientes.getEstado());
        } else {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        return clientesRepository.save(cliente);
    }

    @Override
    public void deleteClientes(Integer id) {
        Clientes cliente = clientesRepository.findById(id).orElse(null);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        clientesRepository.deleteById(id);
    }
}
