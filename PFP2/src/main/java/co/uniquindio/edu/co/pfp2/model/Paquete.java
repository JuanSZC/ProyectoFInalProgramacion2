package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un paquete dentro del sistema de envíos.
 * Un paquete contiene una lista de productos, junto con información
 sobre su precio total, peso y dimensiones.
 * Se utiliza como parte del modelo {@link Envio} para calcular tarifas
 y gestionar los detalles logísticos.</p>
 * Ejemplo de uso:
 *     Paquete paquete = new Paquete(120000, 3.5, 0.25);
 *     paquete.getProductos().add(new Producto("Laptop", 1.5, 100000));
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
     * @param precio precio total del paquete
     * @param peso peso del paquete en kilogramos
     * @param dimension dimensión del paquete
     */
    public Paquete(double precio, double peso, double dimension) {
        this.productos = new ArrayList<>();
        this.precio = precio;
        this.peso = peso;
        this.dimension = dimension;
    }

    // =================== GETTERS Y SETTERS =================== //

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

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
