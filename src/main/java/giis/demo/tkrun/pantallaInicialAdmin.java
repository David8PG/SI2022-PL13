package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pantallaInicialAdmin {

	private JFrame frmPantallaInicialAdministrador;
	private InicioSesion vInicioSesion;
	private ReservasModel modeloReservas = new ReservasModel();
	private ReservaInstalacionFechaDeterminada vReservaInstalacionFechaDeterminada;

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
		this.vInicioSesion=vInicioSesion;
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
		btnReservarInstalacion.setBounds(33, 25, 135, 21);
		frmPantallaInicialAdministrador.getContentPane().add(btnReservarInstalacion);
	}
	
	public JFrame getFrmvAdmin() {
		return this.frmPantallaInicialAdministrador;
	}

}
