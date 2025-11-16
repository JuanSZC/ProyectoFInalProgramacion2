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

public class PdfUsuarioReporte {

    private final Usuario usuario;

    public PdfUsuarioReporte(Usuario usuario) {
        this.usuario = usuario;
    }

    public void generarPdf(String rutaArchivo) throws IOException {
        try {
            Document document = new Document(PageSize.A4, 50, 50, 60, 50);
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
            document.open();

            // ===============================
            //   ESTILOS MINIMALISTAS
            // ===============================
            Font tituloPrincipal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(40, 40, 40));
            Font tituloSeccion = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, new Color(60, 60, 60));
            Font texto = FontFactory.getFont(FontFactory.HELVETICA, 11, new Color(80, 80, 80));

            LineSeparator separator = new LineSeparator(0.5f, 100, new Color(210, 210, 210), Element.ALIGN_CENTER, -2);

            // ===============================
            //        ENCABEZADO
            // ===============================
            Paragraph titulo = new Paragraph("Reporte de Usuario", tituloPrincipal);
            titulo.setAlignment(Element.ALIGN_LEFT);

            Paragraph fecha = new Paragraph(
                    "Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    texto
            );
            fecha.setAlignment(Element.ALIGN_LEFT);

            document.add(titulo);
            document.add(fecha);
            document.add(new Paragraph("\n"));
            document.add(separator);
            document.add(new Paragraph("\n"));

            // ===============================
            //      DATOS PERSONALES
            // ===============================
            document.add(new Paragraph("Datos del Usuario", tituloSeccion));
            document.add(new Paragraph("\n"));

            PdfPTable tablaUsuario = crearTablaMinimal(2);

            addCellLabel(tablaUsuario, "ID Usuario");
            addCellValue(tablaUsuario, String.valueOf(usuario.getIdUsuario()));

            addCellLabel(tablaUsuario, "Nombre");
            addCellValue(tablaUsuario, usuario.getNombreCompleto());

            addCellLabel(tablaUsuario, "Cédula");
            addCellValue(tablaUsuario, usuario.getCedula());

            addCellLabel(tablaUsuario, "Correo");
            addCellValue(tablaUsuario, usuario.getCorreo());

            addCellLabel(tablaUsuario, "Teléfono");
            addCellValue(tablaUsuario, String.valueOf(usuario.getTelefono()));

            document.add(tablaUsuario);
            document.add(new Paragraph("\n"));

            // Direcciones
            document.add(new Paragraph("Direcciones Registradas", tituloSeccion));
            for (Direccion d : usuario.getListDireccionesUsuario()) {
                document.add(new Paragraph("- " + d.getDescripcion(), texto));
            }

            document.add(new Paragraph("\n"));
            document.add(separator);
            document.add(new Paragraph("\n"));

            // ===============================
            //             ENVÍOS
            // ===============================
            document.add(new Paragraph("Historial de Envíos", tituloSeccion));
            document.add(new Paragraph("\n"));

            for (Envio envio : usuario.getListEnviosUsuario()) {

                PdfPTable tablaEnvio = crearTablaMinimal(2);

                addCellLabel(tablaEnvio, "ID Envío");
                addCellValue(tablaEnvio, String.valueOf(envio.getIdEnvio()));

                addCellLabel(tablaEnvio, "Destino");
                addCellValue(tablaEnvio, envio.getDestino().getDescripcion());

                addCellLabel(tablaEnvio, "Estado");
                addCellValue(tablaEnvio, envio.getEstadoEnvio().name());

                addCellLabel(tablaEnvio, "Fecha Creación");
                addCellValue(tablaEnvio, envio.getFechaCreacion().toString());

                addCellLabel(tablaEnvio, "Repartidor");
                addCellValue(tablaEnvio,
                        envio.getRepartidor() != null ? envio.getRepartidor().getNombreCompleto() : "Sin asignar"
                );

                document.add(tablaEnvio);

                // PAQUETE
                Paquete p = envio.getPaquete();
                if (p != null) {
                    document.add(new Paragraph("\nPaquete", tituloSeccion));

                    PdfPTable tablaPaquete = crearTablaMinimal(2);

                    addCellLabel(tablaPaquete, "Precio");
                    addCellValue(tablaPaquete, String.valueOf(p.getPrecio()));

                    addCellLabel(tablaPaquete, "Peso");
                    addCellValue(tablaPaquete, String.valueOf(p.getPeso()));

                    document.add(tablaPaquete);

                    // PRODUCTOS
                    document.add(new Paragraph("Productos", tituloSeccion));

                    PdfPTable tablaProductos = new PdfPTable(4);
                    tablaProductos.setWidthPercentage(100);

                    addCellHeader(tablaProductos, "Nombre");
                    addCellHeader(tablaProductos, "Descripción");
                    addCellHeader(tablaProductos, "Cantidad");
                    addCellHeader(tablaProductos, "Precio");

                    for (Producto prod : p.getProductos()) {
                        tablaProductos.addCell(cell(prod.getNombre()));
                        tablaProductos.addCell(cell(prod.getDescripcion()));
                        tablaProductos.addCell(cell(String.valueOf(prod.getCantidad())));
                        tablaProductos.addCell(cell(String.valueOf(prod.getPrecio())));
                    }

                    document.add(tablaProductos);
                }

                document.add(new Paragraph("\n"));
                document.add(separator);
                document.add(new Paragraph("\n"));
            }

            document.close();

        } catch (Exception ex) {
            throw new IOException("Error generando PDF minimalista: " + ex.getMessage(), ex);
        }
    }

    // ===============================
    //   FUNCIONES AUXILIARES
    // ===============================

    private PdfPCell cell(String text) {
        PdfPCell c = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 11, new Color(80, 80, 80))));
        c.setBorder(Rectangle.NO_BORDER);
        c.setPadding(6);
        return c;
    }

    private void addCellLabel(PdfPTable tabla, String text) {
        PdfPCell c = new PdfPCell(new Phrase(text,
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, new Color(60, 60, 60))));
        c.setBorder(Rectangle.NO_BORDER);
        c.setPadding(6);
        c.setBackgroundColor(new Color(245, 245, 245));
        tabla.addCell(c);
    }

    private void addCellValue(PdfPTable tabla, String text) {
        PdfPCell c = new PdfPCell(new Phrase(text,
                FontFactory.getFont(FontFactory.HELVETICA, 11, new Color(80, 80, 80))));
        c.setBorder(Rectangle.NO_BORDER);
        c.setPadding(6);
        tabla.addCell(c);
    }

    private void addCellHeader(PdfPTable tabla, String text) {
        PdfPCell c = new PdfPCell(new Phrase(text,
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, new Color(60, 60, 60))));
        c.setBorder(Rectangle.BOTTOM);
        c.setBorderColor(new Color(200, 200, 200));
        c.setPadding(6);
        tabla.addCell(c);
    }

    private PdfPTable crearTablaMinimal(int columnas) {
        PdfPTable tabla = new PdfPTable(columnas);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(5);
        tabla.setSpacingAfter(10);
        return tabla;
    }
}
