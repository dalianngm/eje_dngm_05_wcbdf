package com.upiiz.Restaurante.controllers;

import com.upiiz.Restaurante.entities.Cliente;
import com.upiiz.Restaurante.servers.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    //Inyeccion de dependencias
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //http://localhost:8080/
    //Crear nuevo cliente -> POST -> /garufa/public/v1/clientes
    @PostMapping("/garufa/public/v1/clientes")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.addCliente(cliente));
    }

    // Listar clientes -> GET -> /garufa/public/v1/clientes
    @GetMapping("/garufa/public/v1/clientes")
    public ResponseEntity<List<Cliente>> getClientes()
    {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    // Obtener por id -> GET -> /garufa/public/v1/clientes/{id}
    @GetMapping("/garufa/public/v1/clientes/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id)
    {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }

    // Actualizar -> PUT -> /garufa/public/v1/clientes/{id}
    @PutMapping("/garufa/public/v1/clientes/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.updateCliente(id, cliente)); // ✅ Pasar ambos parámetros
    }

    // Eliminar -> DELETE -> /garufa/public/v1/clientes/{id}
    @DeleteMapping("/garufa/public/v1/clientes/{id}")
    public ResponseEntity deleteCliente(@PathVariable Long id)
    {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
