package com.upiiz.Restaurante.servers;

import com.upiiz.Restaurante.entities.DetallePedido;
import com.upiiz.Restaurante.entities.Pedido;
import com.upiiz.Restaurante.entities.Platillo;
import com.upiiz.Restaurante.repositories.DetallePedidoRepository;
import com.upiiz.Restaurante.repositories.PedidoRepository;
import com.upiiz.Restaurante.repositories.PlatilloRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PlatilloRepository platilloRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, PlatilloRepository platilloRepository,
                         DetallePedidoRepository detallePedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.platilloRepository = platilloRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

    // ✅ CREAR PEDIDO - Con validaciones
    public Pedido addPedido(Pedido pedido) {
        if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
            throw new RuntimeException("El pedido debe tener un cliente asociado");
        }
        pedido.setTotal(0.0); // Inicializar total
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getAllPedidos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    public Pedido updatePedido(Long id, Pedido pedidoActualizado) {
        Pedido pedidoExistente = findById(id);

        pedidoExistente.setFecha(pedidoActualizado.getFecha());
        pedidoExistente.setCliente(pedidoActualizado.getCliente());

        return pedidoRepository.save(pedidoExistente);
    }

    public void deletePedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado con id: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    // ✅ AGREGAR PLATILLO - Corregido
    public DetallePedido addPlatilloToPedido(Long pedidoId, Long platilloId) {
        Pedido pedido = findById(pedidoId);
        Platillo platillo = platilloRepository.findById(platilloId)
                .orElseThrow(() -> new RuntimeException("Platillo no encontrado"));

        // ✅ Verificar si ya existe el platillo en el pedido
        if (detallePedidoRepository.existsByPedidoIdAndPlatilloId(pedidoId, platilloId)) {
            throw new RuntimeException("El platillo ya está en el pedido");
        }

        // ✅ Usar constructor que captura precio automáticamente
        DetallePedido detalle = new DetallePedido(pedido, platillo);
        detallePedidoRepository.save(detalle);

        // ✅ Recalcular total
        pedido.recalculateTotal();
        pedidoRepository.save(pedido);

        return detalle;
    }

    // ✅ QUITAR PLATILLO - Corregido
    public void removePlatilloFromPedido(Long pedidoId, Long platilloId) {
        DetallePedido detalle = detallePedidoRepository
                .findByPedidoIdAndPlatilloId(pedidoId, platilloId)
                .orElseThrow(() -> new RuntimeException("Platillo no encontrado en el pedido"));

        detallePedidoRepository.delete(detalle);

        // ✅ Actualizar pedido
        Pedido pedido = findById(pedidoId);
        pedido.recalculateTotal();
        pedidoRepository.save(pedido);
    }

    public List<DetallePedido> listPlatillosOfPedido(Long pedidoId) {
        if (!pedidoRepository.existsById(pedidoId)) {
            throw new RuntimeException("Pedido no encontrado con id: " + pedidoId);
        }
        return detallePedidoRepository.findByPedidoId(pedidoId);
    }
}