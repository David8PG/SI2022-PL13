package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class ColaModel {
	private Database bd = new Database();
	public static final String insert_cola = "INSERT INTO cola(id_cola, dni_clientes, id_actividades, fecha,socio) VALUES (?, ?, ?, ?,?);";

	public void nuevaCola(String cliente, String actividad, String fecha, String socio) {
		long id;
		id = siguienteIdEspera();
		bd.executeUpdate(insert_cola, id, cliente, actividad, fecha, socio);
	}

	public static final String siguienteID = "SELECT MAX(id_cola) from cola;";

	public long siguienteIdEspera() {
		List<Object[]> lista;
		lista = bd.executeQueryArray(siguienteID);
		return (long) lista.get(0)[0] + 1;
	}

	public static final String eliminar_esperas = "DELETE FROM cola WHERE id_actividades=?";

	public void eliminarEsperas(long actividad) {
		bd.executeUpdate(eliminar_esperas, actividad);
	}

	public static final String eliminar_esperas2 = "DELETE FROM cola WHERE id_actividades=? AND dni_clientes=?";

	public void eliminarEsperas2(String string, String cliente) {
		bd.executeUpdate(eliminar_esperas2, string, cliente);
	}

	public static final String esperas_socio = "SELECT id_actividades, dni_clientes, fecha, socio FROM cola WHERE id_actividades=?  ORDER BY socio DESC,fecha ASC";

	public List<Object[]> getEsperasSocio(String actividad) {
		return bd.executeQueryArray(esperas_socio, actividad);
	}

	public static final String esperas_socio2 = "SELECT id_actividades, dni_clientes, fecha, socio FROM cola WHERE id_actividades > 0 ORDER BY id_actividades ASC,socio DESC,fecha ASC";

	public List<Object[]> getEsperasSocio2() {
		return bd.executeQueryArray(esperas_socio2);
	}

	public static final String cliente_actividad = "SELECT id_cola FROM cola WHERE id_actividades=? AND dni_clientes=?";

	public boolean personaActividadEsperas(long actividad, String cliente) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(cliente_actividad, actividad, cliente);
		if (lista.size() == 0) {
			return false;
		} else
			return true;
	}
}
