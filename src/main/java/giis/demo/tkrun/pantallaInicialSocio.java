package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pantallaInicialSocio {

	private JFrame frmPantallaInicialSocio;
	private InicioSesion vInicioSesion;
	private ReservasModel modeloReservas = new ReservasModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pantallaInicialSocio window = new pantallaInicialSocio();
					window.frmPantallaInicialSocio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pantallaInicialSocio() {
		initialize();
	}
	
	public pantallaInicialSocio(InicioSesion vInicioSesion) {
		this.vInicioSesion=vInicioSesion;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPantallaInicialSocio = new JFrame();
		frmPantallaInicialSocio.setTitle("Pantalla Inicial Socio");
		frmPantallaInicialSocio.setBounds(100, 100, 450, 300);
		frmPantallaInicialSocio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPantallaInicialSocio.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vInicioSesion.getFrmInicioSesion().setVisible(true);
				frmPantallaInicialSocio.setVisible(false);
			}
		});
		btnNewButton.setBounds(172, 118, 85, 21);
		frmPantallaInicialSocio.getContentPane().add(btnNewButton);
	}
	
	public JFrame getFrmvSocio() {
		return this.frmPantallaInicialSocio;
	}

}
