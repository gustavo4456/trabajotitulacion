/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui;

import com.gustavo.trabajotitulacion.controllers.ProductoController;
import com.gustavo.trabajotitulacion.objects.Producto;
import com.gustavo.trabajotitulacion.utils.ProductoConfirmadaListener;
import com.gustavo.trabajotitulacion.utils.ProductoConfirmadoEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class IntFrProductos extends javax.swing.JInternalFrame {

    private boolean paraGuardar = false;

    private ProductoController productoController;

    private List<ProductoConfirmadaListener> listeners = new ArrayList<>();

    public void agregarProductoConfirmadaListener(ProductoConfirmadaListener listener) {
        listeners.add(listener);
    }

    private void notificarProductoConfirmada() {
        ProductoConfirmadoEvent event = new ProductoConfirmadoEvent(this);
        for (ProductoConfirmadaListener listener : listeners) {
            listener.productoConfirmada(event);
        }

    }

    /**
     * Creates new form IntFrProducto
     */
    public IntFrProductos() {
        initComponents();
        llenarTabla();
    }

    public void llenarTabla() {
        try {
            productoController = new ProductoController();
            List<Producto> listadoProductos = productoController.getAllObjects();
            grillaProductos1.setProductos(listadoProductos);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IntFrProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IntFrProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void habilitarBotones(boolean btnNuevo, boolean btnEditar, boolean btnCancelar, boolean btnGuardar) {
        this.btnNuevo.setEnabled(btnNuevo);
        this.btnEditar.setEnabled(btnEditar);
        this.btnCancelar.setEnabled(btnCancelar);
        this.btnGuardar.setEnabled(btnGuardar);
    }

    public void habilitarTextBox(boolean estaHabilitado) {
        txtNombre.setEnabled(estaHabilitado);
        txtUbicacion.setEnabled(estaHabilitado);
        txtPrecio.setEnabled(estaHabilitado);
        txtMarca.setEnabled(estaHabilitado);
    }

    public void limpiarTextBox() {
        txtNombre.setText("");
        txtUbicacion.setText("");
        txtPrecio.setText("");
        txtMarca.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grillaProductos1 = new com.gustavo.trabajotitulacion.ui.grillas.GrillaProductos();
        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtUbicacion = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lblTipoOperacion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        cbOpcionesBusqueda = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtPrecio = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Productos");

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/producto.png"))); // NOI18N
        jLabel1.setText("Gestión de productos");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Marca:");

        jLabel4.setText("Precio:");

        jLabel5.setText("Ubicación:");

        txtNombre.setEnabled(false);

        txtUbicacion.setEnabled(false);

        txtMarca.setEnabled(false);

        tbProductos.setModel(grillaProductos1);
        tbProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProductos);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agregar-contacto.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lapices.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/boton-x.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disco-flexible.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel6.setText("Filtrar productos");

        cbOpcionesBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por nombre", "Por marca", "Por ubicacion" }));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-encuentra-hombre-usuario-32.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPrecio.setEnabled(false);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre)
                                    .addComponent(txtUbicacion)
                                    .addComponent(txtMarca)
                                    .addComponent(txtPrecio)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(txtBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbOpcionesBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTipoOperacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6))
                                .addGap(131, 131, 131)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditar)
                            .addComponent(btnNuevo)
                            .addComponent(btnCancelar))
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(lblTipoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbOpcionesBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar))
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        paraGuardar = true;
        limpiarTextBox();
        habilitarTextBox(true);
        habilitarBotones(false, false, true, true);
        lblTipoOperacion.setText("Operacion: agregar producto");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tbProductos.getSelectedRow();
        boolean estanVacios = txtMarca.getText().equals("") && txtUbicacion.getText().equals("") && txtPrecio.getText().equals("") && txtNombre.getText().equals("");

        if (filaSeleccionada >= 0 && !estanVacios) {
            paraGuardar = false;
            habilitarTextBox(true);
            habilitarBotones(false, false, true, true);
            lblTipoOperacion.setText("Operacion: modificar producto");
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para editar.", "Informacion", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        paraGuardar = false;
        limpiarTextBox();
        habilitarTextBox(false);
        habilitarBotones(true, true, false, false);
        lblTipoOperacion.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            // TODO add your handling code here:
            boolean estanVacios = txtMarca.getText().trim().equals("") && txtUbicacion.getText().trim().equals("") && txtPrecio.getText().trim().equals("") && txtNombre.getText().trim().equals("");

            productoController = new ProductoController();

            int filaSeleccionada = tbProductos.getSelectedRow();

            String cadenaConComa = txtPrecio.getText().trim();
            String cadenaConPunto = cadenaConComa.replace(',', '.');

            if (paraGuardar) {
                if (!estanVacios) {
                    Producto productoNuevo = new Producto();
                    productoNuevo.setMarca(txtMarca.getText().trim());
                    productoNuevo.setUbicacion(txtUbicacion.getText().trim());
                    productoNuevo.setNombre(txtNombre.getText().trim());
                    productoNuevo.setPrecio(Double.parseDouble(cadenaConPunto));

                    productoController.insertObject(productoNuevo);
                    llenarTabla();
                    habilitarTextBox(false);
                    habilitarBotones(true, true, false, false);
                    limpiarTextBox();
                    lblTipoOperacion.setText("");
                    notificarProductoConfirmada();
                } else {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                if (!estanVacios) {
                    if (filaSeleccionada >= 0) {
                        Producto productoEditar = grillaProductos1.getProducto(filaSeleccionada);
                        productoEditar.setMarca(txtMarca.getText().trim());
                        productoEditar.setUbicacion(txtUbicacion.getText().trim());
                        productoEditar.setNombre(txtNombre.getText().trim());
                        productoEditar.setPrecio(Double.parseDouble(cadenaConPunto));

                        boolean resultado = productoController.modifiedObject(productoEditar);
                        System.out.println("resultado = " + resultado);
                        llenarTabla();
                        habilitarTextBox(false);
                        habilitarBotones(true, true, false, false);
                        limpiarTextBox();
                        lblTipoOperacion.setText("");
                        notificarProductoConfirmada();
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un producto.", "Advertencia", JOptionPane.WARNING_MESSAGE);

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IntFrProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IntFrProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = tbProductos.getSelectedRow();

        if (filaSeleccionada >= 0) {
            Producto productoSeleccionado = grillaProductos1.getProducto(filaSeleccionada);
            txtNombre.setText(productoSeleccionado.getNombre().trim());
            txtUbicacion.setText(productoSeleccionado.getUbicacion().trim());
            txtMarca.setText(productoSeleccionado.getMarca().trim());
            txtPrecio.setText(String.valueOf(productoSeleccionado.getPrecio()));

        }
    }//GEN-LAST:event_tbProductosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            // TODO add your handling code here:
            String itemSeleccionado = ((String) cbOpcionesBusqueda.getSelectedItem()).trim();
            productoController = new ProductoController();
            List<Producto> listadoProductos;

            switch (itemSeleccionado) {
                case "Por nombre":
                    listadoProductos = productoController.getAllObjectsByName(txtBuscar.getText().trim());
                    grillaProductos1.setProductos(listadoProductos);
                    break;
                case "Por marca":
                    listadoProductos = productoController.getAllObjectsByMarca(txtBuscar.getText().trim());
                    grillaProductos1.setProductos(listadoProductos);
                    break;
                case "Por ubicacion":
                    listadoProductos = productoController.getAllObjectsByUbicacion(txtBuscar.getText().trim());
                    grillaProductos1.setProductos(listadoProductos);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IntFrProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IntFrProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbOpcionesBusqueda;
    private com.gustavo.trabajotitulacion.ui.grillas.GrillaProductos grillaProductos1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTipoOperacion;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField txtPrecio;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
