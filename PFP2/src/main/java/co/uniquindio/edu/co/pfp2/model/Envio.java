package co.uniquindio.edu.co.pfp2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un Envío dentro del sistema.
 * Contiene información del paquete, direcciones, repartidor y estado del envío.
 */
public class Envio {

    private int idEnvio;
    private Direccion origen;
    private Direccion destino;
    private Paquete paquete;
    private EstadoEnvio estadoEnvio;
    private LocalDate fechaCreacion;
    private Repartidor repartidor;

    // --- Constructor ---
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
     * Cambia el estado del envío.
     * @param nuevoEstado nuevo estado del envío
     */
    public void cambiarEstado(EstadoEnvio nuevoEstado) {
        this.estadoEnvio = nuevoEstado;
    }

    /**
     * Convierte este objeto Envio a un EnvioDTO (Data Transfer Object)
     * para transferir solo los datos esenciales a otras capas del sistema.
     * @return objeto EnvioDTO con los datos del envío
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
     * Metodo simulado que devuelve la lista de envíos.
     * (En un caso real, se usaría un repositorio o servicio para esto).
     */
    public static List<Envio> listarEnvios() {
        return new ArrayList<>();
    }
}

