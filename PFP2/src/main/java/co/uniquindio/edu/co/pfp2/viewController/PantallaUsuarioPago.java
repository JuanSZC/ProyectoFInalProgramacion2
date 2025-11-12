package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.model.Paquete;

public class PantallaUsuarioPago {
    public Paquete getPaq() {
        return paq;
    }

    public void setPaq(Paquete paq) {
        this.paq = paq;
    }

    Paquete paq;

    enum MetodoPago{
        PSE,
        EFECTIVO,
        TARJETA;
    }

    App app;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void pagarEfectivo(){
        if (paq != null) {
            
        }
    }

}
