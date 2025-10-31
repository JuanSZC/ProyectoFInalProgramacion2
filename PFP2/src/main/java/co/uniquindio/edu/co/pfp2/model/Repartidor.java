package co.uniquindio.edu.co.pfp2.model;

public class Repartidor  extends Persona {

    private int idRepartidor;
    private ZonaCobertura zonaCobertura;
    private DisponibilidadRepartidor disponibilidadRepartidor = DisponibilidadRepartidor.DISPONIBLE;

    public Repartidor(String nombreCompleto, String cedula, String correo, String contrasenia, int telefono,ZonaCobertura zonaCobertura, DisponibilidadRepartidor disponibilidadRepartidor) {
        super(nombreCompleto, cedula, correo, contrasenia, telefono);
        this.idRepartidor = idRepartidor;
        this.zonaCobertura = zonaCobertura;
        this.disponibilidadRepartidor = disponibilidadRepartidor;

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
    public boolean isDisponible(){
        if(disponibilidadRepartidor == null){
            return false;
        }
        return true;
    }
}
