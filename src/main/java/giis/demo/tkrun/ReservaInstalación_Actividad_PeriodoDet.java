package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class ReservaInstalación_Actividad_PeriodoDet {

	private JFrame frmReservaInstalacion_Actividad_PeriodoDet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaInstalación_Actividad_PeriodoDet window = new ReservaInstalación_Actividad_PeriodoDet();
					window.frmReservaInstalacion_Actividad_PeriodoDet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReservaInstalación_Actividad_PeriodoDet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaInstalacion_Actividad_PeriodoDet = new JFrame();
		frmReservaInstalacion_Actividad_PeriodoDet.setTitle("Reserva para una Actividad");
		frmReservaInstalacion_Actividad_PeriodoDet.setBounds(100, 100, 649, 436);
		frmReservaInstalacion_Actividad_PeriodoDet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReservaInstalacion_Actividad_PeriodoDet.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmReservaInstalacion_Actividad_PeriodoDet.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblInstalacion = new JLabel("Seleccione la Instalación:");
		lblInstalacion.setBounds(68, 59, 202, 13);
		panel.add(lblInstalacion);
		
		JLabel lblActividad = new JLabel("Seleccione la Actividad:");
		lblActividad.setBounds(68, 129, 202, 13);
		panel.add(lblActividad);
		
		JLabel lblFecha_Inicio = new JLabel("Seleccione la fecha de inicio:");
		lblFecha_Inicio.setBounds(68, 198, 147, 13);
		panel.add(lblFecha_Inicio);
		
		JLabel lblFecha_Fin = new JLabel("Seleccione la fecha fin:");
		lblFecha_Fin.setBounds(68, 269, 128, 13);
		panel.add(lblFecha_Fin);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(68, 346, 85, 21);
		panel.add(btnCancelar);
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.setBounds(485, 346, 85, 21);
		panel.add(btnReservar);
		
		JComboBox comboBoxInstalacion = new JComboBox();
		comboBoxInstalacion.setBounds(225, 55, 345, 21);
		panel.add(comboBoxInstalacion);
		
		JComboBox comboBoxActividad = new JComboBox();
		comboBoxActividad.setBounds(225, 125, 345, 21);
		panel.add(comboBoxActividad);
		
		JDateChooser dateChooser_FechaInicio = new JDateChooser();
		dateChooser_FechaInicio.setBounds(225, 192, 156, 19);
		panel.add(dateChooser_FechaInicio);
		
		JDateChooser dateChooser_FechaFin = new JDateChooser();
		dateChooser_FechaFin.setBounds(225, 263, 156, 19);
		panel.add(dateChooser_FechaFin);
	}
}
