package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
}
