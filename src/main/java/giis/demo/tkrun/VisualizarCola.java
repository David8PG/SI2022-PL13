package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class VisualizarCola {

	private JFrame frmVisualizarCola;
	private JTable table;
	private ReservasModel reservasModel = new ReservasModel();
	private ClientesModel clientesModel = new ClientesModel();
	private ActividadesModel actividadesModel = new ActividadesModel();
	private ColaModel colaModel = new ColaModel();
	private PagosModel pagosModel = new PagosModel();
	int id_socio;
	private List<Object[]> lCola;
	private List<Object[]> lCola2;

	private Vector<String> reservasPagadas;
	private List<Object[]> lPagos;
	private String[] lPagos2;
	private Object[] vector = new Object[7];
	private InicioSesion principal;
	private int i;
	private String seleccionado;
	private DefaultTableModel model;
	private String dni;
	private JLabel sinReservasLabel;
	private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date fechaHoraExactaHoy = new Date();
	String fechaHoraExactaHoyStr = formato2.format(fechaHoraExactaHoy);
	String Fecha = formato.format(fechaHoraExactaHoy);
	String ruta = "src/main/resources/Cola/" + "Informacion_" + Fecha + ".txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarCola window = new VisualizarCola();
					window.frmVisualizarCola.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualizarCola() {
		initialize();
	}

	public VisualizarCola(InicioSesion login) {
		principal = login;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVisualizarCola = new JFrame();
		frmVisualizarCola.setBounds(100, 100, 908, 337);
		frmVisualizarCola.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmVisualizarCola.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Lista Espera");

		List<Object[]> lista = actividadesModel.getNombreActividades();
		String[] nombre_actividad = new String[lista.size()];
		Iterator<Object[]> iterador = lista.iterator();

		int i = 0;
		while (iterador.hasNext()) {
			nombre_actividad[i] = iterador.next()[0].toString();
			i++;
		}

		JScrollPane scrollPane = new JScrollPane();
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "ID Actividad", "DNI", "Fecha Reserva", "Socio" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setEnabled(false);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(nombre_actividad));
		String selectedActividad = (String) comboBox.getSelectedItem();
		updateTable(selectedActividad);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedActividad = (String) comboBox.getSelectedItem();
				updateTable(selectedActividad);
			}
		});

		JButton btnNewButton_1 = new JButton("Generar Informe");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lCola2 = colaModel.getEsperasSocio2();
				String linea = "";
				if (lCola2.size() == 0) {
					System.out.println("No hay lista de espera");
				} else {
					Iterator<Object[]> iter2 = lCola2.iterator();
					String id_actividad_anterior = "";
					while (iter2.hasNext()) {

						Object[] datos = iter2.next();

						String id_actividad = datos[0].toString();
						String DNI = datos[1].toString();
						String fecha = datos[2].toString();
						String socio = datos[3].toString();
						if (id_actividad.equals(id_actividad_anterior)) {
							linea += "ID actividad: " + id_actividad + ", DNI: " + DNI + ", Fecha Reserva: " + fecha
									+ ", Es Socio: " + socio + "\n";
						} else {
							linea += "\n" + "------------------------------------ " + id_actividad
									+ " ----------------------------------------------" + "\n";
							linea += "ID actividad: " + id_actividad + ", DNI: " + DNI + ", Fecha Reserva: " + fecha
									+ ", Es Socio: " + socio + "\n";
						}
						id_actividad_anterior = id_actividad;
					}

					File file = new File(ruta);
					if (!file.exists()) {
						try {
							file.createNewFile();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					FileWriter fw = null;
					try {
						fw = new FileWriter(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					BufferedWriter bw = new BufferedWriter(fw);
					try {
						bw.write(linea);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						bw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Actividad");

		GroupLayout groupLayout = new GroupLayout(frmVisualizarCola.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE));

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmVisualizarCola.dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(36)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 525, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(59)).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(22)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1).addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addGap(50).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_1).addComponent(btnNewButton))
						.addGap(20)));

		panel.setLayout(gl_panel);
		frmVisualizarCola.getContentPane().setLayout(groupLayout);
	}

	private void updateTable(String actividad) {
		model.setRowCount(0);
		String idActividad = actividadesModel.getListaIdsActividades(actividad);
		lCola = colaModel.getEsperasSocio(idActividad);

		if (lCola.size() == 0) {
			System.out.println("Sin reservas");
		} else {
			Iterator<Object[]> iter = lCola.iterator();
			while (iter.hasNext()) {
				Object[] datos = iter.next();
				String id_actividad = datos[0].toString();
				String DNI = datos[1].toString();
				Object fecha = datos[2].toString();
				String socio = datos[3].toString();
				model.addRow(new Object[] { id_actividad, DNI, fecha, socio });
			}
		}
	}

	public Window getFrmVisualizarCola() {
		return this.frmVisualizarCola;
	}
}
