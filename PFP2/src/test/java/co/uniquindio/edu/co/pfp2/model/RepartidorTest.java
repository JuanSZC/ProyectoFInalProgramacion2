package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para {@link Repartidor}.
 * Verifica la correcta inicialización de atributos heredados de {@link Persona},
 * la manipulación de zona de cobertura y disponibilidad del repartidor.
 */
public class RepartidorTest {

    private Repartidor repartidor;

    @BeforeEach
    void setUp() {
        repartidor = new Repartidor(
                1111,
                "Carlos Pérez",
                "123456789",
                "carlos@correo.com",
                "clave123",
                312456789,
                ZonaCobertura.NORTE,
                DisponibilidadRepartidor.DISPONIBLE
        );
        repartidor.setIdRepartidor(10);
    }

    @Test
    void testConstructorYGetters() {
        assertEquals("Carlos Pérez", repartidor.getNombreCompleto());
        assertEquals("123456789", repartidor.getCedula());
        assertEquals("carlos@correo.com", repartidor.getCorreo());
        assertEquals("clave123", repartidor.getContrasenia());
        assertEquals(312456789, repartidor.getTelefono());
        assertEquals(10, repartidor.getIdRepartidor());
        assertEquals(ZonaCobertura.NORTE, repartidor.getZonaCobertura());
        assertEquals(DisponibilidadRepartidor.DISPONIBLE, repartidor.getDisponibilidadRepartidor());
    }

    @Test
    void testSettersCambianValores() {
        repartidor.setNombreCompleto("Juan López");
        repartidor.setCedula("987654321");
        repartidor.setCorreo("juan@correo.com");
        repartidor.setContrasenia("nuevaClave");
        repartidor.setTelefono(310987654);
        repartidor.setIdRepartidor(20);
        repartidor.setZonaCobertura(ZonaCobertura.SUR);
        repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.EN_RUTA);

        assertEquals("Juan López", repartidor.getNombreCompleto());
        assertEquals("987654321", repartidor.getCedula());
        assertEquals("juan@correo.com", repartidor.getCorreo());
        assertEquals("nuevaClave", repartidor.getContrasenia());
        assertEquals(310987654, repartidor.getTelefono());
        assertEquals(20, repartidor.getIdRepartidor());
        assertEquals(ZonaCobertura.SUR, repartidor.getZonaCobertura());
        assertEquals(DisponibilidadRepartidor.EN_RUTA, repartidor.getDisponibilidadRepartidor());
    }

    @Test
    void testIsDisponible_ConDisponibilidad() {
        repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.DISPONIBLE);
        assertTrue(repartidor.isDisponible(), "El repartidor debería estar disponible.");
    }

    @Test
    void testIsDisponible_SinDisponibilidad() {
        repartidor.setDisponibilidadRepartidor(null);
        assertFalse(repartidor.isDisponible(), "El repartidor no debería estar disponible si el estado es null.");
    }

    @Test
    void testCambioDeDisponibilidad() {
        repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.EN_RUTA);
        assertEquals(DisponibilidadRepartidor.EN_RUTA, repartidor.getDisponibilidadRepartidor());

        repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.DISPONIBLE);
        assertEquals(DisponibilidadRepartidor.DISPONIBLE, repartidor.getDisponibilidadRepartidor());
    }
}
