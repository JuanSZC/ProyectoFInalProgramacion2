package co.uniquindio.edu.co.pfp2.model;

/**
 * Clase base para los decoradores del patrón Decorator que permiten añadir
 * funcionalidades adicionales a objetos que implementen la interfaz
 * {@link IServicioAdicional}.
 *
 * <p>Este decorador funciona como envoltorio del servicio original, delegando
 * sus métodos sin alterarlos. Las clases hijas pueden extender o modificar
 * la funcionalidad sobrescribiendo los métodos {@code getDescripcion()} y
 * {@code getPrecio()}.</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 *     IServicioAdicional servicio = new ServicioBase();
 *     servicio = new ServicioUrgente(servicio);
 *     servicio = new ServicioFragil(servicio);
 * </pre>
 *
 * Esto permite componer comportamientos sin modificar la clase original.
 */
public class ServicioDecorador implements IServicioAdicional {

    /**
     * Servicio adicional que se está decorando.
     */
    protected IServicioAdicional servicioAdicional;

    /**
     * Constructor que recibe el servicio que será decorado.
     *
     * @param servicioAdicional servicio base o ya decorado
     */
    public ServicioDecorador(IServicioAdicional servicioAdicional) {
        this.servicioAdicional = servicioAdicional;
    }

    /**
     * Retorna la descripción del servicio decorado.
     * Este método puede ser extendido por las subclases para agregar texto adicional.
     *
     * @return descripción del servicio
     */
    @Override
    public String getDescripcion() {
        return this.servicioAdicional.getDescripcion();
    }

    /**
     * Retorna el precio del servicio decorado.
     * Las subclases pueden ajustar el costo sumando o restando valores.
     *
     * @return precio total del servicio
     */
    @Override
    public double getPrecio() {
        return this.servicioAdicional.getPrecio();
    }
}
