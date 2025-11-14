package co.uniquindio.edu.co.pfp2.model;

/**
 * Clase que representa la tarifa asociada a un envío.
 *
 * Una tarifa puede estar compuesta por:
 * - Un {@link Paquete}, cuya información como peso, precio y dimensiones influye en el costo.
 * - Un servicio adicional opcional que implementa la interfaz {@link IServicioAdicional},
 *   como seguros, manejo especial u otros cargos agregados.
 *
 * Esta clase actúa como contenedor de la información necesaria para calcular
 * el costo total de un envío utilizando reglas definidas en otras capas del sistema.
 */
public class Tarifa {

    /** Paquete al cual se le desea calcular o asignar una tarifa */
    private Paquete paquete;

    /** Servicio adicional opcional que aumenta el costo del envío */
    private IServicioAdicional servicioAdicional;

    /**
     * Constructor principal que inicializa la tarifa con un paquete.
     *
     * @param paquete paquete sobre el cual se calculará la tarifa
     */
    public Tarifa(Paquete paquete) {
        this.paquete = paquete;
    }

    /**
     * Obtiene el paquete asociado a la tarifa.
     *
     * @return paquete actual
     */
    public Paquete getPaquete() {
        return paquete;
    }

    /**
     * Asigna un paquete diferente a la tarifa.
     *
     * @param paquete nuevo paquete a asociar
     */
    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    /**
     * Obtiene el servicio adicional asociado a la tarifa.
     *
     * @return servicio adicional actual o null si no hay
     */
    public IServicioAdicional getServicioAdicional() {
        return servicioAdicional;
    }

    /**
     * Asigna un servicio adicional a la tarifa.
     *
     * @param servicioAdicional servicio que implementa {@link IServicioAdicional}
     */
    public void setServicioAdicional(IServicioAdicional servicioAdicional) {
        this.servicioAdicional = servicioAdicional;
    }
}
