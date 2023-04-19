package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class InscripcionesModel {
	private Database bd = new Database();

	public static final String cliente_actividad = "SELECT id_inscripcion FROM inscripciones WHERE id_actividades=? AND dni_clientes=?";

	public boolean clienteInscripcionActividad(long actividad, String cliente) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(cliente_actividad, actividad, cliente);
		if (lista.size() == 0) {
			return false;
		} else
			return true;
	}

	public static final String nueva_inscripcion = "INSERT INTO inscripciones(id_inscripcion, dni_clientes, id_actividades, fecha) VALUES (?, ?, ?, ?);";

	public long nuevaInscripcionRetornaId(String cliente, String actividad, String fecha) {
		long id;
		id = siguienteId();
		bd.executeUpdate(nueva_inscripcion, id, cliente, actividad, fecha);
		return id;
	}

	public static final String siguiente_id = "SELECT MAX(id_inscripcion) from inscripciones;";

	public long siguienteId() {
		List<Object[]> lista;
		lista = bd.executeQueryArray(siguiente_id);
		return (long) lista.get(0)[0] + 1;
	}
	
	public static final String persona_actividad = "SELECT id_inscripcion FROM inscripciones WHERE id_actividades=? AND dni_clientes=?";
	
	public boolean personaInscritaenActividad(long actividad, String cliente) {
		List<Object[]> lista;
		lista = bd.executeQueryArray(persona_actividad, actividad, cliente);
		if(lista.size()==0) {
			return false;
		}
		else return true;
	}
	
	public static final String todas_socio = "SELECT id_inscripcion, dni_clientes, id_actividades, fecha FROM inscripciones WHERE dni_clientes=? AND fecha>=? AND fecha<=? ORDER BY fecha DESC";
	public List<Object[]> getTodasInscripcionesSocio(String persona, String ini, String fin){
		
		return bd.executeQueryArray(todas_socio, persona, ini, fin);
	}
	
	// Obtencion de clientes por actividad
	public static final String SQL_PERSONAS_ACTIVIDAD = "SELECT dni_clientes FROM inscripciones WHERE id_actividades = ? ";
	public List<Object[]> getPersonasActividad(long actividad){
		return bd.executeQueryArray(SQL_PERSONAS_ACTIVIDAD, actividad);
	}
	
	// Eliminar inscripciones
	public static final String eliminar_inscripciones = "DELETE FROM inscripciones WHERE id_actividades = ?";
	public void eliminarInscripciones(long id_actividad) {
		bd.executeUpdate(eliminar_inscripciones, id_actividad);
	}
	
}
