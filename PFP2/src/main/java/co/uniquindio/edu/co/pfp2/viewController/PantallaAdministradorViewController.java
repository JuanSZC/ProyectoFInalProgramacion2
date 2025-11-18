package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import reportesGenerador.PdfReporteEnvioRepartidor;
import reportesGenerador.PdfReporteEnvioUsuario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Parent;
import reportesGenerador.PdfUsuarioReporte;

public class PantallaAdministradorViewController {

    private App app;

    @FXML
    private Button btAM;

    @FXML
    private TableView<Usuario> tbUsuarios;

    @FXML
    private TableColumn<Usuario, String> colIdUsuario;

    @FXML
    private TableColumn<Usuario, String> colNombreUsuario;

    @FXML
    private TableColumn<Usuario, String> colCedulaUsuario;

    @FXML
    private TableColumn<Usuario, String> colCorreoUsuario;

    @FXML
    private TableColumn<Usuario, String> colTelefonoUsuario;

    @FXML
    private TableColumn<Usuario, String> colContrasenaUsuario;

    @FXML
    TableView<Repartidor> tbRepartidores;
    @FXML
    TableColumn<Repartidor, String> colIdRepartidor;
    @FXML
    TableColumn<Repartidor, String> colNombreRepartidor;
    @FXML
    TableColumn<Repartidor, String> colCedulaRepartidor;
    @FXML
    TableColumn<Repartidor, String> colCorreoRepartidor;
    @FXML
    TableColumn<Repartidor, String> colZonaRepartidor;
    @FXML
    TableColumn<Repartidor, String> colDisponibilidadRepartidor;

    @FXML
    Button btAMR;

    @FXML
    TableView<Producto> tbProductos;
    @FXML
    TableColumn<Producto, String> colIdProducto;
    @FXML
    TableColumn<Producto, String> colNombreProducto;
    @FXML
    TableColumn<Producto, String> colDescripcionProducto;
    @FXML
    TableColumn<Producto, String> colPrecioProducto;
    @FXML
    TableColumn<Producto, String> colCantidadProducto;
    @FXML
    TableColumn<Producto, String> colPesoProducto;
    @FXML
    Button btAMP;
    @FXML
    Button btEliminarProducto;

    @FXML
    Button btEliminarUsuario;

    @FXML
    Button btEliminarRepartidor;

    public void setApp(App app) {
        this.app = app;

        if (app != null) {
            tbUsuarios.setItems(app.listGlobalUsuarios);
            tbRepartidores.setItems(app.listGlobalRepartidores);
            tbProductos.setItems(app.listGlobalProductos);
        }
    }
    public void generarPDFUsuario() {
        Usuario usuario = tbUsuarios.getSelectionModel().getSelectedItem();
        if (usuario == null) {
            DialogUtils.mostrarError("No se seleccionó ningún usuario.");
            return;
        }

        try {
            List<Envio> envios = usuario.getListEnviosUsuario();
            if (envios.isEmpty()) {
                DialogUtils.mostrarError("El usuario no tiene envíos para generar reportes.");
                return;
            }

            // Crear la ruta del PDF único
            String ruta = "ReportesPDF" + File.separator + "admin"  + File.separator +
                    "reporte_envios_usuario_" + usuario.getIdUsuario() + ".pdf";

            File file = new File(ruta);
            file.getParentFile().mkdirs();

            // Generar el PDF usando la clase independiente
            PdfUsuarioReporte pdf = new PdfUsuarioReporte(usuario);
            pdf.generarPdf(ruta);

            DialogUtils.mostrarMensaje("PDF generado correctamente para el usuario.");

        } catch (IOException ex) {
            ex.printStackTrace();
            DialogUtils.mostrarError("No se pudo generar el PDF: " + ex.getMessage());
        }
    }

    public void generarPDFRepartidor() {
        Repartidor repartidor = tbRepartidores.getSelectionModel().getSelectedItem();
        if (repartidor == null) {
            DialogUtils.mostrarError("No se seleccionó ningún repartidor.");
            return;
        }

        try {
            // Crear lista de envíos asignados al repartidor
            List<Envio> enviosAsignados = new ArrayList<>();

            for (Usuario usuario : app.listGlobalUsuarios) {
                for (Envio envio : usuario.getListEnviosUsuario()) {
                    if (envio.getRepartidor() != null && envio.getRepartidor().equals(repartidor)) {
                        enviosAsignados.add(envio);
                    }
                }
            }

            if (enviosAsignados.isEmpty()) {
                DialogUtils.mostrarError("El repartidor no tiene envíos asignados.");
                return;
            }

            // Crear ruta del PDF
            String ruta = "ReportesPDF" + File.separator + "admin" + File.separator +
                    File.separator + "reporte_envios_repartidor_" + repartidor.getIdRepartidor() + ".pdf";
            File file = new File(ruta);
            file.getParentFile().mkdirs();

            // Generar PDF
            PdfReporteEnvioRepartidor pdf = new PdfReporteEnvioRepartidor(repartidor, enviosAsignados);
            pdf.generarPdf(ruta);

            DialogUtils.mostrarMensaje("PDF generado correctamente para el repartidor.");

        } catch (IOException ex) {
            ex.printStackTrace();
            DialogUtils.mostrarError("No se pudo generar el PDF: " + ex.getMessage());
        }
    }

    public void initialize() {

        tbUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                btAM.setText("Modificar Usuario");
            } else {
                btAM.setText("Añadir Usuario");
            }
        });
        tbRepartidores.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                btAMR.setText("Modificar Repartidor");
            } else {
                btAMR.setText("Añadir Repartidor");
            }
        });

        colNombreUsuario.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombreCompleto()));

        colIdUsuario.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getIdUsuario())));

        colCedulaUsuario.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCedula())));

        colTelefonoUsuario.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));

        colCorreoUsuario.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCorreo()));

        colContrasenaUsuario.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getContrasenia()));

        colNombreRepartidor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombreCompleto()));

        colIdRepartidor.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getIdRepartidor())));

        colCedulaRepartidor.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCedula())));

        colCorreoRepartidor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCorreo()));

        colZonaRepartidor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getZonaCobertura().toString()));
        colDisponibilidadRepartidor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDisponibilidadRepartidor().toString()));
        colNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colDescripcionProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colCantidadProducto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidad())));
        colPrecioProducto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
        colPesoProducto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPeso())));
        colIdProducto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdProducto())));

        tbProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                btAMP.setText("Modificar Producto");
            } else {
                btAMP.setText("Añadir Producto");
            }
        });

    }

    @FXML
    private void accionAgregarModificarUsuario() {
        Usuario usuarioSeleccionado = tbUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado == null) {
            abrirPantallaAgregarUsuario();
        } else {
            abrirPantallaModificarUsuario(usuarioSeleccionado);
        }
    }

    private void abrirPantallaAgregarUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaAgregarUsuario.fxml"));
            AnchorPane rootLayout = loader.load();

            PantallaAgregarUsuarioViewController controller = loader.getController();
            controller.setApp(app);

            Stage ventana = new Stage();
            ventana.setTitle("Agregar Usuario");
            ventana.setScene(new Scene(rootLayout));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tbUsuarios.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

            tbUsuarios.refresh();

        } catch (IOException e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al abrir la ventana de agregar usuario: " + e.getMessage());
        }
    }

    private void abrirPantallaModificarUsuario(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaModificarUsuario.fxml"));
            AnchorPane rootLayout = loader.load();

            PantallaModificarUsuarioViewController controller = loader.getController();
            controller.setUsuario(usuario);

            Stage ventana = new Stage();
            ventana.setTitle("Modificar Usuario");
            ventana.setScene(new Scene(rootLayout));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tbUsuarios.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

            tbUsuarios.refresh();

        } catch (IOException e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al abrir la ventana de modificar usuario: " + e.getMessage());
        }
    }

    @FXML
    private void eliminarUsuario() {
        Usuario usuarioSeleccionado = tbUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado == null) {
            DialogUtils.mostrarError("Seleccione un usuario para eliminar.");
            return;
        }
       for (Envio en : usuarioSeleccionado.getListEnviosUsuario()){
           if ((en.getEstadoEnvio() == EstadoEnvio.EN_RUTA)){
               DialogUtils.mostrarError("No se puede eliminar un usuario con envios pendientes.");
               return;
           }
       }
       DialogUtils.mostrarMensaje("Usuario eliminado con éxito");



    }

    @FXML
    private void accionAgregarModificarRepartidor() {
        Repartidor repartidorSeleccionado = tbRepartidores.getSelectionModel().getSelectedItem();

        if (repartidorSeleccionado == null) {
            abrirPantallaAgregarRepartidor();
        } else {
            abrirPantallaModificarRepartidor(repartidorSeleccionado);
        }
    }

    private void abrirPantallaAgregarRepartidor() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaAgregarRepartidor.fxml"));
            AnchorPane rootLayout = loader.load();

            PantallaAgregarRepartidorViewController controller = loader.getController();
            controller.setApp(app);

            Stage ventana = new Stage();
            ventana.setTitle("Agregar Repartidor");
            ventana.setScene(new Scene(rootLayout));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tbRepartidores.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

            tbRepartidores.refresh();

        } catch (IOException e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al abrir la ventana de agregar repartidor: " + e.getMessage());
        }
    }

    public void accionAgregarModificarProducto() {
        Producto productoSeleccionado = tbProductos.getSelectionModel().getSelectedItem();

        if (productoSeleccionado == null) {
            abrirPantallaAgregarProducto();
        } else {
            abrirPantallaModificarProducto(productoSeleccionado);
        }
    }

    private void abrirPantallaAgregarProducto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaAgregarProducto.fxml"));
            AnchorPane rootLayout = loader.load();

            PantallaAgregarProductoViewController controller = loader.getController();
            controller.setApp(app);

            Stage ventana = new Stage();
            ventana.setTitle("Agregar Producto");
            ventana.setScene(new Scene(rootLayout));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tbProductos.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

            tbProductos.refresh();

        } catch (IOException e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al abrir la ventana de agregar producto: " + e.getMessage());
        }
    }

    private void abrirPantallaModificarProducto(Producto producto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaModificarProducto.fxml"));
            AnchorPane rootLayout = loader.load();

            PantallaModificarProductoViewController controller = loader.getController();
            controller.setProducto(producto);

            Stage ventana = new Stage();
            ventana.setTitle("Modificar Producto");
            ventana.setScene(new Scene(rootLayout));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tbProductos.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

            tbProductos.refresh();

        } catch (IOException e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al abrir la ventana de modificar producto: " + e.getMessage());
        }
    }

    @FXML
    private void eliminarProducto() {
        Producto productoSeleccionado = tbProductos.getSelectionModel().getSelectedItem();

        if (productoSeleccionado == null) {
            DialogUtils.mostrarError("Seleccione un producto para eliminar.");
            return;
        }

        app.listGlobalProductos.remove(productoSeleccionado);
        tbProductos.refresh();
        DialogUtils.mostrarMensaje("Producto eliminado correctamente.");
    }

    @FXML
    private void limpiarSeleccionProducto() {
        tbProductos.getSelectionModel().clearSelection();
    }

    private void abrirPantallaModificarRepartidor(Repartidor repartidor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaModificarRepartidor.fxml"));
            AnchorPane rootLayout = loader.load();

            PantallaModificarRepartidorViewController controller = loader.getController();
            controller.setRepartidor(repartidor);

            Stage ventana = new Stage();
            ventana.setTitle("Modificar Repartidor");
            ventana.setScene(new Scene(rootLayout));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tbRepartidores.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

            tbRepartidores.refresh();

        } catch (IOException e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al abrir la ventana de modificar repartidor: " + e.getMessage());
        }
    }

    @FXML
    private void eliminarRepartidor() {
        Repartidor repartidorSeleccionado = tbRepartidores.getSelectionModel().getSelectedItem();

        if (repartidorSeleccionado == null) {
            DialogUtils.mostrarError("Seleccione un repartidor para eliminar.");
            return;
        }


        boolean tieneEnviosPendientes = app.listGlobalUsuarios.stream()
                .flatMap(usuario -> usuario.getListEnviosUsuario().stream())
                .anyMatch(envio -> envio.getRepartidor() == repartidorSeleccionado && !envio.getEstadoEnvio().equals(EstadoEnvio.ENTREGADO) && !envio.getEstadoEnvio().equals(EstadoEnvio.CANCELADO));

        if (tieneEnviosPendientes) {
            DialogUtils.mostrarError("El repartidor tiene envíos pendientes y no se puede eliminar.");
            return;
        }

        app.listGlobalRepartidores.remove(repartidorSeleccionado);
        tbRepartidores.refresh();
        DialogUtils.mostrarMensaje("Repartidor eliminado correctamente.");
    }


    @FXML
    private void limpiarSeleccionUsuario() {
        tbUsuarios.getSelectionModel().clearSelection();
    }

    @FXML
    private void limpiarSeleccionRepartidor() {
        tbRepartidores.getSelectionModel().clearSelection();
    }

    @FXML
    private void openAjustesAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaAdministradorConfiguracion.fxml"));
            AnchorPane root = loader.load();
            PantallaAdministradorConfiguracionViewController ctrl = loader.getController();
            ctrl.setApp(this.app);

            Stage ventana = new Stage();
            ventana.setTitle("Ajustes Administrador");
            ventana.setScene(new Scene(root));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(btAM.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al abrir ajustes de administrador: " + e.getMessage());
        }
    }

    @FXML
    private void abrirPantallaReportesAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaReportesAdmin.fxml"));
            AnchorPane root = loader.load();
            PantallaReportesAdminViewController ctrl = loader.getController();
            ctrl.setApp(this.app);

            Stage ventana = new Stage();
            ventana.setTitle("Reportes Administrador");
            ventana.setScene(new Scene(root));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(btAM.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al abrir la ventana de reportes: " + e.getMessage());
        }
    }

    @FXML
    private void cerrarSesionAdmin() {
        if (app != null) app.openPantallaBienvenida();
    }
}
