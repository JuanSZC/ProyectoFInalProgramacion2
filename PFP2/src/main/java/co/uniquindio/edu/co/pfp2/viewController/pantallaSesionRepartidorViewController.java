package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.*;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.scene.Parent;

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
            // Abrir la pantalla principal del repartidor al iniciar sesión
            try {
                if (this.app != null) {
                    this.app.openPantallaRepartidor(repartidor);
                } else {
                    openPantallaBienvenida();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                DialogUtils.mostrarError("No fue posible abrir la pantalla del repartidor: " + ex.getMessage());
            }
        } else {
            DialogUtils.mostrarMensaje("Correo o contraseña incorrectos. Intenta de nuevo.");
        }
    }


}
