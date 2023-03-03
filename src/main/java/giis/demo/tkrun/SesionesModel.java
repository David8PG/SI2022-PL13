package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class SesionesModel {
	private Database bd = new Database();
	
	public static final String siguienteUltID = "SELECT MAX(id_sesion) from sesiones;";
	public int siguienteIDSesion() {
		List<Object[]> listaID;
		listaID = bd.executeQueryArray(siguienteUltID);
		return (int)listaID.get(0)[0] + 1;
	}
	
	public static final String nueva_sesion = "INSERT INTO sesiones(id_sesion, dia, hora_inicio, hora_fin, id_actividades) VALUES (?, ?, ?, ?, ?);";
	public void nuevaSesion(String dia, String hora_ini, String hora_fin, int actividad) {
		int id;
		id = siguienteIDSesion();
		bd.executeUpdate(nueva_sesion, id, dia, hora_ini, hora_fin, actividad);
	}
}
