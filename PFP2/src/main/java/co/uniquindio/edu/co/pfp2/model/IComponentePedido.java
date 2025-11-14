package co.uniquindio.edu.co.pfp2.model;

/**
 * Interfaz que define el comportamiento base para los elementos que conforman
 * un pedido dentro del sistema.
 * Esta interfaz es utilizada como parte del patrón de diseño <strong>Composite</strong>,
 * permitiendo que tanto elementos simples (como un producto) como elementos compuestos
 * (como un paquete o un conjunto de productos) puedan ser tratados de forma uniforme.
 * Ejemplo de uso:
 *     IComponentePedido componente = new Producto(...);
 *     String info = componente.mostrarInformacion();
 */
public interface IComponentePedido {

    /**
     * Permite obtener la información detallada del componente del pedido.
     *
     * @return una cadena con la descripción del componente.
     */
    public String mostrarInformacion();
}
