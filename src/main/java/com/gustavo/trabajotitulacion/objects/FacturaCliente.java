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
public class FacturaCliente {

    private int id;
    private Cliente cliente;
    private Timestamp fecha;
    private int total;
    private List<DetalleVentaCliente> detalles;

    public FacturaCliente() {
        detalles = new ArrayList<>();

    }

    public FacturaCliente(int id, Cliente cliente, Timestamp fecha, int total, List<DetalleVentaCliente> detalles) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.detalles = detalles;
    }

    public boolean addDetalle(DetalleVentaCliente e) {
        return detalles.add(e);
    }

    public boolean removeDetalle(Object o) {
        return detalles.remove(o);
    }

    public DetalleVentaCliente getDetalle(int i) {
        return detalles.get(i);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalCalculo() {
        int resultado = 0;
        for (DetalleVentaCliente detalle : detalles) {
            resultado += detalle.getSubtotal();
        }
        return resultado;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DetalleVentaCliente> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaCliente> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "FacturaCliente{" + "id=" + id + ", cliente=" + cliente + ", fecha=" + fecha + ", total=" + total + ", detalles=" + detalles + '}';
    }

}
