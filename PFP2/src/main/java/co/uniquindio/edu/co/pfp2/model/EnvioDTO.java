package co.uniquindio.edu.co.pfp2.model;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) que representa una versión simplificada
 * y segura de la información de un envío. Su propósito principal es
 * transferir datos entre las capas del sistema (controladores, vistas,
 * servicios) sin exponer la lógica de negocio interna de la clase
 * {@link Envio}.
 *
 * <p>Este objeto resulta especialmente útil para:</p>
 * <ul>
 *     <li>Mostrar información en la interfaz gráfica.</li>
 *     <li>Reducir el acoplamiento entre capas.</li>
 *     <li>Evitar la modificación accidental de entidades del modelo.</li>
 *     <li>Optimizar la transferencia de datos, enviando solo lo necesario.</li>
 * </ul>
 *
 * <p>Incluye información resumida como direcciones, estado, repartidor,
 * fecha y peso, facilitando consultas y reportes.</p>
 */
public class EnvioDTO {

    /** Identificador único del envío. */
    private int idEnvio;

    /** Dirección de origen asociada al envío. */
    private String direccionOrigen;

    /** Dirección de destino donde debe entregarse el pedido. */
    private String direccionDestino;

    /** Nombre completo del repartidor asignado. */
    private String nombreRepartidor;

    /** Estado actual del envío en formato de texto. */
    private String estadoEnvio;

    /** Fecha en que fue creado el envío. */
    private LocalDate fechaCreacion;

    /** Peso total del paquete asociado al envío. */
    private double pesoPaquete;

    /**
     * Constructor vacío requerido para procesos de serialización,
     * frameworks de mapeo y herramientas de interfaz.
     */
    public EnvioDTO() {}

    /**
     * Constructor completo que inicializa un objeto {@code EnvioDTO} con toda la
     * información resumida del envío.
     *
     * @param idEnvio identificador del envío
     * @param direccionOrigen dirección de origen
     * @param direccionDestino dirección de destino
     * @param nombreRepartidor nombre del repartidor encargado
     * @param estadoEnvio estado actual del envío en texto
     * @param fechaCreacion fecha en que se creó el envío
     * @param pesoPaquete peso total del paquete
     */
    public EnvioDTO(int idEnvio, String direccionOrigen, String direccionDestino,
                    String nombreRepartidor, String estadoEnvio,
                    LocalDate fechaCreacion, double pesoPaquete) {
        this.idEnvio = idEnvio;
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.nombreRepartidor = nombreRepartidor;
        this.estadoEnvio = estadoEnvio;
        this.fechaCreacion = fechaCreacion;
        this.pesoPaquete = pesoPaquete;
    }

    // --- Getters y Setters ---

    public int getIdEnvio() { return idEnvio; }
    public void setIdEnvio(int idEnvio) { this.idEnvio = idEnvio; }

    public String getDireccionOrigen() { return direccionOrigen; }
    public void setDireccionOrigen(String direccionOrigen) { this.direccionOrigen = direccionOrigen; }

    public String getDireccionDestino() { return direccionDestino; }
    public void setDireccionDestino(String direccionDestino) { this.direccionDestino = direccionDestino; }

    public String getNombreRepartidor() { return nombreRepartidor; }
    public void setNombreRepartidor(String nombreRepartidor) { this.nombreRepartidor = nombreRepartidor; }

    public String getEstadoEnvio() { return estadoEnvio; }
    public void setEstadoEnvio(String estadoEnvio) { this.estadoEnvio = estadoEnvio; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public double getPesoPaquete() { return pesoPaquete; }
    public void setPesoPaquete(double pesoPaquete) { this.pesoPaquete = pesoPaquete; }

    /**
     * Devuelve una representación textual del objeto, útil para depuración
     * y generación de reportes rápidos.
     *
     * @return cadena que describe el contenido del DTO.
     */
    @Override
    public String toString() {
        return "EnvioDTO{" +
                "idEnvio=" + idEnvio +
                ", origen='" + direccionOrigen + '\'' +
                ", destino='" + direccionDestino + '\'' +
                ", repartidor='" + nombreRepartidor + '\'' +
                ", estado='" + estadoEnvio + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", pesoPaquete=" + pesoPaquete +
                '}';
    }
}
