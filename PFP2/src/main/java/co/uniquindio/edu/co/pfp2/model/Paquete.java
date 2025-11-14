package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un paquete dentro del sistema de envíos.
 * <p>
 * Un paquete contiene una lista de productos, junto con información
 * sobre su precio total, peso y dimensiones. Este objeto se utiliza
 * dentro del modelo {@link Envio} para calcular tarifas, costos y
 * gestionar los detalles logísticos del envío.
 * </p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 *     Paquete paquete = new Paquete(120000, 3.5, 0.25);
 *     paquete.getProductos().add(new Producto(...));
 * </pre>
 */
public class Paquete {

    /** Lista de productos incluidos dentro del paquete. */
    private List<Producto> productos;

    /** Precio total estimado del paquete. */
    private double precio;

    /** Peso total del paquete (en kilogramos). */
    private double peso;

    /** Dimensión volumétrica o tamaño del paquete (en metros cúbicos, por ejemplo). */
    private double dimension;

    /**
     * Constructor principal de la clase {@code Paquete}.
     *
     * @param precio    precio total del paquete
     * @param peso      peso del paquete en kilogramos
     * @param dimension dimensión volumétrica del paquete
     */
    public Paquete(double precio, double peso, double dimension) {
        this.productos = new ArrayList<>();
        this.precio = precio;
        this.peso = peso;
        this.dimension = dimension;
    }

    // =================== GETTERS Y SETTERS =================== //

    /**
     * Obtiene la lista de productos incluidos en el paquete.
     *
     * @return lista de productos
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Establece la lista de productos contenida en el paquete.
     *
     * @param productos nueva lista de productos
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Obtiene el precio total del paquete.
     *
     * @return precio total
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio total del paquete.
     *
     * @param precio nuevo precio total
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el peso total del paquete.
     *
     * @return peso en kilogramos
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Modifica el peso total del paquete.
     *
     * @param peso nuevo peso en kilogramos
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Obtiene la dimensión volumétrica del paquete.
     *
     * @return dimensión
     */
    public double getDimension() {
        return dimension;
    }

    /**
     * Modifica la dimensión volumétrica del paquete.
     *
     * @param dimension nueva dimensión
     */
    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    /**
     * Representación en texto del paquete, útil para depuración.
     *
     * @return cadena con la información del paquete.
     */
    @Override
    public String toString() {
        return "Paquete{" +
                "productos=" + productos +
                ", precio=" + precio +
                ", peso=" + peso +
                ", dimension=" + dimension +
                '}';
    }
}
