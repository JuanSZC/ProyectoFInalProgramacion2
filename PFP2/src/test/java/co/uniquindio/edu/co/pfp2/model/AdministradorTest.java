package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {

    private Administrador admin;

    @BeforeEach
    void setUp() {
        // Reinicia el singleton antes de cada test
        admin = Administrador.getInstance();

        // Limpia las listas para pruebas limpias
        admin.listarUsuarios().clear();
        admin.listarRepartidores().clear();
    }

    // =================== PRUEBAS SINGLETON ===================
    @Test
    void testSingletonDevuelveMismaInstancia() {
        Administrador otra = Administrador.getInstance();
        assertSame(admin, otra, "Debe retornar la misma instancia siempre");
    }

    // =================== PRUEBAS USUARIOS ===================
    @Test
    void testAgregarUsuario() {
        Usuario u = new Usuario("Juan", "123", "juan@mail.com", "pass", 3000000, 1);
        boolean agregado = admin.agregarUsuario(u);
        assertTrue(agregado, "Usuario debe agregarse");
        assertEquals(1, admin.listarUsuarios().size());
    }

    @Test
    void testAgregarUsuarioDuplicado() {
        Usuario u1 = new Usuario("Ana", "111", "ana@mail.com", "abc", 1000, 2);
        Usuario u2 = new Usuario("Ana", "111", "ana@mail.com", "abc", 1000, 2);
        admin.agregarUsuario(u1);
        boolean agregado = admin.agregarUsuario(u2);
        assertFalse(agregado, "No debe agregar usuario duplicado");
    }

    @Test
    void testActualizarUsuario() {
        Usuario u = new Usuario("Carlos", "222", "carlos@mail.com", "pwd", 2000, 3);
        admin.agregarUsuario(u);

        Usuario actualizado = new Usuario("Carlos A.", "222", "nuevo@mail.com", "nueva", 3000, 3);
        boolean res = admin.actualizarUsuario(3, actualizado);

        assertTrue(res);
        assertEquals("nuevo@mail.com", admin.listarUsuarios().get(0).getCorreo());
    }

    @Test
    void testEliminarUsuario() {
        Usuario u = new Usuario("Laura", "333", "laura@mail.com", "pass", 1500, 4);
        admin.agregarUsuario(u);
        boolean eliminado = admin.eliminarusuario(4);
        assertTrue(eliminado);
        assertTrue(admin.listarUsuarios().isEmpty());
    }

    // =================== PRUEBAS REPARTIDORES ===================
    @Test
    void testAgregarRepartidor() {
        Repartidor r = new Repartidor(10, "Pedro", "555", "pedro@mail.com", "pass", 3000, ZonaCobertura.NORTE, DisponibilidadRepartidor.DISPONIBLE);
        boolean agregado = admin.agregarRepartidor(r);
        assertTrue(agregado);
        assertEquals(1, admin.listarRepartidores().size());
    }

    @Test
    void testActualizarRepartidor() {
        Repartidor r = new Repartidor(11, "Miguel", "444", "miguel@mail.com", "clave", 2500, ZonaCobertura.SUR, DisponibilidadRepartidor.DISPONIBLE);
        admin.agregarRepartidor(r);

        Repartidor actualizado = new Repartidor(11, "Miguel A", "444", "nuevo@mail.com", "newpass", 3000, ZonaCobertura.CENTRO, DisponibilidadRepartidor.EN_RUTA);
        boolean modificado = admin.actualizarRepartidor(11, actualizado);

        assertTrue(modificado);
        assertEquals(ZonaCobertura.CENTRO, admin.listarRepartidores().get(0).getZonaCobertura());
    }

    @Test
    void testEliminarRepartidor() {
        Repartidor r = new Repartidor(12, "Lina", "888", "lina@mail.com", "pass", 2000, ZonaCobertura.MUNICIPIO_CERCANO, DisponibilidadRepartidor.DISPONIBLE);
        admin.agregarRepartidor(r);
        boolean eliminado = admin.eliminarRepartidor(12);
        assertTrue(eliminado);
        assertTrue(admin.listarRepartidores().isEmpty());
    }

    // =================== PRUEBAS ASIGNACIÓN ===================
    @Test
    void testAsignarRepartidorDisponible() {
        Envio envio = new Envio(1, null, null, null, LocalDate.now(), EstadoEnvio.SOLICITADO, null);
        Repartidor r = new Repartidor(13, "Andres", "999", "andres@mail.com", "pass", 1000, ZonaCobertura.CENTRO, DisponibilidadRepartidor.DISPONIBLE);

        boolean asignado = admin.asignarRepartidor(envio, r);
        assertTrue(asignado);
        assertEquals(DisponibilidadRepartidor.EN_RUTA, r.getDisponibilidadRepartidor());
        assertEquals(EstadoEnvio.ASIGNADO, envio.getEstadoEnvio());
    }

    @Test
    void testAsignarRepartidorNoDisponible() {
        Envio envio = new Envio(2, null, null, null, LocalDate.now(), EstadoEnvio.SOLICITADO, null);
        Repartidor r = new Repartidor(14, "Tania", "777", "tania@mail.com", "clave", 2000, ZonaCobertura.SUR, DisponibilidadRepartidor.EN_RUTA);

        boolean asignado = admin.asignarRepartidor(envio, r);
        assertFalse(asignado);
        assertEquals(EstadoEnvio.SOLICITADO, envio.getEstadoEnvio());
    }
}
