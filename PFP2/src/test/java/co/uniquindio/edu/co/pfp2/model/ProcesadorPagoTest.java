package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase {@link ProcesadorPago}.
 * Valida el comportamiento del procesamiento de pagos y la correcta
 * inicialización de sus atributos.
 */
public class ProcesadorPagoTest {

    private ProcesadorPago procesadorConPago;
    private ProcesadorPago procesadorSinPago;


    @BeforeEach
    void setUp() {
        procesadorConPago = new ProcesadorPago(new PagoEfectivo());
        procesadorSinPago = new ProcesadorPago(null);
    }

    @Test
    void testConstructorInicializaFechaYMetodoDePago() {
        assertNotNull(procesadorConPago.getFecha());
        assertTrue(procesadorConPago.getFecha().isEqual(LocalDate.now()));
        assertNotNull(procesadorConPago.getPago());
    }

    @Test
    void testProcesarPagoExitoso() {
        String resultado = procesadorConPago.procesarPago();
        assertEquals("Pago realizado con éxito", resultado);
    }

    @Test
    void testProcesarPagoFallidoCuandoNoHayMetodoDePago() {
        String resultado = procesadorSinPago.procesarPago();
        assertEquals("Transacción rechazada", resultado);
    }

    @Test
    void testSettersYGetters() {
        IPago nuevoPago = new PagoEfectivo();
        procesadorConPago.setPago(nuevoPago);
        procesadorConPago.setFecha(LocalDate.of(2025, 1, 1));

        assertEquals(nuevoPago, procesadorConPago.getPago());
        assertEquals(LocalDate.of(2025, 1, 1), procesadorConPago.getFecha());
    }

    @Test
    void testToStringContieneNombreDeClaseDePago() {
        String texto = procesadorConPago.toString();
        assertTrue(texto.contains("PagoMock"));
        assertTrue(texto.contains("fecha"));
    }
}
