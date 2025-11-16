package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;

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





}
