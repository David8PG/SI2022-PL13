package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class SesionesModel {
	private Database bd = new Database();

	public static final String siguienteUltID = "SELECT MAX(id_sesion) from sesiones;";

	public long siguienteIDSesion() {
		List<Object[]> listaID;
		listaID = bd.executeQueryArray(siguienteUltID);
		return (long) listaID.get(0)[0] + 1;
	}

	public static final String nueva_sesion = "INSERT INTO sesiones(id_sesion, dia, hora_inicio, hora_fin, id_actividades) VALUES (?, ?, ?, ?, ?);";

	public void nuevaSesion(String dia, String hora_ini, String hora_fin, long idActividad) {
		long id;
		id = siguienteIDSesion();
		bd.executeUpdate(nueva_sesion, id, dia, hora_ini, hora_fin, idActividad);
	}

	public static final String sesiones_actividad = "SELECT dia, hora_inicio, hora_fin FROM sesiones WHERE id_actividades='";

	public List<Object[]> getSesiones(long l) {
		return bd.executeQueryArray(sesiones_actividad + l + "'");
	}
	
	public List<Object[]> getSesiones2(String l) {
		return bd.executeQueryArray(sesiones_actividad + l + "'");
	}
}
