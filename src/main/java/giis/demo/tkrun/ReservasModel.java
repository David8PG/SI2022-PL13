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

	
	//Método para cambiar la cuota de un cliente
	public static final String SQL_SUMA_CUOTA = "UPDATE clientes SET cuotaReservas=? WHERE (id_socios=?);";
	public void añadeacuota(double cuota, int id_socio) {	
		//System.out.println("La cuota es"+cuota);
		bd.executeUpdate(SQL_SUMA_CUOTA,cuota, id_socio);
	}


	//Metodo para saber la cuota de reservas que tienen los socio
	public static final String SQL_CUOTA = "SELECT cuotaReservas from clientes WHERE (id_socios=?);";
	public double nuevaCuota(int id_socio) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(SQL_CUOTA, id_socio);
		return (double) lista.get(0)[0];

	}

	//Metodo para ver todas las reservas de un socio
	public static final String SQL_RESERVAS_SOCIO = "SELECT fecha_reserva FROM reservas WHERE ((id_socios=?) AND (id_instalaciones=?))";
	public List<Object[]> getListaReservasUsuario(int idsocio, String instalaciones){
		return bd.executeQueryArray(SQL_RESERVAS_SOCIO, idsocio, instalaciones);
	}

	//SQL para ver todas las reservas de un socio
	public static final String SQL_RESERVAS_SOCIO_GENERAL = "SELECT id_socios FROM reservas WHERE ((id_socios= ?) AND (fecha_reserva >= ?)) ";
	public List<Object[]> getListaReservasUsuario_ampliada(int idsocio, String fecha){
		return bd.executeQueryArray(SQL_RESERVAS_SOCIO_GENERAL, idsocio, fecha);
	}

	//SQL para ver todas las reservas de un socio
	public static final String SQL_RESERVAS_SOCIO_DIA = "SELECT fecha_reserva FROM reservas WHERE ((id_socios=?) AND (fecha_reserva >= ?) AND (fecha_reserva <= ?)) ";
	public List<Object[]> getListaReservasUsuario_ampliada2(int idsocio, String fecha, String fecha1){
		return bd.executeQueryArray(SQL_RESERVAS_SOCIO_DIA, idsocio, fecha, fecha1);
	}

	//Método para instertar una nueva reserva
	public static final String SQL_NUEVA_RESERVA_SOCIO = "INSERT INTO reservas (id_reserva, id_socios, id_instalaciones, fecha, fecha_reserva, precio, actividad) VALUES (?, ?, ?, ?, ?, ?, ?);";
	public void nuevaReserva_ampliada(int socio, int instalacion, String fecha, String fecha_reserva, String precio, Object actividad) {
		long id;
		id = siguienteIdReserva();
		bd.executeUpdate(SQL_NUEVA_RESERVA_SOCIO, id, socio, instalacion, fecha, fecha_reserva, precio, actividad);
	}

	//Saber reservas de una instalacion
	public static final String SQL_RESERVAS_INSTALACIONES= "SELECT id_socios, fecha_reserva, actividad, id_reserva FROM reservas WHERE id_instalaciones=";
	public List<Object[]> getReservasInstalaciones(long id_instalacion){
		return bd.executeQueryArray(SQL_RESERVAS_INSTALACIONES+ "'"+id_instalacion+"'");
	}


	//Saber reserva de una actividad sin fecha
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
}
