package co.uniquindio.edu.co.pfp2.model;

public class ServicioBase implements IServicioAdicional{

    @Override
    public String getDescripcion() {
        return "Entrega a Domicilio";
    }

    @Override
    public double getPrecio() {
        return 10000;
    }
}
