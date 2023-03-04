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
		frmPantallaInicialAdministrador.setBounds(100, 100, 450, 300);
		frmPantallaInicialAdministrador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPantallaInicialAdministrador.getContentPane().setLayout(null);

		JButton btnReservarInstalacion = new JButton("Reservar Instalación");
		btnReservarInstalacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vReservaInstalacionFechaDeterminada = new ReservaInstalacionFechaDeterminada();
				vReservaInstalacionFechaDeterminada.getFrmReservaInstalacionFechaDeterminada().setVisible(true);
			}
		});
		btnReservarInstalacion.setBounds(33, 25, 153, 23);
		frmPantallaInicialAdministrador.getContentPane().add(btnReservarInstalacion);

		JButton btnNewButton = new JButton("Crear Actividad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaCrearActividad = new PlanificarActividad();
				ventanaCrearActividad.getFrmCrearActividad().setVisible(true);
			}
		});
		btnNewButton.setBounds(33, 59, 153, 23);
		frmPantallaInicialAdministrador.getContentPane().add(btnNewButton);

		JButton btnCrearPeriodoIns = new JButton("Crear Periodo Inscripción");
		btnCrearPeriodoIns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPeriodoInscripcion = new CrearPeriodoInscripcion();
				ventanaPeriodoInscripcion.getfrmCrearActividad().setVisible(true);
			}
		});
		btnCrearPeriodoIns.setBounds(33, 93, 153, 23);
		frmPantallaInicialAdministrador.getContentPane().add(btnCrearPeriodoIns);
	}

	public JFrame getFrmvAdmin() {
		return this.frmPantallaInicialAdministrador;
	}
}
