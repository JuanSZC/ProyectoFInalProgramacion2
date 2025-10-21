package co.uniquindio.edu.co.pfp2.model;

public class ServicioUrgente extends ServicioDecorador{
    public ServicioUrgente(IServicioAdicional servicioAdicional) {
        super(servicioAdicional);
    }
    @Override
    public String getDescripcion() {
        return this.getDescripcion()+" Urgente.";
    }
    @Override
    public double getPrecio()
    {
        return this.getPrecio()+5000;
    }
}
