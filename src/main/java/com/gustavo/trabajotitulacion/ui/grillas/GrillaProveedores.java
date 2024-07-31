/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui.grillas;

import com.gustavo.trabajotitulacion.objects.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gustavo
 */
public class GrillaProveedores extends AbstractTableModel {

    private List<Proveedor> proveedores = new ArrayList<>();

    private String columnas[] = {"Id", "Nombre", "Dirección", "Telefono", "Email"};

    public GrillaProveedores() {
    }

    public GrillaProveedores(List<Proveedor> datos) {
        this.proveedores = datos;
    }

    public void setProveedores(List<Proveedor> nuevoProveedor) {
        this.proveedores = nuevoProveedor;

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
                return "Dirección";
            case 3:
                return "Telefono";
            case 4:
                return "Email";

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
        return this.proveedores.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proveedor c = proveedores.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getNombre().trim();
            case 2:
                return c.getDireccion().trim();
            case 3:
                return c.getTelefono().trim();
            case 4:
                return c.getEmail().trim();

            default:
                return "";
        }
    }

    public Proveedor getProveedor(int rowIndex) {
        Proveedor p = proveedores.get(rowIndex);

        return p;
    }
}
