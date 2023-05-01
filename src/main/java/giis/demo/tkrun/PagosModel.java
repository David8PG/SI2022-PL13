package giis.demo.tkrun;

import java.sql.Date;
import java.util.List;

import giis.demo.util.Database;

public class PagosModel {
	private Database bd = new Database();

	// SQL para ver todas las reservas de una instalacion
	public static final String get_pagos = "SELECT id_pago FROM pagos WHERE dni_clientes=?";

	public List<Object[]> getPagosCliente(String cliente) {

		return bd.executeQueryArray(get_pagos, cliente);
	}
	
	public static final String get_pagos2 = "SELECT id_pago FROM pagos WHERE dni_clientes=? AND fecha<=? AND fecha >=";

	public List<Object[]> getPagosCliente2(String cliente,Date fechaa, Date fechad) {

		return bd.executeQueryArray(get_pagos, cliente, fechaa,fechad);
	}

	public static final String get_reserva = "SELECT id_reservas FROM pagos WHERE id_pago=?";

	public String getReserva(String pago) {
		List<Object[]> l = bd.executeQueryArray(get_reserva, pago);
		if (l.isEmpty()) {
			return null;
		}
		return l.get(0)[0].toString();
	}

	public static final String elimina_pago = "DELETE from pagos WHERE id_pago=?;";

	public void eliminarPago(String id_pago) {
		bd.executeUpdate(elimina_pago, id_pago);
	}

	public static final String elimina_pago_reserva = "DELETE from pagos WHERE id_reservas=?;";

	public void eliminaPagoReserva(String id_reserva) {

		bd.executeUpdate(elimina_pago_reserva, id_reserva);
	}

	public static final String nuevo_pago = "INSERT INTO pagos (id_pago, fecha, dni_clientes, id_inscripciones, id_reservas) VALUES (?, ?, ?, ?, ?);";

	public void nuevoPago(String fecha, String cliente, String inscripcion, String reserva) {

		bd.executeUpdate(nuevo_pago, Long.toString(siguienteId()), fecha, cliente, inscripcion, reserva);
	}

	public static final String siguienteID = "SELECT MAX(id_pago) from pagos;";

	public long siguienteId() {
		List<Object[]> lista;
		lista = bd.executeQueryArray(siguienteID);
		return (long) lista.get(0)[0] + 1;
	}
	
	public static final String pago_inscripcion = "SELECT id_pago FROM pagos WHERE id_inscripciones=?";
	public boolean getPagoInscripcion(String inscripcion){
		
		List<Object[]> l = bd.executeQueryArray(pago_inscripcion, inscripcion);
		if (l.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static final String pago_reserva = "SELECT id_pago FROM pagos WHERE id_reservas=?";
	public boolean getPagoReserva(String reserva){
			
		List<Object[]> l = bd.executeQueryArray(pago_reserva, reserva);
		if (l.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static final String get_probando = "SELECT DISTINCT a.id_actividad FROM pagos p JOIN reservas r ON p.id_reservas = r.id_reserva JOIN actividades a ON r.actividad = a.id_actividad WHERE p.id_pago=?";

	public String getReservaActividad(String pago) {
		List<Object[]> l = bd.executeQueryArray(get_probando, pago);
		if (l.isEmpty()) {
			return null;
		}
		return l.get(0)[0].toString();
	}

	public static final String reserva_poractividad = "DELETE FROM pagos WHERE id_reservas IN (SELECT id_reserva FROM reservas WHERE actividad = ?)";

	public void eliminaPagoActividad(String id_actividad) {

		bd.executeUpdate(reserva_poractividad, id_actividad);
	}
}
