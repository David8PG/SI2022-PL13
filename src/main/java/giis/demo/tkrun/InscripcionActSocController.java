package giis.demo.tkrun;

import giis.demo.util.SwingUtil;

public class InscripcionActSocController {
	private InscripcionActividadSocio view;
	private InicioSesion login;
	private int id_socio;
	
	
	
	public InscripcionActSocController(InscripcionActividadSocio v, InicioSesion l) {
		this.view=v;
		this.login=l;
		this.id_socio=this.login.getId_socio();
	}
	
	public void initController() {
	}
	
	public void initView() {
		view.getFrame().setVisible(true);
	}

	
}
