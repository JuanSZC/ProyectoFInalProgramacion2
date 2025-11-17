package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import co.uniquindio.edu.co.pfp2.model.Envio;
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
import reportesGenerador.PdfReporteEnvioUsuario;

import java.io.File;
import java.io.IOException;

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
    Button btEliminarUsuario;

    @FXML
    Button btEliminarRepartidor;

    public void setApp(App app) {
        this.app = app;

        if (app != null) {
            tbUsuarios.setItems(app.listGlobalUsuarios);
            tbRepartidores.setItems(app.listGlobalRepartidores);
        }
    }
    public void generarPDF() {
        Usuario usuario = tbUsuarios.getSelectionModel().getSelectedItem();
        if (usuario == null) {
            DialogUtils.mostrarError("No se seleccionó ningún usuario.");
            return;
        }

        try {
            // Generar un PDF por cada envío del usuario usando el generador existente
            if (usuario.getListEnviosUsuario().isEmpty()) {
                DialogUtils.mostrarError("El usuario no tiene envíos para generar reportes.");
                return;
            }

            for (Envio envio : usuario.getListEnviosUsuario()) {
                String ruta = "reportesGenerador" + File.separator + "usuarios" + File.separator
                        + "reporte_envio_" + envio.getIdEnvio() + "_usuario_" + usuario.getIdUsuario() + ".pdf";
                File file = new File(ruta);
                file.getParentFile().mkdirs();

                PdfReporteEnvioUsuario pdf = new PdfReporteEnvioUsuario(usuario, envio);
                pdf.generarPdf(ruta);
            }

            DialogUtils.mostrarMensaje("PDF(s) generados correctamente para el usuario.");

        } catch (IOException ex) {
            ex.printStackTrace();
            DialogUtils.mostrarError("No se pudo generar los PDF(s): " + ex.getMessage());
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

        app.listGlobalUsuarios.remove(usuarioSeleccionado);
        tbUsuarios.refresh();
        DialogUtils.mostrarMensaje("Usuario eliminado correctamente.");
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
