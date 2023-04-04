package giis.demo.tkrun;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.TableModel;

import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class InscripcionActSocController {
	private InscripcionActividadSocio view;
	private InicioSesion login;
	private int id_socio;
	private ActividadesModel actividadesmodel;



	public InscripcionActSocController(InscripcionActividadSocio v, InicioSesion l) {
		this.view=v;
		this.login=l;
		this.id_socio=this.login.getId_socio();
		this.initView();
	}

	public void initController() {
	}

	public void initView() {
		view.setId_Socio(Integer.toString(id_socio));
		view.getFrame().setVisible(true);
	}

	/*public void getListaActividades() {
		List<Object[]> lista=actividadesmodel.getNombreActividades();
		String[] actividades=new String[lista.size()];
		Iterator<Object[]> iterador = lista.iterator();
		int i=0;
		while(iterador.hasNext()) {
			actividades[i]=iterador.next()[0].toString();
			i++;
		}
	}*/

}
