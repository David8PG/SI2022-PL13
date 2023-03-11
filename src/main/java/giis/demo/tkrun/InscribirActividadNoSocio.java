package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class InscribirActividadNoSocio {

	private JFrame frmInscribirActividadNoSocio;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JButton btnInscribir;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private ActividadesModel ModeloActividades = new ActividadesModel();
	private PeriodosInscripcionModel ModeloPeriodo = new PeriodosInscripcionModel();
	private InstalacionesModel ModeloInstalaciones = new InstalacionesModel();
	private SesionesModel ModeloSesiones = new SesionesModel();
	List<String> actividades = new ArrayList<String>();
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	String fechaHoy = formato.format(dateHoy);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscribirActividadNoSocio window = new InscribirActividadNoSocio();
					window.frmInscribirActividadNoSocio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InscribirActividadNoSocio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInscribirActividadNoSocio = new JFrame();
		frmInscribirActividadNoSocio.setBounds(100, 100, 498, 329);
		frmInscribirActividadNoSocio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmInscribirActividadNoSocio.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Actividad");
		lblNewLabel.setBounds(19, 123, 71, 14);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(249, 117, 212, 38);
		panel.add(textPane);

		List<Object[]> modPer = ModeloPeriodo.getFechasNoSocio(fechaHoy);
		Iterator<Object[]> iteradorPer = modPer.iterator();
		while (iteradorPer.hasNext()) {
			List<Object[]> modAct = ModeloActividades
					.getActividadesPeriodoInscripcion(iteradorPer.next()[0].toString());
			Iterator<Object[]> iteradorAct = modAct.iterator();
			while (iteradorAct.hasNext()) {
				actividades.add(iteradorAct.next()[0].toString());
			}
		}

		String[] listaActividades = new String[actividades.size()];
		Iterator<String> iteradorTodas = actividades.iterator();
		int iAct = 0;
		while (iteradorTodas.hasNext()) {
			listaActividades[iAct] = iteradorTodas.next();
			iAct++;
		}

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setBounds(19, 58, 18, 14);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(19, 27, 49, 14);

		JLabel lblNewLabel_1_1 = new JLabel("Teléfono");
		lblNewLabel_1_1.setBounds(19, 91, 58, 14);

		textField = new JTextField();
		textField.setBounds(94, 24, 86, 20);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(94, 55, 86, 20);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(94, 88, 86, 20);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(94, 171, 86, 20);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(94, 208, 86, 20);
		textField_4.setColumns(10);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(279, 171, 86, 20);
		textField_5.setColumns(10);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(279, 208, 86, 20);
		textField_6.setColumns(10);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(10, 256, 75, 23);

		btnInscribir = new JButton("Inscribir");
		btnInscribir.setBounds(188, 256, 89, 23);

		lblNewLabel_3 = new JLabel("Fecha Inicio");
		lblNewLabel_3.setBounds(19, 174, 57, 14);

		lblNewLabel_4 = new JLabel("Fecha Fin");
		lblNewLabel_4.setBounds(19, 211, 57, 14);

		lblNewLabel_5 = new JLabel("Plazas");
		lblNewLabel_5.setBounds(226, 174, 57, 14);

		panel.setLayout(null);
		panel.add(lblNewLabel);
		panel.add(lblNewLabel_2);
		panel.add(lblNewLabel_1);
		panel.add(lblNewLabel_3);
		panel.add(lblNewLabel_4);
		panel.add(textField_3);

		panel.add(lblNewLabel_5);
		panel.add(textField_6);
		panel.add(textField_1);
		panel.add(textField);
		panel.add(lblNewLabel_1_1);
		panel.add(textField_2);
		panel.add(btnNewButton);
		panel.add(btnInscribir);

		lblNewLabel_6 = new JLabel("Precio");
		lblNewLabel_6.setBounds(226, 211, 57, 14);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(listaActividades));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText(ModeloActividades.getDescripcion(comboBox.getSelectedItem().toString()));
				textField_6.setText(ModeloActividades.getPrecioActividadNoSocio(comboBox.getSelectedItem().toString()));
				textField_3.setText(ModeloActividades.getFechaInicioActividad(comboBox.getSelectedItem().toString()));
				textField_4.setText(ModeloActividades.getFechaFinActividad(comboBox.getSelectedItem().toString()));
				textField_5.setText(ModeloActividades.getPlazasActividad(comboBox.getSelectedItem().toString()));
			}
		});

		comboBox.setBounds(94, 119, 116, 22);
		panel.add(comboBox);

	}
}
