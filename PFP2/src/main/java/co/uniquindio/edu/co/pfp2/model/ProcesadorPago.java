package co.uniquindio.edu.co.pfp2.model;

import java.time.LocalDate;

/**
 * Clase encargada de gestionar el procesamiento de pagos dentro del sistema.
 *
 * <p>Esta clase utiliza el patrón Estrategia mediante la interfaz {@code IPago},
 * permitiendo procesar diferentes métodos de pago (como tarjeta, transferencia, etc.)
 * sin acoplarse a implementaciones específicas.</p>
 *
 * <p>Cada vez que se crea un {@code ProcesadorPago}, se registra automáticamente
 * la fecha en la que fue realizado el intento de pago.</p>
 */
public class ProcesadorPago {

    /** Estrategia de pago utilizada para procesar la transacción. */
    private IPago pago;

    /** Fecha en la que se realiza el procesamiento del pago. */
    private LocalDate fecha;

    /**
     * Constructor del procesador de pagos.
     *
     * @param pago objeto que implementa la estrategia de pago
     */
    public ProcesadorPago(IPago pago) {
        this.pago = pago;
        this.fecha = LocalDate.now();
    }

    /**
     * Procesa el pago utilizando la estrategia asignada.
     *
     * @return mensaje indicando si la transacción fue exitosa o rechazada
     */
    public String procesarPago() {
        if (this.pago == null) return "Transaccion Rechazada";
        return "Pago realizado con éxito";
    }

    /**
     * Obtiene la estrategia de pago actual.
     *
     * @return objeto que implementa {@code IPago}
     */
    public IPago getPago() {
        return pago;
    }

    /**
     * Asigna una nueva estrategia de pago.
     *
     * @param pago estrategia de pago
     */
    public void setPago(IPago pago) {
        this.pago = pago;
    }

    /**
     * Obtiene la fecha de procesamiento del pago.
     *
     * @return fecha del pago
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Modifica la fecha registrada del pago.
     *
     * @param fecha nueva fecha a asignar
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
