package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class pantallaInicialAdmin {

	private JFrame frmPantallaInicialAdministrador;
	private InicioSesion vInicioSesion;
	private ReservasModel modeloReservas = new ReservasModel();
	private ReservaInstalacionFechaDeterminada vReservaInstalacionFechaDeterminada;
	private PlanificarActividad ventanaCrearActividad;
	private CrearPeriodoInscripcion ventanaPeriodoInscripcion;
	private ReservaInstalacionActividadPeriodoDet vReservaInstalacionActividadPeriodoDet;
	private ListarActividades ventanaListarActividades;
	private VisualizarReservasInstalaciones ventanaVisualizarReservas;
	private ModificarParametros ventanaModificarParametros;
	private CancelarReservaSocio ventanaCancelarReserva;
	private ReservaAutomatica ventanaReservaAutomatica;
	private VisualizarContabilidad ventanaVisualizarContabilidad;
	private InscribirActividadNoSocio ventanaInscribirActividadNoSocio;
	private InformesMenu ventanaInformesMenu;
	private Cancelar_Reserva_Admin vCRA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pantallaInicialAdmin window = new pantallaInicialAdmin();
					window.frmPantallaInicialAdministrador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pantallaInicialAdmin() {
		initialize();
	}

	public pantallaInicialAdmin(InicioSesion vInicioSesion) {
		this.vInicioSesion = vInicioSesion;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPantallaInicialAdministrador = new JFrame();
		frmPantallaInicialAdministrador.setTitle("Pantalla Inicial Administrador");
		frmPantallaInicialAdministrador.setBounds(100, 100, 543, 380);
		frmPantallaInicialAdministrador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPantallaInicialAdministrador.getContentPane().setLayout(null);

		JButton btnReservarInstalacion = new JButton("Reservar Instalación");
		btnReservarInstalacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vReservaInstalacionFechaDeterminada = new ReservaInstalacionFechaDeterminada();
				vReservaInstalacionFechaDeterminada.getFrmReservaInstalacionFechaDeterminada().setVisible(true);
			}
		});
		btnReservarInstalacion.setBounds(33, 25, 227, 23);
		frmPantallaInicialAdministrador.getContentPane().add(btnReservarInstalacion);

		JButton btnNewButton = new JButton("Crear Actividad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaCrearActividad = new PlanificarActividad();
				ventanaCrearActividad.getFrmCrearActividad().setVisible(true);
			}
		});
		btnNewButton.setBounds(33, 59, 227, 23);
		frmPantallaInicialAdministrador.getContentPane().add(btnNewButton);

		JButton btnCrearPeriodoIns = new JButton("Crear Periodo Inscripción");
		btnCrearPeriodoIns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPeriodoInscripcion = new CrearPeriodoInscripcion();
				ventanaPeriodoInscripcion.getfrmCrearActividad().setVisible(true);
			}
		});
		btnCrearPeriodoIns.setBounds(33, 93, 227, 23);
		frmPantallaInicialAdministrador.getContentPane().add(btnCrearPeriodoIns);

		JButton btnReservarActividad = new JButton("Reservar Actividad Ins.");
		btnReservarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vReservaInstalacionActividadPeriodoDet = new ReservaInstalacionActividadPeriodoDet();
				vReservaInstalacionActividadPeriodoDet.getFrmReservaInstalacionActividadPeriodoDet().setVisible(true);
			}
		});
		btnReservarActividad.setBounds(33, 159, 227, 21);
		frmPantallaInicialAdministrador.getContentPane().add(btnReservarActividad);

		JButton btnListarActividad = new JButton("Listar Actividades");
		btnListarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaListarActividades = new ListarActividades();
				ventanaListarActividades.getListarActividades().setVisible(true);
			}
		});
		btnListarActividad.setBounds(33, 126, 227, 23);
		frmPantallaInicialAdministrador.getContentPane().add(btnListarActividad);

		JButton btnListarReservas = new JButton("Listar Reservas");
		btnListarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaVisualizarReservas = new VisualizarReservasInstalaciones();
				ventanaVisualizarReservas.getVisualizarReservas().setVisible(true);
			}
		});
		btnListarReservas.setBounds(33, 190, 227, 21);
		frmPantallaInicialAdministrador.getContentPane().add(btnListarReservas);

		JButton btnParametros = new JButton("Modificar Parámetros");
		btnParametros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaModificarParametros = new ModificarParametros();
				ventanaModificarParametros.getModificarParametros().setVisible(true);
			}
		});
		btnParametros.setBounds(33, 221, 227, 21);
		frmPantallaInicialAdministrador.getContentPane().add(btnParametros);

		JButton btnCancelarReservaSocio = new JButton("Cancelar Reserva Socio");
		btnCancelarReservaSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaCancelarReserva = new CancelarReservaSocio();
				ventanaCancelarReserva.getFrmCancelarSocio().setVisible(true);
			}
		});
		btnCancelarReservaSocio.setBounds(270, 25, 230, 23);
		frmPantallaInicialAdministrador.getContentPane().add(btnCancelarReservaSocio);

		JButton btnReservaAutomatica = new JButton("Reserva Automática");
		btnReservaAutomatica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaReservaAutomatica = new ReservaAutomatica();
				ventanaReservaAutomatica.getFrmReservaAutomaticaActividad().setVisible(true);
			}
		});
		btnReservaAutomatica.setBounds(270, 60, 230, 21);
		frmPantallaInicialAdministrador.getContentPane().add(btnReservaAutomatica);

		JButton btnVisualizarContabilidad = new JButton("Visualizar contabilidad");
		btnVisualizarContabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaVisualizarContabilidad = new VisualizarContabilidad();
				ventanaVisualizarContabilidad.getVisualizarContabilidad().setVisible(true);
			}
		});
		btnVisualizarContabilidad.setBounds(33, 252, 227, 21);
		frmPantallaInicialAdministrador.getContentPane().add(btnVisualizarContabilidad);


		JButton btnNewButton_1 = new JButton("Inscribir Actividad no Socio");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaInscribirActividadNoSocio = new InscribirActividadNoSocio();
				ventanaInscribirActividadNoSocio.getFrmActividadNoSocio().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(270, 93, 230, 23);
		frmPantallaInicialAdministrador.getContentPane().add(btnNewButton_1);

		JButton btnInformes = new JButton("Informes");
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaInformesMenu = new InformesMenu();
				ventanaInformesMenu.getVisualizarInformesMenu().setVisible(true);
			}
		});
		btnInformes.setBounds(270, 127, 230, 21);
		frmPantallaInicialAdministrador.getContentPane().add(btnInformes);

		
		JButton bCancelarReserva = new JButton("Cancelar Reserva Socio");
		bCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCRA = new Cancelar_Reserva_Admin();
				vCRA.getFrame().setVisible(true);
			}
		});
		bCancelarReserva.setBounds(270, 159, 230, 21);
		frmPantallaInicialAdministrador.getContentPane().add(bCancelarReserva);

	}

	public JFrame getFrmvAdmin() {
		return this.frmPantallaInicialAdministrador;
	}
}
