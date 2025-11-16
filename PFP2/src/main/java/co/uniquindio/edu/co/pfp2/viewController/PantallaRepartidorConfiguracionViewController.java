package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaRepartidorConfiguracionViewController {

    private App app;
    private Repartidor repartidor;

    @FXML
    private TextField tfCorreo;

    @FXML
    private TextField tfContrasenia;

    public void setApp(App app) {
        this.app = app;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
        if (this.repartidor != null) {
            tfCorreo.setText(repartidor.getCorreo());
            tfContrasenia.setText(repartidor.getContrasenia());
        }
    }

    @FXML
    private void guardarCambios() {
        if (repartidor == null || app == null) return;

        String correo = tfCorreo.getText().trim();
        String contrasenia = tfContrasenia.getText().trim();

        if (correo.isEmpty() || contrasenia.isEmpty()) {
            DialogUtils.mostrarError("Los campos no pueden estar vacíos.");
            return;
        }

        repartidor.setCorreo(correo);
        repartidor.setContrasenia(contrasenia);

        DialogUtils.mostrarMensaje("Datos actualizados correctamente.");
        Stage st = (Stage) tfCorreo.getScene().getWindow();
        st.close();
    }

    @FXML
    private void cerrarSesion() {
        Stage st = (Stage) tfCorreo.getScene().getWindow();
        st.close();
        if (app != null) {
            app.openPantallaBienvenida();
        }
    }
}
