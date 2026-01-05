package com.upiiz.Restaurante.repositories;

import com.upiiz.Restaurante.entities.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
}
