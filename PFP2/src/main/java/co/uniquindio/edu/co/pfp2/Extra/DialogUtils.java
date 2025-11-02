package co.uniquindio.edu.co.pfp2.Extra;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class DialogUtils {

    // === ERROR (rojo suave) ===
    public static void mostrarError(String descripcion) {
        crearDialogo(Alert.AlertType.ERROR, descripcion, "#FF5B5B");
    }

    // === MENSAJE (azul cielo claro) ===
    public static void mostrarMensaje(String descripcion) {
        crearDialogo(Alert.AlertType.INFORMATION, descripcion, "#60A5FA");
    }

    // === MÉTODO BASE PRIVADO ===
    private static void crearDialogo(Alert.AlertType tipo, String descripcion, String colorBase) {
        Alert alert = new Alert(tipo);
        alert.setTitle("");              // sin título
        alert.setHeaderText(null);       // sin encabezado
        alert.setContentText(descripcion);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("""
            -fx-background-color: #E8F6FD;
            -fx-font-family: 'Segoe UI';
            -fx-font-size: 15px;
            -fx-text-fill: #2F6FAF;
            -fx-padding: 15 20 15 20;
        """);

        // Botón OK estilizado con el color base
        var button = dialogPane.lookupButton(alert.getButtonTypes().get(0));
        if (button != null)
            button.setStyle("""
                -fx-background-color: %s;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-background-radius: 8;
                -fx-cursor: hand;
                -fx-padding: 6 15 6 15;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 3, 0, 0, 1);
            """.formatted(colorBase));

        alert.showAndWait();
    }
}
