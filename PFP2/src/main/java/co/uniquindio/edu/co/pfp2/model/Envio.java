package co.uniquindio.edu.co.pfp2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Envio {

    private int idEnvio;
    private Direccion origen;
    private Direccion destino;
    private Paquete paquete;
    private EstadoEnvio estado;
    private LocalDate fechaCreacion;
    private EstadoEnvio estadoEnvio = EstadoEnvio.ASIGNADO;
    private List<Envio> listEnvios;
    private Repartidor repartidor;

    public Envio(int idEnvio, Direccion origen, Direccion destino, Paquete paquete, EstadoEnvio estado, LocalDate fechaCreacion, EstadoEnvio estadoEnvio, Repartidor repartidor) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.paquete = paquete;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.estadoEnvio = estadoEnvio;
        this.listEnvios = new ArrayList<Envio>();
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

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoEnvio getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public List<Envio> getListEnvios() {
        return listEnvios;
    }

    public void setListEnvios(List<Envio> listEnvios) {
        this.listEnvios = listEnvios;
    }

    public void registrarEnvio(Envio envio) {
        listEnvios.add(envio);
    }

    public boolean asignarRepartidor(Envio envio, Repartidor repartidor) {
        if (envio == null || repartidor == null)
            return false;

        if (repartidor.getDisponibilidadRepartidor() == DisponibilidadRepartidor.DISPONIBLE) {
            envio.cambiarEstado(EstadoEnvio.ASIGNADO);
            envio.setRepartidor(repartidor);
            repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.EN_RUTA);
            return true;
        }
        return false;
    }

    public void cambiarEstado(Envio envio, EstadoEnvio nuevoEstado) {
        envio.cambiarEstado(nuevoEstado);
        if (nuevoEstado == EstadoEnvio.ENTREGADO && envio.getRepartidor() != null) {
            envio.getRepartidor().setDisponibilidadRepartidor(DisponibilidadRepartidor.DISPONIBLE);
            System.out.println("Envío entregado correctamente. Repartidor disponible nuevamente.");
        }
    }

    //Clase que agrega los nuevos envios
    public List<Envio> listEnvios() {
        return new ArrayList<>(listEnvios());
    }

}
