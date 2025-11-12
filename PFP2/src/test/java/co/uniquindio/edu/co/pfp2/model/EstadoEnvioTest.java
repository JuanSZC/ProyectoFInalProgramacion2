package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la enumeración {@link EstadoEnvio}.
 * Verifica la existencia, orden y uso correcto de los valores del estado de un envío.
 */
public class EstadoEnvioTest {

    @Test
    void testCantidadDeEstados() {
        EstadoEnvio[] estados = EstadoEnvio.values();
        assertEquals(5, estados.length, "Debe haber 5 estados definidos");
    }

    @Test
    void testEstadosDefinidosCorrectamente() {
        assertNotNull(EstadoEnvio.SOLICITADO);
        assertNotNull(EstadoEnvio.ASIGNADO);
        assertNotNull(EstadoEnvio.EN_RUTA);
        assertNotNull(EstadoEnvio.ENTREGADO);
        assertNotNull(EstadoEnvio.CANCELADO);
    }

    @Test
    void testValueOfRetornaEstadoCorrecto() {
        assertEquals(EstadoEnvio.EN_RUTA, EstadoEnvio.valueOf("EN_RUTA"));
        assertEquals(EstadoEnvio.CANCELADO, EstadoEnvio.valueOf("CANCELADO"));
    }

    @Test
    void testValueOfLanzaExcepcionSiNoExiste() {
        assertThrows(IllegalArgumentException.class, () -> {
            EstadoEnvio.valueOf("NO_EXISTE");
        });
    }

    @Test
    void testOrdenEnumEsConsistente() {
        EstadoEnvio[] estados = EstadoEnvio.values();
        assertEquals(EstadoEnvio.SOLICITADO, estados[0]);
        assertEquals(EstadoEnvio.ASIGNADO, estados[1]);
        assertEquals(EstadoEnvio.EN_RUTA, estados[2]);
        assertEquals(EstadoEnvio.ENTREGADO, estados[3]);
        assertEquals(EstadoEnvio.CANCELADO, estados[4]);
    }
}
