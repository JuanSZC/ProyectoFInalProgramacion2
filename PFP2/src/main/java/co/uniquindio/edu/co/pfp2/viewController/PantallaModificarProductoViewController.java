package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaModificarProductoViewController {

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

    private Producto producto;

    public void setProducto(Producto producto) {
        this.producto = producto;
        if (producto != null) {
            txtNombre.setText(producto.getNombre());
            txtDescripcion.setText(producto.getDescripcion());
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
            txtCantidad.setText(String.valueOf(producto.getCantidad()));
            txtPeso.setText(String.valueOf(producto.getPeso()));
        }
    }

    @FXML
    private void modificar() {
        if (producto == null) {
            DialogUtils.mostrarError("No hay producto seleccionado para modificar.");
            return;
        }

        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String precioTxt = txtPrecio.getText().trim();
        String cantidadTxt = txtCantidad.getText().trim();
        String pesoTxt = txtPeso.getText().trim();

        if (nombre.isEmpty() || descripcion.isEmpty() || precioTxt.isEmpty() || cantidadTxt.isEmpty() || pesoTxt.isEmpty()) {
            DialogUtils.mostrarError("Hay campos vacíos.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioTxt);
            int cantidad = Integer.parseInt(cantidadTxt);
            double peso = Double.parseDouble(pesoTxt);

            if (precio <= 0 || cantidad < 0 || peso < 0) {
                DialogUtils.mostrarError("Los valores numéricos deben ser mayores que cero.");
                return;
            }

            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            producto.setPeso(peso);

            DialogUtils.mostrarMensaje("Producto modificado correctamente.");
            cancelar();

        } catch (NumberFormatException e) {
            DialogUtils.mostrarError("Precio, cantidad o peso inválidos.");
        }
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
