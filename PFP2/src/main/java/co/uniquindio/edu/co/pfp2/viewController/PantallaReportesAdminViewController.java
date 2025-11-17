package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PantallaReportesAdminViewController {

    private App app;

    @FXML
    private PieChart pieRepartidores;

    @FXML
    private BarChart<String, Number> barProductosPorUsuario;

    @FXML
    private LineChart<String, Number> lineEnvios;

    @FXML
    private CategoryAxis barCategorias;

    @FXML
    private NumberAxis barNumeros;

    @FXML
    private CategoryAxis lineCategorias;

    @FXML
    private NumberAxis lineNumeros;

    public void setApp(App app) {
        this.app = app;
        // generar inicialmente
        generarReportes();
    }

    @FXML
    private void generarReportes() {
        try {
            generarPieRepartidores();
            generarBarProductosPorUsuario();
            generarLineEnviosPorDia();
        } catch (Exception ex) {
            ex.printStackTrace();
            DialogUtils.mostrarError("Error al generar reportes: " + ex.getMessage());
        }
    }

    private void generarPieRepartidores() {
        // Pie: disponibilidad de repartidores
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        long disponibles = app.listGlobalRepartidores.stream().filter(r -> r.getDisponibilidadRepartidor() != null && r.getDisponibilidadRepartidor().name().equals("DISPONIBLE")).count();
        long enRuta = app.listGlobalRepartidores.stream().filter(r -> r.getDisponibilidadRepartidor() != null && r.getDisponibilidadRepartidor().name().equals("EN_RUTA")).count();
        long otros = app.listGlobalRepartidores.size() - disponibles - enRuta;
        pieData.add(new PieChart.Data("Disponibles", disponibles));
        pieData.add(new PieChart.Data("En ruta", enRuta));
        pieData.add(new PieChart.Data("Otros", otros));
        pieRepartidores.setData(pieData);
    }

    private void generarBarProductosPorUsuario() {
        // Bar: número de productos por usuario (top 10)
        barProductosPorUsuario.getData().clear();

        Map<String, Long> productosPorUsuario = new HashMap<>();
        for (Usuario u : app.listGlobalUsuarios) {
            productosPorUsuario.put(u.getNombreCompleto(), (long) u.getListProductosUsuario().size());
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Productos por usuario");
        productosPorUsuario.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(10)
                .forEach(e -> series.getData().add(new XYChart.Data<>(e.getKey(), e.getValue())));

        barProductosPorUsuario.getData().add(series);
    }

    private void generarLineEnviosPorDia() {
        // Line: envíos por fecha (conteo)
        lineEnvios.getData().clear();
        Map<String, Long> conteoPorDia = app.listGlobalUsuarios.stream()
                .flatMap(u -> u.getListEnviosUsuario().stream())
                .collect(Collectors.groupingBy(e -> e.getFechaCreacion().format(DateTimeFormatter.ISO_DATE), Collectors.counting()));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Envíos por día");
        conteoPorDia.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> series.getData().add(new XYChart.Data<>(e.getKey(), e.getValue())));

        lineEnvios.getData().add(series);
    }
}
