/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui;

import com.gustavo.trabajotitulacion.controllers.ProductoController;
import com.gustavo.trabajotitulacion.controllers.StockController;
import com.gustavo.trabajotitulacion.objects.Producto;
import com.gustavo.trabajotitulacion.objects.Stock;
import com.gustavo.trabajotitulacion.utils.FacturaConfirmadaEvent;
import com.gustavo.trabajotitulacion.utils.FacturaConfirmadaListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class JintFrStock extends javax.swing.JInternalFrame implements FacturaConfirmadaListener {

    private StockController stockController;

    /**
     * Creates new form JintFrStock
     */
    public JintFrStock() {
        initComponents();
        llenarTabla();
        llenarComboBox();
        llenarLabels();
    }

    @Override
    public void facturaConfirmada(FacturaConfirmadaEvent event) {
        // Lógica para actualizar los labels en JintFRstock
        llenarLabels();
        actualizarComboBox();
        llenarTabla();
    }

    public void llenarTabla() {
        try {
            stockController = new StockController();
            List<Stock> listadoStock = stockController.getAllObjects();
            grillaStock1.setStock(listadoStock);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarComboBox() {
        try {
            ProductoController productoController = new ProductoController();
            List<Producto> listadoProductos = productoController.getAllObjects();

            for (Producto prod : listadoProductos) {
                cbCantidadPorProducto.addItem(prod);
                cbCantidadPorProductoVencido.addItem(prod);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarLabels() {
        try {
            stockController = new StockController();
            int cantidadTotal = stockController.getCantidadTodosLosProductos();
            int cantidadProductosNoVencidos = stockController.getCantidadProductoNoVencido();
            int cantidadProductosVencidos = stockController.getCantidadTodosLosProductosVencidos();

            lblTotal.setText("Cantidad total de todos los productos (incluidos los vencidos): " + cantidadTotal);
            lblProductosNovencidos.setText("Productos No vencidos: " + cantidadProductosNoVencidos);
            lblCantidadProductosVencidos.setText("Productos vencidos: " + cantidadProductosVencidos);
            lblTotalProductosVencidosBorrar1.setText("Productos vencidos: " + cantidadProductosVencidos);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualizarComboBox() {
        int indiceCbCantidadPorProducto = cbCantidadPorProducto.getSelectedIndex();
        int indiceCbCantidadPorProductoVencido = cbCantidadPorProductoVencido.getSelectedIndex();

        if (indiceCbCantidadPorProducto >= 0) {
            cbCantidadPorProducto.setSelectedIndex(indiceCbCantidadPorProducto);
        }

        if (indiceCbCantidadPorProductoVencido >= 0) {
            cbCantidadPorProductoVencido.setSelectedIndex(indiceCbCantidadPorProductoVencido);
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

        grillaStock1 = new com.gustavo.trabajotitulacion.ui.grillas.GrillaStock();
        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbStock = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbBuscar = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnBorrarTodo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblTotalProductosVencidosBorrar1 = new javax.swing.JLabel();
        lblCantidadPorProducto = new javax.swing.JLabel();
        cbCantidadPorProducto = new javax.swing.JComboBox<>();
        lblCantidadProductosVencidos = new javax.swing.JLabel();
        cbCantidadPorProductoVencido = new javax.swing.JComboBox<>();
        lblCantidadPorProductoVencido = new javax.swing.JLabel();
        lblProductosNovencidos = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JFormattedTextField();
        btnActualizar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Stock");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Estadisticas");

        tbStock.setModel(grillaStock1);
        jScrollPane1.setViewportView(tbStock);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Listado de productos");

        cbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Productos No vencidos", "Productos vencidos", "Productos por vencer (ingresar meses ej. 2)" }));
        cbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuscarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lupa.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Borrar los productos vencidos");

        lblTotal.setText("Cantidad total de todos los productos (incluidos los vencidos): 0");

        btnBorrarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar.png"))); // NOI18N
        btnBorrarTodo.setText("Borrar todos");
        btnBorrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarTodoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Filtrar Stock");

        lblTotalProductosVencidosBorrar1.setText("Productos vencidos: 0");

        lblCantidadPorProducto.setText("Cantidad por producto: 0");

        cbCantidadPorProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCantidadPorProductoActionPerformed(evt);
            }
        });

        lblCantidadProductosVencidos.setText("Productos vencidos: 0");

        cbCantidadPorProductoVencido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCantidadPorProductoVencidoActionPerformed(evt);
            }
        });

        lblCantidadPorProductoVencido.setText("Cantidad por producto venc.: 0");

        lblProductosNovencidos.setText("Productos No vencidos: 0");

        txtBuscar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtBuscar.setEnabled(false);

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivo.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addComponent(txtBuscar)
                        .addGap(24, 24, 24)
                        .addComponent(cbBuscar, 0, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lblCantidadProductosVencidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addComponent(lblTotalProductosVencidosBorrar1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBorrarTodo))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCantidadPorProductoVencido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblProductosNovencidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCantidadPorProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbCantidadPorProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbCantidadPorProductoVencido, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(568, Short.MAX_VALUE)))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBorrarTodo)
                            .addComponent(lblTotalProductosVencidosBorrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidadPorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCantidadPorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProductosNovencidos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidadPorProductoVencido, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCantidadPorProductoVencido, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidadProductosVencidos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(458, Short.MAX_VALUE)))
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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        try {
            // TODO add your handling code here:
            int filaSeleccionadaBuscar = cbBuscar.getSelectedIndex();
            String tipoBusqueda = ((String) cbBuscar.getSelectedItem()).trim();

            stockController = new StockController();

            if (filaSeleccionadaBuscar >= 0) {
                switch (tipoBusqueda) {
                    case "Todo":
                        List<Stock> listadoTodoStock = stockController.getAllObjects();
                        grillaStock1.setStock(listadoTodoStock);
                        break;
                    case "Productos vencidos":
                        List<Stock> listadoVencidoStock = stockController.getAllProductosVencidos();
                        grillaStock1.setStock(listadoVencidoStock);
                        break;
                    case "Productos por vencer (ingresar meses ej. 2)":
                        if (!txtBuscar.getText().trim().equals("")) {
                            int cantidadMeses = Integer.parseInt(txtBuscar.getText().trim());
                            if (cantidadMeses >= 0) {
                                List<Stock> listadoPorVencerStock = stockController.getProductosProximosAVencer(cantidadMeses);
                                grillaStock1.setStock(listadoPorVencerStock);
                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese un mes valido (numero)", "Advertencia", JOptionPane.WARNING_MESSAGE);

                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El campo de busqueda no puede estar vacio.", "Advertencia", JOptionPane.WARNING_MESSAGE);

                        }

                        break;

                    case "Productos No vencidos":
                        List<Stock> listadoNoVencidoStock = stockController.getAllProductosNoVencidos();
                        grillaStock1.setStock(listadoNoVencidoStock);
                        break;
                    default:
                        throw new AssertionError();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un tipo de busqueda o ingrese un mes valido (numero)", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscarActionPerformed
        // TODO add your handling code here:
        String tipoBusqueda = ((String) cbBuscar.getSelectedItem()).trim();

        switch (tipoBusqueda) {
            case "Todo":
                txtBuscar.setEnabled(false);
                break;
            case "Productos vencidos":
                txtBuscar.setEnabled(false);
                break;
            case "Productos por vencer (ingresar meses ej. 2)":
                txtBuscar.setEnabled(true);
                break;
            case "Productos No vencidos":
                txtBuscar.setEnabled(false);
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_cbBuscarActionPerformed

    private void cbCantidadPorProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCantidadPorProductoActionPerformed
        try {
            // TODO add your handling code here:

            int indiceSeleccionado = cbCantidadPorProducto.getSelectedIndex();

            if (indiceSeleccionado >= 0) {
                stockController = new StockController();
                Producto prodNoVencidoSeleccionado = (Producto) cbCantidadPorProducto.getSelectedItem();
                int cantidadProductoNoVencido = stockController.getCantidadPorProductoNoVencido(prodNoVencidoSeleccionado.getId());

                lblCantidadPorProducto.setText("Productos No vencidos: " + cantidadProductoNoVencido);

                List<Stock> listadoStock = stockController.getAllPorProductosNoVencidos(prodNoVencidoSeleccionado.getId());
                grillaStock1.setStock(listadoStock);
            } else {
                System.out.println("Seleccione un producto en la opcion de por productos No vencidos");

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_cbCantidadPorProductoActionPerformed

    private void cbCantidadPorProductoVencidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCantidadPorProductoVencidoActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            int indiceSeleccionado = cbCantidadPorProductoVencido.getSelectedIndex();

            if (indiceSeleccionado >= 0) {
                stockController = new StockController();
                Producto prodVencidoSeleccionado = (Producto) cbCantidadPorProductoVencido.getSelectedItem();
                int cantidadProductoVencido = stockController.getCantidadPorProductoVencido(prodVencidoSeleccionado.getId());

                lblCantidadPorProductoVencido.setText("Cantidad por producto venc.: " + cantidadProductoVencido);

                List<Stock> listadoStock = stockController.getAllPorProductosVencidos(prodVencidoSeleccionado.getId());
                grillaStock1.setStock(listadoStock);
            } else {
                System.out.println("Seleccione un producto en la opcion de por productos vencidos");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbCantidadPorProductoVencidoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        llenarTabla();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnBorrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarTodoActionPerformed
        // TODO add your handling code here:

        int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar todos los productos vencidos?", "Advertencia", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                stockController = new StockController();
                stockController.borrarTodosLosProductosVencidos();
                llenarLabels();
                llenarTabla();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JintFrStock.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }//GEN-LAST:event_btnBorrarTodoActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        llenarLabels();
        actualizarComboBox();
        llenarTabla();

    }//GEN-LAST:event_formInternalFrameClosing

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        cbCantidadPorProducto.removeAllItems();
        cbCantidadPorProductoVencido.removeAllItems();
        
        llenarLabels();
        llenarComboBox();
        llenarTabla();

    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrarTodo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cbBuscar;
    private javax.swing.JComboBox<Producto> cbCantidadPorProducto;
    private javax.swing.JComboBox<Producto> cbCantidadPorProductoVencido;
    private com.gustavo.trabajotitulacion.ui.grillas.GrillaStock grillaStock1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidadPorProducto;
    private javax.swing.JLabel lblCantidadPorProductoVencido;
    private javax.swing.JLabel lblCantidadProductosVencidos;
    private javax.swing.JLabel lblProductosNovencidos;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalProductosVencidosBorrar1;
    private javax.swing.JTable tbStock;
    private javax.swing.JFormattedTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
