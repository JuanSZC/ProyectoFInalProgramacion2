package co.uniquindio.edu.co.pfp2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un envío dentro del sistema de gestión de entregas.
 * Un envío contiene información relacionada con:
 *     El paquete a transportar.
 *     La dirección de origen y destino.
 *     El repartidor asignado.
 *     El estado actual del envío.
 *     La fecha de creación de la orden.
 * Esta clase forma parte central del modelo, ya que describe la operación
 * fundamental del sistema: la gestión de un envío desde su creación hasta su entrega.
 */
public class Envio {

    /** Identificador único del envío. */
    private int idEnvio;

    /** Dirección de origen desde donde se recoge el paquete. */
    private Direccion origen;

    /** Dirección de destino donde se entrega el paquete. */
    private Direccion destino;

    /** Paquete asociado al envío, con peso, contenido y valor. */
    private Paquete paquete;

    /** Estado actual del envío según el flujo de la operación. */
    private EstadoEnvio estadoEnvio;

    /** Fecha en la que fue creado el envío. */
    private LocalDate fechaCreacion;

    /** Repartidor asignado para realizar la entrega. */
    private Repartidor repartidor;

    // --- Constructor ---

    /**
     * Constructor principal que inicializa un objeto {@code Envio} con toda su información.
     *
     * @param idEnvio identificador del envío
     * @param origen dirección de origen
     * @param destino dirección de destino
     * @param paquete paquete asociado al envío
     * @param fechaCreacion fecha de creación del envío
     * @param estadoEnvio estado actual del envío
     * @param repartidor repartidor asignado
     */
    public Envio(int idEnvio, Direccion origen, Direccion destino, Paquete paquete,
                 LocalDate fechaCreacion, EstadoEnvio estadoEnvio, Repartidor repartidor) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.paquete = paquete;
        this.fechaCreacion = fechaCreacion;
        this.estadoEnvio = estadoEnvio;
        this.repartidor = repartidor;
    }

    // --- Getters y Setters ---

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Direccion getOrigen() {
        return origen;
    }

    public void setOrigen(Direccion origen) {
        this.origen = origen;
    }

    public Direccion getDestino() {
        return destino;
    }

    public void setDestino(Direccion destino) {
        this.destino = destino;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public EstadoEnvio getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    /**
     * Cambia el estado del envío según el flujo del proceso.
     *
     * @param nuevoEstado nuevo estado del envío
     */
    public void cambiarEstado(EstadoEnvio nuevoEstado) {
        this.estadoEnvio = nuevoEstado;
    }

    /**
     * Convierte este envío en un objeto {@link EnvioDTO}, útil para
     * transferir datos hacia la interfaz o controladores sin exponer
     * toda la estructura del modelo.
     *
     * @return instancia de {@code EnvioDTO} con los datos resumidos del envío
     */
    public EnvioDTO toDTO() {
        return new EnvioDTO(
                this.idEnvio,
                this.origen != null ? this.origen.toString() : "Sin origen",
                this.destino != null ? this.destino.toString() : "Sin destino",
                this.repartidor != null ? this.repartidor.getNombreCompleto() : "Sin repartidor",
                this.estadoEnvio != null ? this.estadoEnvio.name() : "No definido",
                this.fechaCreacion,
                this.paquete != null ? this.paquete.getPeso() : 0.0
        );
    }

    /**
     * Retorna una representación en texto del envío completa,
     * útil para depuración, logs o visualización rápida.
     *
     * @return cadena con los valores del envío
     */
    @Override
    public String toString() {
        return "Envio{" +
                "idEnvio=" + idEnvio +
                ", origen=" + origen +
                ", destino=" + destino +
                ", paquete=" + paquete +
                ", estadoEnvio=" + estadoEnvio +
                ", fechaCreacion=" + fechaCreacion +
                ", repartidor=" + repartidor +
                '}';
    }

    /**
     * Metodo simulado que devolvería la lista de envíos existentes.
     * <p>En un sistema real sería reemplazado por una consulta a una base de datos
     * o un repositorio.</p>
     *
     * @return lista vacía de envíos
     */
    public static List<Envio> listarEnvios() {
        return new ArrayList<>();
    }
}

