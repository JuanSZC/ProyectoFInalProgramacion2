package co.uniquindio.edu.co.pfp2.viewController;
import co.uniquindio.edu.co.pfp2.*;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PantallaRegistroUsuarioViewController {

    public ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    public App app;
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtCedula;
    @FXML
    TextField txtCorreo;
    @FXML
    TextField txtTelefono;
    @FXML
    TextField txtContrasena;

    public void openPantallaBienvenida(){
        this.app.openPantallaBienvenida();
    }

    @FXML
    private void registrarUsuario() {
        try {
            String nombre = txtNombre.getText().trim();
            String cedula = txtCedula.getText().trim();
            String correo = txtCorreo.getText().trim();
            String telefonoStr = txtTelefono.getText().trim();
            String contrasena = txtContrasena.getText().trim();

            if (nombre.isEmpty() || cedula.isEmpty() || correo.isEmpty() ||
                    telefonoStr.isEmpty() || contrasena.isEmpty()) {
                DialogUtils.mostrarError("Todos los campos son obligatorios, genio.");
                return;
            }

            int telefono;
            try {
                telefono = Integer.parseInt(telefonoStr);
            } catch (NumberFormatException e) {
                DialogUtils.mostrarError("El teléfono debe ser un número entero, no letras ni jeroglíficos.");
                return;
            }

            if (!correo.contains("@") || !correo.contains(".")) {
                DialogUtils.mostrarError("Ese correo ni tu abuela te lo acepta. Usa uno válido.");
                return;
            }

            int id = app.idUsuario + 1;

            Usuario nuevo = new Usuario(nombre, cedula, correo, contrasena,telefono, id);
            app.listGlobalUsuarios.add(nuevo);

            DialogUtils.mostrarMensaje("Usuario registrado con éxito. Milagro, no se rompió nada.");

            // Limpiar campos
            txtNombre.clear();
            txtCedula.clear();
            txtCorreo.clear();
            txtTelefono.clear();
            txtContrasena.clear();

        } catch (Exception e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Algo explotó en registrarUsuario(): " + e.getMessage());
        }
        System.out.println(app.listGlobalUsuarios);
    }

}
