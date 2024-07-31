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
public class GrillaFacturasClientes extends AbstractTableModel {

    private List<DetalleVentaCliente> detalles = new ArrayList<>();

    private String columnas[] = {"Id Factura", "Cliente", "Fecha de venta", "Nombre", "Marca", "Fecha de vencimiento", "Cantidad", "Precio Unitario", "Subtotal", "Total"};

    public GrillaFacturasClientes() {
    }

    public GrillaFacturasClientes(List<DetalleVentaCliente> datos) {
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
                return "ID Factura";
            case 1:
                return "Cliente";
            case 2:
                return "Fecha de venta";
            case 3:
                return "Nombre";
            case 4:
                return "Marca";
            case 5:
                return "Fecha de vencimiento";
            case 6:
                return "Cantidad";
            case 7:
                return "Precio Unitario";
            case 8:
                return "Subtotal";
            case 9:
                return "Total";
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
                return d.getFacturaCliente().getId();
            case 1:
                return d.getFacturaCliente().getCliente().getNombre();
            case 2:
                return d.getFacturaCliente().getFecha();
            case 3:
                return d.getProducto().getNombre();
            case 4:
                return d.getProducto().getMarca();
            case 5:
                return d.getFechaVencimiento();
            case 6:
                return d.getCantidad();
            case 7:
                return d.getPrecioUnitario();
            case 8:
                return d.getSubtotal();
            case 9:
                return d.getFacturaCliente().getTotal();
            default:
                return "";
        }
    }

    public DetalleVentaCliente getDetalleVentaCliente(int rowIndex) {
        DetalleVentaCliente d = detalles.get(rowIndex);

        return d;
    }
}
