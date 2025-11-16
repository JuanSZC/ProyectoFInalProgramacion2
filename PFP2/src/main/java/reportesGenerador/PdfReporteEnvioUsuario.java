package reportesGenerador;

import co.uniquindio.edu.co.pfp2.model.*;
import org.openpdf.text.*;
import org.openpdf.text.pdf.*;
import org.openpdf.text.pdf.draw.LineSeparator;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PdfReporteEnvioUsuario {

    private final Usuario usuario;
    private final Envio envio;

    public PdfReporteEnvioUsuario(Usuario usuario, Envio envio) {
        this.usuario = usuario;
        this.envio = envio;
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
            Font textoBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);

            LineSeparator separator = new LineSeparator(0.5f, 100, new Color(210, 210, 210), Element.ALIGN_CENTER, -2);

            // ===============================
            // ENCABEZADO
            // ===============================
            document.add(new Paragraph("Reporte de Envío", titulo));
            document.add(new Paragraph("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), texto));
            document.add(new Paragraph("\n"));
            document.add(separator);
            document.add(new Paragraph("\n"));

            // ===============================
            // DATOS USUARIO
            // ===============================
            document.add(new Paragraph("Usuario", seccion));
            document.add(new Paragraph("\n"));

            PdfPTable tablaUsuario = crearTablaMinimal(2);
            addLabel(tablaUsuario, "Nombre");
            addValue(tablaUsuario, usuario.getNombreCompleto());
            addLabel(tablaUsuario, "Correo");
            addValue(tablaUsuario, usuario.getCorreo());
            addLabel(tablaUsuario, "Teléfono");
            addValue(tablaUsuario, usuario.getTelefono());

            document.add(tablaUsuario);
            document.add(new Paragraph("\n"));

            // ===============================
            //   INFORMACIÓN DEL ENVÍO
            // ===============================
            document.add(new Paragraph("Detalles del Envío", seccion));
            document.add(new Paragraph("\n"));

            PdfPTable tablaEnvio = crearTablaMinimal(2);
            addLabel(tablaEnvio, "ID Envío");
            addValue(tablaEnvio, envio.getIdEnvio());
            addLabel(tablaEnvio, "Destino");
            addValue(tablaEnvio, envio.getDestino().getDescripcion());
            addLabel(tablaEnvio, "Estado");
            addValue(tablaEnvio, envio.getEstadoEnvio().name());
            addLabel(tablaEnvio, "Fecha Creación");
            addValue(tablaEnvio, envio.getFechaCreacion().toString());
            addLabel(tablaEnvio, "Repartidor");
            addValue(tablaEnvio, envio.getRepartidor() != null
                    ? envio.getRepartidor().getNombreCompleto()
                    : "Sin asignar");

            document.add(tablaEnvio);

            // ===============================
            // PAQUETE
            // ===============================
            Paquete p = envio.getPaquete();
            if (p != null) {
                document.add(new Paragraph("\nPaquete", seccion));

                PdfPTable tablaPaquete = crearTablaMinimal(2);

                addLabel(tablaPaquete, "Precio");
                addValue(tablaPaquete, p.getPrecio());
                addLabel(tablaPaquete, "Peso");
                addValue(tablaPaquete, p.getPeso());

                document.add(tablaPaquete);

                // PRODUCTOS
                document.add(new Paragraph("\nProductos", seccion));

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

            document.close();

        } catch (Exception e) {
            throw new IOException("Error creando PDF: " + e.getMessage(), e);
        }
    }

    // ============================================
    //  MÉTODOS AUXILIARES
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
