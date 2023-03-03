package giis.demo.tkrun;
import java.util.*;

import giis.demo.util.Database;


public class ClientesModel {

	private Database db=new Database();
	
	public static final String Sql_Id_Clientes = "SELECT id_socio FROM clientes WHERE id_socio IS NOT NULL";
	public List<Object[]> getIdClientes(){
		return db.executeQueryArray(Sql_Id_Clientes);
	}
	
	//Aquí poner todos los métodos que necesiteis para vuestras pantallas como el de arriba.
	
}
