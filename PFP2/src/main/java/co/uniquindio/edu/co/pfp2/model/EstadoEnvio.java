package co.uniquindio.edu.co.pfp2.model;

/**
 * Enumeración que representa los diferentes estados que puede tener un envío
 * dentro del sistema de gestión logística.
 * Cada estado refleja una etapa específica del ciclo de vida de un envío,
 * desde su creación hasta su entrega o posible cancelación.
 * SOLICITADO: El usuario ha creado la solicitud pero aún no ha sido procesada.
 * ASIGNADO: El envío ya fue asignado a un repartidor.
 * EN_RUTA: El repartidor está actualmente transportando el envío.
 * ENTREGADO</b>: El envío llegó correctamente a su destino.
 * CANCELADO: La solicitud fue cancelada antes de completarse.
 *
 * Este enum permite un control claro y seguro del flujo del envío,
   facilitando validaciones, rastreo y cambios de estado.
 */
public enum EstadoEnvio {

    SOLICITADO,
    ASIGNADO,
    EN_RUTA,
    ENTREGADO,
    CANCELADO;
}
