package co.uniquindio.edu.co.pfp2.viewController;
import co.uniquindio.edu.co.pfp2.*;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Administrador;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PantallaSesionAdministradorViewController {

    App app;
    Administrador administrador;

    @FXML
    TextField txtCorreo;
    @FXML
    TextField txtContrasena;

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void openPantallaBienvenida() {
        this.app.openPantallaBienvenida();
    }

    public void verificarAdmin() {
        String correo = txtCorreo.getText().trim();
        String contrasenaTexto = txtContrasena.getText().trim();

        if (correo.isEmpty() || contrasenaTexto.isEmpty()) {
            DialogUtils.mostrarError("Por favor ingresa tu correo y contraseña.");
            return;
        }

        int contrasena;
        try {
            contrasena = Integer.parseInt(contrasenaTexto);
        } catch (NumberFormatException e) {
            DialogUtils.mostrarError("La contraseña debe ser numérica.");
            return;
        }

        if (administrador != null &&
                administrador.getCorreo().equalsIgnoreCase(correo) &&
                administrador.getClave() == contrasena) {

            DialogUtils.mostrarMensaje("¡Bienvenido, administrador!");
            openPantallaBienvenida();

        } else {
            DialogUtils.mostrarError("Correo o contraseña incorrectos.");
        }
    }


}

