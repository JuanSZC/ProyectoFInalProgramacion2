package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaAgregarUsuarioViewController {

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

    private App app;

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

            if (nombre.isEmpty() || cedula.isEmpty() || correo.isEmpty() ||
                    telefonoStr.isEmpty() || contrasena.isEmpty()) {
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

            int id = app.idUsuario++;

            Usuario nuevo = new Usuario(nombre, cedula, correo, contrasena, telefono, id);
            app.listGlobalUsuarios.add(nuevo);

            DialogUtils.mostrarMensaje("User added successfully.");
            cancelar();

        } catch (Exception e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error adding user: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
