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

	// metodo que devuelve el siguiente id actividad disponible
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

	// Obtención de los nombres de todas las actividades
	public static final String nombres_actividades = "SELECT nombre FROM actividades";

	public List<Object[]> getNombreActividades() {
		return bd.executeQueryArray(nombres_actividades);
	}

	/*
	 * // listar actividades de una instalacion en una fecha determinada public
	 * static final String actividades_fecha_det=
	 * "SELECT id_actividad FROM actividades WHERE fecha_inicio =";
	 * 
	 * public List<Object[]> pruebarara(int id_instalacion, String fecha_reserva) {
	 * return bd.executeQueryArray(actividades_fecha_det + "'" + fecha_reserva + "'"
	 * + " AND id_instalaciones = " + "'" + id_instalacion + "'"); }
	 */

	// Listar actividades en un periodo
	public static final String actividadesPeriodo = "SELECT id_actividad, deporte, plazas_disponibles, fecha_inicio, fecha_fin, aforo, precio_socio, precio_no_socio FROM actividades WHERE fecha_inicio >=";

	public List<Object[]> getActividadesPeriodo(String fechaInicial, String fechaFin) {
		return bd.executeQueryArray(
				actividadesPeriodo + "'" + fechaInicial + "'" + " AND fecha_inicio <= '" + fechaFin + "';");
	}

	public static final String precioActividadNoSocio = "SELECT precio_no_socio FROM actividades WHERE id_actividad=";

	public String getPrecioActividadNoSocio(String id) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(precioActividadNoSocio + "'" + id + "'");
		return lista.get(0)[0].toString();
	}

	public static final String plazasActividad = "SELECT plazas_disponibles FROM actividades WHERE nombre=";

	public String getPlazasActividad(String nombre) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(plazasActividad + "'" + nombre + "'");
		return lista.get(0)[0].toString();
	}

	public static final String aforo = "SELECT aforo FROM actividades WHERE nombre=";

	public String getAforo(String nombre) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(aforo + "'" + nombre + "'");
		return lista.get(0)[0].toString();
	}

	public static final String restarPlazas = "UPDATE actividades SET plazas_disponibles=? WHERE nombre=?";

	public void restarPlaza(String nombre) {
		int plazas = Integer.parseInt(getPlazasActividad(nombre)) - 1;
		bd.executeUpdate(restarPlazas, plazas, nombre);
	}

	public static final String fechaInicio = "SELECT fecha_inicio FROM actividades WHERE nombre=";

	public String getFechaInicioActividad(String nombre) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(fechaInicio + "'" + nombre + "'");
		return lista.get(0)[0].toString();
	}

	public static final String fechaFinal = "SELECT fecha_fin FROM actividades WHERE nombre=";

	public String getFechaFinActividad(String nombre) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(fechaFinal + "'" + nombre + "'");
		return lista.get(0)[0].toString();
	}

	public static final String getDescripcionActividad = "SELECT descripcion FROM actividades WHERE nombre=";

	public String getDescripcion(String nombre) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(getDescripcionActividad + "'" + nombre + "'");
		return lista.get(0)[0].toString();
	}

	public static final String actividadesPeriodoInscripcion = "SELECT nombre FROM actividades WHERE id_periodo_inscripciones=";

	public List<Object[]> getActividadesPeriodoInscripcion(String periodo) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(actividadesPeriodoInscripcion + "'" + periodo + "'");
		return lista;
	}

	public static final String precioActividadNoSocio2 = "SELECT precio_no_socio FROM actividades WHERE nombre=";

	public String getPrecioActividadNoSocio2(String nombre) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(precioActividadNoSocio2 + "'" + nombre + "'");
		return lista.get(0)[0].toString();
	}

	public static final String get_instalacion = "SELECT id_instalaciones FROM actividades WHERE nombre=";

	public String getInstalacion(String nombre) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(get_instalacion + "'" + nombre + "'");
		return lista.get(0)[0].toString();
	}

	public static final String nombre_actividad_porID = "SELECT nombre FROM actividades WHERE id_actividad=";

	public String getNombreActividad_porID(String id_actividad) {
		List<Object[]> lista = bd.executeQueryArray(nombre_actividad_porID + "'" + id_actividad + "'");
		if (!lista.isEmpty()) {
			return lista.get(0)[0].toString();
		} else {
			throw new RuntimeException("No se encontraron resultados para la consulta.");
		}
	}

	public static final String nombre_instalacion = "SELECT id_instalaciones FROM actividades WHERE nombre=";

	public String getInstalacionActividad(String nombre) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(nombre_instalacion + "'" + nombre + "'");
		return lista.get(0)[0].toString();
	}

	public static final String idActividades = "SELECT id_actividad FROM actividades WHERE nombre=";

	public String getListaIdsActividades(String nombreActividad) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(idActividades + "'" + nombreActividad + "'");
		return lista.get(0)[0].toString();
	}

	public String getNombreActividad_porID2(Object id_actividad) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(idActividades + "'" + id_actividad + "'");
		return lista.get(0)[0].toString();
	}

	public String getNombreActPorID(String id_actividad) {
		List<Object[]> lista = bd.executeQueryArray(nombre_actividad_porID + "'" + id_actividad + "'");
		if (!lista.isEmpty()) {
			return lista.get(0)[0].toString();
		} else {
			return null;
		}
	}

}