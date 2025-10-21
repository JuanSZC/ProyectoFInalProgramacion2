package co.uniquindio.edu.co.pfp2.model;

public class ServicioDecorador implements IServicioAdicional{

    protected IServicioAdicional servicioAdicional;

    public ServicioDecorador(IServicioAdicional servicioAdicional) {
        this.servicioAdicional = servicioAdicional;
    }

    @Override
    public String getDescripcion() {
        return this.servicioAdicional.getDescripcion();
    }

    @Override
    public double getPrecio() {
        return this.servicioAdicional.getPrecio();
    }
}
