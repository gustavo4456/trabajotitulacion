/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.controllers;

import com.gustavo.trabajotitulacion.objects.Producto;
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
public class ProductoController implements ICrud<Producto> {

    private Connection conn;

    public ProductoController() throws ClassNotFoundException, SQLException {
        this.conn = ConnectionDB.obtenerConexion();
    }

    @Override
    public boolean insertObject(Producto entity) {
        String SQL_INSERT = "insert into productos (nombre, marca, precio, ubicacion) values (?,?,?,?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, entity.getNombre().trim());
            preparedStatement.setString(2, entity.getMarca().trim());
            preparedStatement.setDouble(3, entity.getPrecio());
            preparedStatement.setString(4, entity.getUbicacion().trim());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
        }

        return false;
    }

    @Override
    public boolean deleteObject(int id) {
        String SQL_DELETE = "DELETE FROM productos WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
            return false;
        }

        return true;
    }

    @Override
    public Optional<Producto> getObject(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM productos WHERE id=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Producto filaProducto = new Producto();

                filaProducto.setId(rs.getInt("id"));
                filaProducto.setNombre(rs.getString("nombre").trim());
                filaProducto.setMarca(rs.getString("marca").trim());
                filaProducto.setPrecio(rs.getDouble("precio"));
                filaProducto.setUbicacion(rs.getString("ubicacion").trim());

                return Optional.of(filaProducto);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);

        }

        return null;
    }

    @Override
    public boolean modifiedObject(Producto entity) {
        String SQL_UPDATE = "UPDATE productos SET nombre=?, marca=?, precio=?, ubicacion=? WHERE id=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, entity.getNombre().trim());
            preparedStatement.setString(2, entity.getMarca().trim());
            preparedStatement.setDouble(3, entity.getPrecio());
            preparedStatement.setString(4, entity.getUbicacion().trim());
            preparedStatement.setInt(5, entity.getId());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "No se pudo modificar el registro",
                    ex);
        }

        return false;
    }

    @Override
    public List<Producto> getAllObjects() {
        String SQL_LIST = "select * from productos;";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            List<Producto> listadoProductos = new ArrayList<>();

            while (rs.next()) {

                Producto filaProducto = new Producto();

                filaProducto.setId(rs.getInt("id"));
                filaProducto.setNombre(rs.getString("nombre").trim());
                filaProducto.setMarca(rs.getString("marca").trim());
                filaProducto.setPrecio(rs.getDouble("precio"));
                filaProducto.setUbicacion(rs.getString("ubicacion").trim());

                listadoProductos.add(filaProducto);

            }
            return listadoProductos;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Producto> getAllObjectsByName(String nombre) {
        String SQL_LIST = "SELECT * FROM productos WHERE nombre ILIKE ?;";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            List<Producto> listadoProductos = new ArrayList<>();

            while (rs.next()) {

                Producto filaProducto = new Producto();

                filaProducto.setId(rs.getInt("id"));
                filaProducto.setNombre(rs.getString("nombre").trim());
                filaProducto.setMarca(rs.getString("marca").trim());
                filaProducto.setPrecio(rs.getDouble("precio"));
                filaProducto.setUbicacion(rs.getString("ubicacion").trim());

                listadoProductos.add(filaProducto);

            }
            return listadoProductos;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Producto> getAllObjectsByMarca(String marca) {
        String SQL_LIST = "SELECT * FROM productos WHERE marca ILIKE ?;";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setString(1, "%" + marca + "%");
            ResultSet rs = stmt.executeQuery();

            List<Producto> listadoProductos = new ArrayList<>();

            while (rs.next()) {

                Producto filaProducto = new Producto();

                filaProducto.setId(rs.getInt("id"));
                filaProducto.setNombre(rs.getString("nombre").trim());
                filaProducto.setMarca(rs.getString("marca").trim());
                filaProducto.setPrecio(rs.getDouble("precio"));
                filaProducto.setUbicacion(rs.getString("ubicacion").trim());

                listadoProductos.add(filaProducto);

            }
            return listadoProductos;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Producto> getAllObjectsByUbicacion(String ubicacion) {
        String SQL_LIST = "SELECT * FROM productos WHERE ubicacion ILIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setString(1, "%" + ubicacion + "%");
            ResultSet rs = stmt.executeQuery();

            List<Producto> listadoProductos = new ArrayList<>();

            while (rs.next()) {

                Producto filaProducto = new Producto();

                filaProducto.setId(rs.getInt("id"));
                filaProducto.setNombre(rs.getString("nombre").trim());
                filaProducto.setMarca(rs.getString("marca").trim());
                filaProducto.setPrecio(rs.getDouble("precio"));
                filaProducto.setUbicacion(rs.getString("ubicacion").trim());

                listadoProductos.add(filaProducto);

            }
            return listadoProductos;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
