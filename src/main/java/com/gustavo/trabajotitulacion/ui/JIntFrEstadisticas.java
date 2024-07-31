/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.gustavo.trabajotitulacion.ui;

import com.gustavo.trabajotitulacion.controllers.DetalleCompraProveedorController;
import com.gustavo.trabajotitulacion.controllers.DetalleVentaClienteController;
import com.gustavo.trabajotitulacion.utils.MesesDelAnio;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author gustavo
 */
public class JIntFrEstadisticas extends javax.swing.JInternalFrame {

    private int anioSeleccionado = 0;

    /**
     * Creates new form JIntFrEstadisticas
     */
    public JIntFrEstadisticas() {
        initComponents();
//        createChart();
        // Establecer un tamaño predeterminado
        setSize(900, 600);

        // Agregar años al JComboBox
        agregarAlComboBox();
    }

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
     * Crea el gráfico y lo agrega al panel.
     */
    private void createChart() {
        // Eliminar cualquier ChartPanel existente
        bg.removeAll();

        DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        bg.add(chartPanel);

        // Forzar la actualización de la interfaz gráfica
        bg.revalidate();
        bg.repaint();
    }

    /**
     * Crea el conjunto de datos para el gráfico.
     */
    private DefaultCategoryDataset createDataset() {

        try {
            DetalleVentaClienteController detalleVentaClienteController = new DetalleVentaClienteController();
            DetalleCompraProveedorController detalleCompraProveedorController = new DetalleCompraProveedorController();

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for (MesesDelAnio mes : MesesDelAnio.values()) {
//                System.out.println(mes.name() + " tiene el número " + mes.getNumero());
                dataset.addValue(detalleVentaClienteController.getCantidadVendidaPorMesAnio(mes.getNumero(), anioSeleccionado), "Ventas", mes.name());
            }
            
             for (MesesDelAnio mes : MesesDelAnio.values()) {
//                System.out.println(mes.name() + " tiene el número " + mes.getNumero());
                dataset.addValue(detalleCompraProveedorController.getCantidadVendidaPorMesAnio(mes.getNumero(), anioSeleccionado), "Compras", mes.name());
            }

//            dataset.addValue(1000, "Ventas", "Enero");
//            dataset.addValue(1500, "Ventas", "Febrero");
//            dataset.addValue(1200, "Ventas", "Marzo");
//            dataset.addValue(800, "Compras", "ENERO");
//            dataset.addValue(1200, "Compras", "FEBRERO");
//            dataset.addValue(1000, "Compras", "MARZO");

            return dataset;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JIntFrEstadisticas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JIntFrEstadisticas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Crea el gráfico utilizando JFreeChart.
     */
    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Ventas y Compras a lo largo del tiempo", // Título del gráfico
                "Fecha", // Etiqueta del eje X
                "Cantidad", // Etiqueta del eje Y
                dataset, // Conjunto de datos
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true, // Mostrar leyenda
                true, // Incluir tooltips
                false // Incluir URLs
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        StackedBarRenderer renderer = new StackedBarRenderer();
        plot.setRenderer(renderer);

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
        setTitle("Compras vs ventas");
        setToolTipText("");

        bg.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    // End of variables declaration//GEN-END:variables
}
