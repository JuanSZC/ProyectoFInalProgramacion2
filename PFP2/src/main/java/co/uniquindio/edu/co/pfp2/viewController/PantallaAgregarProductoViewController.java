package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaAgregarProductoViewController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtPeso;

    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void agregar() {
        try {
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String precioTxt = txtPrecio.getText().trim();
            String cantidadTxt = txtCantidad.getText().trim();
            String pesoTxt = txtPeso.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || precioTxt.isEmpty() || cantidadTxt.isEmpty() || pesoTxt.isEmpty()) {
                DialogUtils.mostrarError("Todos los campos son obligatorios.");
                return;
            }

            double precio;
            int cantidad;
            double peso;
            try {
                precio = Double.parseDouble(precioTxt);
                cantidad = Integer.parseInt(cantidadTxt);
                peso = Double.parseDouble(pesoTxt);
            } catch (NumberFormatException e) {
                DialogUtils.mostrarError("Precio, cantidad o peso inválidos.");
                return;
            }

            if (precio <= 0 || cantidad < 0 || peso < 0) {
                DialogUtils.mostrarError("Los valores numéricos deben ser mayores a 0.");
                return;
            }

            Producto nuevo = new Producto(app.idProducto, nombre, descripcion, precio, cantidad, peso);
            app.idProducto++;
            app.listGlobalProductos.add(nuevo);

            DialogUtils.mostrarMensaje("Producto agregado correctamente.");
            cancelar();

        } catch (Exception e) {
            e.printStackTrace();
            DialogUtils.mostrarError("Error al agregar el producto: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
