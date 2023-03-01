package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlanificarActividad {

	private JFrame frmPlanificarActividad;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private ActividadesModel ModeloActividades = new ActividadesModel();
	private PeriodosInscripcionModel ModeloPeriodo = new PeriodosInscripcionModel();
	private InstalacionesModel ModeloInstalaciones = new InstalacionesModel();
	private crearPeriodoInscripcion ventanaPeriodoInscripcion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanificarActividad window = new PlanificarActividad();
					window.frmPlanificarActividad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlanificarActividad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPlanificarActividad = new JFrame();
		frmPlanificarActividad.setTitle("Planificar Actividad");
		frmPlanificarActividad.setBounds(100, 100, 484, 527);
		frmPlanificarActividad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmPlanificarActividad.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(84, 12, 241, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(21, 14, 56, 17);
		panel.add(lblNewLabel);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(21, 42, 56, 17);
		panel.add(lblDescripcin);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(84, 40, 241, 78);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Instalación");
		lblNewLabel_1.setBounds(21, 137, 56, 14);
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(84, 133, 132, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Deporte");
		lblNewLabel_2.setBounds(21, 170, 46, 14);
		panel.add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(84, 166, 132, 22);
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Aforo");
		lblNewLabel_3.setBounds(21, 207, 46, 14);
		panel.add(lblNewLabel_3);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(84, 204, 46, 20);
		panel.add(spinner);
		
		JLabel lblNewLabel_4 = new JLabel("Precio");
		lblNewLabel_4.setBounds(21, 242, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Precio No Socio");
		lblNewLabel_4_1.setBounds(171, 242, 89, 14);
		panel.add(lblNewLabel_4_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(84, 240, 56, 17);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(245, 240, 61, 17);
		panel.add(textField_3);
		
		JLabel lblNewLabel_5 = new JLabel("€");
		lblNewLabel_5.setBounds(142, 242, 46, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("€");
		lblNewLabel_5_1.setBounds(312, 242, 46, 14);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_6 = new JLabel("Periodo Inscripción");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(21, 282, 120, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Fecha Inicio Socios");
		lblNewLabel_7.setBounds(21, 319, 132, 17);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Fecha Fin Socios");
		lblNewLabel_8.setBounds(21, 347, 103, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("Fecha Fin no Socios");
		lblNewLabel_8_1.setBounds(21, 372, 103, 14);
		panel.add(lblNewLabel_8_1);
		
		JButton btnNewButton = new JButton("Crear Actividad");
		btnNewButton.setBounds(186, 454, 120, 23);
		panel.add(btnNewButton);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(129, 317, 114, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(129, 344, 114, 20);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(129, 369, 114, 20);
		panel.add(textField_6);
		
		JLabel lblNewLabel_9 = new JLabel("Duración");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_9.setBounds(367, 102, 76, 14);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Fecha Inicio");
		lblNewLabel_10.setBounds(279, 137, 68, 14);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Fecha Fin");
		lblNewLabel_11.setBounds(279, 170, 46, 14);
		panel.add(lblNewLabel_11);
		
		textField_7 = new JTextField();
		textField_7.setBounds(344, 134, 114, 20);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(344, 167, 114, 20);
		panel.add(textField_8);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(151, 279, 104, 22);
		panel.add(comboBox_2);
		
		JLabel lblNewLabel_6_1 = new JLabel("Crear Periodo Inscripción");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6_1.setBounds(21, 410, 148, 14);
		panel.add(lblNewLabel_6_1);
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPeriodoInscripcion.getFrmCrearPeriodoDe().setVisible(true);
			}});
		btnNewButton_1.setBounds(186, 407, 89, 23);
		panel.add(btnNewButton_1);
	}
}
