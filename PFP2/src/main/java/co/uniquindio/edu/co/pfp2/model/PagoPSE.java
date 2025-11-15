package co.uniquindio.edu.co.pfp2.model;

/**
 * Implementación del metodo de pago mediante PSE,
 * siguiendo el patrón Strategy a través de la interfaz {@link IPago}.
 * <p>
 * Este metodo utiliza un correo asociado a la cuenta bancaria
 * del usuario para procesar la transacción.
 * </p>
 */
public class PagoPSE implements IPago {

    /** Correo electrónico asociado a la cuenta PSE del usuario. */
    private String correo;

    /**
     * Ejecuta el pago utilizando el metodo PSE.
     *
     * @param monto valor total que se desea pagar
     * @return mensaje confirmando que el pago fue realizado exitosamente mediante PSE
     */
    @Override
    public String pagar(double monto) {
        return "Pagó exitosamente un valor de: $ " + monto + " a través de PSE usando el correo " + correo + ".";
    }
}
