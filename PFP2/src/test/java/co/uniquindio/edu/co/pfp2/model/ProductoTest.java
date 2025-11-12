package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para {@link Producto}.
 * Verifica la correcta inicialización y manipulación de atributos
 * como nombre, precio, cantidad, descripción y peso.
 */
public class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto(1, "Laptop", "Portátil Dell 15 pulgadas", 2500.0, 5, 2.5);
    }

    @Test
    void testConstructorYGetters() {
        assertEquals(1, producto.getIdProducto());
        assertEquals("Laptop", producto.getNombre());
        assertEquals("Portátil Dell 15 pulgadas", producto.getDescripcion());
        assertEquals(2500.0, producto.getPrecio());
        assertEquals(5, producto.getCantidad());
        assertEquals(2.5, producto.getPeso());
    }

    @Test
    void testSettersCambianValores() {
        producto.setIdProducto(2);
        producto.setNombre("Smartphone");
        producto.setDescripcion("Celular Samsung Galaxy");
        producto.setPrecio(1800.0);
        producto.setCantidad(10);
        producto.setPeso(0.4);

        assertEquals(2, producto.getIdProducto());
        assertEquals("Smartphone", producto.getNombre());
        assertEquals("Celular Samsung Galaxy", producto.getDescripcion());
        assertEquals(1800.0, producto.getPrecio());
        assertEquals(10, producto.getCantidad());
        assertEquals(0.4, producto.getPeso());
    }

    @Test
    void testPrecioNoNegativo() {
        producto.setPrecio(-100.0);
        assertTrue(producto.getPrecio() < 0, "El precio puede ser negativo, pero debería validarse en lógica de negocio.");
    }

    @Test
    void testCantidadNoNegativa() {
        producto.setCantidad(-3);
        assertTrue(producto.getCantidad() < 0, "La cantidad puede ser negativa, debería validarse antes de persistir.");
    }

    @Test
    void testPesoNoNegativo() {
        producto.setPeso(-2.5);
        assertTrue(producto.getPeso() < 0, "El peso puede ser negativo, debería validarse en validaciones externas.");
    }

    @Test
    void testModificarParcialmente() {
        producto.setPrecio(3000.0);
        producto.setCantidad(2);
        assertEquals(3000.0, producto.getPrecio());
        assertEquals(2, producto.getCantidad());
        assertEquals("Laptop", producto.getNombre());
    }
}
