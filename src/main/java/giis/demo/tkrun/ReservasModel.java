package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class ReservasModel {

	private Database bd = new Database();

	// sentencia para mostrar todas las reservas de una instalación dada
	public static final String reservas_instalacion = "SELECT id_reserva FROM reservas WHERE id_instalaciones=";

	public List<Object[]> getReservasInstalacion(String nombre_instalacion) {
		return bd.executeQueryArray(reservas_instalacion, nombre_instalacion);
	}

	// sentencia para mostrar todas las reservas de una instalación dada en un
	// periodo dado
	public static final String reservasEnUnaInstalacion = "SELECT r.fecha_reserva, r.id_socios FROM reservas r INNER JOIN instalaciones i ON r.id_instalaciones = i.id_instalacion WHERE i.nombre =";

	public List<Object[]> getReservasInstalacionPeriodo(String nombre_instalacion, String fechaInicio,
			String fechaFin) {
		return bd.executeQueryArray(reservasEnUnaInstalacion + "'" + nombre_instalacion + "'" + " AND fecha_reserva <= "
				+ "'" + fechaFin + "'" + " AND fecha_reserva >= " + "'" + fechaInicio + "' ORDER BY fecha_reserva;");
	}

	// comprobación de disponibilidad de una instalación dada
	public static final String fecha_reserva = " AND fecha_reserva=";
	List<Object[]> aux;

	public boolean comprobarDisponibilidad(String nombre_instalacion, String fecha_hora) {
		// Se comprueba con dichas sentencias si la instalacion que queremos reservar en
		// cierta fecha está ya en la base
		aux = bd.executeQueryArray(
				reservas_instalacion + "'" + nombre_instalacion + "'" + fecha_reserva + "'" + fecha_hora + "'");
		if (aux.size() == 0) {
			return true; // disponible para reserva
		}
		return false; // reservada
	}

	// Sentencia para comprobar si una instalación está reservada por una actividad
	// para un socio
	public static final String reserva_actividad = " AND actividad!=0"; // Que exista la actividad en reserva
	public static final String reserva_socio = " AND id_socios is not null"; // Que un socio haya hecho la reserva
	List<Object[]> lActividad, lSocio;

	public int comprobarDisponibilidadActividad(String nombre_instalacion, String fecha_hora) {
		lActividad = bd.executeQueryArray(reservas_instalacion + "'" + nombre_instalacion + "'" + fecha_reserva + "'"
				+ fecha_hora + "'" + reserva_actividad);
		lSocio = bd.executeQueryArray(reservas_instalacion + "'" + nombre_instalacion + "'" + fecha_reserva + "'"
				+ fecha_hora + "'" + reserva_socio);
		if (lActividad.size() == 0 && lSocio.size() == 0) {
			return 0; // Se puede reservar
		} else if (lActividad.size() == 0)
			return 1; // Reservada por un cliente pero administrador tiene preferencia
		return -1; // No se puede realizar la reserva
	}

	// Método para instertar una nueva reserva sin id socio
	public static final String insertar_nueva_reserva = "INSERT INTO reservas (id_reserva, id_socios, id_instalaciones, fecha, fecha_reserva, precio, actividad) VALUES (?, ?, ?, ?, ?, ?, ?);";

	public void nuevaReserva(int id_socio, int id_instalacion, String fecha, String fecha_reserva, String precio,
			Object actividad) {
		long id_reserva;
		id_reserva = siguienteIdReserva();
		bd.executeUpdate(insertar_nueva_reserva, id_reserva, null, id_instalacion, fecha, fecha_reserva, precio,
				actividad);
	}

	public static final String insertar_nueva_reserva_socio = "INSERT INTO reservas (id_reserva, id_socios, id_instalaciones, fecha, fecha_reserva, precio, actividad) VALUES (?, ?, ?, ?, ?, ?, ?);";

	public void nuevaReservaSocio(int id_socio, int id_instalacion, String fecha, String fecha_reserva, String precio,
			Object actividad) {
		long id_reserva;
		id_reserva = siguienteIdReserva();
		bd.executeUpdate(insertar_nueva_reserva_socio, id_reserva, id_socio, id_instalacion, fecha, fecha_reserva,
				precio, actividad);
	}

	// Método para instertar una nueva reserva con actividad
	public static final String insertar_nueva_reserva_actividad = "INSERT INTO reservas (id_reserva, id_socios, id_instalaciones, fecha, fecha_reserva, precio, actividad) VALUES (?, ?, ?, ?, ?, ?, ?);";

	public void nuevaReservaAct(int id_socio, int id_instalacion, String fecha, String fecha_reserva, String precio,
			Object actividad) {
		long id_reserva;
		id_reserva = siguienteIdReserva();
		bd.executeUpdate(insertar_nueva_reserva, id_reserva, id_socio, id_instalacion, fecha, fecha_reserva, precio,
				actividad);
	}

	// Método para conseguir la siguiente reserva (por id)
	public static final String siguiente_id_reserva = "SELECT MAX(id_reserva) from reservas;";

	public long siguienteIdReserva() {
		List<Object[]> lReservas;
		lReservas = bd.executeQueryArray(siguiente_id_reserva);
		return (long) lReservas.get(0)[0] + 1;
	}

	// Método que elimina una reserva
	public static final String eliminar_reserva = "DELETE from reservas WHERE id_instalaciones=? AND fecha_reserva=?;";

	public void eliminarReserva(int id_instalacion, String fecha_reserva) {
		bd.executeUpdate(eliminar_reserva, id_instalacion, fecha_reserva);
	}

	// Método para cambiar la cuota de un cliente
	public static final String SQL_SUMA_CUOTA = "UPDATE clientes SET cuotaReservas=? WHERE (id_socio=?);";

	public void añadeacuota(double cuota, int id_socio) {
		// System.out.println("La cuota es"+cuota);
		bd.executeUpdate(SQL_SUMA_CUOTA, cuota, id_socio);
	}

	// Metodo para saber la cuota de reservas que tienen los socio
	public static final String SQL_CUOTA = "SELECT cuotaReservas from clientes WHERE (id_socio=?);";

	public double nuevaCuota(int id_socio) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(SQL_CUOTA, id_socio);
		return (double) lista.get(0)[0];

	}

	// Metodo para ver todas las reservas de un socio
	public static final String SQL_RESERVAS_SOCIO = "SELECT fecha_reserva FROM reservas WHERE ((id_socios=?) AND (id_instalaciones=?))";

	public List<Object[]> getListaReservasUsuario(int idsocio, String instalaciones) {
		return bd.executeQueryArray(SQL_RESERVAS_SOCIO, idsocio, instalaciones);
	}

	// SQL para ver todas las reservas de un socio
	public static final String SQL_RESERVAS_SOCIO_GENERAL = "SELECT id_socios FROM reservas WHERE ((id_socios= ?) AND (fecha_reserva >= ?)) ";

	public List<Object[]> getListaReservasUsuario_ampliada(int idsocio, String fecha) {
		return bd.executeQueryArray(SQL_RESERVAS_SOCIO_GENERAL, idsocio, fecha);
	}

	// SQL para ver todas las reservas de un socio
	public static final String SQL_RESERVAS_SOCIO_DIA = "SELECT fecha_reserva FROM reservas WHERE ((id_socios=?) AND (fecha_reserva >= ?) AND (fecha_reserva <= ?)) ";

	public List<Object[]> getListaReservasUsuario_ampliada2(int idsocio, String fecha, String fecha1) {
		return bd.executeQueryArray(SQL_RESERVAS_SOCIO_DIA, idsocio, fecha, fecha1);
	}

	// Método para instertar una nueva reserva
	public static final String SQL_NUEVA_RESERVA_SOCIO = "INSERT INTO reservas (id_reserva, id_socios, id_instalaciones, fecha, fecha_reserva, precio, actividad) VALUES (?, ?, ?, ?, ?, ?, ?);";

	public void nuevaReserva_ampliada(int socio, int instalacion, String fecha, String fecha_reserva, String precio,
			Object actividad) {
		long id;
		id = siguienteIdReserva();
		bd.executeUpdate(SQL_NUEVA_RESERVA_SOCIO, id, socio, instalacion, fecha, fecha_reserva, precio, actividad);
	}

	// Saber reservas de una instalacion
	public static final String SQL_RESERVAS_INSTALACIONES = "SELECT id_socios, fecha_reserva, actividad, id_reserva FROM reservas WHERE id_instalaciones=";

	public List<Object[]> getReservasInstalaciones(long id_instalacion) {
		return bd.executeQueryArray(SQL_RESERVAS_INSTALACIONES + "'" + id_instalacion + "'");
	}

	// Saber reserva de una actividad sin fecha
	public static final String SQL_ACTIVIDAD = "SELECT nombre FROM actividades WHERE id_actividad=";

	public List<Object[]> getActividad(long id_actividad) {
		return bd.executeQueryArray(SQL_ACTIVIDAD + id_actividad);
	}

	public static final String obtener_id_socio = "SELECT id_socios from reservas WHERE id_instalaciones=? AND fecha_reserva=?;";

	public long obtener_socio(int id_instalacion, String fecha_reserva) {
		List<Object[]> lSocios;
		lSocios = bd.executeQueryArray(obtener_id_socio, id_instalacion, fecha_reserva);
		return (long) lSocios.get(0)[0];
	}

	/*
	 * // Método para obtener el id del socio que hace una reserva public static
	 * final String obtener_id_socio =
	 * "SELECT id_socios from reservas WHERE id_instalaciones=? AND fecha_reserva=?;"
	 * ; public String obtener_socio(int id_instalacion, String fecha_reserva) {
	 * ResultSet resultado = (ResultSet) bd.executeQueryArray(obtener_id_socio,
	 * id_instalacion, fecha_reserva); String id_socio = ""; try { if
	 * (resultado.next()) { // Leer el valor de la columna "id_socios" del resultado
	 * id_socio = resultado.getString("id_socios"); } } catch (SQLException e) { //
	 * Manejar cualquier excepción que pueda ocurrir al leer el resultado
	 * e.printStackTrace(); } return id_socio; }
	 */
	// método para obtener todas las reservas de un socio
	public static final String todas_reservas = "SELECT id_reserva, id_socios,id_instalaciones, fecha_reserva, precio FROM reservas WHERE id_socios = ?";

	public List<Object[]> todasReservasSocio(int n) {
		return bd.executeQueryArray(todas_reservas, n);
	}

	public static final String eliminar_reserva_con_ID = "DELETE from reservas WHERE id_reserva = ?;";

	public void eliminarReserva(String id) {

		bd.executeUpdate(eliminar_reserva_con_ID, id);
	}

	public static final String existe_reserva = "SELECT id_instalaciones FROM reservas WHERE id_reserva = ? AND id_socios IS NOT NULL";

	public boolean existeReserva(int id) {
		List<Object[]> l = bd.executeQueryArray(existe_reserva, id);
		if (l.isEmpty())
			return false;
		return true;
	}

//Retorna una lista de objetos con el nombre de la instalación y la fecha de reserva en un periodo
	public List<Object[]> getReservasPeriodo(int id, String fechaInicial, String fechaFin) {
		return bd.executeQueryArray(
				"SELECT i.nombre, r.fecha_reserva FROM reservas r JOIN instalaciones i ON r.id_instalaciones = i.id_instalacion "
						+ "WHERE r.id_socios = " + id + " AND r.fecha_reserva > " + "'" + fechaInicial + "'"
						+ " AND r.fecha_reserva < " + "'" + fechaFin + "'");
	}

	public static final String get_cliente = "SELECT id_socios FROM reservas WHERE id_reserva=?";

	public Object getCliente(String id_reserva) {
		List<Object[]> lista = bd.executeQueryArray(get_cliente, id_reserva);
		return lista.get(0)[0];
	}

	public static final String get_precio = "SELECT precio FROM reservas WHERE id_reserva = ?";

	public double getPrecio(int id_reserva) {
		List<Object[]> l = bd.executeQueryArray(get_precio, id_reserva);
		return Double.parseDouble(l.get(0)[0].toString());
	}

	public static final String get_idreserva_fecha_instalacion = "SELECT id_reserva FROM reservas WHERE fecha_reserva=? AND id_instalaciones=?";

	public String get_idreserva_hora_instalacion(String fecha, String ninstalacion) {

		List<Object[]> lista = bd.executeQueryArray(get_idreserva_fecha_instalacion, fecha, ninstalacion);
		if (!lista.isEmpty()) {
			return lista.get(0)[0].toString();
		} else {
			throw new RuntimeException("No se encontraron resultados para la consulta.");
		}
	}

	public static final String obtener_id_socio2 = "SELECT id_socios from reservas WHERE id_instalaciones=? AND fecha_reserva=?;";

	public long obtener_socio2(int id_instalacion, String fecha_reserva) {
		List<Object[]> listaSocios = bd.executeQueryArray(obtener_id_socio2, id_instalacion, fecha_reserva);
		if (!listaSocios.isEmpty()) {
			return (long) listaSocios.get(0)[0];
		} else {
			throw new RuntimeException("No se encontraron resultados para la consulta.");
		}
	}

	// Obtencion de la actividad de una reserva
	public static final String obtener_actividad_instalacion = "SELECT actividad FROM reservas WHERE fecha_reserva=? AND id_instalaciones=?";

	public String getActividadReserva(String fechareserva, String instalacion) {

		List<Object[]> l = bd.executeQueryArray(obtener_actividad_instalacion, fechareserva, instalacion);
		return l.get(0)[0].toString();
	}

	// Obtencion del número de socio de una reserva
	public static final String obtener_socio_res_inst = "SELECT id_socios FROM reservas WHERE fecha_reserva=? AND id_instalaciones=?";

	public String getSocioDef(String fechareserva, String instalacion) {

		List<Object[]> lista = bd.executeQueryArray(obtener_socio_res_inst, fechareserva, instalacion);
		return lista.get(0)[0].toString();
	}

	public static final String todo_socio = "SELECT id_reserva, id_socios, id_instalaciones, fecha, fecha_reserva, precio, actividad FROM reservas WHERE id_socios= ? AND fecha>=? AND fecha<=? ORDER BY fecha DESC";

	public List<Object[]> getReservasSocioTodo(int persona, String ini, String fin) {

		return bd.executeQueryArray(todo_socio, persona, ini, fin);
	}

	// InformeInstalaciones:
	public List<Object[]> getInformeInstalaciones(int numeroHorasDisponibles, String fechaInicial, String fechaFin) {
		return bd.executeQueryArray(
				"SELECT i.nombre as nombre_instalacion, SUM(CASE WHEN r.actividad = 0 THEN 1 ELSE 0 END) as Reservas, "
						+ "SUM(CASE WHEN r.actividad != 0 THEN 1 ELSE 0 END) as Actividades, "
						+ "ROUND((SUM(CASE WHEN r.actividad = 0 THEN 1 ELSE 0 END) + SUM(CASE WHEN r.actividad <> 0 THEN 1 ELSE 0 END)) / "
						+ numeroHorasDisponibles + " * 100, 2) AS Porcentaje "
						+ "FROM reservas r JOIN instalaciones i ON r.id_instalaciones = i.id_instalacion WHERE r.fecha_reserva BETWEEN '"
						+ fechaInicial + "' AND '" + fechaFin + "' GROUP BY i.nombre;");
	}

	// Eliminar una reserva por actividad
	public static final String reservas_actividad = "DELETE from reservas WHERE actividad = ?;";

	public void eliminarReservaPorActividad(String id_actividad) {

		bd.executeUpdate(reservas_actividad, id_actividad);
	}
	
	public static final String fechas_por_socio = "SELECT COUNT(r.id_reserva) AS numeroReservas FROM clientes c LEFT JOIN reservas r ON c.id_socio = r.id_socios WHERE (r.fecha_reserva BETWEEN ? AND ? AND (c.id_socio=?)) GROUP BY c.id_socio";

	// horas reservadas para un socio entre dos fechas (la última tendrá que ser la
	// fecha de los dias de antelación)
	public int horas_reservadas(String fecha_inicio, String fecha_antelacion_parametrizable, String idSocio) {
		List<Object[]> lista = bd.executeQueryArray(fechas_por_socio, fecha_inicio, fecha_antelacion_parametrizable,
				idSocio);
		if (lista.size() > 0) {
			return Integer.parseInt(lista.get(0)[0].toString());
		} else {
			return 0;
		}
	}
}