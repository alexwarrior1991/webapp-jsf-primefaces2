package com.alejandro.webapp.jsf3.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotEmpty(message = "el campo nombre no puede ser vacio")
    private String nombre;

    @Column(name = "precio")
    @NotNull
    @Min(5)
    @Max(100000)
    private Integer precio;

    @Column(name = "fecha_registro")
    @NotNull
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Categoria categoria;

    @Column(name = "sku")
    @NotEmpty
    @Size(min = 4, max = 10)
    private String sku;

    public Producto() {
    }

    public Producto(Long id, String nombre, Integer precio, String sku) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.sku = sku;
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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    ////    @PrePersist
//    public void prePersist(){
//        fecha = LocalDate.now();
//    }



    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", fecha=" + fecha +
                ", sku='" + sku + '\'' +
                '}';
    }
}
