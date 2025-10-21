package co.uniquindio.edu.co.pfp2.model;

public class ServicioEconomico extends ServicioDecorador {

    public ServicioEconomico(IServicioAdicional servicioAdicional) {

        super(servicioAdicional);
    }

    @Override
    public String getDescripcion() {
        return this.servicioAdicional.getDescripcion()+" Económica.";
    }

    @Override
    public double getPrecio()
    {
        return this.servicioAdicional.getPrecio()-5000;
    }
}
