/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui.grillas;

import com.gustavo.trabajotitulacion.objects.Stock;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gustavo
 */
public class GrillaStock extends AbstractTableModel {

    private List<Stock> listadoStock = new ArrayList<>();

    private String columnas[] = {"Id", "Producto", "Proveedor", "Cantidad", "Fecha de vencimiento", "Fecha de ingreso"};

    public GrillaStock() {
    }

    public GrillaStock(List<Stock> datos) {
        this.listadoStock = datos;
    }

    public void setStock(List<Stock> nuevoStock) {
        this.listadoStock = nuevoStock;

        // Notificar a la tabla que los datos han cambiado
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Producto";
            case 2:
                return "Proveedor";
            case 3:
                return "Cantidad";
            case 4:
                return "Fecha de vencimiento";
            case 5:
                return "Fecha de ingreso";

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
        return this.listadoStock.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Stock c = listadoStock.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getProducto().getNombre().trim() + " " + c.getProducto().getMarca().trim();
            case 2:
                return c.getProveedor().getNombre().trim();
            case 3:
                return c.getCantidad();
            case 4:
                return c.getFechaVencimiento();
            case 5:
                return c.getFechaIngreso();

            default:
                return "";
        }
    }

    public Stock getStock(int rowIndex) {
        Stock p = listadoStock.get(rowIndex);

        return p;
    }
}
