package co.uniquindio.edu.co.pfp2.model;

/**
 * Decorador que aplica la modalidad "Económica" a un servicio adicional.
 *
 * Esta clase implementa el patrón de diseño Decorator, permitiendo extender
 * dinámicamente el comportamiento de un servicio que implementa la interfaz
 * {@link IServicioAdicional}.
 *
 * El servicio económico:
 * - Reduce el costo del servicio en comparación con modalidades más rápidas.
 * - Agrega una descripción indicando que el envío es de tipo económico.
 */
public class ServicioEconomico extends ServicioDecorador {

    /**
     * Constructor del servicio económico.
     *
     * @param servicioAdicional servicio adicional que se desea decorar
     */
    public ServicioEconomico(IServicioAdicional servicioAdicional) {
        super(servicioAdicional);
    }

    /**
     * Retorna la descripción del servicio base, agregando la
     * indicación de que se trata de un servicio de modalidad económica.
     *
     * @return descripción del servicio decorado con "Económica"
     */
    @Override
    public String getDescripcion() {
        return this.servicioAdicional.getDescripcion() + " Económica.";
    }

    /**
     * Calcula el precio del servicio económico restando un valor
     * fijo como descuento por ser un servicio de menor prioridad.
     *
     * @return precio total con el descuento aplicado
     */
    @Override
    public double getPrecio() {
        return this.servicioAdicional.getPrecio() - 5000;
    }
}
