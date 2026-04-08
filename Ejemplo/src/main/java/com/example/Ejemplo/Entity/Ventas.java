package com.example.Ejemplo.Entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "Ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Integer codigo_venta;

    @Column(name = "fecha_venta")
    private LocalDate fecha_venta;

    @Column(name = "total")
    private Double total;

    @Column(name = "estado")
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "Clientes_dpi_cliente", referencedColumnName = "dpi_cliente")
    private Clientes Clientes_dpi_cliente;

    @ManyToOne
    @JoinColumn(name = "Usuarios_codigo_usuario")
    private Usuarios Usuarios_codigo_usuario;

    //generar Getters and Setters


    public Integer getCodigo_venta() {
        return codigo_venta;
    }

    public void setCodigo_venta(Integer codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    public LocalDate getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Clientes getClientes_dpi_cliente() {
        return Clientes_dpi_cliente;
    }

    public void setClientes_dpi_cliente(Clientes clientes_dpi_cliente) {
        Clientes_dpi_cliente = clientes_dpi_cliente;
    }

    public Usuarios getUsuarios_codigo_usuario() {
        return Usuarios_codigo_usuario;
    }

    public void setUsuarios_codigo_usuario(Usuarios usuarios_codigo_usuario) {
        Usuarios_codigo_usuario = usuarios_codigo_usuario;
    }
}
