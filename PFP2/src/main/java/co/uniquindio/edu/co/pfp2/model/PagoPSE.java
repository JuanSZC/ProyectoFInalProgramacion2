package co.uniquindio.edu.co.pfp2.model;

public class PagoPSE implements IPago {

    private String correo;

    @Override
    public String pagar(double monto) {
        return "Pagó exitosamente un valor de: $ "+monto+" a través de PSE usando el correo "+correo+".";
    }
}
