package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * Clase de prueba unitaria para {@link Empresa}.
 * Verifica el correcto funcionamiento del patrón Singleton,
 * la asignación de atributos, y la manipulación de listas internas.
 */
public class EmpresaTest {

    private Empresa empresa;

    @BeforeEach
    void setUp() {
        // Reinicia la instancia del Singleton antes de cada prueba (usando reflexión)
        try {
            var field = Empresa.class.getDeclaredField("instance");
            field.setAccessible(true);
            field.set(null, null);
        } catch (Exception e) {
            fail("No se pudo reiniciar la instancia singleton de Empresa");
        }

        empresa = Empresa.getInstance();
    }

    // ==========================
    //   TEST SINGLETON
    // ==========================

    @Test
    void testSingletonDevuelveLaMismaInstancia() {
        Empresa otraInstancia = Empresa.getInstance();
        assertSame(empresa, otraInstancia, "getInstance() debe retornar siempre la misma instancia");
    }

    @Test
    void testSingletonNoEsNull() {
        assertNotNull(empresa, "La instancia de Empresa no debe ser nula");
    }

    // ==========================
    //   TEST ATRIBUTOS BÁSICOS
    // ==========================

    @Test
    void testSettersYGettersDeNombre() {
        empresa.setNombre("Envio Rápido S.A.");
        assertEquals("Envio Rápido S.A.", empresa.getNombre(), "El nombre de la empresa no se asignó correctamente");
    }

    @Test
    void testSettersYGettersDeDireccion() {
        empresa.setDireccion("Carrera 10 #25-34, Armenia");
        assertEquals("Carrera 10 #25-34, Armenia", empresa.getDireccion(), "La dirección no se asignó correctamente");
    }

    @Test
    void testSettersYGettersDeNIT() {
        empresa.setNIT(123456789);
        assertEquals(123456789, empresa.getNIT(), "El NIT no se asignó correctamente");
    }

    // ==========================
    //   TEST DE LISTAS INTERNAS
    // ==========================

    @Test
    void testListasInicianVacias() {
        assertTrue(empresa.getListPedidos().isEmpty(), "La lista de pedidos debe iniciar vacía");
        assertTrue(empresa.getListProductos().isEmpty(), "La lista de productos debe iniciar vacía");
        assertTrue(empresa.getListUsuarios().isEmpty(), "La lista de usuarios debe iniciar vacía");
        assertTrue(empresa.getListRepartidors().isEmpty(), "La lista de repartidores debe iniciar vacía");
    }

    @Test
    void testAgregarElementosAListas() {
        // Crear objetos simulados
        Usuario usuario = new Usuario("Juan Pérez", "123", "juan@correo.com", "1234", 312000000, 1);
        Repartidor repartidor = new Repartidor("Carlos Ruiz", "987", "carlos@correo.com", "abcd", 312123123, ZonaCobertura.CENTRO, DisponibilidadRepartidor.DISPONIBLE);
        Envio envio = new Envio(1, null, null, null, null, EstadoEnvio.ASIGNADO, repartidor);

        // Agregar objetos a las listas
        empresa.getListUsuarios().add(usuario);
        empresa.getListRepartidors().add(repartidor);
        empresa.getListPedidos().add(envio);

        // Verificar tamaños
        assertEquals(1, empresa.getListUsuarios().size(), "La lista de usuarios no se actualizó correctamente");
        assertEquals(1, empresa.getListRepartidors().size(), "La lista de repartidores no se actualizó correctamente");
        assertEquals(1, empresa.getListPedidos().size(), "La lista de pedidos no se actualizó correctamente");
    }

    @Test
    void testSettersYGettersDeListas() {
        var usuarios = new ArrayList<Usuario>();
        var productos = new ArrayList<Producto>();
        var repartidores = new ArrayList<Repartidor>();

        empresa.setListUsuarios(usuarios);
        empresa.setListProductos(productos);
        empresa.setListRepartidors(repartidores);

        assertSame(usuarios, empresa.getListUsuarios(), "La lista de usuarios no se asignó correctamente");
        assertSame(productos, empresa.getListProductos(), "La lista de productos no se asignó correctamente");
        assertSame(repartidores, empresa.getListRepartidors(), "La lista de repartidores no se asignó correctamente");
    }
}
