package co.uniquindio.edu.co.pfp2.model;

/**
 * Clase que representa a un repartidor dentro del sistema de envíos.
 *
 * <p>Hereda de {@link Persona} y añade atributos específicos como el
 * identificador único del repartidor, la zona donde opera y su estado
 * de disponibilidad.</p>
 *
 * <p>Los repartidores pueden estar asignados a distintas zonas de cobertura
 * y su disponibilidad cambia según se encuentren realizando entregas
 * o estén libres para recibir nuevos pedidos.</p>
 */
public class Repartidor extends Persona {

    /** Identificador único del repartidor. */
    private int idRepartidor;

    /** Zona geográfica donde el repartidor presta su servicio. */
    private ZonaCobertura zonaCobertura;

    /**
     * Estado de disponibilidad del repartidor.
     * Por defecto se inicializa como {@code DISPONIBLE}.
     */
    private DisponibilidadRepartidor disponibilidadRepartidor = DisponibilidadRepartidor.DISPONIBLE;

    /**
     * Constructor principal de la clase Repartidor.
     *
     * @param idRepartidor identificador único del repartidor
     * @param nombreCompleto nombre completo del repartidor
     * @param cedula documento de identidad del repartidor
     * @param correo correo electrónico del repartidor
     * @param contrasenia contraseña asociada al usuario repartidor
     * @param telefono número telefónico del repartidor
     * @param zonaCobertura zona en la que opera el repartidor
     * @param disponibilidadRepartidor estado inicial de disponibilidad
     */
    public Repartidor(int idRepartidor, String nombreCompleto, String cedula, String correo,
                      String contrasenia, int telefono, ZonaCobertura zonaCobertura,
                      DisponibilidadRepartidor disponibilidadRepartidor) {

        super(nombreCompleto, cedula, correo, contrasenia, telefono);
        this.idRepartidor = idRepartidor;
        this.zonaCobertura = zonaCobertura;
        this.disponibilidadRepartidor = disponibilidadRepartidor;
    }

    /**
     * Obtiene el identificador del repartidor.
     * @return id del repartidor
     */
    public int getIdRepartidor() {
        return idRepartidor;
    }

    /**
     * Actualiza el identificador del repartidor.
     * @param idRepartidor nuevo ID
     */
    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    /**
     * Obtiene la zona de cobertura del repartidor.
     * @return zona asignada
     */
    public ZonaCobertura getZonaCobertura() {
        return zonaCobertura;
    }

    /**
     * Modifica la zona de cobertura del repartidor.
     * @param zonaCobertura nueva zona
     */
    public void setZonaCobertura(ZonaCobertura zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }

    /**
     * Obtiene el estado de disponibilidad actual del repartidor.
     * @return disponibilidad actual
     */
    public DisponibilidadRepartidor getDisponibilidadRepartidor() {
        return disponibilidadRepartidor;
    }

    /**
     * Modifica la disponibilidad del repartidor.
     * @param disponibilidadRepartidor nuevo estado de disponibilidad
     */
    public void setDisponibilidadRepartidor(DisponibilidadRepartidor disponibilidadRepartidor) {
        this.disponibilidadRepartidor = disponibilidadRepartidor;
    }

    /**
     * Indica si el repartidor está disponible.
     *
     * <p>Retorna {@code false} únicamente si el atributo de disponibilidad es null.
     * En cualquier otro caso se considera que sí tiene un estado válido.</p>
     *
     * @return true si la disponibilidad es diferente de null, false de lo contrario
     */
    public boolean isDisponible() {
        if (disponibilidadRepartidor == null) {
            return false;
        }
        return true;
    }
}
