package reportesGenerador;

import co.uniquindio.edu.co.pfp2.model.Envio;
import co.uniquindio.edu.co.pfp2.model.Usuario;

import java.io.IOException;

/**
 * Wrapper ligero que genera un PDF por cada envío del usuario usando {@link PdfReporteEnvioUsuario}.
 * Evita dependencias directas con las APIs internas de OpenPDF en esta clase.
 */
public class PdfUsuarioReporte {

    private final Usuario usuario;

    public PdfUsuarioReporte(Usuario usuario) {
        this.usuario = usuario;
    }

    public void generarPdf(String rutaArchivoBase) throws IOException {
        if (usuario == null) return;

        for (Envio envio : usuario.getListEnviosUsuario()) {
            String ruta = rutaArchivoBase.replace(".pdf", "_envio_" + envio.getIdEnvio() + ".pdf");
            PdfReporteEnvioUsuario reporte = new PdfReporteEnvioUsuario(usuario, envio);
            reporte.generarPdf(ruta);
        }
    }

}
