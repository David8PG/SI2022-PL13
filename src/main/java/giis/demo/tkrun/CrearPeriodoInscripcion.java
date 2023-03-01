package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CrearPeriodoInscripcion {

	private JFrame frmCrearPeriodoDe;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPeriodoInscripcion window = new CrearPeriodoInscripcion();
					window.frmCrearPeriodoDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrearPeriodoInscripcion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrearPeriodoDe = new JFrame();
		frmCrearPeriodoDe.setTitle("Crear Periodo de Inscripción");
		frmCrearPeriodoDe.setBounds(100, 100, 294, 300);
		frmCrearPeriodoDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmCrearPeriodoDe.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Nombre");
		lblNewLabel.setBounds(22, 11, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(22, 36, 59, 14);
		panel.add(lblDescripcin);
		
		textField = new JTextField();
		textField.setBounds(83, 8, 159, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(83, 33, 159, 77);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha Inicio Socios");
		lblNewLabel_1.setBounds(22, 132, 90, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha Fin Socios");
		lblNewLabel_1_1.setBounds(22, 157, 90, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha Fin no Socios");
		lblNewLabel_1_1_1.setBounds(22, 182, 104, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton = new JButton("Crear Periodo ");
		btnNewButton.setBounds(83, 227, 101, 23);
		panel.add(btnNewButton);
		
		textField_2 = new JTextField();
		textField_2.setBounds(131, 129, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(131, 154, 86, 20);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(131, 179, 86, 20);
		panel.add(textField_4);
	}

}
