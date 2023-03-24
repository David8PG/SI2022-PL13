package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class CancelarReservaSocio {

	private JFrame frmCancelarReservaSocio;
	private JTable table;
	private ReservasModel reservasModel = new ReservasModel();
	private ClientesModel clientesModel = new ClientesModel();
	private PagosModel pagosModel = new PagosModel();
	int id_socio;
	private List<Object[]> lReservas;

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
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelarReservaSocio window = new CancelarReservaSocio();
					window.frmCancelarReservaSocio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CancelarReservaSocio() {
		initialize();
	}

	public CancelarReservaSocio(InicioSesion login) {
		principal = login;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancelarReservaSocio = new JFrame();
		frmCancelarReservaSocio.setBounds(100, 100, 908, 337);
		frmCancelarReservaSocio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmCancelarReservaSocio.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Reservas");

		List<Object[]> lista = clientesModel.getDNITodos();
		String[] DNI_socio = new String[lista.size()];
		Iterator<Object[]> iterador = lista.iterator();

		int i = 0;
		while (iterador.hasNext()) {
			DNI_socio[i] = iterador.next()[0].toString();
			i++;
		}

		JScrollPane scrollPane = new JScrollPane();
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "ID Reserva", "ID Socio", "ID Instalación", "Fecha", "Precio (€)", "Pagado" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setEnabled(false);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(DNI_socio));
		String selectedDNI = (String) comboBox.getSelectedItem();
		updateTable(selectedDNI);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedDNI = (String) comboBox.getSelectedItem();
				updateTable(selectedDNI);
			}
		});

		JButton btnNewButton = new JButton("Cancelar Reserva");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idReserva;

				int idsocio = clientesModel.getID(comboBox.getSelectedItem().toString());
				if (!textField.getText().equals("")) {
					idReserva = textField.getText();
					try {
						if ((reservasModel.existeReserva(Integer.parseInt(idReserva)))
								&& (reservasModel.getCliente(idReserva).toString().equals(Integer.toString(idsocio)))) {
							if (reservasPagadas.contains(idReserva)) {
								double cuota = reservasModel.nuevaCuota(idsocio);
								double devolucion = reservasModel.getPrecio(Integer.parseInt(idReserva));
								reservasModel.añadeacuota(cuota - devolucion, idsocio);
								try {
									String ruta = "src/main/resources/ReservasCanceladas/" + "Reserva" + idReserva
											+ "_Socio" + Integer.toString(idsocio) + ".txt";
									String contenido = "Se ha cancelado la Reserva con ID " + idReserva
											+ "\nAl estar la reserva ya abonada se le devolverán " + devolucion
											+ "€ al final del mes.\n";
									File file = new File(ruta);
									if (!file.exists()) {
										file.createNewFile();
									}
									FileWriter fw = new FileWriter(file);
									BufferedWriter bw = new BufferedWriter(fw);
									bw.write(contenido);
									bw.close();

								} catch (Exception e1) {
									JOptionPane.showMessageDialog(frmCancelarReservaSocio,
											"Introduzca un ID de reserva que sea válido");
								}
								pagosModel.eliminaPagoReserva(idReserva);
								reservasModel.eliminarReserva(idReserva);
								updateTable(comboBox.getSelectedItem().toString());
								JOptionPane.showMessageDialog(frmCancelarReservaSocio,
										"Reserva eliminada, al estar ya abonada, se le devolverá " + devolucion
												+ "€ al final del mes");
							} else {
								try {
									String ruta = "src/main/resources/ReservasCanceladas/" + "Reserva" + idReserva
											+ "_Socio" + Integer.toString(idsocio) + ".txt";
									String contenido = "Se ha cancelado la Reserva con ID " + idReserva
											+ "\nAl no estar abonado su importe, no se le devolverá nada de dinero.\n";
									File file = new File(ruta);
									if (!file.exists()) {
										file.createNewFile();
									}
									FileWriter fw = new FileWriter(file);
									BufferedWriter bw = new BufferedWriter(fw);
									bw.write(contenido);
									bw.close();
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(frmCancelarReservaSocio,
											"Introduzca un ID de reserva que sea válido");
								}
								reservasModel.eliminarReserva(idReserva);
								updateTable(comboBox.getSelectedItem().toString());
								JOptionPane.showMessageDialog(frmCancelarReservaSocio,
										"Reserva eliminada, Al no estar abonado su importe, no se le devolverá nada de dinero.");

							}
						} else {
							JOptionPane.showMessageDialog(frmCancelarReservaSocio,
									"Introduzca un ID de reserva que sea válido", "Error de id",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCancelarReservaSocio.dispose();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("DNI Socio");

		GroupLayout groupLayout = new GroupLayout(frmCancelarReservaSocio.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE));

		JLabel lblNewLabel_2 = new JLabel("ID Reserva");

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() < '0' || e.getKeyChar() > '9') {
					e.setKeyChar((char) 127);
				}
			}
		});
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(36)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														570, Short.MAX_VALUE)
												.addGroup(gl_panel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 499,
																Short.MAX_VALUE)
														.addComponent(btnNewButton_1)))
										.addGap(21))
								.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel).addGap(41)
										.addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 170,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())))
				.addGroup(gl_panel.createSequentialGroup().addGap(88)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
						.addGap(247)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(22)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
						.addGap(33)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(lblNewLabel_2).addComponent(textField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE).addComponent(btnNewButton_1)
						.addContainerGap()));

		panel.setLayout(gl_panel);
		frmCancelarReservaSocio.getContentPane().setLayout(groupLayout);
	}

	private void updateTable(String dni) {
		model.setRowCount(0);
		int idsocio = clientesModel.getID(dni);
		lReservas = reservasModel.todasReservasSocio(idsocio);
		lPagos = pagosModel.getPagosCliente(dni);
		lPagos2 = new String[lPagos.size()];
		reservasPagadas = new Vector<String>();
		for (int i = 0; i < lPagos.size(); i++) {
			lPagos2[i] = lPagos.get(i)[0].toString();
			reservasPagadas.add(pagosModel.getReserva(lPagos2[i]));
		}

		if (lReservas.size() == 0) {
			System.out.println("Sin reservas");
		} else {
			Iterator<Object[]> iter = lReservas.iterator();
			while (iter.hasNext()) {
				String pagada2 = "";
				Object[] datos = iter.next();
				String id_reserva = datos[0].toString();
				String id_socio = datos[1].toString();
				Object instalacion = datos[2].toString();
				Object fecha = datos[3];
				String precio = datos[4].toString();
				if (reservasPagadas.contains(datos[0].toString())) {
					pagada2 = "Sí";
				} else {
					pagada2 = "No";
				}
				model.addRow(new Object[] { id_reserva, id_socio, instalacion, fecha, precio, pagada2 });

			}
		}
	}

	public Window getFrmCancelarSocio() {
		return this.frmCancelarReservaSocio;
	}
}
