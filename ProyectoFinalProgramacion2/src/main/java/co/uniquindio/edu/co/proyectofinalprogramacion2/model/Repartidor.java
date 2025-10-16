package co.uniquindio.edu.co.proyectofinalprogramacion2.model;

public class Repartidor extends Persona {

    private int idRepartidor;
    private DisponibilidadRepartidor disponibilidadRepartidor;
    private ZonaCobertura zonaCobertura;


    public Repartidor(String nombre, String correo, int telefono,String cedula, int idRepartido, String documento, DisponibilidadRepartidor disponibilidadRepartidor, ZonaCobertura zonaCobertura) {
        super(nombre, correo, telefono, cedula);
        this.idRepartidor = idRepartido;
        this.disponibilidadRepartidor = disponibilidadRepartidor;
        this.zonaCobertura = zonaCobertura;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public ZonaCobertura getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(ZonaCobertura zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }

    public DisponibilidadRepartidor getDisponibilidadRepartidor() {
        return disponibilidadRepartidor;
    }

    public void setDisponibilidadRepartidor(DisponibilidadRepartidor disponibilidadRepartidor) {
        this.disponibilidadRepartidor = disponibilidadRepartidor;
    }

}
