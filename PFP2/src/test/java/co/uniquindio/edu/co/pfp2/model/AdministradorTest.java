package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Clase de pruebas unitarias para la clase {@link Administrador}.
 * Verifica las operaciones principales de gestión de usuarios, repartidores
 * y asignaciones de envíos.
 */
public class AdministradorTest {

    private Administrador admin;

    @BeforeEach
    void setUp() {
        // Reiniciar instancia Singleton antes de cada prueba
        admin = Administrador.getInstance();

        // Limpiar listas antes de cada test
        admin.listarUsuarios().clear();
        admin.listarRepartidores().clear();
    }

    // ===================== PRUEBAS DE SINGLETON =====================

    @Test
    void testSingletonDevuelveLaMismaInstancia() {
        Administrador admin2 = Administrador.getInstance();
        assertSame(admin, admin2, "Debe retornar siempre la misma instancia");
    }

    // ===================== PRUEBAS DE USUARIOS =====================

    @Test
    void testAgregarUsuario() {
        Usuario usuario = new Usuario("Juan", "123", "juan@mail.com", "1234", 300000000, 1);
        boolean agregado = admin.agregarUsuario(usuario);
        assertTrue(agregado, "El usuario debería agregarse correctamente");
        assertEquals(1, admin.listarUsuarios().size());
    }

    @Test
    void testAgregarUsuarioDuplicado() {
        Usuario u1 = new Usuario("Ana", "111", "ana@mail.com", "abc", 310000000, 2);
        Usuario u2 = new Usuario("Ana", "111", "ana@mail.com", "abc", 310000000, 2);

        admin.agregarUsuario(u1);
        boolean agregado = admin.agregarUsuario(u2);

        assertFalse(agregado, "No debería agregar usuarios duplicados por ID");
    }

    @Test
    void testActualizarUsuario() {
        Usuario u1 = new Usuario("Carlos", "222", "carlos@mail.com", "pass", 312345678, 3);
        admin.agregarUsuario(u1);

        Usuario actualizado = new Usuario("Carlos A.", "222", "nuevo@mail.com", "newpass", 399999999, 3);
        boolean resultado = admin.actualizarUsuario(3, actualizado);

        assertTrue(resultado, "Debe permitir actualizar el usuario");
        assertEquals("nuevo@mail.com", admin.listarUsuarios().get(0).getCorreo());
    }

    @Test
    void testEliminarUsuario() {
        Usuario usuario = new Usuario("Laura", "333", "laura@mail.com", "pwd", 300123456, 4);
        admin.agregarUsuario(usuario);

        boolean eliminado = admin.eliminarusuario(4);
        assertTrue(eliminado, "Debe eliminar el usuario correctamente");
        assertTrue(admin.listarUsuarios().isEmpty(), "La lista debería quedar vacía");
    }

    // ===================== PRUEBAS DE REPARTIDORES =====================

    @Test
    void testAgregarRepartidor() {
        Repartidor repartidor = new Repartidor("Pedro", "555", "pedro@mail.com", "pass", 322222222, ZonaCobertura.SUR,  DisponibilidadRepartidor.DISPONIBLE);
        boolean agregado = admin.agregarRepartidor(repartidor);
        assertTrue(agregado);
        assertEquals(1, admin.listarRepartidores().size());
    }

    @Test
    void testActualizarRepartidor() {
        Repartidor rep = new Repartidor("Miguel", "444", "miguel@mail.com", "clave", 312000000, ZonaCobertura.NORTE,  DisponibilidadRepartidor.DISPONIBLE);
        admin.agregarRepartidor(rep);

        Repartidor actualizado = new Repartidor("Miguel A", "444", "miguelnuevo@mail.com", "nueva", 311111111, ZonaCobertura.CENTRO,  DisponibilidadRepartidor.EN_RUTA);
        boolean modificado = admin.actualizarRepartidor(10, actualizado);

        assertTrue(modificado, "Debe actualizar correctamente los datos del repartidor");
        assertEquals("Sur", admin.listarRepartidores().get(0).getZonaCobertura());
    }

    @Test
    void testEliminarRepartidor() {
        Repartidor rep = new Repartidor("Lina", "888", "lina@mail.com", "pass", 320999999, ZonaCobertura.MUNICIPIO_CERCANO,  DisponibilidadRepartidor.DISPONIBLE);
        admin.agregarRepartidor(rep);

        boolean eliminado = admin.eliminarRepartidor(11);
        assertTrue(eliminado, "Debe eliminar correctamente el repartidor");
        assertTrue(admin.listarRepartidores().isEmpty());
    }

    // ===================== PRUEBAS DE ASIGNACIÓN =====================

    @Test
    void testAsignarRepartidorDisponible() {
        Envio envio = new Envio(1, null, null, null, java.time.LocalDate.now(), EstadoEnvio.SOLICITADO, null);
        Repartidor rep = new Repartidor("Andrés", "999", "andres@mail.com", "pass", 300000000, ZonaCobertura.CENTRO,  DisponibilidadRepartidor.DISPONIBLE);

        boolean asignado = admin.asignarRepartidor(envio, rep);

        assertTrue(asignado, "Debe permitir asignar un repartidor disponible");
        assertEquals(DisponibilidadRepartidor.EN_RUTA, rep.getDisponibilidadRepartidor());
        assertEquals(EstadoEnvio.ASIGNADO, envio.getEstadoEnvio());
    }

    @Test
    void testAsignarRepartidorNoDisponible() {
        Envio envio = new Envio(2, null, null, null, java.time.LocalDate.now(), EstadoEnvio.SOLICITADO, null);
        Repartidor rep = new Repartidor("Tania", "777", "tania@mail.com", "clave", 301000000, ZonaCobertura.CENTRO,  DisponibilidadRepartidor.EN_RUTA);

        boolean asignado = admin.asignarRepartidor(envio, rep);

        assertFalse(asignado, "No debe asignar repartidores no disponibles");
        assertEquals(EstadoEnvio.SOLICITADO, envio.getEstadoEnvio(), "El envío debe mantener su estado original");
    }
}
