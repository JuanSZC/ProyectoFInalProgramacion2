package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase {@link Envio}.
 * Validan la creación de envíos, cambio de estado, conversión a DTO
 * y comportamiento de sus métodos básicos.
 */
public class EnvioTest {

    private Envio envio;
    private Direccion origen;
    private Direccion destino;
    private Paquete paquete;
    private Repartidor repartidor;
    private LocalDate fecha;

    @BeforeEach
    void setUp() {
        origen = new Direccion(1, "Calle 10 #15-23, Armenia", 4.5342, -75.6721,ZonaCobertura.NORTE);
        destino = new Direccion(2, "Carrera 20 #12-45, Pereira", 4.8132, -75.6984,ZonaCobertura.SUR);
        paquete = new Paquete(1, 3.5, 4);
        repartidor = new Repartidor(1111,"Carlos Ruiz", "123", "carlos@correo.com", "1234", 312345678, ZonaCobertura.CENTRO, DisponibilidadRepartidor.DISPONIBLE);
        fecha = LocalDate.of(2025, 10, 10);

        envio = new Envio(1, origen, destino, paquete, fecha, EstadoEnvio.SOLICITADO, repartidor);
    }

    // ===============================
    // TEST CONSTRUCTOR Y GETTERS
    // ===============================

    @Test
    void testConstructorInicializaCamposCorrectamente() {
        assertEquals(1, envio.getIdEnvio());
        assertEquals(origen, envio.getOrigen());
        assertEquals(destino, envio.getDestino());
        assertEquals(paquete, envio.getPaquete());
        assertEquals(EstadoEnvio.SOLICITADO, envio.getEstadoEnvio());
        assertEquals(fecha, envio.getFechaCreacion());
        assertEquals(repartidor, envio.getRepartidor());
    }

    // ===============================
    // TEST SETTERS
    // ===============================

    @Test
    void testSettersActualizanValores() {
        Direccion nuevaDireccion = new Direccion(3, "Av. Bolívar #80", 4.5312, -75.6782,ZonaCobertura.NORTE);
        envio.setOrigen(nuevaDireccion);
        envio.setEstadoEnvio(EstadoEnvio.ASIGNADO);
        envio.setIdEnvio(2);

        assertEquals(2, envio.getIdEnvio());
        assertEquals(nuevaDireccion, envio.getOrigen());
        assertEquals(EstadoEnvio.ASIGNADO, envio.getEstadoEnvio());
    }

    // ===============================
    // TEST cambiarEstado()
    // ===============================

    @Test
    void testCambiarEstadoActualizaCorrectamente() {
        envio.cambiarEstado(EstadoEnvio.EN_RUTA);
        assertEquals(EstadoEnvio.EN_RUTA, envio.getEstadoEnvio());
    }

    // ===============================
    // TEST toDTO()
    // ===============================

    @Test
    void testToDTOConvierteCorrectamente() {
        EnvioDTO dto = envio.toDTO();

        assertEquals(envio.getIdEnvio(), dto.getIdEnvio());
        assertTrue(dto.getDireccionOrigen().contains("Calle 10"));
        assertTrue(dto.getDireccionDestino().contains("Carrera 20"));
        assertEquals(envio.getRepartidor().getNombreCompleto(), dto.getNombreRepartidor());
        assertEquals(envio.getEstadoEnvio().name(), dto.getEstadoEnvio());
        assertEquals(envio.getFechaCreacion(), dto.getFechaCreacion());
        assertEquals(envio.getPaquete().getPeso(), dto.getPesoPaquete());
    }

    @Test
    void testToDTOManejaValoresNulosCorrectamente() {
        Envio envioNulo = new Envio(2, null, null, null, fecha, null, null);
        EnvioDTO dto = envioNulo.toDTO();

        assertEquals("Sin origen", dto.getDireccionOrigen());
        assertEquals("Sin destino", dto.getDireccionDestino());
        assertEquals("Sin repartidor", dto.getNombreRepartidor());
        assertEquals("No definido", dto.getEstadoEnvio());
        assertEquals(0.0, dto.getPesoPaquete());
    }

    // ===============================
    // TEST listarEnvios()
    // ===============================

    @Test
    void testListarEnviosDevuelveListaVacia() {
        List<Envio> lista = Envio.listarEnvios();
        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    // ===============================
    // TEST toString()
    // ===============================

    @Test
    void testToStringIncluyeCamposPrincipales() {
        String texto = envio.toString();
        assertTrue(texto.contains("Envio"));
        assertTrue(texto.contains("idEnvio=1"));
        assertTrue(texto.contains("paquete"));
        assertTrue(texto.contains("estadoEnvio=SOLICITADO"));
    }
}
