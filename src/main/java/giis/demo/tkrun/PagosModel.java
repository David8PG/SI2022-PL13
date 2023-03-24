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
}
