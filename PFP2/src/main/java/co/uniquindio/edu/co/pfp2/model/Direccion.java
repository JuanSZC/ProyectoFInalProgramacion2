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

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public ZonaCobertura getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(ZonaCobertura zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }
}
