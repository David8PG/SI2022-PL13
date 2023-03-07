package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class InstalacionesModel {
	private Database bd = new Database();

	public static final String nombre_instalaciones = "SELECT nombre FROM instalaciones";

	public List<Object[]> getNombreInstalaciones() {
		return bd.executeQueryArray(nombre_instalaciones);
	}

	// Obtencion del id instalacion a partir del nombre de la misma
	public static final String id_instalacion = "SELECT id_instalacion FROM instalaciones WHERE nombre=";

	public List<Object[]> getId_Instalacion(String nombre_instalacion) {
		return bd.executeQueryArray(id_instalacion + "'" + nombre_instalacion + "'");
	}

	// método para obtener el precio de reserva de una instalación a partir de su
	// nombre
	public static final String precio_instalacion = "SELECT precio FROM instalaciones WHERE nombre=";

	public String getPrecio(String nombre_instalacion) {
		List<Object[]> lista_instalaciones;
		String precio;
		lista_instalaciones = bd.executeQueryArray(precio_instalacion + "'" + nombre_instalacion + "'");
		precio = lista_instalaciones.get(0)[0].toString();
		return precio;
	}

	public static final String deportes = "SELECT DISTINCT deporte FROM instalaciones";

	public List<Object[]> getDeportes() {
		return bd.executeQueryArray(deportes);
	}
}
