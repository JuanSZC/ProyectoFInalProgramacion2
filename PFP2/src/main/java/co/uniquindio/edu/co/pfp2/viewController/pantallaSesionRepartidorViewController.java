package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.*;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class pantallaSesionRepartidorViewController {

    App app;
    public ObservableList<Repartidor> listGlobalRepartidores;

    @FXML
    TextField txtCorreo;
    @FXML
    TextField txtContrasena;


    public void setListGlobalRepartidores(ObservableList<Repartidor> listGlobalRepartidores) {
        this.listGlobalRepartidores = listGlobalRepartidores;
    }


    public void setApp(App app){
        this.app = app;
    }

    public void openPantallaBienvenida(){
        this.app.openPantallaBienvenida();
    }

    public void verificarRepartidor() {
        String correo = txtCorreo.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            DialogUtils.mostrarError("Por favor ingresa tu correo y contraseña.");
            return;
        }

        Repartidor repartidor = listGlobalRepartidores.stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(correo) && u.getContrasenia().equals(contrasena))
                .findFirst()
                .orElse(null);

        if (repartidor != null) {
            DialogUtils.mostrarMensaje("¡Bienvenido " + repartidor.getNombreCompleto() + "!");
            openPantallaBienvenida();
        } else {
            DialogUtils.mostrarMensaje("Correo o contraseña incorrectos. Intenta de nuevo.");
        }
    }
}
