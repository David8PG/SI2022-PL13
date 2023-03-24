package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class CancelarReservaSocio {

	private JFrame frmCancelarReservaSocio;
	private JTable table;
	private InstalacionesModel instalacionesModel = new InstalacionesModel();
	private ReservasModel reservasModel = new ReservasModel();
	private ClientesModel clientesModel = new ClientesModel();
	private PagosModel pagosModel = new PagosModel();
	private InicioSesion sesion;
	int id_socio;
	private Iterator<Object[]> iter;
	private Object[][] matriz;
	private String[][] matriz2;
	private List<Object[]> lReservas;
	private Vector<String> reservasCancelar;
	private Vector<String> reservasPagadas;
	private List<Object[]> lPagos;
	private String[] lPagos2;

	private InicioSesion principal;
	private int i;
	private String seleccionado;
	private DefaultTableModel model;
	private long hoy;
	private String dni;
	private JLabel sinReservasLabel;
	private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

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
		this.id_socio = this.principal.getId_socio();
		this.id_socio = this.sesion.getId_socio();
		this.dni = clientesModel.getDNI(Integer.toString(id_socio));
		Calendar cal = Calendar.getInstance();
		hoy = cal.getTime().getTime();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancelarReservaSocio = new JFrame();
		frmCancelarReservaSocio.setBounds(100, 100, 643, 340);
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

		JButton btnNewButton = new JButton("Cancelar Reserva");

		JButton btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCancelarReservaSocio.dispose();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("DNI Socio");

		model = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Instalación", "Fecha", "Hora", "Instalación" }) {
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
		seleccionado = comboBox.getSelectedItem().toString();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedDNI = (String) comboBox.getSelectedItem();
				updateTable(selectedDNI);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmCancelarReservaSocio.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(36)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														570, Short.MAX_VALUE)
												.addGroup(gl_panel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 499,
																Short.MAX_VALUE)
														.addComponent(btnNewButton_1)))
										.addGap(21))
								.addGroup(Alignment.LEADING,
										gl_panel.createSequentialGroup().addComponent(lblNewLabel).addGap(41)
												.addComponent(lblNewLabel_1)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(comboBox,
														GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(250, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addGap(247)));

		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(22)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
						.addGap(33).addComponent(btnNewButton)
						.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE).addComponent(btnNewButton_1)
						.addContainerGap()));

		panel.setLayout(gl_panel);
		frmCancelarReservaSocio.getContentPane().setLayout(groupLayout);
	}

	private void updateTable(String dni) {
		model.setRowCount(0);
		int idsocio = clientesModel.getID(dni);
		lReservas = reservasModel.todasReservasSocio(idsocio);
		if (lReservas.size() == 0) {
			System.out.println("Sin reservas");
		} else {
			Iterator<Object[]> iter = lReservas.iterator();
			while (iter.hasNext()) {

				Object[] datos = iter.next();
				// System.out.println(datos[0]);
				// System.out.println(datos[1]);
				// System.out.println(datos[2]);
				// System.out.println(datos[3]);
				String id_reserva = datos[0].toString();
				String fecha = datos[2].toString();
				String precio = datos[3].toString();
				String instalacion = datos[1].toString();
				boolean pagada = false;

				model.addRow(new Object[] { id_reserva, instalacion, fecha, precio });

			}
		}
	}

	public Window getFrmCancelarSocio() {
		return this.frmCancelarReservaSocio;
	}
}
