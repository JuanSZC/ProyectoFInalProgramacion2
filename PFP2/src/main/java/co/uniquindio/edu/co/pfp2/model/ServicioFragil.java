package co.uniquindio.edu.co.pfp2.model;

public class ServicioFragil extends ServicioDecorador{

    public ServicioFragil(IServicioAdicional servicioAdicional) {
        super(servicioAdicional);
    }

    @Override
    public String getDescripcion() {
        return this.getDescripcion()+" Con producto frágil.";
    }

    @Override
    public double getPrecio(){
        return this.getPrecio()+5000;
    }
}
