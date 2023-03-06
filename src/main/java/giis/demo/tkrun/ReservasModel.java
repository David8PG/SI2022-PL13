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
	public static final String reservasEnUnaInstalacion = "SELECT r.id_reserva, r.id_socios, r.fecha_reserva FROM reservas r INNER JOIN instalaciones i ON r.id_instalaciones = i.id_instalacion WHERE i.nombre =";

	public List<Object[]> getReservasInstalacionPeriodo(String nombre_instalacion, String fechaInicio,
			String fechaFin) {
		return bd.executeQueryArray(reservasEnUnaInstalacion + "'" + nombre_instalacion + "'" + " AND fecha_reserva <= "
				+ "'" + fechaFin + "'" + " AND fecha_reserva >= " + "'" + fechaInicio + "'");
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

	public void nuevaReserva(int id_socio, int id_instalacion, String fecha, String fecha_reserva, String precio, Object actividad) {
		long id_reserva;
		id_reserva = siguienteIdReserva();
		bd.executeUpdate(insertar_nueva_reserva, id_reserva, null, id_instalacion, fecha, fecha_reserva, precio, actividad);
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
}
