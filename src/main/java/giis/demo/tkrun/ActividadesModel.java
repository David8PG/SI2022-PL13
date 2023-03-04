package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class ActividadesModel {
	private Database bd = new Database();
	
	// Obtencion del id de actividad
	public static final String id_actividad = "SELECT id_actividad FROM actividades WHERE nombre=";
	public long getIdActividad(String nombre_actividad) {
		List<Object[]> lIds_actividad;
		lIds_actividad = bd.executeQueryArray(id_actividad + "'" + nombre_actividad + "'");
		return (long) lIds_actividad.get(0)[0];
	}
	
	public static final String siguienteUltID = "SELECT MAX(id_actividad) from actividades;";
	public int siguienteIdActividad() {
		List<Object[]> listaID;
		listaID = bd.executeQueryArray(siguienteUltID);
		return (int)listaID.get(0)[0]+1;
	}
	public static final String nueva_actividad = "INSERT INTO actividades(id_actividad, nombre, descripcion, aforo, plazas_disponibles, precio_socio, precio_no_socio, fecha_inicio, fecha_fin, deporte , id_instalaciones, id_periodo_inscripciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public void nuevaActividad(String nombre, String descripcion, String aforo, String precio_socio, String precio_no_socio, String fecha_inicio, String fecha_fin, String deporte, String id_instalaciones, String id_periodo_inscripciones) {
		long id;
		id = siguienteIdActividad();
		bd.executeUpdate(nueva_actividad,id, nombre, descripcion, aforo, aforo, precio_socio, precio_no_socio, fecha_inicio, fecha_fin, deporte, id_instalaciones, id_periodo_inscripciones);
	}
	public int nuevaActividadConId(String nombre, String descripcion, String aforo, String precio_socio, String precio_no_socio, String fecha_inicio, String fecha_fin, String deporte, String id_instalaciones, String id_periodo_inscripciones) {
		int id;
		id = siguienteIdActividad();
		bd.executeUpdate(nueva_actividad,id, nombre, descripcion, aforo, aforo, precio_socio, precio_no_socio, fecha_inicio, fecha_fin, deporte, id_instalaciones, id_periodo_inscripciones);
		return id;
	}
	
	// Obtención de los nombres de todas las actividades
	public static final String nombres_actividades = "SELECT nombre FROM actividades";
	public List<Object[]> getNombreActividades(){
		return bd.executeQueryArray(nombres_actividades);}
}
