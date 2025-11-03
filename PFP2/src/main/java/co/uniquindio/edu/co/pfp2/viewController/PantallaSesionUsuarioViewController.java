package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PantallaSesionUsuarioViewController {
    App app;
    public ObservableList<Usuario> listGlobalUsuarios;
    @FXML
    TextField txtCorreo;
    @FXML
    TextField txtContrasena;


    public void setListGlobalUsuarios(ObservableList<Usuario> listGlobalUsuarios) {
        this.listGlobalUsuarios = listGlobalUsuarios;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void openPantallaBienvenida(){
        this.app.openPantallaBienvenida();
    }
    public void openPantallaRegistroUsuario(){
        this.app.openPantallaRegistroUsuario();
    }

    public void verificarUsuario() {
        String correo = txtCorreo.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            DialogUtils.mostrarError("Por favor ingresa tu correo y contraseña.");
            return;
        }

        Usuario usuarioEncontrado = listGlobalUsuarios.stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(correo) && u.getContrasenia().equals(contrasena))
                .findFirst()
                .orElse(null);

        if (usuarioEncontrado != null) {
            DialogUtils.mostrarMensaje("¡Bienvenido " + usuarioEncontrado.getNombreCompleto() + "!");
            openPantallaBienvenida();
        } else {
            DialogUtils.mostrarMensaje("Correo o contraseña incorrectos. Intenta de nuevo.");
        }
    }



}
