/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.controllers;

import com.gustavo.trabajotitulacion.objects.Proveedor;
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
public class ProveedorController implements ICrud<Proveedor> {

    private Connection conn;

    public ProveedorController() throws ClassNotFoundException, SQLException {
        this.conn = ConnectionDB.obtenerConexion();
    }

    @Override
    public boolean insertObject(Proveedor entity) {
        String SQL_INSERT = "insert into proveedores (nombre, direccion, telefono, email) values (?,?,?,?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, entity.getNombre().trim());
            preparedStatement.setString(2, entity.getDireccion().trim());
            preparedStatement.setString(3, entity.getTelefono().trim());
            preparedStatement.setString(4, entity.getEmail().trim());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
        }

        return false;
    }

    @Override
    public boolean deleteObject(int id) {
        String SQL_DELETE = "DELETE FROM proveedores WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
            return false;
        }

        return true;
    }

    @Override
    public Optional<Proveedor> getObject(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM proveedores WHERE id=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Proveedor filaProveedor = new Proveedor();

                filaProveedor.setId(rs.getInt("id"));
                filaProveedor.setNombre(rs.getString("nombre").trim());
                filaProveedor.setDireccion(rs.getString("direccion").trim());
                filaProveedor.setTelefono(rs.getString("telefono").trim());
                filaProveedor.setEmail(rs.getString("email").trim());

                return Optional.of(filaProveedor);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);

        }

        return null;
    }

    @Override
    public boolean modifiedObject(Proveedor entity) {
        String SQL_UPDATE = "UPDATE proveedores SET nombre=?, direccion=?, telefono=?, email=? WHERE id=?;";

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
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, "No se pudo modificar el registro",
                    ex);
        }

        return false;
    }

    @Override
    public List<Proveedor> getAllObjects() {
        String SQL_LIST = "select * from proveedores;";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            List<Proveedor> listadoProveedors = new ArrayList<>();

            while (rs.next()) {

                Proveedor filaProveedor = new Proveedor();

                filaProveedor.setId(rs.getInt("id"));
                filaProveedor.setNombre(rs.getString("nombre").trim());
                filaProveedor.setDireccion(rs.getString("direccion").trim());
                filaProveedor.setTelefono(rs.getString("telefono").trim());
                filaProveedor.setEmail(rs.getString("email").trim());

                listadoProveedors.add(filaProveedor);

            }
            return listadoProveedors;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Proveedor> getAllObjectsByName(String nombre) {
        String SQL_LIST = "SELECT * FROM proveedores WHERE nombre ILIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            List<Proveedor> listadoProveedors = new ArrayList<>();

            while (rs.next()) {

                Proveedor filaProveedor = new Proveedor();

                filaProveedor.setId(rs.getInt("id"));
                filaProveedor.setNombre(rs.getString("nombre").trim());
                filaProveedor.setDireccion(rs.getString("direccion").trim());
                filaProveedor.setTelefono(rs.getString("telefono").trim());
                filaProveedor.setEmail(rs.getString("email").trim());

                listadoProveedors.add(filaProveedor);

            }
            return listadoProveedors;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Proveedor> getAllObjectsByPhone(String telefono) {
        String SQL_LIST = "SELECT * FROM proveedores WHERE telefono ILIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setString(1, "%" + telefono + "%");
            ResultSet rs = stmt.executeQuery();

            List<Proveedor> listadoProveedors = new ArrayList<>();

            while (rs.next()) {

                Proveedor filaProveedor = new Proveedor();

                filaProveedor.setId(rs.getInt("id"));
                filaProveedor.setNombre(rs.getString("nombre").trim());
                filaProveedor.setDireccion(rs.getString("direccion").trim());
                filaProveedor.setTelefono(rs.getString("telefono").trim());
                filaProveedor.setEmail(rs.getString("email").trim());

                listadoProveedors.add(filaProveedor);

            }
            return listadoProveedors;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public List<Proveedor> getAllObjectsByEmail(String email) {
        String SQL_LIST = "SELECT * FROM proveedores WHERE email ILIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setString(1, "%" + email + "%");
            ResultSet rs = stmt.executeQuery();

            List<Proveedor> listadoProveedors = new ArrayList<>();

            while (rs.next()) {

                Proveedor filaProveedor = new Proveedor();

                filaProveedor.setId(rs.getInt("id"));
                filaProveedor.setNombre(rs.getString("nombre").trim());
                filaProveedor.setDireccion(rs.getString("direccion").trim());
                filaProveedor.setTelefono(rs.getString("telefono").trim());
                filaProveedor.setEmail(rs.getString("email").trim());

                listadoProveedors.add(filaProveedor);

            }
            return listadoProveedors;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
