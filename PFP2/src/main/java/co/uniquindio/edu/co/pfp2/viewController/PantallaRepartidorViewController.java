package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.model.Envio;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import co.uniquindio.edu.co.pfp2.model.EstadoEnvio;
import co.uniquindio.edu.co.pfp2.model.DisponibilidadRepartidor;
import co.uniquindio.edu.co.pfp2.model.Direccion;
import co.uniquindio.edu.co.pfp2.model.ZonaCobertura;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import reportesGenerador.PdfReporteEnvioUsuario;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import java.util.List;

public class PantallaRepartidorViewController {

	private App app;
	private Repartidor repartidor;

	@FXML
	private Text tRepartidor;

	@FXML
	private TableView<Envio> tbEnvios;

	@FXML
	private TableView<Envio> tbEnCurso;

	@FXML
	private TableColumn<Envio, String> colId;

	@FXML
	private TableColumn<Envio, String> colFecha;

	@FXML
	private TableColumn<Envio, String> colUsuario;

	@FXML
	private TableColumn<Envio, String> colDireccion;

	@FXML
	private TableColumn<Envio, String> colCosto;

	@FXML
	private TableColumn<Envio, String> colEstado;

	@FXML
	private TableColumn<Envio, String> colId2;

	@FXML
	private TableColumn<Envio, String> colFecha2;

	@FXML
	private TableColumn<Envio, String> colUsuario2;

	@FXML
	private TableColumn<Envio, String> colDireccion2;

	@FXML
	private TableColumn<Envio, String> colCosto2;

	@FXML
	private TableColumn<Envio, String> colEstado2;

	public void setApp(App app) {
		this.app = app;
	}



	public void setRepartidor(Repartidor repartidor) {
		this.repartidor = repartidor;
		if (tRepartidor != null && repartidor != null) {
			tRepartidor.setText(repartidor.getNombreCompleto());
		}
		cargarEnvios();
		cargarEnviosEnCurso();
	}

	private void cargarEnviosEnCurso() {
		if (app == null || repartidor == null) return;

		ObservableList<Envio> datos = FXCollections.observableArrayList();

		for (Usuario u : app.listGlobalUsuarios) {
			List<Envio> envs = u.getListEnviosUsuario();
			for (Envio e : envs) {
				if (e.getRepartidor() != null && e.getRepartidor().getIdRepartidor() == repartidor.getIdRepartidor()) {
					if (e.getEstadoEnvio() == EstadoEnvio.ASIGNADO || e.getEstadoEnvio() == EstadoEnvio.EN_RUTA) {
						datos.add(e);
					}
				}
			}
		}

		tbEnCurso.setItems(datos);

		colId2.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getIdEnvio())));
		colFecha2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFechaCreacion() != null ? c.getValue().getFechaCreacion().toString() : "-"));
		colUsuario2.setCellValueFactory(c -> {
			String nombre = "Desconocido";
			for (Usuario u : app.listGlobalUsuarios) {
				if (u.getListDireccionesUsuario() != null) {
					for (Direccion d : u.getListDireccionesUsuario()) {
						if (c.getValue().getDestino() != null && d.getIdDireccion() == c.getValue().getDestino().getIdDireccion()) {
							nombre = u.getNombreCompleto();
							break;
						}
					}
				}
			}
			return new SimpleStringProperty(nombre);
		});
		colDireccion2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDestino() != null ? c.getValue().getDestino().getDescripcion() : "-"));
		colCosto2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPaquete() != null ? String.format("$%.2f", c.getValue().getPaquete().getPrecio()) : "$0.00"));
		colEstado2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstadoEnvio() != null ? c.getValue().getEstadoEnvio().name() : "-"));
	}

	@FXML
	private void proximoEstado() {
		Envio seleccionado = tbEnCurso.getSelectionModel().getSelectedItem();
		if (seleccionado == null) {
			DialogUtils.mostrarError("Seleccione un envío.");
			return;
		}

		EstadoEnvio estadoActual = seleccionado.getEstadoEnvio();

		// Transiciones permitidas: ASIGNADO -> EN_RUTA -> ENTREGADO
		if (estadoActual == EstadoEnvio.ASIGNADO) {
			seleccionado.setEstadoEnvio(EstadoEnvio.EN_RUTA);
			if (seleccionado.getRepartidor() != null) {
				seleccionado.getRepartidor().setDisponibilidadRepartidor(DisponibilidadRepartidor.EN_RUTA);
			}
		} else if (estadoActual == EstadoEnvio.EN_RUTA) {
			seleccionado.setEstadoEnvio(EstadoEnvio.ENTREGADO);
			// Liberar repartidor si no tiene más envíos en ruta
			if (seleccionado.getRepartidor() != null) {
				boolean tieneEnRuta = false;
				for (Usuario u : app.listGlobalUsuarios) {
					for (Envio e : u.getListEnviosUsuario()) {
						if (e.getRepartidor() != null && e.getRepartidor().getIdRepartidor() == seleccionado.getRepartidor().getIdRepartidor() && e.getEstadoEnvio() == EstadoEnvio.EN_RUTA) {
							tieneEnRuta = true;
							break;
						}
					}
					if (tieneEnRuta) break;
				}
				if (!tieneEnRuta) {
					seleccionado.getRepartidor().setDisponibilidadRepartidor(DisponibilidadRepartidor.DISPONIBLE);
				}
			}
		}

		cargarEnviosEnCurso();
		cargarEnvios();
	}

	@FXML
	private void cancelarYReasignar() {
		Envio seleccionado = tbEnCurso.getSelectionModel().getSelectedItem();
		if (seleccionado == null) {
			DialogUtils.mostrarError("Seleccione un envío.");
			return;
		}

		// Buscar otro repartidor disponible en la MISMA ZONA de cobertura del destino
		Repartidor nuevoRepartidor = null;
		if (seleccionado.getDestino() != null && seleccionado.getDestino().getZonaCobertura() != null) {
			ZonaCobertura zonaDestino = seleccionado.getDestino().getZonaCobertura();
			for (Repartidor r : app.listGlobalRepartidores) {
				// Buscar: distinto del actual, misma zona, disponible
				if (r.getIdRepartidor() != repartidor.getIdRepartidor() &&
						r.getZonaCobertura() == zonaDestino &&
						r.getDisponibilidadRepartidor() == DisponibilidadRepartidor.DISPONIBLE) {
					nuevoRepartidor = r;
					break;
				}
			}
		}

		if (nuevoRepartidor != null) {
			// Reasignar a nuevo repartidor
			seleccionado.setRepartidor(nuevoRepartidor);
			seleccionado.setEstadoEnvio(EstadoEnvio.ASIGNADO);
			nuevoRepartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.EN_RUTA);

			// Liberar repartidor actual
			boolean tieneEnRuta = false;
			for (Usuario u : app.listGlobalUsuarios) {
				for (Envio e : u.getListEnviosUsuario()) {
					if (e.getRepartidor() != null && e.getRepartidor().getIdRepartidor() == repartidor.getIdRepartidor() && e.getEstadoEnvio() == EstadoEnvio.EN_RUTA) {
						tieneEnRuta = true;
						break;
					}
				}
				if (tieneEnRuta) break;
			}
			if (!tieneEnRuta) {
				repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.DISPONIBLE);
			}
		} else {
			// No hay repartidor disponible en la zona: CANCELAR envío
			seleccionado.setEstadoEnvio(EstadoEnvio.CANCELADO);

			// Liberar repartidor actual
			boolean tieneEnRuta = false;
			for (Usuario u : app.listGlobalUsuarios) {
				for (Envio e : u.getListEnviosUsuario()) {
					if (e.getRepartidor() != null && e.getRepartidor().getIdRepartidor() == repartidor.getIdRepartidor() && e.getEstadoEnvio() == EstadoEnvio.EN_RUTA) {
						tieneEnRuta = true;
						break;
					}
				}
				if (tieneEnRuta) break;
			}
			if (!tieneEnRuta) {
				repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.DISPONIBLE);
			}
		}

		cargarEnviosEnCurso();
		cargarEnvios();
	}

	private void cargarEnvios() {
		if (app == null || repartidor == null) return;

		ObservableList<Envio> datos = FXCollections.observableArrayList();

		// Recorremos todos los usuarios y sus envíos para encontrar los que pertenecen a este repartidor
		for (Usuario u : app.listGlobalUsuarios) {
			List<Envio> envs = u.getListEnviosUsuario();
			for (Envio e : envs) {
				if (e.getRepartidor() != null && e.getRepartidor().getIdRepartidor() == repartidor.getIdRepartidor()) {
					datos.add(e);
				}
			}
		}

		tbEnvios.setItems(datos);

		colId.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getIdEnvio())));
		colFecha.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFechaCreacion() != null ? c.getValue().getFechaCreacion().toString() : "-"));
		colUsuario.setCellValueFactory(c -> {
			// Buscar el usuario que tiene la dirección destino
			String nombre = "Desconocido";
			for (Usuario u : app.listGlobalUsuarios) {
				if (u.getListDireccionesUsuario() != null) {
					if (u.getListDireccionesUsuario().contains(c.getValue().getDestino())) {
						nombre = u.getNombreCompleto();
						break;
					}
				}
			}
			return new SimpleStringProperty(nombre);
		});
		colDireccion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDestino() != null ? c.getValue().getDestino().getDescripcion() : "-"));
		colCosto.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPaquete() != null ? String.format("$%.2f", c.getValue().getPaquete().getPrecio()) : "$0.00"));
		colEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstadoEnvio() != null ? c.getValue().getEstadoEnvio().name() : "-"));
	}

	/** Genera PDF para el envío seleccionado en el historial */
	@FXML
	private void generarPdfHistorial() {
		Envio seleccionado = tbEnvios.getSelectionModel().getSelectedItem();
		if (seleccionado == null) {
			DialogUtils.mostrarError("Seleccione un envío del historial para generar el PDF.");
			return;
		}

		Usuario usuario = findUsuarioByEnvio(seleccionado);
		if (usuario == null) {
			DialogUtils.mostrarError("No se encontró el usuario receptor del envío seleccionado.");
			return;
		}

		try {
			String ruta = "reportesGenerador" + File.separator + "repartidores" + File.separator
					+ "reporte_envio_" + seleccionado.getIdEnvio() + ".pdf";
			File file = new File(ruta);
			file.getParentFile().mkdirs();

			PdfReporteEnvioUsuario pdf = new PdfReporteEnvioUsuario(usuario, seleccionado);
			pdf.generarPdf(ruta);
			DialogUtils.mostrarMensaje("PDF generado correctamente en: " + ruta);
		} catch (IOException ex) {
			ex.printStackTrace();
			DialogUtils.mostrarError("No se pudo generar el PDF: " + ex.getMessage());
		}
	}

	/** Genera PDF para el envío seleccionado en la pestaña 'En Curso' */
	@FXML
	private void generarPdfEnCurso() {
		Envio seleccionado = tbEnCurso.getSelectionModel().getSelectedItem();
		if (seleccionado == null) {
			DialogUtils.mostrarError("Seleccione un envío en curso para generar el PDF.");
			return;
		}

		Usuario usuario = findUsuarioByEnvio(seleccionado);
		if (usuario == null) {
			DialogUtils.mostrarError("No se encontró el usuario receptor del envío seleccionado.");
			return;
		}

		try {
			String ruta = "reportesGenerador" + File.separator + "repartidores" + File.separator
					+ "reporte_envio_" + seleccionado.getIdEnvio() + ".pdf";
			File file = new File(ruta);
			file.getParentFile().mkdirs();

			PdfReporteEnvioUsuario pdf = new PdfReporteEnvioUsuario(usuario, seleccionado);
			pdf.generarPdf(ruta);
			DialogUtils.mostrarMensaje("PDF generado correctamente en: " + ruta);
		} catch (IOException ex) {
			ex.printStackTrace();
			DialogUtils.mostrarError("No se pudo generar el PDF: " + ex.getMessage());
		}
	}

	/** Busca el usuario receptor correspondiente a un envio (por destino). */
	private Usuario findUsuarioByEnvio(Envio envio) {
		if (envio == null || envio.getDestino() == null) return null;
		for (Usuario u : app.listGlobalUsuarios) {
			if (u.getListDireccionesUsuario() != null) {
				for (Direccion d : u.getListDireccionesUsuario()) {
					if (envio.getDestino().getIdDireccion() == d.getIdDireccion()) {
						return u;
					}
				}
			}
		}
		return null;
	}

	@FXML
	private void volver() {
		// Cerrar ventana o volver a pantalla de sesión de repartidor
		if (app != null) {
			// Intentamos volver a la pantalla de sesión de repartidor
			app.openPantallaSesionRepartidor();
		} else {
			Stage stage = (Stage) tbEnvios.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	private void openAjustesRepartidor() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaRepartidorConfiguracion.fxml"));
			AnchorPane root = loader.load();
			PantallaRepartidorConfiguracionViewController ctrl = loader.getController();
			ctrl.setApp(this.app);
			ctrl.setRepartidor(this.repartidor);

			Stage ventana = new Stage();
			ventana.setTitle("Ajustes Repartidor");
			ventana.setScene(new Scene(root));
			ventana.initModality(Modality.WINDOW_MODAL);
			ventana.initOwner(this.tbEnvios.getScene().getWindow());
			ventana.setResizable(false);
			ventana.centerOnScreen();
			ventana.showAndWait();

			cargarEnviosEnCurso();
			cargarEnvios();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	private void cerrarSesionRepartidor() {
		if (app != null) {
			app.openPantallaBienvenida();
		}
	}
}
