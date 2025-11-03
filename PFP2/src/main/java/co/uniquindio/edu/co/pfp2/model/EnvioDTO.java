package co.uniquindio.edu.co.pfp2.model;

import java.time.LocalDate;

/**
 * Data Transfer Object para representar información resumida del Envío.
 * Este objeto se usa para transferir datos entre capas sin exponer la lógica interna del modelo.
 */
public class EnvioDTO {

    private int idEnvio;
    private String direccionOrigen;
    private String direccionDestino;
    private String nombreRepartidor;
    private String estadoEnvio;
    private LocalDate fechaCreacion;
    private double pesoPaquete;

    public EnvioDTO() {}

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
