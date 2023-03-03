package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class ReservasModel {
	
	private Database bd = new Database();

	// sentencia para mostrar todas las reservas de una instalación dada
	public static final String reservas_instalacion = "SELECT id_reserva FROM reservas WHERE instalacion=";
	public List<Object[]> getReservasInstalacion(String nombre_instalacion) {
		return bd.executeQueryArray(reservas_instalacion, nombre_instalacion);
	}

	// comprobación de disponibilidad de una instalación dada
	public static final String fecha_reserva = " AND fecha_reserva=";
	List<Object[]> aux;
	public boolean comprobarDisponibilidad(String nombre_instalacion, String fecha_hora) {
		// Se comprueba con dichas sentencias si la instalacion que queremos reservar en cierta fecha está ya en la base
		aux = bd.executeQueryArray(reservas_instalacion + "'" + nombre_instalacion + "'" + fecha_reserva + "'" + fecha_hora + "'");
		if (aux.size() == 0) {
			return true; // disponible para reserva
		}
		return false; // reservada
	}
	
	// Método para instertar una nueva reserva
	public static final String insertar_nueva_reserva = "INSERT INTO reservas (id_reserva, id_socios, id_instalaciones, fecha_inscripcion, fecha_reserva, precio, actividad) VALUES (?, ?, ?, ?, ?, ?, ?);";
	public void nuevaReserva(int socio, int instalacion, String fecha, String fecha_reserva, String precio, Object actividad) {
		long id_reserva;
		id_reserva = siguienteIdReserva();
		bd.executeUpdate(insertar_nueva_reserva, id_reserva, null, instalacion, fecha, fecha_reserva, precio, actividad);
	}
	
	// Método para conseguir la siguiente reserva (por id)
	public static final String siguiente_id_reserva = "SELECT MAX(id_reserva) from reservas;";
	public long siguienteIdReserva() {
		List<Object[]> lReservas;
		lReservas = bd.executeQueryArray(siguiente_id_reserva);
		return (long) lReservas.get(0)[0] + 1;
	}
}
