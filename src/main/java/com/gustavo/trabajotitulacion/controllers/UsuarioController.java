/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.controllers;

import com.gustavo.trabajotitulacion.objects.Usuario;
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
public class UsuarioController implements ICrud<Usuario> {

    private Connection conn;

    public UsuarioController() throws ClassNotFoundException, SQLException {
        this.conn = ConnectionDB.obtenerConexion();
    }

    @Override
    public boolean insertObject(Usuario entity) {
        String SQL_INSERT = "insert into usuarios (usuario, clave, privilegios) values (?,?,?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, entity.getUsuarioNombre().trim());
            preparedStatement.setString(2, entity.getClave().trim());
            preparedStatement.setString(3, entity.getPrivilegios().trim());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
        }

        return false;
    }

    @Override
    public boolean deleteObject(int id) {
        String SQL_DELETE = "DELETE FROM usuarios WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
            return false;
        }

        return true;
    }

    @Override
    public Optional<Usuario> getObject(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM usuarios WHERE id=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Usuario filaUsuario = new Usuario();

                filaUsuario.setId(rs.getInt("id"));
                filaUsuario.setUsuarioNombre(rs.getString("usuario").trim());
                filaUsuario.setClave(rs.getString("clave").trim());
                filaUsuario.setPrivilegios(rs.getString("privilegios").trim());

                return Optional.of(filaUsuario);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);

        }

        return null;
    }

    @Override
    public boolean modifiedObject(Usuario entity) {
        String SQL_UPDATE = "UPDATE usuarios SET usuario=?, clave=?, privilegios=? WHERE id=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, entity.getUsuarioNombre().trim());
            preparedStatement.setString(2, entity.getClave().trim());
            preparedStatement.setString(3, entity.getPrivilegios().trim());
            preparedStatement.setInt(4, entity.getId());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, "No se pudo modificar el registro",
                    ex);
        }

        return false;
    }

    @Override
    public List<Usuario> getAllObjects() {
        String SQL_LIST = "select * from usuarios;";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            List<Usuario> listadoUsuarios = new ArrayList<>();

            while (rs.next()) {

                Usuario filaUsuario = new Usuario();

                filaUsuario.setId(rs.getInt("id"));
                filaUsuario.setUsuarioNombre(rs.getString("usuario").trim());
                filaUsuario.setClave(rs.getString("clave").trim());
                filaUsuario.setPrivilegios(rs.getString("privilegios").trim());

                listadoUsuarios.add(filaUsuario);

            }
            return listadoUsuarios;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Optional<Usuario> autenticarUsuario(String username, String password) {
        String SQL_AUTHENTICATE = "SELECT * FROM usuarios WHERE usuario=? AND clave=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_AUTHENTICATE);
            preparedStatement.setString(1, username.trim());
            preparedStatement.setString(2, password.trim());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                // Si hay una coincidencia, devolver un Optional con el Usuario
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsuarioNombre(rs.getString("usuario").trim());
                usuario.setClave(rs.getString("clave").trim());
                usuario.setPrivilegios(rs.getString("privilegios").trim());

                return Optional.of(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, "Error durante la autenticación",
                    ex);
        }

        return Optional.empty();
    }
}
