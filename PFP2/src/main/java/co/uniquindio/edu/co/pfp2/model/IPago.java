package co.uniquindio.edu.co.pfp2.model;

/**
 * Interfaz que define el comportamiento general para los diferentes métodos de pago
   disponibles en el sistema.
 * Forma parte de la implementación del patrón de diseño <strong>Strategy</strong>,
 * permitiendo que cada tipo de pago (tarjeta, PSE, efectivo, etc.) implemente su propia
 * lógica sin afectar al resto del sistema.
 * Ejemplo de uso:
 *     IPago pago = new PagoTarjeta();
 *     String resultado = pago.pagar(50000);
 */
public interface IPago {

    /**
     * Ejecuta el proceso de pago según el método implementado.
     *
     * @param monto valor total que se desea pagar.
     * @return mensaje indicando el resultado del pago.
     */
    public String pagar(double monto);
}
