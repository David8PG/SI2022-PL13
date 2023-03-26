package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class PagosModel {
	private Database bd = new Database();

	// SQL para ver todas las reservas de una instalacion
	public static final String get_pagos = "SELECT id_pago FROM pagos WHERE dni_clientes=?";

	public List<Object[]> getPagosCliente(String cliente) {

		return bd.executeQueryArray(get_pagos, cliente);
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

	public void nuevoPago(String fecha, String persona, String inscripcion, String reserva) {

		bd.executeUpdate(nuevo_pago, Long.toString(siguienteId()), fecha, persona, inscripcion, reserva);
	}

	public static final String siguienteID = "SELECT MAX(id_pago) from pagos;";

	public long siguienteId() {
		List<Object[]> lista;
		lista = bd.executeQueryArray(siguienteID);
		return (long) lista.get(0)[0] + 1;
	}
}
