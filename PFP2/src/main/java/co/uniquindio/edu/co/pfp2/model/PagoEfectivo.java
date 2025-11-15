package co.uniquindio.edu.co.pfp2.model;

/**
 * Implementación del metodo de pago en efectivo,
 * siguiendo el patrón Strategy mediante la interfaz {@link IPago}.
 * Este tipo de pago no requiere información adicional del usuario,
 * ya que la transacción se realiza directamente en físico.
 */
public class PagoEfectivo implements IPago {

    /**
     * Procesa un pago realizado en efectivo.
     * @param monto valor total que el usuario desea pagar
     * @return mensaje confirmando que el pago fue realizado exitosamente en efectivo
     */
    @Override
    public String pagar(double monto) {
        return "Pagó exitosamente un valor de: $ " + monto + " usando efectivo.";
    }
}
