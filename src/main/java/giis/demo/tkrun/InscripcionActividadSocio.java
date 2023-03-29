package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class InscripcionActividadSocio {

	private JFrame frame;
	private JTextField tfSocio;
	private JTextField textField;
	private JTextField tfPrecio;
	private JTextField tfIniAct;
	private JTextField tfFinAct;
	private JButton bCancelar;
	private JButton bIncribirse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscripcionActividadSocio window = new InscripcionActividadSocio();
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
	public InscripcionActividadSocio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Inscripción a un actividad");
		frame.setBounds(100, 100, 565, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nº de Socio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(195, 21, 76, 15);
		frame.getContentPane().add(lblNewLabel);
		
		tfSocio = new JTextField();
		tfSocio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfSocio.setBounds(267, 19, 48, 19);
		frame.getContentPane().add(tfSocio);
		tfSocio.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Actividad:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(28, 54, 58, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(86, 52, 119, 21);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha fin de inscripción:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(27, 79, 136, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(161, 78, 103, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Total de aforo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(28, 104, 85, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lAforoT = new JLabel("New label");
		lAforoT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lAforoT.setBounds(118, 106, 45, 13);
		frame.getContentPane().add(lAforoT);
		
		JLabel lblNewLabel_4 = new JLabel("Plazas restantes:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(28, 129, 89, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lAforoA = new JLabel("New label");
		lAforoA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lAforoA.setBounds(128, 131, 45, 13);
		frame.getContentPane().add(lAforoA);
		
		JLabel lblNewLabel_5 = new JLabel("Precio:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(28, 154, 45, 13);
		frame.getContentPane().add(lblNewLabel_5);
		
		tfPrecio = new JTextField();
		tfPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfPrecio.setBounds(67, 152, 45, 19);
		frame.getContentPane().add(tfPrecio);
		tfPrecio.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Inicio de actividad:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(320, 54, 103, 15);
		frame.getContentPane().add(lblNewLabel_6);
		
		tfIniAct = new JTextField();
		tfIniAct.setBounds(423, 53, 96, 19);
		frame.getContentPane().add(tfIniAct);
		tfIniAct.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Fin de actividad:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(320, 81, 89, 15);
		frame.getContentPane().add(lblNewLabel_7);
		
		tfFinAct = new JTextField();
		tfFinAct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfFinAct.setBounds(411, 78, 96, 19);
		frame.getContentPane().add(tfFinAct);
		tfFinAct.setColumns(10);
		
		bCancelar = new JButton("Cancelar");
		bCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bCancelar.setBounds(78, 199, 85, 21);
		frame.getContentPane().add(bCancelar);
		
		bIncribirse = new JButton("Incribirse");
		bIncribirse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bIncribirse.setBounds(381, 200, 85, 21);
		frame.getContentPane().add(bIncribirse);
	}
	
	public JFrame getFrame() { return this.frame; }
	public JButton getBIncribirse() { return this.bIncribirse; }
	public JButton getBCancelar() { return this.bCancelar; }
}
