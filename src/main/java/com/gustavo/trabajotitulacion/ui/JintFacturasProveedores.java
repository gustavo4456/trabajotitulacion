/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui;

import com.gustavo.trabajotitulacion.controllers.ProveedorController;
import com.gustavo.trabajotitulacion.controllers.DetalleCompraProveedorController;
import com.gustavo.trabajotitulacion.controllers.FacturaProveedorController;
import com.gustavo.trabajotitulacion.controllers.ProductoController;
import com.gustavo.trabajotitulacion.objects.Proveedor;
import com.gustavo.trabajotitulacion.objects.DetalleCompraProveedor;
import com.gustavo.trabajotitulacion.objects.FacturaProveedor;
import com.gustavo.trabajotitulacion.objects.Producto;
import com.gustavo.trabajotitulacion.utils.ClienteConfirmadaListener;
import com.gustavo.trabajotitulacion.utils.ClienteConfirmadoEvent;
import com.gustavo.trabajotitulacion.utils.FacturaConfirmadaEvent;
import com.gustavo.trabajotitulacion.utils.FacturaConfirmadaListener;
import com.gustavo.trabajotitulacion.utils.ProductoConfirmadaListener;
import com.gustavo.trabajotitulacion.utils.ProductoConfirmadoEvent;
import com.gustavo.trabajotitulacion.utils.ProveedorConfirmadaListener;
import com.gustavo.trabajotitulacion.utils.ProveedorConfirmadoEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class JintFacturasProveedores extends javax.swing.JInternalFrame implements ProveedorConfirmadaListener, ProductoConfirmadaListener {

    private ProveedorController clienteController;
    private ProductoController productoController;

    private FacturaProveedor facturaNueva = new FacturaProveedor();
    private FacturaProveedorController facturaController;

    private DetalleCompraProveedorController detalleController;

    private List<FacturaConfirmadaListener> listeners = new ArrayList<>();
    private List<ClienteConfirmadaListener> listenersCliente = new ArrayList<>();

    public void agregarFacturaConfirmadaListener(FacturaConfirmadaListener listener) {
        listeners.add(listener);
    }

    private void notificarFacturaConfirmada() {
        FacturaConfirmadaEvent event = new FacturaConfirmadaEvent(this);
        for (FacturaConfirmadaListener listener : listeners) {
            listener.facturaConfirmada(event);
        }

    }

    public void agregarClienteConfirmadaListener(ClienteConfirmadaListener listener) {
        listenersCliente.add(listener);
    }

    private void notificarClienteConfirmada() {
        ClienteConfirmadoEvent event = new ClienteConfirmadoEvent(this);
        for (ClienteConfirmadaListener listener : listenersCliente) {
            listener.clienteConfirmada(event);
        }

    }

    /**
     * Creates new form JintFacturaProveedores
     */
    public JintFacturasProveedores() {
        initComponents();
        llenarTablaFacturaProveedores();
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
    public void proveedorConfirmada(ProveedorConfirmadoEvent event) {
        try {

            cbProveedores.removeAllItems();

            // Lógica para actualizar los labels en JintFRstock
            clienteController = new ProveedorController();

            List<Proveedor> listadoProveedores = clienteController.getAllObjects();

            for (Proveedor prov : listadoProveedores) {
                cbProveedores.addItem(prov);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFacturasClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llenarTablaFacturaProveedores() {
        try {
            detalleController = new DetalleCompraProveedorController();
            List<DetalleCompraProveedor> listadoDetalleCompraProveedor = detalleController.getAllObjects();
            grillaFacturasProveedores1.setDetalleCompraProveedores(listadoDetalleCompraProveedor);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFacturasProveedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFacturasProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llenarComboBox() {
        try {
            clienteController = new ProveedorController();
            productoController = new ProductoController();

            List<Producto> listadoProductos = productoController.getAllObjects();
            List<Proveedor> listadoProveedores = clienteController.getAllObjects();

            for (Proveedor cli : listadoProveedores) {
                cbProveedores.addItem(cli);
            }

            for (Producto prod : listadoProductos) {
                cbProducto.addItem(prod);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFacturasProveedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFacturasProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grillaDetallesProveedores1 = new com.gustavo.trabajotitulacion.ui.grillas.GrillaDetallesProveedores();
        grillaFacturasProveedores1 = new com.gustavo.trabajotitulacion.ui.grillas.GrillaFacturasProveedores();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbProducto = new javax.swing.JComboBox<>();
        cbProveedores = new javax.swing.JComboBox<>();
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
        txtVencimiento = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Compra a proveedores");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factura.png"))); // NOI18N
        jLabel1.setText("Facturas Proveedores");

        jLabel2.setText("Proveedor:");

        jLabel3.setText("Producto:");

        jLabel4.setText("Cantidad:");

        jLabel5.setText("Precio:");

        cbProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductoActionPerformed(evt);
            }
        });

        cbProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProveedoresActionPerformed(evt);
            }
        });

        txtCantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCantidad.setText("0");

        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtPrecio.setText("0");

        tbListadoProductos.setModel(grillaDetallesProveedores1);
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

        tbListadoFacturas.setModel(grillaFacturasProveedores1);
        jScrollPane2.setViewportView(tbListadoFacturas);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paquete.png"))); // NOI18N
        jLabel7.setText("Listado de productos");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lista-de-verificacion.png"))); // NOI18N
        jLabel8.setText("Listado de facturas");

        txtVencimiento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-M-d"))));

        jLabel6.setText("Vencimiento:");

        jLabel9.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        jLabel9.setText("(AAAA-MM-DD)");

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
                                            .addComponent(cbProveedores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtPrecio)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel9)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnAgregarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnQuitarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(txtVencimiento))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(273, 273, 273)
                                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)))
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
                            .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(txtVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuitarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        if (!txtCantidad.getText().trim().equals("") && !txtPrecio.getText().trim().equals("") && !txtVencimiento.getText().trim().equals("")) {
            boolean existeItem = false;
            int indiceItem = cbProducto.getSelectedIndex();

            int cantidadAingresar = Integer.parseInt(txtCantidad.getText().trim());
            double precioAingresar = Double.parseDouble(txtPrecio.getText().trim());
            String fechaVen = txtVencimiento.getText().trim();

            if (indiceItem >= 0 && cantidadAingresar > 0 && precioAingresar > 0 && !fechaVen.equals("")) {
                DetalleCompraProveedor nuevoDetalleCompraProveedor = new DetalleCompraProveedor();
                Producto productoSeleccionado = (Producto) cbProducto.getSelectedItem();

                nuevoDetalleCompraProveedor.setProducto(productoSeleccionado);
                nuevoDetalleCompraProveedor.setPrecioUnitario(Double.parseDouble(txtPrecio.getText().trim()));
                nuevoDetalleCompraProveedor.setCantidad(Integer.parseInt(txtCantidad.getText().trim()));
                nuevoDetalleCompraProveedor.setFechaVencimiento(Date.valueOf(txtVencimiento.getText().trim()));

                // Recorrer lista de detalles para saber si el item ya esta ingresado y no duplicarlos
                for (DetalleCompraProveedor detalle : facturaNueva.getDetalles()) {
                    if (detalle.getProducto().getId() == nuevoDetalleCompraProveedor.getProducto().getId() && nuevoDetalleCompraProveedor.getFechaVencimiento().equals(detalle.getFechaVencimiento())) {
                        existeItem = true;
                        detalle.setCantidad(detalle.getCantidad() + nuevoDetalleCompraProveedor.getCantidad());
                        detalle.setPrecioUnitario(Double.parseDouble(txtPrecio.getText().trim()));
                    }
                }

                if (!existeItem) {
                    facturaNueva.addDetalle(nuevoDetalleCompraProveedor);
                }

                grillaDetallesProveedores1.setDetalleCompraProveedores(facturaNueva.getDetalles());

                lblTotal.setText("Total: " + facturaNueva.getTotalCalculo());
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un item e ingreses valores mayores a cero en los campos cantidad y precio.", "Advertencia", JOptionPane.WARNING_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Advertencia", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btnAgregarItemActionPerformed

    private void btnQuitarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarItemActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tbListadoProductos.getSelectedRow();

        if (filaSeleccionada >= 0) {
            DetalleCompraProveedor detalleSeleccionado = grillaDetallesProveedores1.getDetalleCompraProveedor(filaSeleccionada);
            facturaNueva.removeDetalle(detalleSeleccionado);
            grillaDetallesProveedores1.setDetalleCompraProveedores(facturaNueva.getDetalles());
            lblTotal.setText("Total: " + facturaNueva.getTotalCalculo());
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para quitar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btnQuitarItemActionPerformed

    private void cbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductoActionPerformed
        // TODO add your handling code here:

        int indiceProducto = cbProducto.getSelectedIndex();

        if (indiceProducto >= 0) {
            Producto prod = (Producto) cbProducto.getSelectedItem();
            txtPrecio.setText(String.valueOf(prod.getPrecio()));
        }


    }//GEN-LAST:event_cbProductoActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        // TODO add your handling code here:
        int indiceProveedorSeleccionado = cbProveedores.getSelectedIndex();

        if (indiceProveedorSeleccionado >= 0) {
            if (!facturaNueva.getDetalles().isEmpty()) {
                try {
                    facturaController = new FacturaProveedorController();
                    facturaNueva.setProveedor((Proveedor) cbProveedores.getSelectedItem());
                    facturaController.insertObject(facturaNueva);
                    llenarTablaFacturaProveedores();
                    facturaNueva.getDetalles().clear();
                    grillaDetallesProveedores1.setDetalleCompraProveedores(facturaNueva.getDetalles());
                    lblTotal.setText("Total: " + facturaNueva.getTotalCalculo());

                    // Notificar a los listeners que la factura ha sido confirmada
                    notificarFacturaConfirmada();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JintFacturasProveedores.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(JintFacturasProveedores.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "La lista de productos esta vacia", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void cbProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbProveedoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarItem;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnQuitarItem;
    private javax.swing.JComboBox<Producto> cbProducto;
    private javax.swing.JComboBox<Proveedor> cbProveedores;
    private com.gustavo.trabajotitulacion.ui.grillas.GrillaDetallesProveedores grillaDetallesProveedores1;
    private com.gustavo.trabajotitulacion.ui.grillas.GrillaFacturasProveedores grillaFacturasProveedores1;
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
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tbListadoFacturas;
    private javax.swing.JTable tbListadoProductos;
    private javax.swing.JFormattedTextField txtCantidad;
    private javax.swing.JFormattedTextField txtPrecio;
    private javax.swing.JFormattedTextField txtVencimiento;
    // End of variables declaration//GEN-END:variables

}
