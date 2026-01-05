package com.upiiz.Restaurante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "platillo")

public class Platillo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre del platillo es obligatorio")
    private String nombre;
    private String descripcion;
    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    public Platillo() {}

    public Platillo(String nombre, Long id, String descripcion, Double precio) {
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
