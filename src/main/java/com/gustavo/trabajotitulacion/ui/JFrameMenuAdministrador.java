/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author gustavo
 */
public class JFrameMenuAdministrador extends javax.swing.JFrame {

    /**
     * Creates new form JFrameMenu
     */
    JintFrStock jintFRstock;
    JintFacturasClientes jintFacturasClientes;
    JintFacturasProveedores jintFacturasProveedores;
    IntFrCliente intFrCliente;
    IntFrProveedor intFrProveedor;
    IntFrProductos intFrProductos;

    private JFrameInicioSesion frameInicioSesion;

    public JFrameMenuAdministrador(String usuarioNombre, String privilegio, JFrameInicioSesion frameInicioSesion) {

        this.frameInicioSesion = frameInicioSesion;

        // Instanciar JintFRstock y JintFacturasClientes
        jintFRstock = new JintFrStock();
        jintFacturasClientes = new JintFacturasClientes();
        jintFacturasProveedores = new JintFacturasProveedores();
        intFrCliente = new IntFrCliente();
        intFrProveedor = new IntFrProveedor();
        intFrProductos = new IntFrProductos();

        // Agregar JintFRstock como listener en JintFacturasClientes
        jintFacturasClientes.agregarFacturaConfirmadaListener(jintFRstock);
        jintFacturasProveedores.agregarFacturaConfirmadaListener(jintFRstock);
        jintFacturasProveedores.agregarFacturaConfirmadaListener(jintFacturasClientes);
        intFrCliente.agregarClienteConfirmadaListener(jintFacturasClientes);
        intFrProveedor.agregarProveedorConfirmadaListener(jintFacturasProveedores);
        intFrProductos.agregarProductoConfirmadaListener(jintFacturasClientes);
        intFrProductos.agregarProductoConfirmadaListener(jintFacturasProveedores);

        initComponents();

        menuUsuario.setText("Usuario: " + usuarioNombre + " (" + privilegio + ")");

        // Establecer el estado del JFrame a pantalla completa
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public JFrameMenuAdministrador() {

        // Instanciar JintFRstock y JintFacturasClientes
        jintFRstock = new JintFrStock();
        jintFacturasClientes = new JintFacturasClientes();
        jintFacturasProveedores = new JintFacturasProveedores();
        intFrCliente = new IntFrCliente();
        intFrProveedor = new IntFrProveedor();
        intFrProductos = new IntFrProductos();

        // Agregar JintFRstock como listener en JintFacturasClientes
        jintFacturasClientes.agregarFacturaConfirmadaListener(jintFRstock);
        jintFacturasProveedores.agregarFacturaConfirmadaListener(jintFRstock);
        jintFacturasProveedores.agregarFacturaConfirmadaListener(jintFacturasClientes);
        intFrCliente.agregarClienteConfirmadaListener(jintFacturasClientes);
        intFrProveedor.agregarProveedorConfirmadaListener(jintFacturasProveedores);
        intFrProductos.agregarProductoConfirmadaListener(jintFacturasClientes);
        intFrProductos.agregarProductoConfirmadaListener(jintFacturasProveedores);

        initComponents();

        // Establecer el estado del JFrame a pantalla completa
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void mostrarFrameInicioSesion() {
        if (frameInicioSesion != null) {
            frameInicioSesion.mostrarFrame();
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

        jDeskPrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAgregarProveedor = new javax.swing.JMenu();
        itemAgregarProveedor = new javax.swing.JMenuItem();
        itemAgregarCliente = new javax.swing.JMenuItem();
        menuAgregarProducto = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        itemGenerarVenta = new javax.swing.JMenuItem();
        itemGenerarCompra = new javax.swing.JMenuItem();
        itemStock = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        itemEstadisticas = new javax.swing.JMenuItem();
        itemLineasParaSeguimientoStock = new javax.swing.JMenuItem();
        itemLineasParaDineroPorMes = new javax.swing.JMenuItem();
        itemLineasParaCantidadPorProducto = new javax.swing.JMenuItem();
        itemGraficoTortaProductosVendidos = new javax.swing.JMenuItem();
        menuUsuario = new javax.swing.JMenu();
        itemCerrarSesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de stock");

        jDeskPrincipal.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jDeskPrincipalLayout = new javax.swing.GroupLayout(jDeskPrincipal);
        jDeskPrincipal.setLayout(jDeskPrincipalLayout);
        jDeskPrincipalLayout.setHorizontalGroup(
            jDeskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jDeskPrincipalLayout.setVerticalGroup(
            jDeskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        menuAgregarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-empresa-20.png"))); // NOI18N
        menuAgregarProveedor.setText("Gestion de Entidades");

        itemAgregarProveedor.setText("Agregar Proveedor");
        itemAgregarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarProveedorActionPerformed(evt);
            }
        });
        menuAgregarProveedor.add(itemAgregarProveedor);

        itemAgregarCliente.setText("Agregar Cliente");
        itemAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarClienteActionPerformed(evt);
            }
        });
        menuAgregarProveedor.add(itemAgregarCliente);

        menuAgregarProducto.setText("Agregar Producto");
        menuAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAgregarProductoActionPerformed(evt);
            }
        });
        menuAgregarProveedor.add(menuAgregarProducto);

        jMenuBar1.add(menuAgregarProveedor);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-comercio-20.png"))); // NOI18N
        jMenu5.setText("Operaciones Comerciales");

        itemGenerarVenta.setText("Generar Venta");
        itemGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGenerarVentaActionPerformed(evt);
            }
        });
        jMenu5.add(itemGenerarVenta);

        itemGenerarCompra.setText("Generar Compra");
        itemGenerarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGenerarCompraActionPerformed(evt);
            }
        });
        jMenu5.add(itemGenerarCompra);

        itemStock.setText("Ver Stock");
        itemStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemStockActionPerformed(evt);
            }
        });
        jMenu5.add(itemStock);

        jMenuBar1.add(jMenu5);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-estad¡sticas-20.png"))); // NOI18N
        jMenu1.setText("Estadisticas");

        itemEstadisticas.setText("Ventas y compras a lo largo del tiempo");
        itemEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEstadisticasActionPerformed(evt);
            }
        });
        jMenu1.add(itemEstadisticas);

        itemLineasParaSeguimientoStock.setText("Gráfico de Líneas para Seguimiento de Stock");
        itemLineasParaSeguimientoStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLineasParaSeguimientoStockActionPerformed(evt);
            }
        });
        jMenu1.add(itemLineasParaSeguimientoStock);

        itemLineasParaDineroPorMes.setText("Grafico de lines para dinero de ventas por mes");
        itemLineasParaDineroPorMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLineasParaDineroPorMesActionPerformed(evt);
            }
        });
        jMenu1.add(itemLineasParaDineroPorMes);

        itemLineasParaCantidadPorProducto.setText("Grafico de lineas para cantidad de ventas y compras por producto");
        itemLineasParaCantidadPorProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLineasParaCantidadPorProductoActionPerformed(evt);
            }
        });
        jMenu1.add(itemLineasParaCantidadPorProducto);

        itemGraficoTortaProductosVendidos.setText("Grafico de torta productos vendidos");
        itemGraficoTortaProductosVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGraficoTortaProductosVendidosActionPerformed(evt);
            }
        });
        jMenu1.add(itemGraficoTortaProductosVendidos);

        jMenuBar1.add(jMenu1);

        menuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-encuentra-hombre-usuario-32.png"))); // NOI18N
        menuUsuario.setText("usuario: ");

        itemCerrarSesion.setText("Cerrar sesion");
        itemCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCerrarSesionActionPerformed(evt);
            }
        });
        menuUsuario.add(itemCerrarSesion);

        jMenuBar1.add(menuUsuario);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDeskPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDeskPrincipal, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAgregarProductoActionPerformed
        // TODO add your handling code here:
        // Verificar si ya existe un JInternalFrame del mismo tipo
        for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
            if (frame instanceof IntFrProductos) {
                frame.toFront();
                return;
            }
        }

        // Si no existe, crear y agregar el nuevo JInternalFrame
//        IntFrProductos intFrProducto = new IntFrProductos();
        intFrProductos.setVisible(true);
        jDeskPrincipal.add(intFrProductos);
        intFrProductos.toFront();


    }//GEN-LAST:event_menuAgregarProductoActionPerformed

    private void itemStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemStockActionPerformed
        // TODO add your handling code here:
        // Verificar si ya existe un JInternalFrame del mismo tipo
        for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
            if (frame instanceof JintFrStock) {
                frame.toFront();
                return;
            }
        }

        // Si no existe, crear y agregar el nuevo JInternalFrame
//        JintFrStock intFrStock = new JintFrStock();
        jintFRstock.setVisible(true);
        jDeskPrincipal.add(jintFRstock);
        jintFRstock.toFront();

    }//GEN-LAST:event_itemStockActionPerformed

    private void itemGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGenerarVentaActionPerformed
        // TODO add your handling code here:

        // Verificar si ya existe un JInternalFrame del mismo tipo
        for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
            if (frame instanceof JintFacturasClientes) {
                frame.toFront();
                return;
            }
        }

        // Si no existe, crear y agregar el nuevo JInternalFrame
//        JintFacturasClientes intFrCliente = new JintFacturasClientes();
        jintFacturasClientes.setVisible(true);

        jDeskPrincipal.add(jintFacturasClientes);
        jintFacturasClientes.toFront();
    }//GEN-LAST:event_itemGenerarVentaActionPerformed

    private void itemCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCerrarSesionActionPerformed
        // TODO add your handling code here:
        dispose();
        mostrarFrameInicioSesion();
    }//GEN-LAST:event_itemCerrarSesionActionPerformed

    private void itemAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarClienteActionPerformed
        // TODO add your handling code here:

        // Verificar si ya existe un JInternalFrame del mismo tipo
        for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
            if (frame instanceof IntFrCliente) {
                frame.toFront();
                return;
            }
        }

        // Si no existe, crear y agregar el nuevo JInternalFrame
        //        IntFrCliente intFrCliente = new IntFrCliente();
        intFrCliente.setVisible(true);
        jDeskPrincipal.add(intFrCliente);
        intFrCliente.toFront();

    }//GEN-LAST:event_itemAgregarClienteActionPerformed

    private void itemAgregarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarProveedorActionPerformed
        // TODO add your handling code here:

        // Verificar si ya existe un JInternalFrame del mismo tipo
        for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
            if (frame instanceof IntFrProveedor) {
                frame.toFront();
                return;
            }
        }

        // Si no existe, crear y agregar el nuevo JInternalFrame
        //        IntFrProveedor intFrProveedor = new IntFrProveedor();
        intFrProveedor.setVisible(true);

        jDeskPrincipal.add(intFrProveedor);
        intFrProveedor.toFront();

    }//GEN-LAST:event_itemAgregarProveedorActionPerformed

    private void itemGenerarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGenerarCompraActionPerformed
        // TODO add your handling code here:
        // Verificar si ya existe un JInternalFrame del mismo tipo
        for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
            if (frame instanceof JintFacturasProveedores) {
                frame.toFront();
                return;
            }
        }

        // Si no existe, crear y agregar el nuevo JInternalFrame
        //        JintFacturasProveedores intFrFactura = new JintFacturasProveedores();
        jintFacturasProveedores.setVisible(true);

        jDeskPrincipal.add(jintFacturasProveedores);
        jintFacturasProveedores.toFront();
    }//GEN-LAST:event_itemGenerarCompraActionPerformed

    private void itemGraficoTortaProductosVendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGraficoTortaProductosVendidosActionPerformed
        try {
            // TODO add your handling code here:

            // Verificar si ya existe un JInternalFrame del mismo tipo
            for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
                if (frame instanceof JIntFrEstadisticasTortaVentas) {
                    frame.toFront();
                    return;
                }
            }

            // Si no existe, crear y agregar el nuevo JInternalFrame
            JIntFrEstadisticasTortaVentas intFrEstadisticas = new JIntFrEstadisticasTortaVentas();
            intFrEstadisticas.setVisible(true);
            jDeskPrincipal.add(intFrEstadisticas);
            intFrEstadisticas.toFront();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JFrameMenuOperario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JFrameMenuOperario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemGraficoTortaProductosVendidosActionPerformed

    private void itemLineasParaCantidadPorProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLineasParaCantidadPorProductoActionPerformed
        // TODO add your handling code here:
        // Verificar si ya existe un JInternalFrame del mismo tipo
        for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
            if (frame instanceof JIntFrEstadisticasLineasSeguimientoPorProductosCompradosYvendidosStock) {
                frame.toFront();
                return;
            }
        }

        // Si no existe, crear y agregar el nuevo JInternalFrame
        JIntFrEstadisticasLineasSeguimientoPorProductosCompradosYvendidosStock intFrEstadisticas = new JIntFrEstadisticasLineasSeguimientoPorProductosCompradosYvendidosStock();
        intFrEstadisticas.setVisible(true);
        jDeskPrincipal.add(intFrEstadisticas);
        intFrEstadisticas.toFront();

    }//GEN-LAST:event_itemLineasParaCantidadPorProductoActionPerformed

    private void itemLineasParaDineroPorMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLineasParaDineroPorMesActionPerformed
        // TODO add your handling code here:

        // Verificar si ya existe un JInternalFrame del mismo tipo
        for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
            if (frame instanceof JIntFrEstadisticasLineasSeguimientoStockDineroPorMes) {
                frame.toFront();
                return;
            }
        }

        // Si no existe, crear y agregar el nuevo JInternalFrame
        JIntFrEstadisticasLineasSeguimientoStockDineroPorMes intFrEstadisticas = new JIntFrEstadisticasLineasSeguimientoStockDineroPorMes();
        intFrEstadisticas.setVisible(true);
        jDeskPrincipal.add(intFrEstadisticas);
        intFrEstadisticas.toFront();

    }//GEN-LAST:event_itemLineasParaDineroPorMesActionPerformed

    private void itemLineasParaSeguimientoStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLineasParaSeguimientoStockActionPerformed
        // TODO add your handling code here:

        // Verificar si ya existe un JInternalFrame del mismo tipo
        for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
            if (frame instanceof JIntFrEstadisticasLineasSeguimientoStock) {
                frame.toFront();
                return;
            }
        }

        // Si no existe, crear y agregar el nuevo JInternalFrame
        JIntFrEstadisticasLineasSeguimientoStock intFrEstadisticas = new JIntFrEstadisticasLineasSeguimientoStock();
        intFrEstadisticas.setVisible(true);
        jDeskPrincipal.add(intFrEstadisticas);
        intFrEstadisticas.toFront();

    }//GEN-LAST:event_itemLineasParaSeguimientoStockActionPerformed

    private void itemEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEstadisticasActionPerformed
        // TODO add your handling code here:

        // Verificar si ya existe un JInternalFrame del mismo tipo
        for (JInternalFrame frame : jDeskPrincipal.getAllFrames()) {
            if (frame instanceof JIntFrEstadisticas) {
                frame.toFront();
                return;
            }
        }

        // Si no existe, crear y agregar el nuevo JInternalFrame
        JIntFrEstadisticas intFrEstadisticas = new JIntFrEstadisticas();
        intFrEstadisticas.setVisible(true);
        jDeskPrincipal.add(intFrEstadisticas);
        intFrEstadisticas.toFront();

    }//GEN-LAST:event_itemEstadisticasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMenuAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemAgregarCliente;
    private javax.swing.JMenuItem itemAgregarProveedor;
    private javax.swing.JMenuItem itemCerrarSesion;
    private javax.swing.JMenuItem itemEstadisticas;
    private javax.swing.JMenuItem itemGenerarCompra;
    private javax.swing.JMenuItem itemGenerarVenta;
    private javax.swing.JMenuItem itemGraficoTortaProductosVendidos;
    private javax.swing.JMenuItem itemLineasParaCantidadPorProducto;
    private javax.swing.JMenuItem itemLineasParaDineroPorMes;
    private javax.swing.JMenuItem itemLineasParaSeguimientoStock;
    private javax.swing.JMenuItem itemStock;
    private javax.swing.JDesktopPane jDeskPrincipal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuAgregarProducto;
    private javax.swing.JMenu menuAgregarProveedor;
    private javax.swing.JMenu menuUsuario;
    // End of variables declaration//GEN-END:variables
}
