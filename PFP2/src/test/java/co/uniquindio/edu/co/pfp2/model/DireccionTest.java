package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase {@link Direccion}.
 * Verifica el correcto funcionamiento de su constructor, getters y setters.
 */
public class DireccionTest {

    private Direccion direccion;

    @BeforeEach
    void setUp() {
        // Inicializa una dirección antes de cada prueba
        direccion = new Direccion(1, "Calle 10 #15-23, Armenia", 4.5342, -75.6721);
    }

    @Test
    void testConstructorInicializaCorrectamente() {
        assertEquals(1, direccion.getIdDireccion());
        assertEquals("Calle 10 #15-23, Armenia", direccion.getDescripcion());
        assertEquals(4.5342, direccion.getLatitud());
        assertEquals(-75.6721, direccion.getLongitud());
    }

    @Test
    void testSettersModificanValores() {
        direccion.setIdDireccion(2);
        direccion.setDescripcion("Carrera 14 #8-10, Bogotá");
        direccion.setLatitud(4.7110);
        direccion.setLongitud(-74.0721);

        assertEquals(2, direccion.getIdDireccion());
        assertEquals("Carrera 14 #8-10, Bogotá", direccion.getDescripcion());
        assertEquals(4.7110, direccion.getLatitud());
        assertEquals(-74.0721, direccion.getLongitud());
    }

    @Test
    void testCambiarSoloDescripcion() {
        direccion.setDescripcion("Nueva dirección de prueba");
        assertEquals("Nueva dirección de prueba", direccion.getDescripcion());
    }

    @Test
    void testCambiarCoordenadasIndependientemente() {
        direccion.setLatitud(6.2518);
        direccion.setLongitud(-75.5636);

        assertEquals(6.2518, direccion.getLatitud(), 0.0001);
        assertEquals(-75.5636, direccion.getLongitud(), 0.0001);
    }

    @Test
    void testValoresLimiteCoordenadas() {
        direccion.setLatitud(90.0);
        direccion.setLongitud(180.0);

        assertEquals(90.0, direccion.getLatitud());
        assertEquals(180.0, direccion.getLongitud());
    }

    @Test
    void testValoresNegativosCoordenadas() {
        direccion.setLatitud(-90.0);
        direccion.setLongitud(-180.0);

        assertEquals(-90.0, direccion.getLatitud());
        assertEquals(-180.0, direccion.getLongitud());
    }
}
