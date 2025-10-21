package co.uniquindio.edu.co.pfp2.model;

public class PagoTarjeta implements IPago {

    private int numeroTarjeta;
    private int cvv;

    @Override
    public String pagar(double monto) {
        return "Pagó exitosamente un valor de: $ "+monto+" usando la tarjeta no. "+numeroTarjeta+".";
    }
}
