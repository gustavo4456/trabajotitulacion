/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui.grillas;

import com.gustavo.trabajotitulacion.objects.DetalleVentaCliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gustavo
 */
public class GrillaDetallesClientes extends AbstractTableModel {

    private List<DetalleVentaCliente> detalles = new ArrayList<>();

    private String columnas[] = {"Nombre", "Marca", "Fecha de vencimiento", "Cantidad", "Precio Unitario", "Subtotal"};

    public GrillaDetallesClientes() {
    }

    public GrillaDetallesClientes(List<DetalleVentaCliente> datos) {
        this.detalles = datos;
    }

    public void setDetalleVentaClientes(List<DetalleVentaCliente> nuevosDetalleVentaClientes) {
        this.detalles = nuevosDetalleVentaClientes;

        // Notificar a la tabla que los datos han cambiado
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {

            case 0:
                return "Nombre";
            case 1:
                return "Marca";
            case 2:
                return "Fecha de Vencimiento";
            case 3:
                return "Cantidad";
            case 4:
                return "Precio Unitario";
            case 5:
                return "Subtotal";
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
        return this.detalles.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DetalleVentaCliente d = detalles.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return d.getProducto().getNombre().trim();
            case 1:
                return d.getProducto().getMarca().trim();
            case 2:
                return d.getFechaVencimiento();
            case 3:
                return d.getCantidad();
            case 4:
                return d.getPrecioUnitario();
            case 5:
                return d.getSubtotal();

            default:
                return "";
        }
    }

    public DetalleVentaCliente getDetalleVentaCliente(int rowIndex) {
        DetalleVentaCliente d = detalles.get(rowIndex);

        return d;
    }
}
