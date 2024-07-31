/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.controllers;

import com.gustavo.trabajotitulacion.objects.DetalleCompraProveedor;
import com.gustavo.trabajotitulacion.objects.FacturaProveedor;
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
public class DetalleCompraProveedorController implements ICrud<DetalleCompraProveedor> {

    private Connection conn;

    public DetalleCompraProveedorController() throws ClassNotFoundException, SQLException {
        this.conn = ConnectionDB.obtenerConexion();
    }

    @Override
    public boolean deleteObject(int id) {
        String SQL_DELETE = "DELETE FROM detalle_compra_proveedores WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DetalleCompraProveedorController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
            return false;
        }

        return true;
    }

    @Override
    public List<DetalleCompraProveedor> getAllObjects() {
        String SQL_LIST = "select * from detalle_compra_proveedores;";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LIST);

            List<DetalleCompraProveedor> listadoDetalleCompraProveedor = new ArrayList<>();

            while (rs.next()) {

                DetalleCompraProveedor filaDetalleCompraProveedor = new DetalleCompraProveedor();

                ProductoController prodController = new ProductoController();
                Optional<Producto> prod = prodController.getObject(rs.getInt("productos_id"));

                FacturaProveedorController facturaController = new FacturaProveedorController();
                Optional<FacturaProveedor> fac = facturaController.getObject(rs.getInt("facturas_id"));

                filaDetalleCompraProveedor.setId(rs.getInt("id"));
                filaDetalleCompraProveedor.setCantidad(rs.getInt("cantidad"));
                filaDetalleCompraProveedor.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                filaDetalleCompraProveedor.setPrecioUnitario(rs.getDouble("precio_unitario"));
                filaDetalleCompraProveedor.setSubtotal(rs.getDouble("subtotal"));

                if (prod.isPresent()) {
                    filaDetalleCompraProveedor.setProducto(prod.get());
                }

                if (fac.isPresent()) {
                    filaDetalleCompraProveedor.setFacturaProveedor(fac.get());
                }

                listadoDetalleCompraProveedor.add(filaDetalleCompraProveedor);

            }
            return listadoDetalleCompraProveedor;

        } catch (SQLException ex) {
            Logger.getLogger(DetalleCompraProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetalleCompraProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Optional<DetalleCompraProveedor> getObject(int id) {
        String SQL_QUERY_OBJECT = "SELECT * FROM detalle_compra_proveedores WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_OBJECT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                DetalleCompraProveedor filaDetalleCompraProveedor = new DetalleCompraProveedor();

                ProductoController prodController = new ProductoController();
                Optional<Producto> prod = prodController.getObject(rs.getInt("productos_id"));

                FacturaProveedorController facturaController = new FacturaProveedorController();
                Optional<FacturaProveedor> fac = facturaController.getObject(rs.getInt("facturas_id"));

                filaDetalleCompraProveedor.setId(rs.getInt("id"));
                filaDetalleCompraProveedor.setCantidad(rs.getInt("cantidad"));
                filaDetalleCompraProveedor.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                filaDetalleCompraProveedor.setPrecioUnitario(rs.getDouble("precio_unitario"));
                filaDetalleCompraProveedor.setSubtotal(rs.getDouble("subtotal"));

                if (prod.isPresent()) {
                    filaDetalleCompraProveedor.setProducto(prod.get());
                }

                if (fac.isPresent()) {
                    filaDetalleCompraProveedor.setFacturaProveedor(fac.get());
                }

                return Optional.of(filaDetalleCompraProveedor);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DetalleCompraProveedorController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetalleCompraProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public boolean insertObject(DetalleCompraProveedor entity) {

        String SQL_INSERT = "insert into detalle_compra_proveedores (cantidad, fecha_vencimiento, precio_unitario, subtotal, productos_id, facturas_id) values (?,?,?,?,?,?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, entity.getCantidad());
            preparedStatement.setDate(2, entity.getFechaVencimiento());
            preparedStatement.setDouble(3, entity.getPrecioUnitario());
            preparedStatement.setDouble(4, entity.getSubtotal());
            preparedStatement.setInt(5, entity.getProducto().getId());
            preparedStatement.setInt(6, entity.getFacturaProveedor().getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DetalleCompraProveedorController.class.getName()).log(Level.SEVERE, "no se puedo guardar los datos",
                    ex);
        }

        return false;
    }

    @Override
    public boolean modifiedObject(DetalleCompraProveedor entity) {
        String SQL_UPDATE = "UPDATE detalle_compra_proveedores SET cantidad=?, fecha_vencimiento=?, precio_unitario=?, subtotal=?, productos_id=? WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, entity.getCantidad());
            preparedStatement.setDate(2, entity.getFechaVencimiento());
            preparedStatement.setDouble(3, entity.getPrecioUnitario());
            preparedStatement.setDouble(4, entity.getSubtotal());
            preparedStatement.setInt(5, entity.getProducto().getId());
            preparedStatement.setInt(6, entity.getId());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleCompraProveedorController.class.getName()).log(Level.SEVERE, "No se pudo modificar el registro",
                    ex);
        }

        return false;
    }

    public int getCantidadVendidaPorMesAnio(int mes, int anio) {
        String SQL_QUERY_CANTIDAD_VENDIDA = "SELECT SUM(dcp.cantidad) AS totalCantidad "
                + "FROM detalle_compra_proveedores dcp "
                + "JOIN facturas_proveedor fp ON dcp.facturas_id = fp.id "
                + "WHERE EXTRACT(YEAR FROM fp.fecha) = ? AND EXTRACT(MONTH FROM fp.fecha) = ?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_CANTIDAD_VENDIDA);
            preparedStatement.setInt(1, anio);
            preparedStatement.setInt(2, mes);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt("totalCantidad");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DetalleVentaClienteController.class.getName()).log(Level.SEVERE, "Error al obtener la cantidad vendida por mes y año",
                    ex);
        }

        return 0;  // Si no se encuentra ninguna cantidad, retornar 0.
    }

    public int getCantidadVendidaPorProductoMesAnio(int mes, int anio, int idProducto) {
        String SQL_QUERY_CANTIDAD_VENDIDA = "SELECT SUM(dcp.cantidad) AS totalCantidad "
                + "FROM detalle_compra_proveedores dcp "
                + "JOIN facturas_proveedor fp ON dcp.facturas_id = fp.id "
                + "WHERE EXTRACT(YEAR FROM fp.fecha) = ? AND EXTRACT(MONTH FROM fp.fecha) = ? AND dcp.productos_id = ?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_CANTIDAD_VENDIDA);
            preparedStatement.setInt(1, anio);
            preparedStatement.setInt(2, mes);
            preparedStatement.setInt(3, idProducto);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt("totalCantidad");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DetalleVentaClienteController.class.getName()).log(Level.SEVERE, "Error al obtener la cantidad vendida por mes y año",
                    ex);
        }

        return 0;  // Si no se encuentra ninguna cantidad, retornar 0.
    }

    public int getDineroPorVentasPorMesAnio(int mes, int anio) {
        String SQL_QUERY_CANTIDAD_VENDIDA = "SELECT SUM(dcp.precio_unitario) AS precioUnitario "
                + "FROM detalle_compra_proveedores dcp "
                + "JOIN facturas_proveedor fp ON dcp.facturas_id = fp.id "
                + "WHERE EXTRACT(YEAR FROM fp.fecha) = ? AND EXTRACT(MONTH FROM fp.fecha) = ?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_CANTIDAD_VENDIDA);
            preparedStatement.setInt(1, anio);
            preparedStatement.setInt(2, mes);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt("precioUnitario");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DetalleVentaClienteController.class.getName()).log(Level.SEVERE, "Error al obtener el dinero todal de venta por mes y año",
                    ex);
        }

        return 0;  // Si no se encuentra ninguna cantidad, retornar 0.
    }

    public int getDineroVentasPorProductoMesAnio(int mes, int anio, int idProducto) {
        String SQL_QUERY_CANTIDAD_VENDIDA = "SELECT SUM(dcp.precio_unitario) AS precioUnitario "
                + "FROM detalle_compra_proveedores dcp "
                + "JOIN facturas_proveedor fp ON dcp.facturas_id = fp.id "
                + "WHERE EXTRACT(YEAR FROM fp.fecha) = ? AND EXTRACT(MONTH FROM fp.fecha) = ? AND dcp.productos_id = ?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_CANTIDAD_VENDIDA);
            preparedStatement.setInt(1, anio);
            preparedStatement.setInt(2, mes);
            preparedStatement.setInt(2, idProducto);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt("precioUnitario");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DetalleVentaClienteController.class.getName()).log(Level.SEVERE, "Error al obtener el dinero todal de venta por mes y año",
                    ex);
        }

        return 0;  // Si no se encuentra ninguna cantidad, retornar 0.
    }

}
