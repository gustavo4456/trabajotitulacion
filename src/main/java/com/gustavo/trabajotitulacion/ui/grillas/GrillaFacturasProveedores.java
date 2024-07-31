/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui.grillas;

import com.gustavo.trabajotitulacion.objects.DetalleCompraProveedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gustavo
 */
public class GrillaFacturasProveedores extends AbstractTableModel {

    private List<DetalleCompraProveedor> detalles = new ArrayList<>();

    private String columnas[] = {"Id Factura", "Proveedor", "Fecha ingreso", "Nombre", "Marca", "Fecha de vencimiento", "Cantidad", "Precio Unitario", "Subtotal", "Total"};

    public GrillaFacturasProveedores() {
    }

    public GrillaFacturasProveedores(List<DetalleCompraProveedor> datos) {
        this.detalles = datos;
    }

    public void setDetalleCompraProveedores(List<DetalleCompraProveedor> nuevosDetalleCompraProveedors) {
        this.detalles = nuevosDetalleCompraProveedors;

        // Notificar a la tabla que los datos han cambiado
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "ID Factura";
            case 1:
                return "Proveedor";
            case 2:
                return "Fecha ingreso";
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
        DetalleCompraProveedor d = detalles.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return d.getFacturaProveedor().getId();
            case 1:
                return d.getFacturaProveedor().getProveedor().getNombre();
            case 2:
                return d.getFacturaProveedor().getFecha();
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
                return d.getFacturaProveedor().getTotal();
            default:
                return "";
        }
    }

    public DetalleCompraProveedor getDetalleCompraProveedor(int rowIndex) {
        DetalleCompraProveedor d = detalles.get(rowIndex);

        return d;
    }
}
