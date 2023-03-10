package giis.demo.tkrun;

import java.util.*;

import giis.demo.util.Database;

public class ClientesModel {
	
	private Database bd = new Database();
	
	// Método que devuelve todos los ids de los socios
	public static final String todos_ids_socios = "SELECT id_socio FROM clientes WHERE id_socio IS NOT NULL";
	public List<Object[]> getIdTodos() {
		return bd.executeQueryArray(todos_ids_socios);
	}
	
	// Método que comprueba la existencia de un id para ver si es válido
	public static final String id_valido = "SELECT id_socio FROM clientes WHERE id_socio =";
	public boolean validarId(String id_socio) {
		List<Object[]> lista_ids;
		lista_ids = bd.executeQueryArray(id_valido + "'" + id_socio + "'");
		if (lista_ids.size() == 0) {
			return false;
		}
		return true;
	}
	
	// Obtención de la contraseña de los socios
	public static final String contraseña_socios = "SELECT contraseña FROM clientes WHERE id_socio=";
	public List<Object[]> getContraseña(String id_socio) {
		return bd.executeQueryArray(contraseña_socios + "'" + id_socio + "';");
	}


}
