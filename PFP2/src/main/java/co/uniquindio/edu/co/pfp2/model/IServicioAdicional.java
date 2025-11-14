package co.uniquindio.edu.co.pfp2.model;

/**
 * Interfaz que define el comportamiento base para los servicios adicionales
   asociados a un envío dentro del sistema.
 * Esta interfaz forma parte de la implementación del patrón de diseño
 * Decorator, permitiendo agregar funcionalidades adicionales
 * (como servicio urgente, frágil o económico) sin modificar la estructura
 * de la clase base. Cada servicio adicional puede modificar la descripción
 * y el precio final del envío.
 */
public interface IServicioAdicional {

    /**
     * Obtiene la descripción del servicio adicional aplicado.
     *
     * @return una cadena que describe el servicio.
     */
    public String getDescripcion();

    /**
     * Obtiene el precio total del servicio adicional aplicado.
     *
     * @return el valor monetario del servicio adicional.
     */
    public double getPrecio();
}
