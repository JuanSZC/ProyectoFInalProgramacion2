package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la enumeración {@link DisponibilidadRepartidor}.
 * Verifica que los valores de la enumeración existan, sean correctos
 * y se comporten como se espera en el sistema.
 */
public class DisponibilidadRepartidorTest {

    @Test
    void testCantidadDeValoresEsperada() {
        DisponibilidadRepartidor[] valores = DisponibilidadRepartidor.values();
        assertEquals(2, valores.length, "Debe haber exactamente dos estados de disponibilidad");
    }

    @Test
    void testValoresDefinidosCorrectamente() {
        assertNotNull(DisponibilidadRepartidor.DISPONIBLE);
        assertNotNull(DisponibilidadRepartidor.EN_RUTA);
    }

    @Test
    void testOrdenDeDeclaracion() {
        DisponibilidadRepartidor[] valores = DisponibilidadRepartidor.values();
        assertEquals(DisponibilidadRepartidor.DISPONIBLE, valores[0]);
        assertEquals(DisponibilidadRepartidor.EN_RUTA, valores[1]);
    }

    @Test
    void testValueOfDevuelveEnumCorrecto() {
        assertEquals(DisponibilidadRepartidor.DISPONIBLE, DisponibilidadRepartidor.valueOf("DISPONIBLE"));
        assertEquals(DisponibilidadRepartidor.EN_RUTA, DisponibilidadRepartidor.valueOf("EN_RUTA"));
    }

    @Test
    void testValueOfLanzaExcepcionConNombreInvalido() {
        assertThrows(IllegalArgumentException.class, () -> DisponibilidadRepartidor.valueOf("OCUPADO"));
    }

    @Test
    void testNameDevuelveNombreCorrecto() {
        assertEquals("DISPONIBLE", DisponibilidadRepartidor.DISPONIBLE.name());
        assertEquals("EN_RUTA", DisponibilidadRepartidor.EN_RUTA.name());
    }
}
