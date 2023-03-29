package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class pantallaInicialSocio {

	private JFrame frmPantallaInicialSocio;
	private InicioSesion vInicioSesion;
	private ReservasModel modeloReservas = new ReservasModel();
	private VisualizarReservasSocio verReservas;
	private ReservarInstalacionSocio rSocio;
	private int id_socio;
	private JTextField tSocio;
	
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
		this.id_socio=this.vInicioSesion.getId_socio();
		System.out.println(id_socio);
		initialize();
		System.out.println(id_socio);
		System.out.println(vInicioSesion.getId_socio());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPantallaInicialSocio = new JFrame();
		frmPantallaInicialSocio.setTitle("Pantalla Inicial Socio");
		frmPantallaInicialSocio.setBounds(100, 100, 450, 220);
		frmPantallaInicialSocio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPantallaInicialSocio.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vInicioSesion.getFrmInicioSesion().setVisible(true);
				//frmPantallaInicialSocio.setVisible(false);
			}
		});
		btnNewButton.setBounds(164, 119, 85, 21);
		frmPantallaInicialSocio.getContentPane().add(btnNewButton);
		
		JButton bVerReservas = new JButton("Ver reservas");
		bVerReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verReservas = new VisualizarReservasSocio(vInicioSesion);
				verReservas.getFrame().setVisible(true);
				//frmPantallaInicialSocio.setVisible(false);
			}
		});
		bVerReservas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bVerReservas.setBounds(64, 59, 120, 23);
		frmPantallaInicialSocio.getContentPane().add(bVerReservas);
		
		JButton bRevervar = new JButton("Hacer reserva");
		bRevervar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rSocio = new ReservarInstalacionSocio(vInicioSesion);
				rSocio.getFrame().setVisible(true);
				//frmPantallaInicialSocio.setVisible(false);
			}
		});
		bRevervar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bRevervar.setBounds(260, 59, 132, 23);
		frmPantallaInicialSocio.getContentPane().add(bRevervar);
		
		JLabel lblNewLabel = new JLabel("Nº Socio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(149, 22, 65, 15);
		frmPantallaInicialSocio.getContentPane().add(lblNewLabel);
		
		tSocio = new JTextField();
		tSocio.setBounds(205, 21, 33, 19);
		frmPantallaInicialSocio.getContentPane().add(tSocio);
		tSocio.setText("");
		tSocio.setEditable(false);
		tSocio.setColumns(10);
	}
	
	public JFrame getFrmvSocio() {
		return this.frmPantallaInicialSocio;
	}
}
