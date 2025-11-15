package co.uniquindio.edu.co.pfp2.model;

/**
 * Implementación básica del servicio adicional para envíos.
 *
 * <p>Esta clase representa el servicio estándar de entrega a domicilio
 * con un precio fijo. Es el servicio base sobre el cual se aplican
 * los decoradores del patrón Decorator, como {@link ServicioUrgente},
 * {@link ServicioFragil} o {@link ServicioEconomico}.</p>
 *
 * <p>Se utiliza como punto inicial para componer servicios adicionales,
 * permitiendo agregar comportamientos dinámicamente sin modificar la clase.</p>
 *
 * Ejemplo:
 * <pre>
 *     IServicioAdicional servicio = new ServicioBase();
 *     servicio = new ServicioUrgente(servicio);
 *     servicio = new ServicioFragil(servicio);
 * </pre>
 */
public class ServicioBase implements IServicioAdicional {

    /**
     * Retorna la descripción del servicio base.
     *
     * @return descripción por defecto del servicio: "Entrega a Domicilio"
     */
    @Override
    public String getDescripcion() {
        return "Entrega a Domicilio";
    }

    /**
     * Retorna el precio base del servicio.
     *
     * @return precio fijo del servicio: 10000
     */
    @Override
    public double getPrecio() {
        return 10000;
    }
}
