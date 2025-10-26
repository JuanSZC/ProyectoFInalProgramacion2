package co.uniquindio.edu.co.pfp2.model;

public class Tarifa {

    private Paquete paquete;
    private IServicioAdicional servicioAdicional;

    public Tarifa(Paquete paquete) {
        this.paquete = paquete;

    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public IServicioAdicional getServicioAdicional() {
        return servicioAdicional;
    }

    public void setServicioAdicional(IServicioAdicional servicioAdicional) {
        this.servicioAdicional = servicioAdicional;
    }
}
