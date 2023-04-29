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

	public static final String cuota_actividad = "SELECT cuotaActividades FROM clientes WHERE dni=";

	public double getCuotaActividad(String dni) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(cuota_actividad + "'" + dni + "'");
		return (double) lista.get(0)[0];
	}

	public static final String nuevo_cliente = "INSERT INTO clientes (nombre,dni, id_socio, fecha_nacimiento, PagoPendiente, contraseña, cuotaInicial, cuotaReservas, cuotaActividades,telefono, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?,? ,?, ?);";

	public void nuevoCliente(String nombre, String dni, String telefono) {

		bd.executeUpdate(nuevo_cliente, nombre, dni, null, null, 0, null, null, null, null, telefono, null);
	}

//Metodo para obtener la contabilidad en un periodo de fechas en formato yyyy-MM-dd Falla contando la contabilidad de las actividades
	public List<Object[]> getContabilidadPeriodo(String fechaInicial, String fechaFin) {
		return bd.executeQueryArray(
				"SELECT clientes.nombre, clientes.dni, COALESCE(SUM(actividades.precio_socio), 0) AS sum_precio_socio, COALESCE(SUM(reservas.precio), 0) AS sum_precio_reservas, COALESCE(SUM(actividades.precio_socio), 0) + COALESCE(SUM(reservas.precio), 0) AS total FROM clientes LEFT JOIN inscripciones ON clientes.dni = inscripciones.dni_clientes LEFT JOIN actividades ON inscripciones.id_actividades = actividades.id_actividad LEFT JOIN reservas ON clientes.id_socio = reservas.id_socios WHERE (reservas.fecha_reserva BETWEEN "
						+ "'" + fechaInicial + "'" + " AND " + "'" + fechaFin + "'"
						+ " OR actividades.fecha_inicio BETWEEN " + "'" + fechaInicial + "'" + " AND " + "'" + fechaFin
						+ "'" + ") GROUP BY clientes.dni;");

	}

	// Metodo para obtener la contabilidad de las reservas
	public List<Object[]> getContabilidadReservas(String fechaInicial, String fechaFin) {
		return bd.executeQueryArray("SELECT c.nombre, c.dni, " + "COALESCE(SUM(CASE WHEN r.fecha_reserva BETWEEN " + "'"
				+ fechaInicial + "'" + " AND " + "'" + fechaFin + "'"
				+ " THEN r.precio ELSE 0 END), 0) AS precio_reserva " + "FROM clientes c "
				+ "LEFT JOIN reservas r ON c.id_socio = r.id_socios " + "WHERE (r.fecha_reserva BETWEEN " + "'"
				+ fechaInicial + "'" + " AND " + "'" + fechaFin + "'" + " ) " + "GROUP BY c.nombre, c.dni;");
	}

	// Metodo para obtener la contabilidad de las actividades
	public List<Object[]> getContabilidadActividades(String fechaInicial, String fechaFin) {
		return bd.executeQueryArray("SELECT c.nombre, c.dni, IFNULL(SUM(a.precio_socio), 0) AS total_precio_socio "
				+ "FROM clientes c" + " LEFT JOIN inscripciones i ON c.dni = i.dni_clientes "
				+ "LEFT JOIN actividades a ON i.id_actividades = a.id_actividad " + "WHERE ((a.fecha_inicio BETWEEN "
				+ "'" + fechaInicial + "'" + " AND " + "'" + fechaFin + "'" + " )and c.id_socio is not null) "
				+ "GROUP BY c.nombre, c.dni;");
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

	// Informe socios
	public List<Object[]> getInformeSocios(String fechaInicial, String fechaFin) {
		return bd.executeQueryArray(
				"SELECT c.nombre, COUNT(r.id_reserva) AS numeroReservas, SUM(r.precio) AS totalGastado, "
						+ "(SELECT i.nombre FROM instalaciones i WHERE i.id_instalacion = (SELECT id_instalaciones FROM reservas WHERE id_socios = c.id_socio GROUP BY id_instalaciones ORDER BY COUNT(*) DESC LIMIT 1)) AS instalacionMasReservada, "
						+ "((SELECT COUNT(*) FROM reservas WHERE id_socios = c.id_socio AND id_instalaciones = (SELECT id_instalaciones FROM reservas WHERE id_socios = c.id_socio GROUP BY id_instalaciones ORDER BY COUNT(*) DESC LIMIT 1)) / COUNT(r.id_reserva) * 100) AS porcentaje "
						+ "FROM clientes c " + "LEFT JOIN reservas r ON c.id_socio = r.id_socios "
						+ "WHERE r.fecha_reserva BETWEEN '" + fechaInicial + "' AND '" + fechaFin
						+ "' GROUP BY c.id_socio;");
	}
}
