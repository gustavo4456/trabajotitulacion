/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui;

import com.gustavo.trabajotitulacion.controllers.DetalleVentaClienteController;
import com.gustavo.trabajotitulacion.controllers.ProductoController;
import com.gustavo.trabajotitulacion.objects.DetalleVentaCliente;
import com.gustavo.trabajotitulacion.objects.Producto;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author gustavo
 */
public class JIntFrEstadisticasTortaVentas extends javax.swing.JInternalFrame {

    private int anioSeleccionado = 0;
    private final DetalleVentaClienteController detalleVentaClienteController;

    /**
     * Creates new form JIntFrEstadisticasTortaVentas
     */
    public JIntFrEstadisticasTortaVentas() throws ClassNotFoundException, SQLException {
        initComponents();

        // Establecer un tamaño predeterminado
        setSize(900, 600);

        detalleVentaClienteController = new DetalleVentaClienteController();
        agregarAlComboBox();
    }

    /**
     * Agrega componentes al ComboBox.
     */
    private void agregarAlComboBox() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Vector<Integer> anios = new Vector<>();

        for (int anio = currentYear; anio >= 1950; anio--) {
            anios.add(anio);
        }

        JComboBox<Integer> anioComboBox = new JComboBox<>(anios);

        JButton generateButton = new JButton("Generar");
        generateButton.addActionListener((e) -> {
            anioSeleccionado = (Integer) anioComboBox.getSelectedItem();
            System.out.println("Generando gráfico para el año: " + anioSeleccionado);
            createChart();
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(anioComboBox);
        controlPanel.add(generateButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(bg, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Crea y muestra el gráfico de torta.
     */
    private void createChart() {
        // Eliminar cualquier ChartPanel existente
        bg.removeAll();

        DefaultPieDataset dataset = createDataset();
        JFreeChart chart = createPieChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        bg.add(chartPanel);

        // Forzar la actualización de la interfaz gráfica
        bg.revalidate();
        bg.repaint();
    }

    /**
     * Crea el conjunto de datos para el gráfico de torta.
     */
    private DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            ProductoController productoController = new ProductoController();

            List<Producto> listadoProductos = productoController.getAllObjects();

            int totalVentas = 0;

            // Calcular el total de ventas
            for (Producto itemProd : listadoProductos) {
                totalVentas += detalleVentaClienteController.getCantidadVendidaPorProductosYanio(anioSeleccionado, itemProd.getId());
            }

            System.out.println("Total ventas acumuladas: " + totalVentas);

            // Agregar valores al dataset
            for (Producto itemProd : listadoProductos) {
                int cantidadVendida = detalleVentaClienteController.getCantidadVendidaPorProductosYanio(anioSeleccionado, itemProd.getId());
                double porcentaje = (double) cantidadVendida / totalVentas * 100;
                dataset.setValue(itemProd.getNombre() + " " + itemProd.getMarca() + " - " + cantidadVendida + " (" + String.format("%.2f", porcentaje) + "%)", cantidadVendida);
            }

            return dataset;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Crea el gráfico de torta utilizando JFreeChart.
     */
    private JFreeChart createPieChart(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Ventas por Producto (Año " + anioSeleccionado + ")", // Título del gráfico
                dataset, // Conjunto de datos
                true, // Mostrar leyenda
                true, // Incluir tooltips
                false // Incluir URLs
        );

        return chart;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Grafico de torta sobre ventas por producto");

        bg.setLayout(new java.awt.BorderLayout());

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    // End of variables declaration//GEN-END:variables
}
