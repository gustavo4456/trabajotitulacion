/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui;

import com.gustavo.trabajotitulacion.controllers.ClienteController;
import com.gustavo.trabajotitulacion.controllers.DetalleVentaClienteController;
import com.gustavo.trabajotitulacion.controllers.FacturaClienteController;
import com.gustavo.trabajotitulacion.controllers.ProductoController;
import com.gustavo.trabajotitulacion.controllers.StockController;
import com.gustavo.trabajotitulacion.objects.Cliente;
import com.gustavo.trabajotitulacion.objects.DetalleVentaCliente;
import com.gustavo.trabajotitulacion.objects.FacturaCliente;
import com.gustavo.trabajotitulacion.objects.Producto;
import com.gustavo.trabajotitulacion.objects.Stock;
import com.gustavo.trabajotitulacion.utils.ClienteConfirmadaListener;
import com.gustavo.trabajotitulacion.utils.ClienteConfirmadoEvent;
import com.gustavo.trabajotitulacion.utils.FacturaConfirmadaEvent;
import com.gustavo.trabajotitulacion.utils.FacturaConfirmadaListener;
import com.gustavo.trabajotitulacion.utils.ProductoConfirmadaListener;
import com.gustavo.trabajotitulacion.utils.ProductoConfirmadoEvent;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author gustavo
 */
public class JintFacturasClientes extends javax.swing.JInternalFrame implements FacturaConfirmadaListener, ClienteConfirmadaListener, ProductoConfirmadaListener {

    private ClienteController clienteController;
    private ProductoController productoController;

    private FacturaCliente facturaNueva = new FacturaCliente();
    private FacturaClienteController facturaController;

    private DetalleVentaClienteController detalleController;

    private StockController stockIngreController;
    private List<Stock> listadoStockAgregar = new ArrayList<>();

    private List<FacturaConfirmadaListener> listeners = new ArrayList<>();

    public void agregarFacturaConfirmadaListener(FacturaConfirmadaListener listener) {
        listeners.add(listener);
    }

    private void notificarFacturaConfirmada() {
        FacturaConfirmadaEvent event = new FacturaConfirmadaEvent(this);
        for (FacturaConfirmadaListener listener : listeners) {
            listener.facturaConfirmada(event);
        }

    }

    /**
     * Creates new form JintFacturaClientees
     */
    public JintFacturasClientes() {
        initComponents();
        llenarTablaFacturaClientees();
        llenarComboBox();
    }

    @Override
    public void productoConfirmada(ProductoConfirmadoEvent event) {
        try {

            cbProducto.removeAllItems();

            // Lógica para actualizar los labels en JintFRstock
            productoController = new ProductoController();

            List<Producto> listadoProductos = productoController.getAllObjects();

            for (Producto prod : listadoProductos) {
                cbProducto.addItem(prod);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void facturaConfirmada(FacturaConfirmadaEvent event) {
        // Lógica para actualizar los labels en JintFRstock
        seleccionarItemCbProducto();
    }

    @Override
    public void clienteConfirmada(ClienteConfirmadoEvent event) {
        try {

            cbClientes.removeAllItems();

            // Lógica para actualizar los labels en JintFRstock
            clienteController = new ClienteController();

            List<Cliente> listadoClientees = clienteController.getAllObjects();

            for (Cliente cli : listadoClientees) {
                cbClientes.addItem(cli);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void llenarTablaFacturaClientees() {
        try {
            detalleController = new DetalleVentaClienteController();
            List<DetalleVentaCliente> listadoDetalleVentaCliente = detalleController.getAllObjects();
            grillaFacturasClientes1.setDetalleVentaClientes(listadoDetalleVentaCliente);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llenarComboBox() {
        try {
            clienteController = new ClienteController();
            productoController = new ProductoController();

            List<Producto> listadoProductos = productoController.getAllObjects();
            List<Cliente> listadoClientees = clienteController.getAllObjects();

            for (Cliente cli : listadoClientees) {
                cbClientes.addItem(cli);
            }

            for (Producto prod : listadoProductos) {
                cbProducto.addItem(prod);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Para contar la cantidad dentro de la lista de detalles de un producto y fecha seleccionda
    private int obtenerCantidadTotalVencimientoSeleccionado() {
        Stock fechaVen = (Stock) cbVencimiento.getSelectedItem();
        // acumula la cantidad de la lista de detalles
        int cantidadAcumuladoDelProducto = 0;

        for (DetalleVentaCliente det : facturaNueva.getDetalles()) {
            if (det.getProducto().equals((Producto) cbProducto.getSelectedItem()) && det.getFechaVencimiento().equals(fechaVen.getFechaVencimiento())) {
                cantidadAcumuladoDelProducto += det.getCantidad();
            }
        }

        return cantidadAcumuladoDelProducto;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grillaDetallesClientes1 = new com.gustavo.trabajotitulacion.ui.grillas.GrillaDetallesClientes();
        grillaFacturasClientes1 = new com.gustavo.trabajotitulacion.ui.grillas.GrillaFacturasClientes();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbProducto = new javax.swing.JComboBox<>();
        cbClientes = new javax.swing.JComboBox<>();
        txtCantidad = new javax.swing.JFormattedTextField();
        txtPrecio = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListadoProductos = new javax.swing.JTable();
        btnAgregarItem = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        btnQuitarItem = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbListadoFacturas = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbVencimiento = new javax.swing.JComboBox<>();
        lblQuedan = new javax.swing.JLabel();
        btnProforma = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Venta a clientes");
        setToolTipText("");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factura.png"))); // NOI18N
        jLabel1.setText("Facturas a clientes");

        jLabel2.setText("Cliente:");

        jLabel3.setText("Producto:");

        jLabel4.setText("Cantidad:");

        jLabel5.setText("Precio:");

        cbProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductoActionPerformed(evt);
            }
        });

        cbClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClientesActionPerformed(evt);
            }
        });

        txtCantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCantidad.setText("0");

        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtPrecio.setText("0");

        tbListadoProductos.setModel(grillaDetallesClientes1);
        jScrollPane1.setViewportView(tbListadoProductos);

        btnAgregarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agregar-contacto.png"))); // NOI18N
        btnAgregarItem.setText("Agregar");
        btnAgregarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarItemActionPerformed(evt);
            }
        });

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disco-flexible.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnQuitarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar.png"))); // NOI18N
        btnQuitarItem.setText("Quitar");
        btnQuitarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarItemActionPerformed(evt);
            }
        });

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotal.setText("Total: 0");

        tbListadoFacturas.setModel(grillaFacturasClientes1);
        jScrollPane2.setViewportView(tbListadoFacturas);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paquete.png"))); // NOI18N
        jLabel7.setText("Listado de productos");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lista-de-verificacion.png"))); // NOI18N
        jLabel8.setText("Listado de facturas de clientes");

        jLabel6.setText("Vencimiento:");

        jLabel9.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        jLabel9.setText("(AAAA-MM-DD)");

        cbVencimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVencimientoActionPerformed(evt);
            }
        });

        lblQuedan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblQuedan.setText("Quedan: 0");

        btnProforma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pdf.png"))); // NOI18N
        btnProforma.setText("Proforma PDF");
        btnProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProformaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtPrecio)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel9)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(btnAgregarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnQuitarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(cbVencimiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(75, 75, 75)
                                        .addComponent(lblQuedan, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(158, 158, 158)
                                        .addComponent(btnProforma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuitarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuedan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProforma, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarItemActionPerformed
        // TODO add your handling code here:

        if (!txtCantidad.getText().trim().equals("") && !txtPrecio.getText().trim().equals("")) {
            boolean existeItem = false;
            boolean existeItemStock = false;
            int indiceItem = cbProducto.getSelectedIndex();
            int indiceVencimiento = cbVencimiento.getSelectedIndex();

            int cantidadAingresar = Integer.parseInt(txtCantidad.getText().trim());
            double precioAingresar = Double.parseDouble(txtPrecio.getText().trim());

            Stock fechaVen = (Stock) cbVencimiento.getSelectedItem();

            // acumula la cantidad de la lista de detalles
            int cantidadAcumuladoDelProducto = 0;

            // aca acumula la cantidad de la lista mas la cantidad que se quiere ingresar, es decir el del txtCantidad
            int totalAcunulado = 0;

            cantidadAcumuladoDelProducto = obtenerCantidadTotalVencimientoSeleccionado();

            totalAcunulado = cantidadAcumuladoDelProducto + cantidadAingresar;

            if (indiceVencimiento >= 0) {
                if (cantidadAingresar <= fechaVen.getCantidad() && totalAcunulado <= fechaVen.getCantidad()) {
                    if (indiceItem >= 0 && cantidadAingresar > 0 && precioAingresar > 0) {

                        DetalleVentaCliente nuevoDetalleVentaCliente = new DetalleVentaCliente();
                        Producto productoSeleccionado = (Producto) cbProducto.getSelectedItem();

                        nuevoDetalleVentaCliente.setProducto(productoSeleccionado);
                        nuevoDetalleVentaCliente.setPrecioUnitario(Double.parseDouble(txtPrecio.getText().trim()));
                        nuevoDetalleVentaCliente.setCantidad(Integer.parseInt(txtCantidad.getText().trim()));
                        nuevoDetalleVentaCliente.setFechaVencimiento(fechaVen.getFechaVencimiento());

                        // para el stock
                        Stock stockNuevo = new Stock();
                        stockNuevo.setCantidad(Integer.parseInt(txtCantidad.getText().trim()));
                        stockNuevo.setId(fechaVen.getId());
                        stockNuevo.setFechaIngreso(fechaVen.getFechaIngreso());
                        stockNuevo.setFechaVencimiento(fechaVen.getFechaVencimiento());
                        stockNuevo.setProducto(productoSeleccionado);
                        stockNuevo.setProveedor(fechaVen.getProveedor());

                        // Recorrer lista de detalles para saber si el item ya esta ingresado y no duplicarlos
                        for (DetalleVentaCliente detalle : facturaNueva.getDetalles()) {
                            if (detalle.getProducto().getId() == nuevoDetalleVentaCliente.getProducto().getId() && nuevoDetalleVentaCliente.getFechaVencimiento().equals(detalle.getFechaVencimiento())) {
                                existeItem = true;
                                detalle.setCantidad(detalle.getCantidad() + nuevoDetalleVentaCliente.getCantidad());
                                detalle.setPrecioUnitario(Double.parseDouble(txtPrecio.getText().trim()));

                            }
                        }

                        // Recorrer lista de listadoStock para saber si el item ya esta ingresado y no duplicarlos
                        for (Stock stockItem : listadoStockAgregar) {
                            if (stockItem.getProducto().getId() == stockNuevo.getProducto().getId() && stockNuevo.getFechaVencimiento().equals(stockItem.getFechaVencimiento())) {
                                existeItemStock = true;

                                // para stock
                                stockItem.setCantidad(stockItem.getCantidad() - stockNuevo.getCantidad());
                            }
                        }

                        if (!existeItem) {
                            facturaNueva.addDetalle(nuevoDetalleVentaCliente);

                        }

                        if (!existeItemStock) {
                            // para stock
                            stockNuevo.setCantidad(fechaVen.getCantidad() - Integer.parseInt(txtCantidad.getText().trim()));
                            listadoStockAgregar.add(stockNuevo);
                        }

                        grillaDetallesClientes1.setDetalleVentaClientes(facturaNueva.getDetalles());

                        lblTotal.setText("Total: " + facturaNueva.getTotalCalculo());
                        lblQuedan.setText("Quedan: " + (fechaVen.getCantidad() - obtenerCantidadTotalVencimientoSeleccionado()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un item e ingreses valores mayores a cero en los campos cantidad y precio.", "Advertencia", JOptionPane.WARNING_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Supero el stock, ingrese una cantida valida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Campo vencimineto vacio", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Advertencia", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnAgregarItemActionPerformed


    private void btnQuitarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarItemActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tbListadoProductos.getSelectedRow();

        if (filaSeleccionada >= 0) {
            DetalleVentaCliente detalleSeleccionado = grillaDetallesClientes1.getDetalleVentaCliente(filaSeleccionada);
            facturaNueva.removeDetalle(detalleSeleccionado);
            grillaDetallesClientes1.setDetalleVentaClientes(facturaNueva.getDetalles());
            lblTotal.setText("Total: " + facturaNueva.getTotalCalculo());

            Date fechaVencimientoDelListado = detalleSeleccionado.getFechaVencimiento();
            Date fechaVencimientoComboBox = ((Stock) cbVencimiento.getSelectedItem()).getFechaVencimiento();
            Producto prodSeleccionadoDelStock = ((Stock) cbVencimiento.getSelectedItem()).getProducto();
            Producto prodSeleccionadoDelListado = detalleSeleccionado.getProducto();

            int cantidadStockTotal = ((Stock) cbVencimiento.getSelectedItem()).getCantidad();

            if (fechaVencimientoComboBox.equals(fechaVencimientoDelListado) && prodSeleccionadoDelStock.getId() == prodSeleccionadoDelListado.getId()) {
                lblQuedan.setText("Quedan: " + (cantidadStockTotal - obtenerCantidadTotalVencimientoSeleccionado()));
            }

            for (Stock stockItem : listadoStockAgregar) {
                if (stockItem.getProducto().getId() == detalleSeleccionado.getProducto().getId() && stockItem.getFechaVencimiento().equals(detalleSeleccionado.getFechaVencimiento())) {
                    listadoStockAgregar.remove(stockItem);
                    break;
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para quitar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btnQuitarItemActionPerformed

    private void cbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductoActionPerformed
        try {
            // TODO add your handling code here:
            int indiceProducto = cbProducto.getSelectedIndex();

            if (indiceProducto >= 0) {
                Producto prod = (Producto) cbProducto.getSelectedItem();
                txtPrecio.setText(String.valueOf(prod.getPrecio()));

                StockController stockController = new StockController();
                List<Stock> listadoStock = stockController.getAllPorProductosNoVencidos(prod.getId());

                cbVencimiento.removeAllItems();

                for (Stock stockItem : listadoStock) {
                    cbVencimiento.addItem(stockItem);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_cbProductoActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        // TODO add your handling code here:
        int indiceClienteSeleccionado = cbClientes.getSelectedIndex();
        int indiceProductoSeleccionado = cbProducto.getSelectedIndex();

        if (indiceClienteSeleccionado >= 0) {
            if (!facturaNueva.getDetalles().isEmpty()) {
                try {
                    facturaController = new FacturaClienteController();
                    stockIngreController = new StockController();
                    facturaNueva.setCliente((Cliente) cbClientes.getSelectedItem());
                    facturaController.insertObject(facturaNueva);
                    stockIngreController.actualizarStock(listadoStockAgregar);

                    llenarTablaFacturaClientees();
                    facturaNueva.getDetalles().clear();
                    listadoStockAgregar.clear();
                    grillaDetallesClientes1.setDetalleVentaClientes(facturaNueva.getDetalles());
                    lblTotal.setText("Total: " + facturaNueva.getTotalCalculo());

                    if (indiceProductoSeleccionado >= 0) {
                        cbProducto.setSelectedIndex(0);
                    }

                    // Notificar a los listeners que la factura ha sido confirmada
                    notificarFacturaConfirmada();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "La lista de productos esta vacia", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void cbClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClientesActionPerformed
        // TODO add your handling code here:

        for (Stock stc : listadoStockAgregar) {
            System.out.println("Id: " + stc.getId());
            System.out.println("Cantidad: " + stc.getCantidad());
            System.out.println("Fecha vencimiento: " + stc.getFechaVencimiento());
            System.out.println("Fecha ingreso: " + stc.getFechaIngreso());
            System.out.println("Proveedor: " + stc.getProveedor());
            System.out.println("Producto: " + stc.getProducto());

        }
        System.out.println("========================================================================================");

    }//GEN-LAST:event_cbClientesActionPerformed

    private void cbVencimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVencimientoActionPerformed
        // TODO add your handling code here:

        Stock fechaVen = (Stock) cbVencimiento.getSelectedItem();
        int indiceFechaVen = cbVencimiento.getSelectedIndex();

        if (indiceFechaVen >= 0) {
            // acumula la cantidad de la lista de detalles
            int cantidadAcumuladoDelProducto = 0;

            int totalStock = fechaVen.getCantidad();

            cantidadAcumuladoDelProducto = obtenerCantidadTotalVencimientoSeleccionado();

            lblQuedan.setText("Quedan: " + (totalStock - cantidadAcumuladoDelProducto));
        } else {
            lblQuedan.setText("Quedan: 0");
        }
    }//GEN-LAST:event_cbVencimientoActionPerformed

    private void seleccionarItemCbProducto() {
        int indiceProductoSeleccionado = cbProducto.getSelectedIndex();

        if (indiceProductoSeleccionado >= 0) {
            cbProducto.setSelectedIndex(indiceProductoSeleccionado);
        }
    }

    private void seleccionarItemCbCliente() {
        int indiceClienteSeleccionado = cbClientes.getSelectedIndex();

        if (indiceClienteSeleccionado >= 0) {
            cbClientes.setSelectedIndex(indiceClienteSeleccionado);
        }
    }

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        seleccionarItemCbProducto();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProformaActionPerformed
        // TODO add your handling code here:

        List<DetalleVentaCliente> listaDetalles = facturaNueva.getDetalles();

        if (!listaDetalles.isEmpty()) {
            // Crear un JFileChooser para seleccionar la ubicación de guardado
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar PDF");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String dest = fileToSave.getAbsolutePath();

                // Asegurarse de que el archivo tenga la extensión .pdf
                if (!dest.toLowerCase().endsWith(".pdf")) {
                    dest += ".pdf";
                }

                try {
                    // Crear un documento PDF
                    PdfWriter writer = new PdfWriter(dest);
                    PdfDocument pdf = new PdfDocument(writer);
                    Document document = new Document(pdf);

                    // Crear una fuente en negrita
                    PdfFont bold = PdfFontFactory.createFont("Helvetica-Bold");

                    // Agregar título
                    document.add(new Paragraph("Proforma \n“Los Pioneros”\n"
                            + "Distribuidora de mozzarella").setFont(bold));

                    // Crear una tabla con 5 columnas
                    float[] columnWidths = {200F, 100F, 50F, 100F, 100F};
                    Table table = new Table(columnWidths);

                    // Agregar encabezados a la tabla
                    table.addCell(new Cell().add(new Paragraph("Producto").setFont(bold)));
                    table.addCell(new Cell().add(new Paragraph("Marca").setFont(bold)));
                    table.addCell(new Cell().add(new Paragraph("Cantidad").setFont(bold)));
                    table.addCell(new Cell().add(new Paragraph("Precio Unitario").setFont(bold)));
                    table.addCell(new Cell().add(new Paragraph("Subtotal").setFont(bold)));

                    // Agregar filas a la tabla
                    for (DetalleVentaCliente itm : listaDetalles) {
                        table.addCell(new Cell().add(new Paragraph(itm.getProducto().getNombre())));
                        table.addCell(new Cell().add(new Paragraph(itm.getProducto().getMarca())));
                        table.addCell(new Cell().add(new Paragraph(String.valueOf(itm.getCantidad()))));
                        table.addCell(new Cell().add(new Paragraph(String.valueOf(itm.getPrecioUnitario()))));
                        table.addCell(new Cell().add(new Paragraph(String.valueOf(itm.getSubtotal()))));

                    }

//                    // Agregar filas de ejemplo a la tabla
//                    table.addCell(new Cell().add(new Paragraph("Producto 1")));
//                    table.addCell(new Cell().add(new Paragraph("Marca 1")));
//                    table.addCell(new Cell().add(new Paragraph("10")));
//                    table.addCell(new Cell().add(new Paragraph("$5.00")));
//                    table.addCell(new Cell().add(new Paragraph("$50.00")));
//
//                    table.addCell(new Cell().add(new Paragraph("Producto 2")));
//                    table.addCell(new Cell().add(new Paragraph("Marca 2")));
//                    table.addCell(new Cell().add(new Paragraph("5")));
//                    table.addCell(new Cell().add(new Paragraph("$7.00")));
//                    table.addCell(new Cell().add(new Paragraph("$35.00")));
                    // Agregar una fila para el total
                    table.addCell(new Cell(1, 4).add(new Paragraph("Total").setFont(bold)));
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(facturaNueva.getTotalCalculo())).setFont(bold)));

                    // Agregar la tabla al documento
                    document.add(table);

                    // Cerrar el documento
                    document.close();

                    System.out.println("PDF creado en: " + dest);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException ex) {
                    Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La lista esta Vacia, debe agregar productos para generar el pdf.", "Advertencia", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnProformaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarItem;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnProforma;
    private javax.swing.JButton btnQuitarItem;
    private javax.swing.JComboBox<Cliente> cbClientes;
    private javax.swing.JComboBox<Producto> cbProducto;
    private javax.swing.JComboBox<Stock> cbVencimiento;
    private com.gustavo.trabajotitulacion.ui.grillas.GrillaDetallesClientes grillaDetallesClientes1;
    private com.gustavo.trabajotitulacion.ui.grillas.GrillaFacturasClientes grillaFacturasClientes1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblQuedan;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tbListadoFacturas;
    private javax.swing.JTable tbListadoProductos;
    private javax.swing.JFormattedTextField txtCantidad;
    private javax.swing.JFormattedTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

}
