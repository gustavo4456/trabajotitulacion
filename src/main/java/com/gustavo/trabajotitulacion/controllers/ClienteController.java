/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.controllers;

import com.gustavo.trabajotitulacion.objects.Cliente;
import com.gustavo.trabajotitulacion.utils.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gustavo
 */
public class ClienteController implements ICrud<Cliente> {

    private Connection conn;

    public ClienteController() throws ClassNotFoundException, SQLException {
        this.conn = ConnectionDB.obtenerConexion();
    }

    @Override
    public boolean insertObject(Cliente entity) {
        String SQL_INSERT = "insert into clientes (nombre, direccion, telefono, email) values (?,?,?,?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, entity.getNombre().trim());
            preparedStatement.setString(2, entity.getDireccion().trim());
            preparedStatement.setString(3, entity.getTelefono().trim());
            preparedStatement.setString(4, entity.getEmail().trim());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
        }

        return false;
    }

    @Override
    public boolean deleteObject(int id) {
        String SQL_DELETE = "DELETE FROM clientes WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
            return false;
        }

        return true;
    }

    @Override
    public Optional<Cliente> getObject(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM clientes WHERE id=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Cliente filaCliente = new Cliente();

                filaCliente.setId(rs.getInt("id"));
                filaCliente.setNombre(rs.getString("nombre").trim());
                filaCliente.setDireccion(rs.getString("direccion").trim());
                filaCliente.setTelefono(rs.getString("telefono").trim());
                filaCliente.setEmail(rs.getString("email").trim());

                return Optional.of(filaCliente);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);

        }

        return null;
    }

    @Override
    public boolean modifiedObject(Cliente entity) {
        String SQL_UPDATE = "UPDATE clientes SET nombre=?, direccion=?, telefono=?, email=? WHERE id=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, entity.getNombre().trim());
            preparedStatement.setString(2, entity.getDireccion().trim());
            preparedStatement.setString(3, entity.getTelefono().trim());
            preparedStatement.setString(4, entity.getEmail().trim());
            preparedStatement.setInt(5, entity.getId());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "No se pudo modificar el registro",
                    ex);
        }

        return false;
    }

    @Override
    public List<Cliente> getAllObjects() {
        String SQL_LIST = "select * from clientes;";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            List<Cliente> listadoClientes = new ArrayList<>();

            while (rs.next()) {

                Cliente filaCliente = new Cliente();

                filaCliente.setId(rs.getInt("id"));
                filaCliente.setNombre(rs.getString("nombre").trim());
                filaCliente.setDireccion(rs.getString("direccion").trim());
                filaCliente.setTelefono(rs.getString("telefono").trim());
                filaCliente.setEmail(rs.getString("email").trim());

                listadoClientes.add(filaCliente);

            }
            return listadoClientes;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
     public List<Cliente> getAllObjectsByName(String nombre) {
        String SQL_LIST = "SELECT * FROM clientes WHERE nombre ILIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            List<Cliente> listadoClientes = new ArrayList<>();

            while (rs.next()) {

                Cliente filaCliente = new Cliente();

                filaCliente.setId(rs.getInt("id"));
                filaCliente.setNombre(rs.getString("nombre").trim());
                filaCliente.setDireccion(rs.getString("direccion").trim());
                filaCliente.setTelefono(rs.getString("telefono").trim());
                filaCliente.setEmail(rs.getString("email").trim());

                listadoClientes.add(filaCliente);

            }
            return listadoClientes;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Cliente> getAllObjectsByPhone(String telefono) {
        String SQL_LIST = "SELECT * FROM clientes WHERE telefono ILIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setString(1, "%" + telefono + "%");
            ResultSet rs = stmt.executeQuery();

            List<Cliente> listadoClientes = new ArrayList<>();

            while (rs.next()) {

                Cliente filaCliente = new Cliente();

                filaCliente.setId(rs.getInt("id"));
                filaCliente.setNombre(rs.getString("nombre").trim());
                filaCliente.setDireccion(rs.getString("direccion").trim());
                filaCliente.setTelefono(rs.getString("telefono").trim());
                filaCliente.setEmail(rs.getString("email").trim());

                listadoClientes.add(filaCliente);

            }
            return listadoClientes;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public List<Cliente> getAllObjectsByEmail(String email) {
        String SQL_LIST = "SELECT * FROM clientes WHERE email ILIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setString(1, "%" + email + "%");
            ResultSet rs = stmt.executeQuery();

            List<Cliente> listadoClientes = new ArrayList<>();

            while (rs.next()) {

                Cliente filaCliente = new Cliente();

                filaCliente.setId(rs.getInt("id"));
                filaCliente.setNombre(rs.getString("nombre").trim());
                filaCliente.setDireccion(rs.getString("direccion").trim());
                filaCliente.setTelefono(rs.getString("telefono").trim());
                filaCliente.setEmail(rs.getString("email").trim());

                listadoClientes.add(filaCliente);

            }
            return listadoClientes;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
