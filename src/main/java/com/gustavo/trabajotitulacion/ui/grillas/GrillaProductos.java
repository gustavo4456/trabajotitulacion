/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui.grillas;

import com.gustavo.trabajotitulacion.objects.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gustavo
 */
public class GrillaProductos extends AbstractTableModel {

    private List<Producto> productos = new ArrayList<>();

    private String columnas[] = {"Id", "Nombre", "Marca", "Precio", "Ubicacion"};

    public GrillaProductos() {
    }

    public GrillaProductos(List<Producto> datos) {
        this.productos = datos;
    }

    public void setProductos(List<Producto> nuevoProducto) {
        this.productos = nuevoProducto;

        // Notificar a la tabla que los datos han cambiado
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Nombre";
            case 2:
                return "Marca";
            case 3:
                return "Precio";
            case 4:
                return "Ubicación";

            default:
                return "";
        }
    }

    @Override
    public int getColumnCount() {
        return this.columnas.length;
    }

    @Override
    public int getRowCount() {
        return this.productos.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto c = productos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getNombre().trim();
            case 2:
                return c.getMarca().trim();
            case 3:
                return c.getPrecio();
            case 4:
                return c.getUbicacion().trim();

            default:
                return "";
        }
    }

    public Producto getProducto(int rowIndex) {
        Producto p = productos.get(rowIndex);

        return p;
    }
}
