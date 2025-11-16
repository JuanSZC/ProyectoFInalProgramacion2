package co.uniquindio.edu.co.pfp2.model;

/**
 * Implementación concreta de la estrategia de pago {@link IPago}
 * que permite realizar pagos mediante tarjeta de crédito o débito.
 * <p>
 * Esta clase forma parte del patrón Strategy, permitiendo que el
 * sistema procese diferentes métodos de pago de forma flexible.
 * </p>
 */
public class PagoTarjeta implements IPago {

    /** Número de la tarjeta usada para realizar el pago. */
    private int numeroTarjeta;

    /**
     * Procesa el pago utilizando los datos de la tarjeta.
     *
     * @param monto valor total a pagar
     * @return mensaje confirmando la transacción realizada con tarjeta
     */
    @Override
    public String pagar(double monto) {
        return "Pagó exitosamente un valor de: $ " + monto + " usando la tarjeta no. " + numeroTarjeta + ".";
    }
}
