package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaModificarUsuarioViewController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtContrasena;

    private Usuario usuarioAModificar;

    public void setUsuario(Usuario usuario) {
        this.usuarioAModificar = usuario;
        cargarDatos();
    }

    private void cargarDatos() {
        if (usuarioAModificar != null) {
            txtNombre.setText(usuarioAModificar.getNombreCompleto());
            txtCorreo.setText(usuarioAModificar.getCorreo());
            txtTelefono.setText(String.valueOf(usuarioAModificar.getTelefono()));
            txtContrasena.setText(usuarioAModificar.getContrasenia());
        }
    }

    @FXML
    private void modificar() {
        try {
            String nombre = txtNombre.getText().trim();
            String correo = txtCorreo.getText().trim();
            String telefonoStr = txtTelefono.getText().trim();
            String contrasena = txtContrasena.getText().trim();

            if (nombre.isEmpty() || correo.isEmpty() || telefonoStr.isEmpty() || contrasena.isEmpty()) {
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

            usuarioAModificar.setNombreCompleto(nombre);
            usuarioAModificar.setCorreo(correo);
            usuarioAModificar.setTelefono(telefono);
            usuarioAModificar.setContrasenia(contrasena);

            DialogUtils.mostrarMensaje("Usuario actualizado correctamente.");
            cancelar();

        } catch (Exception e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
