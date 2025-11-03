package co.uniquindio.edu.co.pfp2.model;

import java.lang.classfile.instruction.ThrowInstruction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Envio {

    private int idEnvio;
    private Direccion origen;
    private Direccion destino;
    private Paquete paquete;
    private EstadoEnvio estadoEnvio;
    private LocalDate fechaCreacion;
    private Repartidor repartidor;

    public Envio(int idEnvio, Direccion origen, Direccion destino, Paquete paquete, LocalDate fechaCreacion, EstadoEnvio estadoEnvio, Repartidor repartidor) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.paquete = paquete;
        this.fechaCreacion = fechaCreacion;
        this.estadoEnvio = estadoEnvio;
        this.repartidor = repartidor;
    }

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

    public void setEstadoEnvio(EstadoEnvio estado) {
        this.estadoEnvio = estado;
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

    public void cambiarEstado( EstadoEnvio nuevoEstado) {
        this.setEstadoEnvio(nuevoEstado);
    }

    //Clase que agrega los nuevos envios
    public List<Envio> listEnvios() {
        return new ArrayList<>(listEnvios());
    }
    }





