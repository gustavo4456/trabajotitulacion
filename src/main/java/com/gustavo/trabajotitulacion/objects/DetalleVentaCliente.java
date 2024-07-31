/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.objects;

import java.sql.Date;

/**
 *
 * @author gustavo
 */
public class DetalleVentaCliente {

    private int id;
    private FacturaCliente facturaCliente;
    private Producto producto;
    private int cantidad;
    private Date fechaVencimiento;
    private double precioUnitario;
    private double subtotal;

    public DetalleVentaCliente() {
    }

    public DetalleVentaCliente(int id, FacturaCliente facturaCliente, Producto producto, int cantidad, Date fechaVencimiento, double precio_unitario, double subtotal) {
        this.id = id;
        this.facturaCliente = facturaCliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
        this.precioUnitario = precio_unitario;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FacturaCliente getFacturaCliente() {
        return facturaCliente;
    }

    public void setFacturaCliente(FacturaCliente facturaCliente) {
        this.facturaCliente = facturaCliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return precioUnitario * cantidad;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleVentaCliente{" + "id=" + id + ", facturaCliente=" + facturaCliente + ", producto=" + producto + ", cantidad=" + cantidad + ", fechaVencimiento=" + fechaVencimiento + ", precio_unitario=" + precioUnitario + ", subtotal=" + subtotal + '}';
    }

}
