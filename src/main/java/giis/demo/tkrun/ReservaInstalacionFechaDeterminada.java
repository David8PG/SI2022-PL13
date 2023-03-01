package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.toedter.calendar.JDateChooser;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class ReservaInstalacionFechaDeterminada {

	private JFrame frmReservaInstalacionFechaDeterminada;
	private JFrame frame_1;
	private JFrame frmReservarInstalacin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaInstalacionFechaDeterminada window = new ReservaInstalacionFechaDeterminada();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReservaInstalacionFechaDeterminada() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame;
		frmReservarInstalacin = new JFrame();
		frmReservarInstalacin.setTitle("Reservar Instalación");
		frmReservarInstalacin = frmReservarInstalacin;
		frmReservarInstalacin.setBounds(100, 100, 450, 300);
		frmReservarInstalacin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReservarInstalacin.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmReservarInstalacin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(41, 220, 85, 21);
		panel.add(btnCancelar);
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.setBounds(306, 220, 85, 21);
		panel.add(btnReservar);
		
		JLabel lblInstalacion = new JLabel("Seleccione una Instalación:");
		lblInstalacion.setBounds(41, 38, 125, 13);
		panel.add(lblInstalacion);
		
		JComboBox comboBoxInstalación = new JComboBox();
		comboBoxInstalación.setBounds(176, 34, 215, 21);
		panel.add(comboBoxInstalación);
		
		JLabel lblHoraReserva = new JLabel("Hora de la Reserva:");
		lblHoraReserva.setBounds(41, 129, 125, 13);
		panel.add(lblHoraReserva);
		
		JLabel lblFechaReserva = new JLabel("Fecha de la Reserva:");
		lblFechaReserva.setBounds(41, 87, 125, 13);
		panel.add(lblFechaReserva);
		
		JComboBox comboBoxHorasReserva = new JComboBox();
		comboBoxHorasReserva.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
		comboBoxHorasReserva.setBounds(176, 125, 111, 21);
		panel.add(comboBoxHorasReserva);
		
		JDateChooser dateChooser_FechaReserva = new JDateChooser();
		dateChooser_FechaReserva.setBounds(176, 81, 156, 19);
		panel.add(dateChooser_FechaReserva);
	}
}
