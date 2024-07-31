/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.controllers;

import com.gustavo.trabajotitulacion.objects.DetalleCompraProveedor;
import com.gustavo.trabajotitulacion.objects.FacturaProveedor;
import com.gustavo.trabajotitulacion.objects.Proveedor;
import com.gustavo.trabajotitulacion.objects.Stock;
import com.gustavo.trabajotitulacion.utils.ConnectionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gustavo
 */
public class FacturaProveedorController implements ICrud<FacturaProveedor> {

    private Connection conn;

    public FacturaProveedorController() throws ClassNotFoundException, SQLException {
        this.conn = ConnectionDB.obtenerConexion();
    }

    @Override
    public boolean deleteObject(int id) {
        String SQL_DELETE = "DELETE FROM facturas_proveedor WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedorController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
            return false;
        }

        return true;
    }

    @Override
    public List<FacturaProveedor> getAllObjects() {
        String SQL_LIST = "select * from facturas_proveedor;";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            List<FacturaProveedor> listadoFacturaProveedores = new ArrayList<>();

            while (rs.next()) {

                FacturaProveedor filaFacturaProveedor = new FacturaProveedor();

                ProveedorController provController = new ProveedorController();

                Optional<Proveedor> prov = provController.getObject(rs.getInt("proveedores_id"));

                filaFacturaProveedor.setId(rs.getInt("id"));
                filaFacturaProveedor.setFecha(rs.getTimestamp("fecha"));
                filaFacturaProveedor.setTotal(rs.getInt("total"));

                if (prov.isPresent()) {
                    filaFacturaProveedor.setProveedor(prov.get());
                }

                listadoFacturaProveedores.add(filaFacturaProveedor);

            }
            return listadoFacturaProveedores;

        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacturaProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Optional<FacturaProveedor> getObject(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM facturas_proveedor WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                FacturaProveedor filaFacturaProveedor = new FacturaProveedor();

                ProveedorController provController = new ProveedorController();

                Optional<Proveedor> prov = provController.getObject(rs.getInt("proveedores_id"));

                filaFacturaProveedor.setId(rs.getInt("id"));
                filaFacturaProveedor.setFecha(rs.getTimestamp("fecha"));
                filaFacturaProveedor.setTotal(rs.getInt("total"));

                if (prov.isPresent()) {
                    filaFacturaProveedor.setProveedor(prov.get());
                }

                return Optional.of(filaFacturaProveedor);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedorController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacturaProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public boolean insertObject(FacturaProveedor entity) {

        String SQL_INSERT_FACTURA = "INSERT INTO facturas_proveedor (fecha, total, proveedores_id) VALUES (?, ?, ?) RETURNING id;";

        try {

            DetalleCompraProveedorController detalleController = new DetalleCompraProveedorController();
            StockController stockController = new StockController();

            // Insertar la factura y obtener el ID generado
            PreparedStatement preparedStatementFacturaProveedor = conn.prepareStatement(SQL_INSERT_FACTURA);

            // Obtener la fecha y hora actual
            LocalDateTime fechaActual = LocalDateTime.now();

            // Crear un Timestamp a partir de la fecha y hora actual
            Timestamp timestampActual = Timestamp.valueOf(fechaActual);

            preparedStatementFacturaProveedor.setTimestamp(1, timestampActual);
            preparedStatementFacturaProveedor.setDouble(2, entity.getTotalCalculo());
            preparedStatementFacturaProveedor.setInt(3, entity.getProveedor().getId());

            ResultSet rs = preparedStatementFacturaProveedor.executeQuery();

            if (rs.next()) {
                int facturaId = rs.getInt(1);

                // Insertar detalles de la factura
                if (entity.getDetalles() != null && !entity.getDetalles().isEmpty()) {
                    for (DetalleCompraProveedor detalle : entity.getDetalles()) {

                        FacturaProveedor facturaAgregar = new FacturaProveedor();
                        facturaAgregar.setId(facturaId);

                        detalle.setFacturaProveedor(facturaAgregar);
                        detalleController.insertObject(detalle);

                        // Cargando el stock
                        Stock agregarStock = new Stock();
                        agregarStock.setCantidad(detalle.getCantidad());
                        agregarStock.setFechaVencimiento(detalle.getFechaVencimiento());
                        agregarStock.setFechaIngreso(timestampActual);
                        agregarStock.setProducto(detalle.getProducto());
                        agregarStock.setProveedor(entity.getProveedor());

                        stockController.insertObject(agregarStock);
                    }
                }

                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedorController.class.getName()).log(Level.SEVERE, "No se pudo insertar la factura", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacturaProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean modifiedObject(FacturaProveedor entity) {
        return false;
    }
}
