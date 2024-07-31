/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.controllers;

import com.gustavo.trabajotitulacion.objects.Producto;
import com.gustavo.trabajotitulacion.objects.Proveedor;
import com.gustavo.trabajotitulacion.objects.Stock;
import com.gustavo.trabajotitulacion.utils.ConnectionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gustavo
 */
public class StockController implements ICrud<Stock> {

    private Connection conn;

    public StockController() throws ClassNotFoundException, SQLException {
        this.conn = ConnectionDB.obtenerConexion();
    }

    @Override
    public boolean deleteObject(int id) {
        String SQL_DELETE = "DELETE FROM stock WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
            return false;
        }

        return true;
    }

    @Override
    public List<Stock> getAllObjects() {
        String SQL_LIST = "select * from stock where cantidad > 0 order by fecha_vencimiento asc;";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            List<Stock> listadoStocks = new ArrayList<>();

            while (rs.next()) {

                Stock filaStock = new Stock();

                ProductoController prodController = new ProductoController();
                Optional<Producto> prod = prodController.getObject(rs.getInt("productos_id"));

                ProveedorController proveedorController = new ProveedorController();
                Optional<Proveedor> prov = proveedorController.getObject(rs.getInt("proveedores_id"));

                filaStock.setId(rs.getInt("id"));
                filaStock.setCantidad(rs.getInt("cantidad"));
                filaStock.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                filaStock.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));

                if (prod.isPresent()) {
                    filaStock.setProducto(prod.get());
                }

                if (prov.isPresent()) {
                    filaStock.setProveedor(prov.get());
                }

                listadoStocks.add(filaStock);

            }
            return listadoStocks;

        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Stock> getProductosProximosAVencer(int mesesAntesVencimiento) {
        // Obtener la fecha actual y sumarle los meses para obtener la fecha límite
        LocalDate fechaLimite = LocalDate.now().plusMonths(mesesAntesVencimiento);

        // Construir la consulta SQL con la condición de fecha de vencimiento dentro del rango
        String SQL_PRODUCTOS_PROXIMOS_A_VENCER = "SELECT * FROM stock WHERE cantidad > 0 AND fecha_vencimiento BETWEEN CURRENT_DATE AND ?;";

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_PRODUCTOS_PROXIMOS_A_VENCER);
            pstmt.setDate(1, Date.valueOf(fechaLimite));

            ResultSet rs = pstmt.executeQuery();

            List<Stock> listadoStocks = new ArrayList<>();

            while (rs.next()) {
                // Resto del código para construir los objetos Stock y agregarlos a la lista
                Stock filaStock = new Stock();

                ProductoController prodController = new ProductoController();
                Optional<Producto> prod = prodController.getObject(rs.getInt("productos_id"));

                ProveedorController proveedorController = new ProveedorController();
                Optional<Proveedor> prov = proveedorController.getObject(rs.getInt("proveedores_id"));

                filaStock.setId(rs.getInt("id"));
                filaStock.setCantidad(rs.getInt("cantidad"));
                filaStock.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                filaStock.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));

                if (prod.isPresent()) {
                    filaStock.setProducto(prod.get());
                }

                if (prov.isPresent()) {
                    filaStock.setProveedor(prov.get());
                }

                listadoStocks.add(filaStock);
            }

            return listadoStocks;

        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Stock> getAllProductosVencidos() {
        String SQL_LIST = "SELECT * FROM stock WHERE fecha_vencimiento < CURRENT_DATE AND cantidad > 0;";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            List<Stock> listadoStocks = new ArrayList<>();

            while (rs.next()) {

                Stock filaStock = new Stock();

                ProductoController prodController = new ProductoController();
                Optional<Producto> prod = prodController.getObject(rs.getInt("productos_id"));

                ProveedorController proveedorController = new ProveedorController();
                Optional<Proveedor> prov = proveedorController.getObject(rs.getInt("proveedores_id"));

                filaStock.setId(rs.getInt("id"));
                filaStock.setCantidad(rs.getInt("cantidad"));
                filaStock.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                filaStock.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));

                if (prod.isPresent()) {
                    filaStock.setProducto(prod.get());
                }

                if (prov.isPresent()) {
                    filaStock.setProveedor(prov.get());
                }

                listadoStocks.add(filaStock);

            }
            return listadoStocks;

        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Stock> getAllPorProductosVencidos(int idProducto) {
        String SQL_LIST = "SELECT * FROM stock WHERE fecha_vencimiento < CURRENT_DATE AND cantidad > 0 AND productos_id=?;";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();

            List<Stock> listadoStocks = new ArrayList<>();

            while (rs.next()) {

                Stock filaStock = new Stock();

                ProductoController prodController = new ProductoController();
                Optional<Producto> prod = prodController.getObject(rs.getInt("productos_id"));

                ProveedorController proveedorController = new ProveedorController();
                Optional<Proveedor> prov = proveedorController.getObject(rs.getInt("proveedores_id"));

                filaStock.setId(rs.getInt("id"));
                filaStock.setCantidad(rs.getInt("cantidad"));
                filaStock.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                filaStock.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));

                if (prod.isPresent()) {
                    filaStock.setProducto(prod.get());
                }

                if (prov.isPresent()) {
                    filaStock.setProveedor(prov.get());
                }

                listadoStocks.add(filaStock);

            }
            return listadoStocks;

        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Stock> getAllProductosNoVencidos() {
        String SQL_LIST = "SELECT * FROM stock WHERE fecha_vencimiento > CURRENT_DATE AND cantidad > 0;";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            List<Stock> listadoStocks = new ArrayList<>();

            while (rs.next()) {

                Stock filaStock = new Stock();

                ProductoController prodController = new ProductoController();
                Optional<Producto> prod = prodController.getObject(rs.getInt("productos_id"));

                ProveedorController proveedorController = new ProveedorController();
                Optional<Proveedor> prov = proveedorController.getObject(rs.getInt("proveedores_id"));

                filaStock.setId(rs.getInt("id"));
                filaStock.setCantidad(rs.getInt("cantidad"));
                filaStock.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                filaStock.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));

                if (prod.isPresent()) {
                    filaStock.setProducto(prod.get());
                }

                if (prov.isPresent()) {
                    filaStock.setProveedor(prov.get());
                }

                listadoStocks.add(filaStock);

            }
            return listadoStocks;

        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Stock> getAllPorProductosNoVencidos(int idProducto) {
        String SQL_LIST = "SELECT * FROM stock WHERE fecha_vencimiento > CURRENT_DATE AND cantidad > 0 AND productos_id=?;";

        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_LIST);
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();

            List<Stock> listadoStocks = new ArrayList<>();

            while (rs.next()) {

                Stock filaStock = new Stock();

                ProductoController prodController = new ProductoController();
                Optional<Producto> prod = prodController.getObject(rs.getInt("productos_id"));

                ProveedorController proveedorController = new ProveedorController();
                Optional<Proveedor> prov = proveedorController.getObject(rs.getInt("proveedores_id"));

                filaStock.setId(rs.getInt("id"));
                filaStock.setCantidad(rs.getInt("cantidad"));
                filaStock.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                filaStock.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));

                if (prod.isPresent()) {
                    filaStock.setProducto(prod.get());
                }

                if (prov.isPresent()) {
                    filaStock.setProveedor(prov.get());
                }

                listadoStocks.add(filaStock);

            }
            return listadoStocks;

        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Optional<Stock> getObject(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM stock WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Stock filaStock = new Stock();

                ProductoController prodController = new ProductoController();
                Optional<Producto> prod = prodController.getObject(rs.getInt("productos_id"));

                ProveedorController proveedorController = new ProveedorController();
                Optional<Proveedor> prov = proveedorController.getObject(rs.getInt("proveedores_id"));

                filaStock.setId(rs.getInt("id"));
                filaStock.setCantidad(rs.getInt("cantidad"));
                filaStock.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                filaStock.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));

                if (prod.isPresent()) {
                    filaStock.setProducto(prod.get());
                }

                if (prov.isPresent()) {
                    filaStock.setProveedor(prov.get());
                }

                return Optional.of(filaStock);

            }

        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public boolean insertObject(Stock entity) {

        String SQL_INSERT = "insert into stock (productos_id, proveedores_id, cantidad, fecha_vencimiento, fecha_ingreso) values (?,?,?,?,?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, entity.getProducto().getId());
            preparedStatement.setInt(2, entity.getProveedor().getId());
            preparedStatement.setInt(3, entity.getCantidad());
            preparedStatement.setDate(4, entity.getFechaVencimiento());
            preparedStatement.setTimestamp(5, entity.getFechaIngreso());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
        }

        return false;
    }

    @Override
    public boolean modifiedObject(Stock entity) {
        String SQL_UPDATE = "UPDATE stock SET productos_id=?, proveedores_id=?, cantidad=?, fecha_vencimiento=?, fecha_ingreso=? WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, entity.getProducto().getId());
            preparedStatement.setInt(2, entity.getProveedor().getId());
            preparedStatement.setInt(3, entity.getCantidad());
            preparedStatement.setDate(4, entity.getFechaVencimiento());
            preparedStatement.setTimestamp(5, entity.getFechaIngreso());
            preparedStatement.setInt(6, entity.getId());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, "No se pudo modificar el registro",
                    ex);
        }

        return false;
    }

    public boolean modifiedCantidadObject(int cantidad, int idStock) {
        String SQL_UPDATE = "UPDATE stock SET cantidad=? WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setInt(2, idStock);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, "No se pudo modificar el registro",
                    ex);
        }

        return false;
    }

    public int getCantidadTodosLosProductos() {
        String SQL_LIST = "select * from stock where cantidad > 0;";
        int total = 0;

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            while (rs.next()) {
                total += rs.getInt("cantidad");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public int getCantidadPorProducto(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM stock WHERE productos_id=? ORDER BY fecha_ingreso ASC;";
        int total = 0;

        try {

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                total += rs.getInt("cantidad");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public int getCantidadTodosLosProductosVencidos() {
        String SQL_LIST = "select * from stock;";
        int total = 0;

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            LocalDate fechaActual = LocalDate.now();

            while (rs.next()) {
                // Obtener la fecha de vencimiento del resultado
                Date fechaVencimientoDate = rs.getDate("fecha_vencimiento");

                // Convertir Date a LocalDate
                LocalDate fechaVencimiento = fechaVencimientoDate.toLocalDate();

                // Comparar con la fecha actual
                if (fechaVencimiento.isBefore(fechaActual)) {
                    // Si la fecha de vencimiento es anterior a la fecha actual, acumular la cantidad
                    total += rs.getInt("cantidad");
                }
            }

            return total;
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public int getCantidadPorProductoVencido(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM stock WHERE productos_id=? ORDER BY fecha_ingreso ASC;";
        int total = 0;

        try {

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            LocalDate fechaActual = LocalDate.now();

            while (rs.next()) {
                // Obtener la fecha de vencimiento del resultado
                Date fechaVencimientoDate = rs.getDate("fecha_vencimiento");

                // Convertir Date a LocalDate
                LocalDate fechaVencimiento = fechaVencimientoDate.toLocalDate();

                // Comparar con la fecha actual
                if (fechaVencimiento.isBefore(fechaActual)) {
                    // Si la fecha de vencimiento es anterior a la fecha actual, acumular la cantidad
                    total += rs.getInt("cantidad");
                }
            }

            return total;
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public int getCantidadPorProductoNoVencido(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM stock WHERE productos_id=? ORDER BY fecha_ingreso ASC;";
        int total = 0;

        try {

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            LocalDate fechaActual = LocalDate.now();

            while (rs.next()) {
                // Obtener la fecha de vencimiento del resultado
                Date fechaVencimientoDate = rs.getDate("fecha_vencimiento");

                // Convertir Date a LocalDate
                LocalDate fechaVencimiento = fechaVencimientoDate.toLocalDate();

                // Comparar con la fecha actual
                if (fechaVencimiento.isAfter(fechaActual)) {
                    // Si la fecha de vencimiento es posterior a la fecha actual, acumular la cantidad
                    total += rs.getInt("cantidad");
                }
            }

            return total;
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public int getCantidadProductoNoVencido() {
        String SQL_QUERY_OBJECT = "SELECT * FROM stock ORDER BY fecha_ingreso ASC;";
        int total = 0;

        try {

            Statement preparedStatement = conn.createStatement();
            ResultSet rs = preparedStatement.executeQuery(SQL_QUERY_OBJECT);

            LocalDate fechaActual = LocalDate.now();

            while (rs.next()) {
                // Obtener la fecha de vencimiento del resultado
                Date fechaVencimientoDate = rs.getDate("fecha_vencimiento");

                // Convertir Date a LocalDate
                LocalDate fechaVencimiento = fechaVencimientoDate.toLocalDate();

                // Comparar con la fecha actual
                if (fechaVencimiento.isAfter(fechaActual)) {
                    // Si la fecha de vencimiento es posterior a la fecha actual, acumular la cantidad
                    total += rs.getInt("cantidad");
                }
            }

            return total;
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public boolean borrarTodosLosProductosVencidos() {
        String SQL_LIST = "select * from stock;";

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            LocalDate fechaActual = LocalDate.now();

            while (rs.next()) {
                // Obtener la fecha de vencimiento del resultado
                Date fechaVencimientoDate = rs.getDate("fecha_vencimiento");

                // Convertir Date a LocalDate
                LocalDate fechaVencimiento = fechaVencimientoDate.toLocalDate();

                // Comparar con la fecha actual
                if (fechaVencimiento.isBefore(fechaActual)) {
                    // Si la fecha de vencimiento es anterior a la fecha actual, acumular la cantidad
//                    deleteObject(rs.getInt("id"));
                    modifiedCantidadObject(0, rs.getInt("id"));
                }
            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int actualizarStock(List<Stock> entities) {

        int cantidadAfectada = 0;

        for (Stock item : entities) {
            boolean resultado = modifiedCantidadObject(item.getCantidad(), item.getId());

            if (resultado) {
                cantidadAfectada++;
            }
        }

        return cantidadAfectada;
    }

}
