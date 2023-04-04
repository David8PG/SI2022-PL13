package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class PeriodosInscripcionModel {

	private Database bd = new Database();

	public static final String siguiente_id = "SELECT MAX(id_periodo_inscripcion) from periodos_inscripcion;";

	public long SiguienteId() {

		List<Object[]> l;
		l = bd.executeQueryArray(siguiente_id);
		return (long) l.get(0)[0] + 1;
	}

	public static final String nuevo_periodo = "INSERT INTO periodos_inscripcion(id_periodo_inscripcion, nombre_periodo, descripcion, fecha_inicio_socio, fecha_fin_socio, fecha_fin_no_socio) VALUES (?, ?, ?, ?, ?, ?);";

	public void nuevoPeriodoInscripcion(String nombre, String descripcion, String FechaInicioSocio1,
			String FechaFinSocio1, String FechaFinNoSocio1) {
		long id;
		id = SiguienteId();
		bd.executeUpdate(nuevo_periodo, id, nombre, descripcion, FechaInicioSocio1, FechaFinSocio1, FechaFinNoSocio1);
	}

	public static final String obtener_periodos = "SELECT nombre_periodo FROM periodos_inscripcion";

	public List<Object[]> getPeriodosInscripcion() {
		return bd.executeQueryArray(obtener_periodos);
	}

	public static final String obtener_fechas = "SELECT fecha_inicio_socio, fecha_fin_socio, fecha_fin_no_socio FROM periodos_inscripcion WHERE nombre_periodo=";

	public List<Object[]> getFechas(String nombre) {
		return bd.executeQueryArray(obtener_fechas + "'" + nombre + "'");
	}

	public static final String getID_periodo = "SELECT id_periodo_inscripcion FROM periodos_inscripcion WHERE nombre_periodo=";

	public List<Object[]> getIdPeriodoIns(String nombre) {
		return bd.executeQueryArray(getID_periodo + "'" + nombre + "'");
	}

	public static final String getFechasNoSocio = "SELECT id_periodo_inscripcion FROM periodos_inscripcion WHERE fecha_fin_socio<=? AND fecha_fin_no_socio>=?";

	public List<Object[]> getFechasNoSocio(String fecha) {
		return bd.executeQueryArray(getFechasNoSocio, fecha, fecha);
	}

	public static final String plazo_abierto_socio = "SELECT id_periodo_inscripcion FROM periodos_inscripcion WHERE fecha_inicio_socio<=? AND fecha_fin_socio>=?";

	public List<Object[]> getFechasAbiertasSocio(String fecha){
		return bd.executeQueryArray(plazo_abierto_socio,fecha,fecha);	
	}

	public static final String fecha_fin_socio = "SELECT fecha_fin_socio FROM periodos_inscripcion WHERE id_periodo_inscripcion=?";

	public String getFechaFinSocio(int id){
		List<Object[]> lista;
		lista = bd.executeQueryArray(fecha_fin_socio,id);
		return lista.get(0)[0].toString() ;
	
	}

}
