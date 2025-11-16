package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaAdministradorConfiguracionViewController {

    private App app;

    @FXML
    private TextField tfCorreo;

    @FXML
    private TextField tfContrasenia;

    public void setApp(App app) {
        this.app = app;
        if (this.app != null && this.app.admin != null) {
            tfCorreo.setText(this.app.admin.getCorreo());
            tfContrasenia.setText(String.valueOf(this.app.admin.getClave()));
        }
    }

    @FXML
    private void guardarCambios() {
        if (app == null || app.admin == null) return;

        String correo = tfCorreo.getText().trim();
        String contras = tfContrasenia.getText().trim();

        if (correo.isEmpty() || contras.isEmpty()) {
            DialogUtils.mostrarError("Campos vacíos.");
            return;
        }

        app.admin.setCorreo(correo);
        try {
            int clave = Integer.parseInt(contras);
            app.admin.setClave(clave);
            DialogUtils.mostrarMensaje("Datos del administrador actualizados.");
            Stage st = (Stage) tfCorreo.getScene().getWindow();
            st.close();
        } catch (NumberFormatException ex) {
            DialogUtils.mostrarError("La clave debe ser numérica.");
        }
    }

    @FXML
    private void cerrarSesion() {
        Stage st = (Stage) tfCorreo.getScene().getWindow();
        st.close();
        if (app != null) app.openPantallaBienvenida();
    }
}
