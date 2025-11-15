package co.uniquindio.edu.co.pfp2.model;

/**
 * Representa un servicio adicional de tipo "Urgente" aplicado a un envío.
 *
 * Esta clase forma parte del patrón Decorator, extendiendo {@link ServicioDecorador}
 * para añadir características adicionales a un objeto que implementa {@link IServicioAdicional}.
 *
 * El servicio urgente:
 * - Incrementa el costo del servicio decorado.
 * - Agrega una anotación textual a la descripción del servicio.
 *
 * Este decorador se utiliza cuando el usuario desea que el envío sea procesado
 * con mayor prioridad o rapidez.
 */
public class ServicioUrgente extends ServicioDecorador {

    /**
     * Constructor que recibe un servicio adicional a decorar.
     *
     * @param servicioAdicional servicio base al que se le agregará la funcionalidad urgente
     */
    public ServicioUrgente(IServicioAdicional servicioAdicional) {
        super(servicioAdicional);
    }

    /**
     * Retorna la descripción del servicio decorado,
     * añadiendo la etiqueta "Urgente." al final.
     *
     * @return descripción extendida del servicio adicional
     */
    @Override
    public String getDescripcion() {
        return this.getDescripcion() + " Urgente.";
    }

    /**
     * Retorna el precio del servicio decorado,
     * sumando un costo adicional fijo de 5000.
     *
     * @return precio total del servicio urgente
     */
    @Override
    public double getPrecio() {
        return this.getPrecio() + 5000;
    }
}
