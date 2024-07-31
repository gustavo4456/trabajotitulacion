/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.objects;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class FacturaProveedor {

    private int id;
    private Proveedor proveedor;
    private Timestamp fecha;
    private double total;
    private List<DetalleCompraProveedor> detalles;

    public FacturaProveedor() {
        detalles = new ArrayList<>();
    }

    public FacturaProveedor(int id, Proveedor proveedor, Timestamp fecha, double total) {
        detalles = new ArrayList<>();
        this.id = id;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.total = total;
    }

    public FacturaProveedor(int id, Proveedor proveedor, Timestamp fecha, double total, List<DetalleCompraProveedor> detalles) {
        this.id = id;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.total = total;
        this.detalles = detalles;
    }

    public boolean addDetalle(DetalleCompraProveedor e) {
        return detalles.add(e);
    }

    public boolean removeDetalle(Object o) {
        return detalles.remove(o);
    }

    public DetalleCompraProveedor getDetalle(int i) {
        return detalles.get(i);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public int getTotalCalculo() {
        int resultado = 0;
        for (DetalleCompraProveedor detalle : detalles) {
            resultado += detalle.getSubtotal();
        }
        return resultado;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleCompraProveedor> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompraProveedor> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "FacturaProveedor{" + "id=" + id + ", proveedor=" + proveedor + ", fecha=" + fecha + ", total=" + total + ", detalles=" + detalles + '}';
    }

}
