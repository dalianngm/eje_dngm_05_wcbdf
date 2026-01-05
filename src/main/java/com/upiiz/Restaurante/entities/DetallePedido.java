package com.upiiz.Restaurante.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // precio_actual - precio en el momento del pedido
    private Double precioActual;

    // Relación con Pedido
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    // Relación con Platillo
    @ManyToOne
    @JoinColumn(name = "id_platillo", nullable = false)
    private Platillo platillo;

    public DetallePedido() {}

    // Constructor que captura el precio actual del platillo
    public DetallePedido(Pedido pedido, Platillo platillo) {
        this.pedido = pedido;
        this.platillo = platillo;
        this.precioActual = platillo.getPrecio(); // Guarda el precio histórico
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getPrecioActual() { return precioActual; }
    public void setPrecioActual(Double precioActual) { this.precioActual = precioActual; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Platillo getPlatillo() { return platillo; }
    public void setPlatillo(Platillo platillo) { this.platillo = platillo; }
}
