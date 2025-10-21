package co.uniquindio.edu.co.pfp2.model;

public class PagoEfectivo implements IPago {
    @Override
    public String pagar(double monto) {
        return "Pagó exitosamente un valor de: $ "+monto+" usando efectivo.";
    }
}
