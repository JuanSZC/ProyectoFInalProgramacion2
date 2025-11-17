package reportesGenerador;

import co.uniquindio.edu.co.pfp2.model.*;
import org.openpdf.text.*;
import org.openpdf.text.pdf.*;
import org.openpdf.text.pdf.draw.LineSeparator;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PdfReporteEnvioRepartidor {

    private final Repartidor repartidor;
    private final List<Envio> enviosAsignados;

    public PdfReporteEnvioRepartidor(Repartidor repartidor, List<Envio> enviosAsignados) {
        this.repartidor = repartidor;
        this.enviosAsignados = enviosAsignados;
    }

    public void generarPdf(String rutaArchivo) throws IOException {
        try {
            Document document = new Document(PageSize.A4, 50, 50, 60, 50);
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
            document.open();

            // ===============================
            // ESTILOS
            // ===============================
            Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(40, 40, 40));
            Font seccion = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, new Color(60, 60, 60));
            Font texto = FontFactory.getFont(FontFactory.HELVETICA, 11, new Color(80, 80, 80));

            LineSeparator separator = new LineSeparator();
            separator.setLineWidth(0.5f);
            separator.setPercentage(100);
            separator.setLineColor(new Color(210, 210, 210));

            // ===============================
            // ENCABEZADO
            // ===============================
            document.add(new Paragraph("Reporte de Repartidor", titulo));
            document.add(new Paragraph("Fecha: " + java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), texto));
            document.add(Chunk.NEWLINE);

            // Corregido: añadir el separador correctamente
            document.add(new Chunk(separator));
            document.add(Chunk.NEWLINE);

            // ===============================
            // DATOS REPARTIDOR
            // ===============================
            document.add(new Paragraph("Repartidor", seccion));
            document.add(Chunk.NEWLINE);

            PdfPTable tablaRepartidor = crearTablaMinimal(2);
            addLabel(tablaRepartidor, "Nombre");
            addValue(tablaRepartidor, repartidor.getNombreCompleto());
            addLabel(tablaRepartidor, "Correo");
            addValue(tablaRepartidor, repartidor.getCorreo());
            addLabel(tablaRepartidor, "Teléfono");
            addValue(tablaRepartidor, repartidor.getTelefono());

            document.add(tablaRepartidor);
            document.add(Chunk.NEWLINE);

            // ===============================
            // ENVIOS ASIGNADOS
            // ===============================
            document.add(new Paragraph("Envíos Asignados", seccion));
            document.add(Chunk.NEWLINE);

            for (Envio envio : enviosAsignados) {
                PdfPTable tablaEnvio = crearTablaMinimal(2);
                addLabel(tablaEnvio, "ID Envío");
                addValue(tablaEnvio, envio.getIdEnvio());
                addLabel(tablaEnvio, "Destino");
                addValue(tablaEnvio, envio.getDestino().getDescripcion());
                addLabel(tablaEnvio, "Estado");
                addValue(tablaEnvio, envio.getEstadoEnvio().name());
                addLabel(tablaEnvio, "Fecha Creación");
                addValue(tablaEnvio, envio.getFechaCreacion().toString());

                document.add(tablaEnvio);
                document.add(Chunk.NEWLINE);

                // Paquete
                Paquete p = envio.getPaquete();
                if (p != null) {
                    document.add(new Paragraph("Paquete", seccion));
                    PdfPTable tablaPaquete = crearTablaMinimal(2);
                    addLabel(tablaPaquete, "Precio");
                    addValue(tablaPaquete, p.getPrecio());
                    addLabel(tablaPaquete, "Peso");
                    addValue(tablaPaquete, p.getPeso());
                    document.add(tablaPaquete);

                    // Productos
                    document.add(Chunk.NEWLINE);
                    document.add(new Paragraph("Productos", seccion));
                    PdfPTable tablaProductos = new PdfPTable(4);
                    tablaProductos.setWidthPercentage(100);

                    addHeader(tablaProductos, "Nombre");
                    addHeader(tablaProductos, "Descripción");
                    addHeader(tablaProductos, "Cantidad");
                    addHeader(tablaProductos, "Precio");

                    for (Producto prod : p.getProductos()) {
                        tablaProductos.addCell(cell(prod.getNombre()));
                        tablaProductos.addCell(cell(prod.getDescripcion()));
                        tablaProductos.addCell(cell(String.valueOf(prod.getCantidad())));
                        tablaProductos.addCell(cell(String.valueOf(prod.getPrecio())));
                    }

                    document.add(tablaProductos);
                }

                document.add(Chunk.NEWLINE);
                document.add(new Chunk(separator));
                document.add(Chunk.NEWLINE);
            }

            document.close();

        } catch (Exception e) {
            throw new IOException("Error creando PDF: " + e.getMessage(), e);
        }
    }

    // ============================================
    // MÉTODOS AUXILIARES
    // ============================================

    private PdfPTable crearTablaMinimal(int columnas) {
        PdfPTable tabla = new PdfPTable(columnas);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(5);
        tabla.setSpacingAfter(10);
        return tabla;
    }

    private void addLabel(PdfPTable table, String text) {
        PdfPCell c = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11)));
        c.setBorder(Rectangle.NO_BORDER);
        c.setPadding(6);
        c.setBackgroundColor(new Color(245, 245, 245));
        table.addCell(c);
    }

    private void addValue(PdfPTable table, Object text) {
        PdfPCell c = new PdfPCell(new Phrase(String.valueOf(text), FontFactory.getFont(FontFactory.HELVETICA, 11)));
        c.setBorder(Rectangle.NO_BORDER);
        c.setPadding(6);
        table.addCell(c);
    }

    private void addHeader(PdfPTable table, String text) {
        PdfPCell c = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11)));
        c.setBorder(Rectangle.BOTTOM);
        c.setBorderColor(new Color(200, 200, 200));
        c.setPadding(6);
        table.addCell(c);
    }

    private PdfPCell cell(String text) {
        PdfPCell c = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 11)));
        c.setBorder(Rectangle.NO_BORDER);
        c.setPadding(6);
        return c;
    }
}
