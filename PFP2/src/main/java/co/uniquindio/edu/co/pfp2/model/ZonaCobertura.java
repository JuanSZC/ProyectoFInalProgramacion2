package co.uniquindio.edu.co.pfp2.model;

/**
 * Enumeración que representa las distintas zonas de cobertura
 * en las que un {@link Repartidor} puede operar dentro del sistema.
 * Esta clasificación permite asignar envíos de manera adecuada
 * según la ubicación geográfica y optimizar la distribución.
 * Zonas disponibles:
 *     NORTE: Cobertura para la zona norte del municipio.
 *     CENTRO: Cobertura en el área central o urbana principal.
 *     SUR: Cobertura para la zona sur del municipio.
 *     MUNICIPIO_CERCANO</b>: Cobertura para municipios aledaños.
 * Ejemplo de uso:
   {@code
 * Repartidor r = new Repartidor(...);
 * r.setZonaCobertura(ZonaCobertura.CENTRO);
 */
public enum ZonaCobertura {

    /** Zona norte del municipio */
    NORTE,

    /** Zona central o área urbana principal */
    CENTRO,

    /** Zona sur del municipio */
    SUR,

    /** Municipios cercanos o áreas externas adicionales */
    MUNICIPIO_CERCANO;
}