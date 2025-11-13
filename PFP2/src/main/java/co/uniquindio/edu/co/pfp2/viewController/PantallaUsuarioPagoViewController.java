package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.DisponibilidadRepartidor;
import co.uniquindio.edu.co.pfp2.model.Paquete;
import co.uniquindio.edu.co.pfp2.model.Producto;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PantallaUsuarioPagoViewController {

    @FXML
    private Text txProductos;
    @FXML
    private Text txEnvio;
    @FXML
    private Text txSuma;
    @FXML
    private ComboBox<String> cbServicio;
    @FXML
   ComboBox<String> cbMetodo;

    @FXML
    private TableView<Producto> tbCarritoPago;
    @FXML
    private TableColumn<Producto, String> colCNombrePago;
    @FXML
    private TableColumn<Producto, String> colCCantidadPago;
    @FXML
    private TableColumn<Producto, String> colCSubTotalPago;


    private App app;


    public void setApp(App app) {
        this.app = app;
        cargarDatos();
    }

    @FXML
    private void initialize() {

        colCNombrePago.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombre()));

        colCCantidadPago.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidad())));

        colCSubTotalPago.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(
                        cellData.getValue().getPrecio() * cellData.getValue().getCantidad())));




    }

    private void cargarDatos() {
        if (app == null || app.usuarioSesion == null) {
            System.err.println("⚠ No hay referencia a la App o al usuario en sesión.");
            return;
        }

        tbCarritoPago.setItems(FXCollections.observableList(
                app.usuarioSesion.getListCarritosUsuario()
        ));

        // Ejemplo de textos (puedes adaptarlo según tu modelo)
        double total = app.usuarioSesion.getListCarritosUsuario()
                .stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum();


        double precio = 0;
        double peso = 0;


        for (Producto producto : app.usuarioSesion.getListCarritosUsuario()) {
            precio += producto.getPrecio()*producto.getCantidad();
            peso += producto.getPeso()*producto.getCantidad();
        }

        Paquete paquete = new Paquete(precio, peso, peso);
        paquete.setProductos(new ArrayList<>(app.usuarioSesion.getListCarritosUsuario()));
        txProductos.setText(String.valueOf(paquete.getPrecio()));
        txEnvio.setText(String.valueOf(paquete.getPeso()*100));
        cbMetodo.getItems().setAll("PSE","Efectivo","Tarjeta");
        cbServicio.getItems().setAll("Envio Normal", "Envio Urgente","Envio Fragil","Envio Económico");
        cbServicio.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.isEmpty()) {
                double valorSub = paquete.getPrecio()+(paquete.getPeso()*100);
                if (cbServicio.getValue().equals("Envio Normal")) {
                     txSuma.setText(String.valueOf(String.valueOf(valorSub)));
                }
                else if  (cbServicio.getValue().equals("Envio Urgente")) {
                    txSuma.setText(String.valueOf(String.valueOf(valorSub+ 5000)));
                }
                else if  (cbServicio.getValue().equals("Envio Fragil")) {
                    txSuma.setText(String.valueOf(String.valueOf(valorSub+5000)));
                }
                else if (cbServicio.getValue().equals("Envio Económico")) {
                    txSuma.setText(String.valueOf(String.valueOf(valorSub-5000)));
                }
            } else {
                txSuma.setText("---------------------");
            }
        });





    }

    public void hacerPago(){
        if ( cbServicio.getValue().isEmpty()){
            DialogUtils.mostrarError("Debes Seleccionar un servicio.");
            return;
        }
        if (cbMetodo.getValue().isEmpty()){
            DialogUtils.mostrarError("Debes Seleccionar un metodo de Pago.");
            return;
        }



        List<Repartidor> repartidores = app.listGlobalRepartidores.stream()
                .filter(r -> r.getDisponibilidadRepartidor() == DisponibilidadRepartidor.DISPONIBLE)
                .toList();

        if (repartidores.isEmpty()) {
            DialogUtils.mostrarError("No hay repartidores disponibles para realizar el pedido.");
            return;
        }
        DialogUtils.mostrarMensaje("Pago Realizado Correctamente usando el metodo de Pago: "+cbMetodo.getValue()+".\nPor un Valor de: $ "+txSuma.getText()+".");
        Random random = new Random();
        Repartidor repartidor = repartidores.get(random.nextInt(repartidores.size()));
        repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.EN_RUTA);
        DialogUtils.mostrarMensaje("Pedido Realizado con éxito.\nEl Repartidor elegido fue: "+repartidor.getNombreCompleto()+".");
        app.usuarioSesion.getListCarritosUsuario().removeAll(app.usuarioSesion.getListCarritosUsuario());
        
        app.cerrarVentanaModular();
        app.openPantallaUsuario();
    }


}
