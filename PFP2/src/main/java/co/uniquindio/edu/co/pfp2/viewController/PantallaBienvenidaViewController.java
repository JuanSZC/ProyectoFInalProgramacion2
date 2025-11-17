package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import javafx.application.Platform;
import javafx.scene.Parent;

public class PantallaBienvenidaViewController {
    App app;

    public void setApp(App app) {
        this.app = app;
    }

    public void openPantallaSesionUsuario(){
        this.app.openPantallaSesionUsuario();
    }
    public void openPantallaSesionRepartidor(){
        this.app.openPantallaSesionRepartidor();
    }
    public void openPantallaSesionAdministrador(){
        this.app.openPantallaSesionAdministrador();
    }

    public void initialize(){
        Platform.runLater(() -> {
            if (app != null && app.stage != null && app.stage.getScene() != null){
                Parent root = app.stage.getScene().getRoot();
                VisualUtils.applyRoleStyles(root, "neutral");
            }
        });
    }

}
