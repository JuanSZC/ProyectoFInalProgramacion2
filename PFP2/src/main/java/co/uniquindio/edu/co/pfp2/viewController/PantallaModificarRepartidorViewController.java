package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import co.uniquindio.edu.co.pfp2.model.ZonaCobertura;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaModificarRepartidorViewController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtContrasena;
    @FXML
    private ComboBox<ZonaCobertura> cbZona;

    private Repartidor repartidorAModificar;

    @FXML
    public void initialize() {
        cbZona.setItems(FXCollections.observableArrayList(ZonaCobertura.values()));
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidorAModificar = repartidor;
        cargarDatos();
    }

    private void cargarDatos() {
        if (repartidorAModificar != null) {
            txtNombre.setText(repartidorAModificar.getNombreCompleto());
            txtCorreo.setText(repartidorAModificar.getCorreo());
            txtTelefono.setText(String.valueOf(repartidorAModificar.getTelefono()));
            txtContrasena.setText(repartidorAModificar.getContrasenia());
            cbZona.setValue(repartidorAModificar.getZonaCobertura());
        }
    }

    @FXML
    private void modificar() {
        try {
            String nombre = txtNombre.getText().trim();
            String correo = txtCorreo.getText().trim();
            String telefonoStr = txtTelefono.getText().trim();
            String contrasena = txtContrasena.getText().trim();
            ZonaCobertura zona = cbZona.getValue();

            if (nombre.isEmpty() || correo.isEmpty() || telefonoStr.isEmpty() || 
                    contrasena.isEmpty() || zona == null) {
                DialogUtils.mostrarError("All fields are required.");
                return;
            }

            int telefono;
            try {
                telefono = Integer.parseInt(telefonoStr);
            } catch (NumberFormatException e) {
                DialogUtils.mostrarError("Phone must be a number.");
                return;
            }

            if (!correo.contains("@") || !correo.contains(".")) {
                DialogUtils.mostrarError("Invalid email format.");
                return;
            }

            repartidorAModificar.setNombreCompleto(nombre);
            repartidorAModificar.setCorreo(correo);
            repartidorAModificar.setTelefono(telefono);
            repartidorAModificar.setContrasenia(contrasena);
            repartidorAModificar.setZonaCobertura(zona);

            DialogUtils.mostrarMensaje("Repartidor updated successfully.");
            cancelar();

        } catch (Exception e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error updating repartidor: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
