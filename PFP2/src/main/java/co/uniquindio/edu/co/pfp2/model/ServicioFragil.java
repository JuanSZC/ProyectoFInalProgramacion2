package co.uniquindio.edu.co.pfp2.model;

/**
 * Decorador que agrega la característica "Frágil" a un servicio adicional.
 *
 * Esta clase forma parte del patrón de diseño Decorator, permitiendo añadir
 * comportamiento extra a objetos que implementan la interfaz {@link IServicioAdicional}.
 *
 * El servicio frágil:
 * - Añade una anotación especial a la descripción indicando que el paquete es delicado.
 * - Aumenta el costo del servicio debido al manejo especial requerido.
 */
public class ServicioFragil extends ServicioDecorador {

    /**
     * Constructor del decorador.
     *
     * @param servicioAdicional servicio adicional base que será decorado con la opción "frágil"
     */
    public ServicioFragil(IServicioAdicional servicioAdicional) {
        super(servicioAdicional);
    }

    /**
     * Retorna la descripción original del servicio decorado,
     * agregando la indicación de que el producto es frágil.
     *
     * @return descripción extendida del servicio con la indicación "Con producto frágil"
     */
    @Override
    public String getDescripcion() {
        return this.getDescripcion() + " Con producto frágil.";
    }

    /**
     * Retorna el precio original del servicio decorado,
     * sumando un valor adicional por manejo de objetos frágiles.
     *
     * @return precio total del servicio con el recargo por fragilidad
     */
    @Override
    public double getPrecio() {
        return this.getPrecio() + 5000;
    }
}
