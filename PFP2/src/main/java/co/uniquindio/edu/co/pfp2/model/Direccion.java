package co.uniquindio.edu.co.pfp2.model;
/**
 * Clase que representa una dirección geográfica dentro del sistema de envíos.
 * Cada dirección contiene un identificador único, una descripción (como una calle o referencia),
   y sus coordenadas geográficas (latitud y longitud).
 * Esta clase es utilizada por usuarios, repartidores y envíos para definir puntos de origen y destino.
 * Ejemplo de uso:
 *  Direccion direccion = new Direccion(1, "Calle 10 #15-23, Armenia", 4.5342, -75.6721);
 */
public class Direccion {

    private int idDireccion;
    private String descripcion;
    private double latitud;
    private double longitud;
    private ZonaCobertura zonaCobertura;

    /**
     * Constructor principal de la clase {Direccion}.
     * @param idDireccion identificador único de la dirección
     * @param descripcion texto descriptivo de la ubicación
     * @param latitud     coordenada de latitud geográfica
     * @param longitud    coordenada de longitud geográfica
     */
    public Direccion(int idDireccion, String descripcion, double latitud, double longitud,  ZonaCobertura zonaCobertura) {
        this.idDireccion = idDireccion;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.zonaCobertura = zonaCobertura;
    }
    // =================== GETTERS Y SETTERS =================== //

    /**
     * Obtiene el identificador único de la dirección.
     * @return ID de la dirección
     */
    public int getIdDireccion() {
        return idDireccion;
    }

    /**
     * Establece el identificador único de la dirección.
     * @param idDireccion nuevo ID de la dirección
     */
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    /**
     * Obtiene la descripción textual de la dirección.
     * @return descripción de la ubicación
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción textual de la dirección.
     * @param descripcion nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la coordenada de latitud.
     * @return latitud geográfica
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Establece la latitud de la dirección.
     * @param latitud nueva coordenada de latitud
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * Obtiene la coordenada de longitud.
     * @return longitud geográfica
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Establece la longitud de la dirección.
     * @param longitud nueva coordenada de longitud
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * Obtiene la zona de cobertura asociada a esta dirección.
     * @return zona de cobertura
     */
    public ZonaCobertura getZonaCobertura() {
        return zonaCobertura;
    }

    /**
     * Establece la zona de cobertura para esta dirección.
     * @param zonaCobertura nueva zona de cobertura
     */
    public void setZonaCobertura(ZonaCobertura zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }
}
