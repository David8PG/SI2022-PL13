package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class ColaModel {
	private Database bd = new Database();
	public static final String insert_cola = "INSERT INTO cola(id_cola, dni_clientes, id_actividades, fecha) VALUES (?, ?, ?, ?);";

	public void nuevaCola(String cliente, String actividad, String fecha) {
		long id;
		id = siguienteIdEspera();
		bd.executeUpdate(insert_cola, id, cliente, actividad, fecha);
	}

	public static final String siguienteID = "SELECT MAX(id_cola) from cola;";

	public long siguienteIdEspera() {
		List<Object[]> lista;
		lista = bd.executeQueryArray(siguienteID);
		return (long) lista.get(0)[0] + 1;
	}
}
