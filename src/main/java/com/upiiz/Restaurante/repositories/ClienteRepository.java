package com.upiiz.Restaurante.repositories;

import com.upiiz.Restaurante.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
