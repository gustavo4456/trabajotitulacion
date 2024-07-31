/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.objects;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author gustavo
 */
public class Stock {

    private int id;
    private Producto producto;
    private Proveedor proveedor;
    private int cantidad;
    private Date fechaVencimiento;
    private Timestamp fechaIngreso;

    public Stock() {
    }

    public Stock(int id, Producto producto, Proveedor proveedor, int cantidad, Date fecha_vencimiento, Timestamp fecha_ingreso) {
        this.id = id;
        this.producto = producto;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.fechaVencimiento = fecha_vencimiento;
        this.fechaIngreso = fecha_ingreso;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return fechaVencimiento.toString().trim() + " ----- unidades: " + cantidad;
    }

}
