package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import reportesGenerador.PdfReporteEnvioUsuario;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class PantallaUsuarioViewController {
    App app;
    Usuario usuarioSesion;
    ObservableList<Integer> cbLista = FXCollections.observableArrayList();
    @FXML
    TableView<Producto> tbCatalogoDisponible;
    @FXML
    TableColumn<Producto,String> tbCDNombre;
    @FXML
    TableColumn<Producto,String> tbCDDescripcion;
    @FXML
    TableColumn<Producto,String> tbCDPrecio;
    @FXML
    TableColumn<Producto,String> tbCDCantidad;
    @FXML
    TableView<Producto> tbMiCatalogo;
    @FXML
    TableColumn<Producto,String> colMCNombre;
    @FXML
    TableColumn<Producto,String> colMCDescripcion;
    @FXML
    TableColumn<Producto,String> colMCPrecio;
    @FXML
    TableColumn<Producto,String> colMCCantidad;

    @FXML
    TableView<Producto> tbCarrito;
    @FXML
    TableColumn<Producto,String> colCNombre;
    @FXML
    TableColumn<Producto,String> colCDescripcion;
    @FXML
    TableColumn<Producto,String> colCCantidad;
    @FXML
    TableColumn<Producto,String> colCSubTotal;
    @FXML
    Button btHP;


    public Text gettUsuario() {
        return tUsuario;
    }

    public void settUsuario(Text tUsuario) {
        this.tUsuario = tUsuario;
    }

    @FXML
    public Text tUsuario;
    @FXML
    ComboBox<Integer> cbUnidadesPedir;

    @FXML
    TextField txNombreProducto;
    @FXML
    TextArea txDescripcionProducto;
    @FXML
    TextField txPrecioProducto;
    @FXML
    TextField txCantidadProducto;
    @FXML
    TextField txPesoProducto;

    @FXML
    Button btAM;

    @FXML
    TableView<Envio> tbEnviosUsuario;
    @FXML
    TableColumn<Envio,String> colFechaEnvio;
    @FXML
    TableColumn<Envio,String> colValorEnvio;
    @FXML
    TableColumn<Envio,String> colRepartidorEnvio;
    @FXML
    TableColumn<Envio,String> colEstadoEnvio;


    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void openPantallaUsuarioConfiguracion(){
        this.app.openPantallaUsuarioConfiguracion();
    }
    public void cerrarSesion(){
        app.openPantallaBienvenida();
    }

    public void pedirProducto(){
        Producto p = tbCatalogoDisponible.getSelectionModel().getSelectedItem();
        Integer cantidad = cbUnidadesPedir.getValue();

        if (p == null){
            DialogUtils.mostrarError("Debe seleccionar un producto.");
            return;
        }
        if (cbUnidadesPedir.getValue() == null){
            DialogUtils.mostrarError("Debe seleccionar una cantidad.");
            return;
        }
        for (Producto pr : app.listGlobalProductos){
            if (pr == p){
                Producto nuevo = new Producto(pr.getIdProducto(),pr.getNombre(),pr.getDescripcion(),pr.getPrecio(),cantidad,pr.getPeso());
                pr.setCantidad(pr.getCantidad() - cantidad);
                app.usuarioSesion.getListCarritosUsuario().add(nuevo);

                listarTablas();
                tbCatalogoDisponible.refresh();
                tbCatalogoDisponible.getSelectionModel().select(null);
                cbUnidadesPedir.setValue(null);
                DialogUtils.mostrarMensaje("Producto agregado al carrito.");
            }
        }
    }
    public void agregarProducto() {
        String nombre = txNombreProducto.getText();
        String descripcion = txDescripcionProducto.getText();
        String precioTxt = txPrecioProducto.getText();
        String cantidadTxt = txCantidadProducto.getText();
        String pesoTxt = txPesoProducto.getText();

        if (nombre.isEmpty() || descripcion.isEmpty() || precioTxt.isEmpty() || cantidadTxt.isEmpty() || pesoTxt.isEmpty()) {
            DialogUtils.mostrarError("Hay campos vacíos.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioTxt);
            int cantidad = Integer.parseInt(cantidadTxt);
            double peso = Double.parseDouble(pesoTxt);

            // Validar decimales (máximo 2)
            if (Math.round(precio * 100) != precio * 100 || Math.round(peso * 100) != peso * 100) {
                DialogUtils.mostrarError("No se permiten valores con más de 2 decimales.");
                return;
            }

            if (precio <= 1 || cantidad < 0 || peso < 0) {
                DialogUtils.mostrarError("Los valores numéricos deben ser mayores a 0.");
                return;
            }

            if (precio > 10000000) {
                DialogUtils.mostrarError("No se puede publicar un producto que supere los $10.000.000.");
                return;
            }

            if (peso > 1000) {
                DialogUtils.mostrarError("Trátame serio, contrate un avión mejor.");
                return;
            }

            if (cantidad > 999) {
                DialogUtils.mostrarError("Supera el límite de cantidad por producto.");
                return;
            }

            Producto nuevo = new Producto(app.idProducto, nombre, descripcion, precio, cantidad, peso);
            app.idProducto++;

            app.usuarioSesion.getListProductosUsuario().add(nuevo);
            app.listGlobalProductos.add(nuevo);

            tbMiCatalogo.refresh();
            tbCatalogoDisponible.refresh();

            DialogUtils.mostrarMensaje("Producto agregado correctamente.");

        } catch (NumberFormatException e) {
            DialogUtils.mostrarError("Precio, cantidad o peso inválidos.");
        }
    }
    public void generarReporte() {

        Usuario usuario = app.usuarioSesion; // ← usuario logueado
        Envio envio = tbEnviosUsuario.getSelectionModel().getSelectedItem(); // ← envío seleccionado

        if (envio != null) {
            try {

                String ruta = "reportesGenerador" + File.separator + "usuarios" + File.separator
                        + "reporte_envio_" + envio.getIdEnvio() + ".pdf";

                File file = new File(ruta);
                file.getParentFile().mkdirs();

                // Generación del PDF
                PdfReporteEnvioUsuario pdf = new PdfReporteEnvioUsuario(usuario, envio);
                pdf.generarPdf(ruta);

                DialogUtils.mostrarMensaje("PDF generado correctamente en: " + ruta);

            } catch (IOException e) {
                e.printStackTrace();
                DialogUtils.mostrarError("No se pudo generar el PDF: " + e.getMessage());
            }

        } else {
            DialogUtils.mostrarError("Debe seleccionar un envío.");
        }
    }


    public void listarTablas() {
        if (app == null || app.usuarioSesion == null) return;

        FilteredList<Producto> filtrados = new FilteredList<>(app.listGlobalProductos);
        filtrados.setPredicate(p -> p.getCantidad() > 0 &&
                !app.usuarioSesion.getListProductosUsuario().contains(p));

        tbCatalogoDisponible.setItems(filtrados);
        tbMiCatalogo.setItems(app.usuarioSesion.getListProductosUsuario());
        tbCarrito.setItems(app.usuarioSesion.getListCarritosUsuario());
        tbEnviosUsuario.setItems(app.usuarioSesion.getListEnviosUsuario());

        tbCatalogoDisponible.refresh();
        tbMiCatalogo.refresh();
        tbCarrito.refresh();
        tbEnviosUsuario.refresh();
    }

    public void accionAgregarModificar() {
        Producto seleccionado = tbMiCatalogo.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            modificarProducto();
        } else {
            agregarProducto();
        }
    }

    public void modificarProducto() {
        Producto seleccionado = tbMiCatalogo.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            DialogUtils.mostrarError("Debe seleccionar un producto para modificar.");
            return;
        }

        String nombre = txNombreProducto.getText();
        String descripcion = txDescripcionProducto.getText();
        String precioTxt = txPrecioProducto.getText();
        String cantidadTxt = txCantidadProducto.getText();
        String pesoTxt = txPesoProducto.getText();

        if (nombre.isEmpty() || descripcion.isEmpty() ||
                precioTxt.isEmpty() || cantidadTxt.isEmpty() || pesoTxt.isEmpty()) {
            DialogUtils.mostrarError("Hay campos vacíos.");
            tbMiCatalogo.getSelectionModel().clearSelection();
            return;
        }

        try {
            double precio = Double.parseDouble(precioTxt);
            int cantidad = Integer.parseInt(cantidadTxt);
            double peso = Double.parseDouble(pesoTxt);

            if (precio <= 0 || cantidad < 0 || peso < 0) {
                DialogUtils.mostrarError("Los valores numéricos deben ser mayores que cero.");
                tbMiCatalogo.getSelectionModel().clearSelection();
                return;
            }

            seleccionado.setNombre(nombre);
            seleccionado.setDescripcion(descripcion);
            seleccionado.setPrecio(precio);
            seleccionado.setCantidad(cantidad);
            seleccionado.setPeso(peso);

            tbMiCatalogo.refresh();
            tbCatalogoDisponible.refresh();

            DialogUtils.mostrarMensaje("Producto modificado correctamente.");
            tbMiCatalogo.getSelectionModel().clearSelection();

        } catch (NumberFormatException e) {
            DialogUtils.mostrarError("Precio, cantidad o peso inválidos.");
            tbMiCatalogo.getSelectionModel().clearSelection();
        }
    }

    public void hacerPedido() {
        if (usuarioSesion.getListDireccionesUsuario().size() == 0) {
            DialogUtils.mostrarError("El usuario no posee direcciones activas por favor agrega una..");
            app.openPantallaDireccionUsuario((Stage)btHP.getScene().getWindow() );
        }
        if (usuarioSesion.getListCarritosUsuario().isEmpty()) {
            DialogUtils.mostrarError("Debe tener por lo menos un producto para realizar el pedido.");
            return;
        }
        app.openPantallaUsuarioPago();
    }

    public void cancelarPedido() {
        Envio envio = tbEnviosUsuario.getSelectionModel().getSelectedItem();

        if (envio == null) {
            DialogUtils.mostrarError("Debe seleccionar un pedido para cancelar.");
            return;
        }

        EstadoEnvio estado = envio.getEstadoEnvio();

        if (estado == EstadoEnvio.EN_RUTA) {
            DialogUtils.mostrarError("No se puede cancelar un pedido en ruta.");
            return;
        }

        if (estado == EstadoEnvio.ENTREGADO) {
            DialogUtils.mostrarError("¿En serio? Es cancelar, no devolver.");
            return;
        }
        if (estado == EstadoEnvio.CANCELADO) {
            DialogUtils.mostrarError("No se puede cancelar un pedido que ya esta cancelado mi papacho.");
        return;
        }

        envio.setEstadoEnvio(EstadoEnvio.CANCELADO);
        DialogUtils.mostrarMensaje("Pedido cancelado correctamente.");

        if (envio.getRepartidor() != null) {
            envio.getRepartidor().setDisponibilidadRepartidor(DisponibilidadRepartidor.DISPONIBLE);
        }

        if (envio.getPaquete() != null && envio.getPaquete().getProductos() != null) {
            Map<Integer, Producto> mapaGlobal = app.listGlobalProductos.stream()
                    .collect(Collectors.toMap(Producto::getIdProducto, p -> p));

            for (Producto producto : envio.getPaquete().getProductos()) {
                Producto pGlobal = mapaGlobal.get(producto.getIdProducto());
                if (pGlobal != null) {
                    pGlobal.setCantidad(pGlobal.getCantidad() + producto.getCantidad());
                }
            }
        }

        listarTablas();
    }


    public void initialize(){

        tbMiCatalogo.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                btAM.setText("Modificar Producto");
            } else {
                btAM.setText("Añadir Producto");
            }
        });

        tbCDNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbCDDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        tbCDCantidad.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidad()))
        );
        tbCDPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));

        colMCNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colMCDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colMCCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidad())));
        colMCPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));

        colCNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colCCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidad())));
        colCSubTotal.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio()*cellData.getValue().getCantidad())));

        colFechaEnvio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaCreacion().toString()));
        colValorEnvio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPaquete().getPrecio())));
        colRepartidorEnvio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getRepartidor().getNombreCompleto())));
        colEstadoEnvio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEstadoEnvio().toString())));

        listarTablas();

        tbCatalogoDisponible.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            cbUnidadesPedir.getItems().clear();
            cbUnidadesPedir.setValue(null);

            if (newSel != null) {
                ObservableList<Integer> lista = FXCollections.observableArrayList();
                for (int i = 1; i <= newSel.getCantidad(); i++) {
                    lista.add(i);
                }
                cbUnidadesPedir.setItems(lista);
            }
        });
        tbMiCatalogo.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txNombreProducto.setText(newSel.getNombre());
                txDescripcionProducto.setText(newSel.getDescripcion());
                txPrecioProducto.setText(String.valueOf(newSel.getPrecio()));
                txCantidadProducto.setText(String.valueOf(newSel.getCantidad()));
                txPesoProducto.setText(String.valueOf(newSel.getPeso()));
            } else {
                txNombreProducto.clear();
                txDescripcionProducto.clear();
                txPrecioProducto.clear();
                txCantidadProducto.clear();
                txPesoProducto.clear();
            }
        });
        tUsuario.textProperty().addListener((observable, oldValue, newValue) -> {
            String valorSesion = app.usuarioSesion.getNombreCompleto();
            if (!newValue.equals(valorSesion)) {
                tUsuario.setText(valorSesion);
            }
        });










    }

    public void limpiarSeleccionCatalogoDisponible() {
        tbCatalogoDisponible.getSelectionModel().clearSelection();
        cbUnidadesPedir.setValue(null);
    }

    public void limpiarSeleccionMiCatalogo() {
        tbMiCatalogo.getSelectionModel().clearSelection();
        txNombreProducto.clear();
        txDescripcionProducto.clear();
        txPrecioProducto.clear();
        txCantidadProducto.clear();
        txPesoProducto.clear();
    }

    public void limpiarSeleccionCarrito() {
        tbCarrito.getSelectionModel().clearSelection();
    }

    public void limpiarSeleccionEnvios() {
        tbEnviosUsuario.getSelectionModel().clearSelection();
    }

}
