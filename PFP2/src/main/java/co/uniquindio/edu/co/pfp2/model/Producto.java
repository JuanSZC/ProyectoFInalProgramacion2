package co.uniquindio.edu.co.pfp2.model;

/**
 * Clase que representa un producto dentro del sistema.
 *
 * <p>Un producto contiene información básica como su identificador,
 * nombre, descripción, precio, cantidad disponible y peso.
 * Esta clase es utilizada para construir paquetes, gestionar compras
 * y asociar productos a usuarios dentro del sistema.</p>
 */
public class Producto {

    /** Identificador único del producto. */
    private int idProducto;

    /** Nombre del producto. */
    private String nombre;

    /** Descripción general del producto. */
    private String descripcion;

    /** Precio unitario del producto. */
    private double precio;

    /** Cantidad disponible del producto. */
    private int cantidad;

    /** Peso del producto en kilogramos. */
    private double peso;

    /**
     * Constructor principal de la clase Producto.
     *
     * @param idProducto identificador único del producto
     * @param nombre nombre del producto
     * @param descripcion descripción general del producto
     * @param precio precio monetario del producto
     * @param cantidad cantidad disponible o en inventario
     * @param peso peso del producto
     */
    public Producto(int idProducto, String nombre, String descripcion,
                    double precio, int cantidad, double peso) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.peso = peso;
    }

    /**
     * Obtiene el peso del producto.
     * @return peso en kilogramos
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Modifica el peso del producto.
     * @param peso nuevo peso en kilogramos
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Obtiene el identificador único del producto.
     * @return id del producto
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Modifica el identificador del producto.
     * @param idProducto nuevo ID
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el nombre del producto.
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del producto.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     * @return descripción del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripción del producto.
     * @param descripcion nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     * @return precio monetario
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio del producto.
     * @param precio nuevo precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad disponible del producto.
     * @return cantidad en inventario
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Modifica la cantidad disponible del producto.
     * @param cantidad nueva cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
