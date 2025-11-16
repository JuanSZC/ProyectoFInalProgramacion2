package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import reportesGenerador.PdfUsuarioReporte;

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
            tbUsuarios.setItems(FXCollections.observableArrayList(app.listGlobalUsuarios));
            tbRepartidores.setItems(app.listGlobalRepartidores);
        }
    }
    public void generarPDF() {
        Usuario usuario = tbUsuarios.getSelectionModel().getSelectedItem();
        if (usuario != null) {
            try {

                String ruta = "reportesGenerador" + File.separator + "usuarios" + File.separator
                        + "reporte_usuario_" + usuario.getIdUsuario() + ".pdf";
                File file = new File(ruta);
                file.getParentFile().mkdirs();


                PdfUsuarioReporte pdfUsuarioReporte = new PdfUsuarioReporte(usuario);
                pdfUsuarioReporte.generarPdf(ruta);

            } catch (IOException ex) {
                ex.printStackTrace();
               
            }
        } else {
            System.out.println("No se seleccionó ningún usuario.");
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
            ventana.setTitle("Add User");
            ventana.setScene(new Scene(rootLayout));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tbUsuarios.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

            tbUsuarios.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirPantallaModificarUsuario(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaModificarUsuario.fxml"));
            AnchorPane rootLayout = loader.load();

            PantallaModificarUsuarioViewController controller = loader.getController();
            controller.setUsuario(usuario);

            Stage ventana = new Stage();
            ventana.setTitle("Edit User");
            ventana.setScene(new Scene(rootLayout));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tbUsuarios.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

            tbUsuarios.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarUsuario() {
        Usuario usuarioSeleccionado = tbUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado == null) {
            DialogUtils.mostrarError("Select a user to delete.");
            return;
        }

        app.listGlobalUsuarios.remove(usuarioSeleccionado);
        tbUsuarios.refresh();
        DialogUtils.mostrarMensaje("User deleted successfully.");
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
            ventana.setTitle("Add Repartidor");
            ventana.setScene(new Scene(rootLayout));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tbRepartidores.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

            tbRepartidores.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirPantallaModificarRepartidor(Repartidor repartidor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaModificarRepartidor.fxml"));
            AnchorPane rootLayout = loader.load();

            PantallaModificarRepartidorViewController controller = loader.getController();
            controller.setRepartidor(repartidor);

            Stage ventana = new Stage();
            ventana.setTitle("Edit Repartidor");
            ventana.setScene(new Scene(rootLayout));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(tbRepartidores.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();

            tbRepartidores.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarRepartidor() {
        Repartidor repartidorSeleccionado = tbRepartidores.getSelectionModel().getSelectedItem();

        if (repartidorSeleccionado == null) {
            DialogUtils.mostrarError("Select a repartidor to delete.");
            return;
        }

        app.listGlobalRepartidores.remove(repartidorSeleccionado);
        tbRepartidores.refresh();
        DialogUtils.mostrarMensaje("Repartidor deleted successfully.");
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
            ventana.setTitle("Ajustes Admin");
            ventana.setScene(new Scene(root));
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(btAM.getScene().getWindow());
            ventana.setResizable(false);
            ventana.centerOnScreen();
            ventana.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrarSesionAdmin() {
        if (app != null) app.openPantallaBienvenida();
    }
}
