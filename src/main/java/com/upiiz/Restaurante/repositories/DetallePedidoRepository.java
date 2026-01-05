package com.upiiz.Restaurante.repositories;

import com.upiiz.Restaurante.entities.DetallePedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoRepository extends CrudRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedidoId(Long pedidoId);
    Optional<DetallePedido> findByPedidoIdAndPlatilloId(Long pedidoId, Long platilloId);

    // AGREGAR ESTOS - Para los endpoints de relación
    void deleteByPedidoIdAndPlatilloId(Long pedidoId, Long platilloId);
    boolean existsByPedidoIdAndPlatilloId(Long pedidoId, Long platilloId);
}
