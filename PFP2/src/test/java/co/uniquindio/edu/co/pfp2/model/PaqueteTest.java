package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase {@link Paquete}.
 * Se verifica la correcta creación del objeto, manejo de productos
 * y comportamiento de los atributos básicos.
 */
public class PaqueteTest {

    private Paquete paquete;

    @BeforeEach
    void setUp() {
        paquete = new Paquete(150000, 4.5, 0.35);
    }

    @Test
    void testConstructorInicializaCorrectamente() {
        assertEquals(150000, paquete.getPrecio());
        assertEquals(4.5, paquete.getPeso());
        assertEquals(0.35, paquete.getDimension());
        assertNotNull(paquete.getProductos());
        assertTrue(paquete.getProductos().isEmpty());
    }

    @Test
    void testSettersYGetters() {
        paquete.setPrecio(200000);
        paquete.setPeso(5.0);
        paquete.setDimension(0.5);

        assertEquals(200000, paquete.getPrecio());
        assertEquals(5.0, paquete.getPeso());
        assertEquals(0.5, paquete.getDimension());
    }

    @Test
    void testAgregarProductos() {
        Producto producto1 = new Producto(1234, "Tablet", "Xiaomi", 234.543, 1, 0.90);
        Producto producto2 = new Producto(2345, "Mouse", "Generico", 40000, 2, 0.20);

        paquete.getProductos().add(producto1);
        paquete.getProductos().add(producto2);

        List<Producto> productos = paquete.getProductos();

        assertEquals(2, productos.size());
        assertTrue(productos.contains(producto1));
        assertTrue(productos.contains(producto2));
    }

    @Test
    void testToStringNoEsNulo() {
        String texto = paquete.toString();
        assertNotNull(texto);
        assertTrue(texto.contains("Paquete"));
    }
}
