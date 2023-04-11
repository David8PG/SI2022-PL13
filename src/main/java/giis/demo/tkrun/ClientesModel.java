package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class ClientesModel {

	private Database bd = new Database();

	// Método que devuelve todos los ids de los socios
	public static final String todos_ids_socios = "SELECT id_socio FROM clientes WHERE id_socio IS NOT NULL";

	public List<Object[]> getIdTodos() {
		return bd.executeQueryArray(todos_ids_socios);
	}

	public static final String todos_dni_socios = "SELECT dni FROM clientes WHERE id_socio IS NOT NULL";

	public List<Object[]> getDNITodos() {
		return bd.executeQueryArray(todos_dni_socios);
	}

	// Método que comprueba la existencia de un id para ver si es válido
	public static final String id_valido = "SELECT id_socio FROM clientes WHERE id_socio =";

	public boolean validarId(String id_socio) {
		List<Object[]> lista_ids;
		lista_ids = bd.executeQueryArray(id_valido + "'" + id_socio + "'");
		if (lista_ids.size() == 0) {
			return false;
		}
		return true;
	}

	// Obtención de la contraseña de los socios
	public static final String contraseña_socios = "SELECT contraseña FROM clientes WHERE id_socio=";

	public List<Object[]> getContraseña(String id_socio) {
		return bd.executeQueryArray(contraseña_socios + "'" + id_socio + "';");
	}

	public static final String get_DNI = "SELECT dni FROM clientes WHERE id_socio =";

	public String getDNI(String id) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(get_DNI + "'" + id + "'");
		return lista.get(0)[0].toString();
	}

	public static final String get_id = "SELECT id_socio FROM clientes WHERE dni =";

	public int getID(String id) {

		List<Object[]> lista;
		lista = bd.executeQueryArray(get_id + "'" + id + "'");
		return Integer.parseInt(lista.get(0)[0].toString());
	}

	public static final String existe_DNI = "SELECT dni FROM clientes WHERE dni=";

	public boolean existeDNI(String dni) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(existe_DNI + "'" + dni + "'");
		if (lista.size() == 0) {
			return false;
		}
		return true;
	}

	public static final String nuevo_cliente = "INSERT INTO clientes (nombre,dni, id_socio, fecha_nacimiento, PagoPendiente, contraseña, cuotaInicial, cuotaReservas, cuotaActividades,telefono, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?,? ,?, ?);";

	public void nuevoCliente(String nombre, String dni, String telefono) {

		bd.executeUpdate(nuevo_cliente, nombre, dni, null, null, 0, null, null, null, null, telefono, null);
	}
	
	public static final String añadir_cuota_reserva = "UPDATE clientes SET cuotaReservas=? WHERE (id_socio=?);";
	public void añadirCuotaReserva(double cuota, int id_socio) {
		// System.out.println("La cuota es"+cuota);
		bd.executeUpdate(añadir_cuota_reserva, cuota, id_socio);
	}
	
	public static final String añadir_cuota_actividad = "UPDATE clientes SET cuotaActividades=? WHERE (id_socio=?);";
	public void añadirCuotaActividad(double cuota, int id_socio) {
		// System.out.println("La cuota es"+cuota);
		bd.executeUpdate(añadir_cuota_actividad, cuota, id_socio);
	}
}
