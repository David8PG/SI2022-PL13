package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class ActividadesModel {
	private Database bd = new Database();

	public static final String siguienteUltID = "SELECT MAX(id_actividad) from actividades;";

	public long siguienteIdActividad() {
		List<Object[]> listaID;
		listaID = bd.executeQueryArray(siguienteUltID);
		return (long) listaID.get(0)[0] + 1;
	}

	public static final String nueva_actividad = "INSERT INTO actividades(id_actividad, nombre, descripcion, aforo, plazas_disponibles, precio_socio, precio_no_socio, fecha_inicio, fecha_fin, deporte , id_instalaciones, id_periodo_inscripciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public void nuevaActividad(String nombre, String descripcion, String aforo, String plazas, String precio_socio,
			String precio_no_socio, String fecha_inicio, String fecha_fin, String deporte, String id_instalaciones,
			String id_periodo_inscripciones) {
		long id;
		id = siguienteIdActividad();
		bd.executeUpdate(nueva_actividad, id, nombre, descripcion, aforo, plazas, precio_socio, precio_no_socio,
				fecha_inicio, fecha_fin, deporte, id_instalaciones, id_periodo_inscripciones);
	}

	public long nuevaActividadConId(String nombre, String descripcion, String aforo, String plazas, String precio_socio,
			String precio_no_socio, String fecha_inicio, String fecha_fin, String deporte, String id_instalaciones,
			String id_periodo_inscripciones) {
		long id;
		id = siguienteIdActividad();
		bd.executeUpdate(nueva_actividad, id, nombre, descripcion, aforo, plazas, precio_socio, precio_no_socio,
				fecha_inicio, fecha_fin, deporte, id_instalaciones, id_periodo_inscripciones);
		return id;
	}
}
