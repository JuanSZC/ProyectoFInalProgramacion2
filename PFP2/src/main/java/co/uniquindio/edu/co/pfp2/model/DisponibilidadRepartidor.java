package co.uniquindio.edu.co.pfp2.model;

/**
 * Enumeración que representa los diferentes estados de disponibilidad
   que puede tener un {Repartidor} dentro del sistema.
 * Esta enumeración es utilizada por la clase {Repartidor} y por el
 {Administrador} para controlar la asignación de pedidos.
 * Valores posibles:
 * DISPONIBLE: El repartidor está libre y puede recibir nuevos envíos.
 * EN_RUTA: El repartidor se encuentra actualmente realizando una entrega.
 * Ejemplo de uso:
 *     Repartidor repartidor = new Repartidor(...);
 *     repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.EN_RUTA);
 */
public enum DisponibilidadRepartidor {

        /** El repartidor está libre y disponible para recibir nuevos envíos. */
        DISPONIBLE,

        /** El repartidor se encuentra actualmente en proceso de entrega. */
        EN_RUTA;
}