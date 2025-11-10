package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para {@link Usuario}.
 * Se validan las operaciones CRUD sobre direcciones, autenticación de usuario
 * y gestión del flujo de solicitudes de envío.
 */
public class UsuarioTest {

    private Usuario usuario;
    private Direccion direccion1;
    private Direccion direccion2;
    private Paquete paquete;
    private Envio envio1;
    private Envio envio2;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Juan Pérez", "123456", "juan@correo.com", "clave123", 314222333, 1);

        direccion1 = new Direccion(1, "Calle 10 #15-23", 4.5342, -75.6721);
        direccion2 = new Direccion(2, "Carrera 8 #20-45", 4.5400, -75.6750);

        paquete = new Paquete(15000, 2.5, 0.4);

        envio1 = new Envio(101, direccion1, direccion2, paquete,
                LocalDate.now(), EstadoEnvio.SOLICITADO, null);

        envio2 = new Envio(102, direccion2, direccion1, paquete,
                LocalDate.now(), EstadoEnvio.ASIGNADO, null);
    }

    // ===================== PRUEBAS DE USUARIO ===================== //

    @Test
    void testIniciarSesion_Exitoso() {
        usuario.getListUsuarios().add(usuario);

        Usuario result = usuario.iniciarSesion("juan@correo.com", "clave123");
        assertNotNull(result);
        assertEquals("Juan Pérez", result.getNombreCompleto());
    }

    @Test
    void testIniciarSesion_Fallido() {
        usuario.getListUsuarios().add(usuario);

        Usuario result = usuario.iniciarSesion("otro@correo.com", "clave123");
        assertNull(result);
    }

    // ===================== DIRECCIONES ===================== //

    @Test
    void testAgregarDireccion_Exitoso() {
        boolean agregado = usuario.agregarDireccion(direccion1);
        assertTrue(agregado);
        assertEquals(1, usuario.getListDireccionesUsuario().size());
    }

    @Test
    void testAgregarDireccion_Repetida() {
        usuario.agregarDireccion(direccion1);
        boolean agregado = usuario.agregarDireccion(direccion1);
        assertFalse(agregado);
    }

    @Test
    void testActualizarDireccion_Exitoso() {
        usuario.agregarDireccion(direccion1);
        Direccion nueva = new Direccion(1, "Calle Nueva #5-50", 4.53, -75.67);

        boolean actualizado = usuario.actualizarDireccion(1, nueva);
        assertTrue(actualizado);
        assertEquals("Calle Nueva #5-50", usuario.getListDireccionesUsuario().get(0).getDescripcion());
    }

    @Test
    void testEliminarDireccion_Exitoso() {
        usuario.agregarDireccion(direccion1);
        boolean eliminado = usuario.eliminarDireccion(1);
        assertTrue(eliminado);
        assertTrue(usuario.getListDireccionesUsuario().isEmpty());
    }

    @Test
    void testBuscarDireccionPorID() {
        usuario.agregarDireccion(direccion1);
        Direccion encontrada = usuario.buscarDireccionPorID(1);
        assertNotNull(encontrada);
        assertEquals("Calle 10 #15-23", encontrada.getDescripcion());
    }

    // ===================== ENVÍOS ===================== //

    @Test
    void testCrearSolicitudEnvio_Exitoso() {
        boolean creado = usuario.crearSolicitudEnvio(envio1);
        assertTrue(creado);
        assertEquals(1, usuario.getListEnviosUsuario().size());
    }

    @Test
    void testCrearSolicitudEnvio_Repetido() {
        usuario.crearSolicitudEnvio(envio1);
        boolean creado = usuario.crearSolicitudEnvio(envio1);
        assertFalse(creado);
    }

    @Test
    void testModificarSolicitudEnvio_Exitoso() {
        usuario.crearSolicitudEnvio(envio1);
        Envio nuevo = new Envio(101, direccion2, direccion1, paquete,
                LocalDate.now().plusDays(1), EstadoEnvio.SOLICITADO, null);

        boolean modificado = usuario.modificarSolicitudEnvio(101, nuevo);
        assertTrue(modificado);
        assertEquals(direccion2, usuario.getListEnviosUsuario().get(0).getOrigen());
    }

    @Test
    void testModificarSolicitudEnvio_FallidoPorEstado() {
        usuario.crearSolicitudEnvio(envio2); // estado ASIGNADO
        boolean modificado = usuario.modificarSolicitudEnvio(102, envio1);
        assertFalse(modificado);
    }

    @Test
    void testCancelarSolicitudEnvio_Exitoso() {
        usuario.crearSolicitudEnvio(envio1);
        boolean cancelado = usuario.cancelarSolicitudEnvio(101);
        assertTrue(cancelado);
        assertEquals(EstadoEnvio.CANCELADO, usuario.getListEnviosUsuario().get(0).getEstadoEnvio());
    }

    @Test
    void testCancelarSolicitudEnvio_Fallido() {
        usuario.crearSolicitudEnvio(envio2); // estado ASIGNADO
        boolean cancelado = usuario.cancelarSolicitudEnvio(102);
        assertFalse(cancelado);
    }

    @Test
    void testConsultarHistorialEnvio() {
        usuario.crearSolicitudEnvio(envio1);
        List<Envio> historial = usuario.consultarHistorialEnvio();
        assertEquals(1, historial.size());
    }

    @Test
    void testRastrearEstadoEnvio_Encontrado() {
        usuario.crearSolicitudEnvio(envio1);
        EstadoEnvio estado = usuario.rastrearEstadoEnvio(101);
        assertEquals(EstadoEnvio.SOLICITADO, estado);
    }

    @Test
    void testRastrearEstadoEnvio_NoEncontrado() {
        EstadoEnvio estado = usuario.rastrearEstadoEnvio(999);
        assertNull(estado);
    }

    // ===================== REPORTES ===================== //

    @Test
    void testGenerarReportePDF() {
        assertDoesNotThrow(() -> usuario.generarReporte("pdf"));
    }

    @Test
    void testGenerarReporteTipoNoSoportado() {
        assertDoesNotThrow(() -> usuario.generarReporte("xml"));
    }
}
