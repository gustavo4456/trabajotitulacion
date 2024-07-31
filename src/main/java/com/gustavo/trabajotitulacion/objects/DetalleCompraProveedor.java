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
public class DetalleCompraProveedor {

    private int id;
    private Producto producto;
    private FacturaProveedor facturaProveedor;
    private int cantidad;
    private Date fechaVencimiento;
    private double precioUnitario;
    private double subtotal;

    public DetalleCompraProveedor() {
    }

    public DetalleCompraProveedor(int id, Producto producto, FacturaProveedor facturaProveedor, int cantidad, Date fechaVencimiento, double precio_unitario, double subtotal) {
        this.id = id;
        this.producto = producto;
        this.facturaProveedor = facturaProveedor;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public FacturaProveedor getFacturaProveedor() {
        return facturaProveedor;
    }

    public void setFacturaProveedor(FacturaProveedor facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
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
        return "DetalleCompraProveedor{" + "id=" + id + ", producto=" + producto + ", facturaProveedor=" + facturaProveedor + ", cantidad=" + cantidad + ", fechaVencimiento=" + fechaVencimiento + ", precio_unitario=" + precioUnitario + ", subtotal=" + subtotal + '}';
    }

}
