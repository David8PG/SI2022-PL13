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
import javax.swing.JTextArea;
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
	private ClientesModel ModeloClientes = new ClientesModel();
	private ActividadesModel ModeloActividades = new ActividadesModel();
	private PeriodosInscripcionModel ModeloPeriodo = new PeriodosInscripcionModel();
	private InstalacionesModel ModeloInstalaciones = new InstalacionesModel();
	private InscripcionesModel ModeloInscripciones = new InscripcionesModel();
	private PagosModel ModeloPagos = new PagosModel();
	private SesionesModel ModeloSesiones = new SesionesModel();
	List<String> actividades = new ArrayList<String>();
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	String fechaHoy = formato.format(dateHoy);
	private JTextField textField_9;
	private JTextField textField_10;

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
		frmInscribirActividadNoSocio.setBounds(100, 100, 729, 401);
		frmInscribirActividadNoSocio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmInscribirActividadNoSocio.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Actividad");
		lblNewLabel.setBounds(19, 123, 71, 14);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(319, 119, 212, 38);
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
		System.out.println(actividades.size());
		String[] listaActividades = new String[actividades.size()];
		Iterator<String> iteradorTodas = actividades.iterator();
		int iAct = 0;
		while (iteradorTodas.hasNext()) {
			listaActividades[iAct] = iteradorTodas.next();
			iAct++;
		}

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setBounds(19, 58, 49, 14);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(19, 27, 49, 14);

		JLabel lblNewLabel_1_1 = new JLabel("Teléfono");
		lblNewLabel_1_1.setBounds(19, 91, 71, 14);

		textField = new JTextField();
		textField.setBounds(94, 24, 86, 20);
		textField.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(19, 246, 414, 69);
		panel.add(textArea);

		textField_1 = new JTextField();
		textField_1.setBounds(94, 55, 86, 20);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(94, 88, 86, 20);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(105, 171, 86, 20);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(105, 208, 86, 20);
		textField_4.setColumns(10);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(262, 171, 86, 20);
		textField_5.setColumns(10);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(262, 208, 86, 20);
		textField_6.setColumns(10);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(10, 328, 89, 23);

		lblNewLabel_3 = new JLabel("Fecha Inicio");
		lblNewLabel_3.setBounds(19, 174, 71, 14);

		lblNewLabel_4 = new JLabel("Fecha Fin");
		lblNewLabel_4.setBounds(19, 211, 57, 14);

		lblNewLabel_5 = new JLabel("Aforo");
		lblNewLabel_5.setBounds(215, 174, 57, 14);

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

		lblNewLabel_6 = new JLabel("Precio");
		lblNewLabel_6.setBounds(226, 211, 57, 14);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(listaActividades));
		comboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String sele = comboBox.getSelectedItem().toString();
				List<Object[]> Lista = ModeloSesiones.getSesiones(ModeloActividades.getIdActividad(sele));
				textPane.setText(ModeloActividades.getDescripcion(sele));
				textField_6.setText(ModeloActividades.getPrecioActividadNoSocio2(sele));
				textField_3.setText(ModeloActividades.getFechaInicioActividad(sele));
				textField_4.setText(ModeloActividades.getFechaFinActividad(sele));
				textField_5.setText(ModeloActividades.getAforo(sele));
				textField_9.setText(ModeloActividades.getPlazasActividad(sele));
				textField_10.setText(ModeloInstalaciones.getNombre_Instalacion(ModeloActividades.getInstalacion(sele)));
				for (Object[] sesion : Lista) {
					for (Object elemento : sesion) {
						textArea.setText("Dia: " + sesion[0].toString() + " | Hora de inicio: " + sesion[1].toString()
								+ " | Hora de fin: " + sesion[2].toString() + "\n");
					}
				}
			}
		});
		comboBox.setBounds(94, 119, 178, 22);
		panel.add(comboBox);

		btnInscribir = new JButton("Inscribir");
		/*
		 * btnInscribir.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { String dni = textField_3.getText(); if
		 * (!ModeloClientes.existeDNI(dni)) { ModeloClientes.nuevoCliente(null, dni,
		 * null); } if (yaInscrito(dni, comboBox.getSelectedItem().toString())) {
		 * JOptionPane.showMessageDialog(frmInscribirActividadNoSocio,
		 * "El cliente ya está inscrito en la actividad.", "Error",
		 * JOptionPane.ERROR_MESSAGE); } else { if
		 * (quedanPlazas(comboBox.getSelectedItem().toString())) {
		 * ModeloActividades.restarPlaza(comboBox.getSelectedItem().toString()); long
		 * idInscripcion = ModeloInscripciones.nuevaInscripcionRetornaId(dni, "" +
		 * ModeloActividades.getIdActividad(comboBox.getSelectedItem().toString()),
		 * fechaHoy); ModeloPagos.nuevoPago(fechaHoy, dni, "" + idInscripcion, "" + 0);
		 * JOptionPane.showMessageDialog( frmInscribirActividadNoSocio,
		 * "Cliente inscrito en la actividad.\nRecibo: \n-Importe: " +
		 * textField.getText() + " €\n-Fecha: " + fechaHoy, "Inscrito",
		 * JOptionPane.INFORMATION_MESSAGE); frmInscribirActividadNoSocio.dispose(); }
		 * else { JOptionPane.showMessageDialog(frmInscribirActividadNoSocio,
		 * "No quedan plazas disponibles, el cliente pasará a la lista de espera.",
		 * "Error", JOptionPane.ERROR_MESSAGE); // ModeloCola.nuevaCola(dni, // "" +
		 * ModeloActividades.getIdActividad(comboBox.getSelectedItem().toString()), //
		 * fechaHoy); } } }
		 * 
		 * });
		 */
		btnInscribir.setBounds(259, 328, 89, 23);
		panel.add(btnInscribir);

		JLabel lblNewLabel_7 = new JLabel("Precio");
		lblNewLabel_7.setBounds(215, 211, 46, 14);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Descripción");
		lblNewLabel_8.setBounds(390, 94, 71, 14);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_10 = new JLabel("Plazas Disponibles");
		lblNewLabel_10.setBounds(375, 174, 127, 14);
		panel.add(lblNewLabel_10);

		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setBounds(490, 171, 86, 20);
		panel.add(textField_9);
		textField_9.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Instalación");
		lblNewLabel_11.setBounds(375, 211, 86, 14);
		panel.add(lblNewLabel_11);

		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setBounds(449, 208, 127, 20);
		panel.add(textField_10);
		textField_10.setColumns(10);

	}

	public boolean yaInscrito(String dni, String nombreActividad) {
		boolean retur = false;
		retur = ModeloInscripciones.clienteInscripcionActividad(ModeloActividades.getIdActividad(nombreActividad), dni);
		return retur;
	}

	public boolean quedanPlazas(String nombreActividad) {
		boolean bool = true;
		String plazas = ModeloActividades.getPlazasActividad(nombreActividad);
		if (plazas.equals("0")) {
			bool = false;
		}
		return bool;
	}
}
