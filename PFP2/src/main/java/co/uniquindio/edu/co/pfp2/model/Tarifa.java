package co.uniquindio.edu.co.pfp2.model;

public class Tarifa {

    private Paquete paquete;
    private PrioridadPedido prioridadPedido;

    public Tarifa(Paquete paquete, PrioridadPedido prioridadPedido) {
        this.paquete = paquete;
        this.prioridadPedido = prioridadPedido;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public PrioridadPedido getPrioridadPedido() {
        return prioridadPedido;
    }

    public void setPrioridadPedido(PrioridadPedido prioridadPedido) {
        this.prioridadPedido = prioridadPedido;
    }
}
