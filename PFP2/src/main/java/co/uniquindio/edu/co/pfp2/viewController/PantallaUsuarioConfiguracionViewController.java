package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import javafx.stage.Stage;

public class PantallaUsuarioConfiguracionViewController {
    App app;

    public void setApp(App app) {
        this.app = app;
    }

    public void agregarDireccion(){
        app.openPantallaDireccionUsuario((Stage) this.app.stage.getScene().getWindow());
    }
}
