package com.upiiz.Restaurante.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private Double total = 0.0;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "pedido_platillo",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "platillo_id")
    )
    private List<Platillo> platillos = new ArrayList<>();

    public Pedido() {
        this.fecha = LocalDate.now();
    }

    // ===== GETTERS Y SETTERS =====

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Platillo> getPlatillos() {
        return platillos;
    }

    public void setPlatillos(List<Platillo> platillos) {
        this.platillos = platillos;
        recalculateTotal();
    }

    // ===== MÉTODO CLAVE =====
    public void recalculateTotal() {
        this.total = platillos.stream()
                .mapToDouble(Platillo::getPrecio)
                .sum();
    }
}


