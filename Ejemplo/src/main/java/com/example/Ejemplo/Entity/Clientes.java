package com.example.Ejemplo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dpi_cliente")
    private Integer dpi_cliente;

    @Column(name = "nombre_cliente")
    private String nombre_cliente;

    @Column(name = "apellido_cliente")
    private String apellido_cliente;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado")
    private Integer estado;

    public Integer getDpi_cliente() { return dpi_cliente; }
    public void setDpi_cliente(Integer dpi_cliente) { this.dpi_cliente = dpi_cliente; }

    public String getNombre_cliente() { return nombre_cliente; }
    public void setNombre_cliente(String nombre_cliente) { this.nombre_cliente = nombre_cliente; }

    public String getApellido_cliente() { return apellido_cliente; }
    public void setApellido_cliente(String apellido_cliente) { this.apellido_cliente = apellido_cliente; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
}