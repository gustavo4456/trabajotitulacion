/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.controllers;

import com.gustavo.trabajotitulacion.objects.DetalleVentaCliente;
import com.gustavo.trabajotitulacion.objects.FacturaCliente;
import com.gustavo.trabajotitulacion.objects.Cliente;
import com.gustavo.trabajotitulacion.utils.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
public class FacturaClienteController implements ICrud<FacturaCliente> {

    private Connection conn;

    public FacturaClienteController() throws ClassNotFoundException, SQLException {
        this.conn = ConnectionDB.obtenerConexion();
    }

    @Override
    public boolean deleteObject(int id) {
        String SQL_DELETE = "DELETE FROM facturas_clientes WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FacturaClienteController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
            return false;
        }

        return true;
    }

    @Override
    public List<FacturaCliente> getAllObjects() {
        String SQL_LIST = "select * from facturas_clientes;";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            List<FacturaCliente> listadoFacturaClientes = new ArrayList<>();

            while (rs.next()) {

                FacturaCliente filaFacturaCliente = new FacturaCliente();

                ClienteController cliController = new ClienteController();

                Optional<Cliente> cli = cliController.getObject(rs.getInt("clientes_id"));

                filaFacturaCliente.setId(rs.getInt("id"));
                filaFacturaCliente.setFecha(rs.getTimestamp("fecha"));
                filaFacturaCliente.setTotal(rs.getInt("total"));

                if (cli.isPresent()) {
                    filaFacturaCliente.setCliente(cli.get());
                }

                listadoFacturaClientes.add(filaFacturaCliente);

            }
            return listadoFacturaClientes;

        } catch (SQLException ex) {
            Logger.getLogger(FacturaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacturaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Optional<FacturaCliente> getObject(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM facturas_clientes WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                FacturaCliente filaFacturaCliente = new FacturaCliente();

                ClienteController cliController = new ClienteController();

                Optional<Cliente> cli = cliController.getObject(rs.getInt("clientes_id"));

                filaFacturaCliente.setId(rs.getInt("id"));
                filaFacturaCliente.setFecha(rs.getTimestamp("fecha"));
                filaFacturaCliente.setTotal(rs.getInt("total"));

                if (cli.isPresent()) {
                    filaFacturaCliente.setCliente(cli.get());
                }

                return Optional.of(filaFacturaCliente);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturaClienteController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacturaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public boolean insertObject(FacturaCliente entity) {

        String SQL_INSERT_FACTURA = "INSERT INTO facturas_clientes (fecha, total, clientes_id) VALUES (?, ?, ?) RETURNING id;";

        try {

            DetalleVentaClienteController detalleController = new DetalleVentaClienteController();

            // Insertar la factura y obtener el ID generado
            PreparedStatement preparedStatementFacturaCliente = conn.prepareStatement(SQL_INSERT_FACTURA);

            // Obtener la fecha y hora actual
            LocalDateTime fechaActual = LocalDateTime.now();

            // Crear un Timestamp a partir de la fecha y hora actual
            Timestamp timestampActual = Timestamp.valueOf(fechaActual);

            preparedStatementFacturaCliente.setTimestamp(1, timestampActual);
            preparedStatementFacturaCliente.setDouble(2, entity.getTotalCalculo());
            preparedStatementFacturaCliente.setInt(3, entity.getCliente().getId());

            ResultSet rs = preparedStatementFacturaCliente.executeQuery();

            if (rs.next()) {
                int facturaId = rs.getInt(1);

                // Insertar detalles de la factura
                if (entity.getDetalles() != null && !entity.getDetalles().isEmpty()) {
                    for (DetalleVentaCliente detalle : entity.getDetalles()) {

                        FacturaCliente facturaAgregar = new FacturaCliente();
                        facturaAgregar.setId(facturaId);

                        detalle.setFacturaCliente(facturaAgregar);
                        detalleController.insertObject(detalle);
                    }
                }

                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturaClienteController.class.getName()).log(Level.SEVERE, "No se pudo insertar la factura", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacturaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean modifiedObject(FacturaCliente entity) {
        return false;
    }
}
