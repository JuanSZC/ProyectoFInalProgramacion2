package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase {@link EnvioDTO}.
 * Validan la correcta asignación y recuperación de valores
 * mediante los getters, setters y el método toString().
 */
public class EnvioDTOTest {

    private EnvioDTO envioDTO;
    private LocalDate fecha;

    @BeforeEach
    void setUp() {
        fecha = LocalDate.of(2025, 10, 10);
        envioDTO = new EnvioDTO(
                1,
                "Cra 15 #20-30",
                "Cl 8 #12-40",
                "Carlos Ruiz",
                "EN_RUTA",
                fecha,
                3.5
        );
    }

    // ===============================
    // TEST CONSTRUCTOR Y GETTERS
    // ===============================

    @Test
    void testConstructorInicializaCamposCorrectamente() {
        assertEquals(1, envioDTO.getIdEnvio());
        assertEquals("Cra 15 #20-30", envioDTO.getDireccionOrigen());
        assertEquals("Cl 8 #12-40", envioDTO.getDireccionDestino());
        assertEquals("Carlos Ruiz", envioDTO.getNombreRepartidor());
        assertEquals("EN_RUTA", envioDTO.getEstadoEnvio());
        assertEquals(fecha, envioDTO.getFechaCreacion());
        assertEquals(3.5, envioDTO.getPesoPaquete());
    }

    // ===============================
    // TEST SETTERS
    // ===============================

    @Test
    void testSettersActualizanValores() {
        envioDTO.setIdEnvio(2);
        envioDTO.setDireccionOrigen("Calle 9 #45-12");
        envioDTO.setDireccionDestino("Av. Bolívar 100");
        envioDTO.setNombreRepartidor("María Gómez");
        envioDTO.setEstadoEnvio("ENTREGADO");
        envioDTO.setFechaCreacion(LocalDate.of(2025, 12, 25));
        envioDTO.setPesoPaquete(7.8);

        assertEquals(2, envioDTO.getIdEnvio());
        assertEquals("Calle 9 #45-12", envioDTO.getDireccionOrigen());
        assertEquals("Av. Bolívar 100", envioDTO.getDireccionDestino());
        assertEquals("María Gómez", envioDTO.getNombreRepartidor());
        assertEquals("ENTREGADO", envioDTO.getEstadoEnvio());
        assertEquals(LocalDate.of(2025, 12, 25), envioDTO.getFechaCreacion());
        assertEquals(7.8, envioDTO.getPesoPaquete());
    }

    // ===============================
    // TEST toString()
    // ===============================

    @Test
    void testToStringIncluyeCamposPrincipales() {
        String result = envioDTO.toString();
        assertTrue(result.contains("EnvioDTO"));
        assertTrue(result.contains("idEnvio=1"));
        assertTrue(result.contains("Cra 15 #20-30"));
        assertTrue(result.contains("Cl 8 #12-40"));
        assertTrue(result.contains("Carlos Ruiz"));
        assertTrue(result.contains("EN_RUTA"));
    }

    // ===============================
    // TEST CONSTRUCTOR VACÍO
    // ===============================

    @Test
    void testConstructorVacioInicializaConValoresPorDefecto() {
        EnvioDTO dtoVacio = new EnvioDTO();
        assertEquals(0, dtoVacio.getIdEnvio());
        assertNull(dtoVacio.getDireccionOrigen());
        assertNull(dtoVacio.getDireccionDestino());
        assertNull(dtoVacio.getNombreRepartidor());
        assertNull(dtoVacio.getEstadoEnvio());
        assertNull(dtoVacio.getFechaCreacion());
        assertEquals(0.0, dtoVacio.getPesoPaquete());
    }
}
