package co.uniquindio.edu.co.pfp2.model;

import java.time.LocalDate;

public class ProcesadorPago {

    private IPago pago;
    private LocalDate fecha;

    public ProcesadorPago(IPago pago) {
        this.pago = pago;
        this.fecha = LocalDate.now();
    }

    public String procesarPago() {
        if (this.pago == null) return "Transaccion Rechazada";
        return "Pago realizado con éxito";
    }

    public IPago getPago() {
        return pago;
    }

    public void setPago(IPago pago) {
        this.pago = pago;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
