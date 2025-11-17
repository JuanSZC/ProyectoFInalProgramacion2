package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import co.uniquindio.edu.co.pfp2.model.ZonaCobertura;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaAgregarRepartidorViewController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtContrasena;
    @FXML
    private ComboBox<ZonaCobertura> cbZona;

    private App app;

    @FXML
    public void initialize() {
        cbZona.setItems(FXCollections.observableArrayList(ZonaCobertura.values()));
    }

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void agregar() {
        try {
            String nombre = txtNombre.getText().trim();
            String cedula = txtCedula.getText().trim();
            String correo = txtCorreo.getText().trim();
            String telefonoStr = txtTelefono.getText().trim();
            String contrasena = txtContrasena.getText().trim();
            ZonaCobertura zona = cbZona.getValue();

            if (nombre.isEmpty() || cedula.isEmpty() || correo.isEmpty() ||
                    telefonoStr.isEmpty() || contrasena.isEmpty() || zona == null) {
                DialogUtils.mostrarError("Todos los campos son obligatorios.");
                return;
            }

            int telefono;
            try {
                telefono = Integer.parseInt(telefonoStr);
            } catch (NumberFormatException e) {
                DialogUtils.mostrarError("El teléfono debe ser un número.");
                return;
            }

            if (!correo.contains("@") || !correo.contains(".")) {
                DialogUtils.mostrarError("Formato de correo inválido.");
                return;
            }

            int id = app.idRepartidor++;

            Repartidor nuevo = new Repartidor(id, nombre, cedula, correo, contrasena, telefono, zona, co.uniquindio.edu.co.pfp2.model.DisponibilidadRepartidor.DISPONIBLE);
            app.listGlobalRepartidores.add(nuevo);

            DialogUtils.mostrarMensaje("Repartidor agregado correctamente.");
            cancelar();

        } catch (Exception e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al agregar el repartidor: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
