package co.uniquindio.edu.co.pfp2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase {@link Persona}.
 * Se valida la correcta inicialización, acceso y modificación
 * de los atributos de la clase base.
 */
public class PersonaTest {

    private Persona persona;

    @BeforeEach
    void setUp() {
        persona = new Persona("Juan Pérez", "12345678", "juan@mail.com", "1234", 315789456);
    }

    @Test
    void testConstructorInicializaCamposCorrectamente() {
        assertEquals("Juan Pérez", persona.getNombreCompleto());
        assertEquals("12345678", persona.getCedula());
        assertEquals("juan@mail.com", persona.getCorreo());
        assertEquals("1234", persona.getContrasenia());
        assertEquals(315789456, persona.getTelefono());
    }

    @Test
    void testSettersYGettersFuncionan() {
        persona.setNombreCompleto("Carlos Gómez");
        persona.setCedula("98765432");
        persona.setCorreo("carlos@mail.com");
        persona.setContrasenia("abcd");
        persona.setTelefono(310123456);

        assertEquals("Carlos Gómez", persona.getNombreCompleto());
        assertEquals("98765432", persona.getCedula());
        assertEquals("carlos@mail.com", persona.getCorreo());
        assertEquals("abcd", persona.getContrasenia());
        assertEquals(310123456, persona.getTelefono());
    }

    @Test
    void testToStringContieneNombreYCorreo() {
        String texto = persona.toString();
        assertTrue(texto.contains("Juan Pérez"));
        assertTrue(texto.contains("juan@mail.com"));
    }
}
