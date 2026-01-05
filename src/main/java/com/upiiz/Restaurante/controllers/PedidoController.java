package com.upiiz.Restaurante.controllers;

import com.upiiz.Restaurante.entities.DetallePedido;
import com.upiiz.Restaurante.entities.Pedido;
import com.upiiz.Restaurante.servers.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garufa/public/v1")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = pedidoService.findById(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.addPedido(pedido));
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.updatePedido(id, pedido)); // ✅ Pasar ambos
    }

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints para gestión de platillos en pedidos
    @PostMapping("/pedidos/{idPedido}/platillos/{idPlatillo}")
    public ResponseEntity<DetallePedido> addPlatilloToPedido(
            @PathVariable Long idPedido,
            @PathVariable Long idPlatillo) { // ✅ ELIMINADO precioActual
        try {
            DetallePedido detalle = pedidoService.addPlatilloToPedido(idPedido, idPlatillo);
            return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/pedidos/{idPedido}/platillos/{idPlatillo}")
    public ResponseEntity<Void> removePlatilloFromPedido(
            @PathVariable Long idPedido,
            @PathVariable Long idPlatillo) {
        try {
            pedidoService.removePlatilloFromPedido(idPedido, idPlatillo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pedidos/{idPedido}/platillos")
    public ResponseEntity<List<DetallePedido>> getPlatillosFromPedido(@PathVariable Long idPedido) {
        List<DetallePedido> detalles = pedidoService.listPlatillosOfPedido(idPedido);
        return ResponseEntity.ok(detalles);
    }
}
