package com.upiiz.Restaurante.servers;

import com.upiiz.Restaurante.entities.Cliente;
import com.upiiz.Restaurante.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository; // ✅ agregar 'final'

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id)); // ✅ corregir typo
    }

    public Cliente addCliente(Cliente cliente) {
        // ✅ Validar email único si implementaste el método en repository
        // if (clienteRepository.existsByEmail(cliente.getEmail())) {
        //     throw new RuntimeException("El email ya está registrado");
        // }
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = getClienteById(id); // ✅ usa el parámetro 'id'
        clienteExistente.setNombre(cliente.getNombre()); // ✅ usa 'cliente' no 'clienteActualizado'
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefono(cliente.getTelefono());
        return clienteRepository.save(clienteExistente); // ✅ guarda 'clienteExistente'
    }

    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}