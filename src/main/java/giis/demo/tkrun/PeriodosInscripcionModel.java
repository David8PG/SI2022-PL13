package giis.demo.tkrun;


import java.util.List;

import giis.demo.util.Database;

public class PeriodosInscripcionModel {
	
	private Database bd = new Database();
	
	public static final String SQL_ID_SIGUIENTE = "SELECT MAX(id_periodo_inscripcion) from periodos_inscripcion;";
	public int SiguienteId() {
		List<Object[]> l;
		l = bd.executeQueryArray(SQL_ID_SIGUIENTE);
		return (int)l.get(0)[0] + 1;
	}
	
	public static final String SQL_NUEVO_PERIODO_INSCRIPCION = 
			"INSERT INTO periodos_inscripcion(id_periodo_inscripcion, nombre_periodo, descripcion, fecha_inicio_socio, fecha_fin_socio, fecha_fin_no_socio) VALUES (?, ?, ?, ?, ?, ?);";
	public void nuevoPeriodoInscripcion(String nombre, String descripcion,String FechaInicioSocio1, String FechaFinSocio1, String FechaFinNoSocio1) {
		int id;
		id = SiguienteId();
		bd.executeUpdate(SQL_NUEVO_PERIODO_INSCRIPCION,id, nombre, descripcion,FechaInicioSocio1,FechaFinSocio1,FechaFinNoSocio1);
	}
	
	public static final String SQL_GET_PERIODOS = "SELECT nombre FROM periodos_inscripcion";
	public List<Object[]> getPeriodosInscripcion(){
		return bd.executeQueryArray(SQL_GET_PERIODOS );
	}
	
	public static final String SQL_GET_FECHAS = "SELECT fecha_inicio_socio, fecha_fin_socio, fecha_fin_no_socio FROM periodos_inscripcion WHERE nombre=";
	public List<Object[]> getFechas(String nombre){
		return bd.executeQueryArray(SQL_GET_FECHAS + "'" + nombre + "'" );	
	}

	public static final String getID_periodo = "SELECT id_periodo_inscripcion FROM periodos_inscripcion WHERE nombre=";
	public List<Object[]> getIdPeriodoIns(String nombre){
		return bd.executeQueryArray(getID_periodo+"'"+nombre+"'");	
	}

}
