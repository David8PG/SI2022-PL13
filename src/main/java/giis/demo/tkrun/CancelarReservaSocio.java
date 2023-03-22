package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class CancelarReservaSocio {

	private JFrame frmCancelarReservaSocio;
	private JTable table;
	private InstalacionesModel instalacionesModel = new InstalacionesModel();
	private ReservasModel reservasModel = new ReservasModel();
	private ClientesModel clientesModel = new ClientesModel();
	private InicioSesion sesion;
	int id_socio;
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
		this.sesion = login;
		this.id_socio = this.sesion.getId_socio();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancelarReservaSocio = new JFrame();
		frmCancelarReservaSocio.setBounds(100, 100, 450, 300);
		frmCancelarReservaSocio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmCancelarReservaSocio.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Reservas");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNewButton = new JButton("Cancelar Reserva");

		JButton btnNewButton_1 = new JButton("Aceptar");

		JLabel lblNewLabel_1 = new JLabel("ID socio");

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnNewButton_2 = new JButton("Buscar");
		GroupLayout gl_panel = new GroupLayout(frmCancelarReservaSocio.getContentPane());
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addGap(36)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel).addGap(127)
								.addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton_2)))
				.addContainerGap(30, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(342, Short.MAX_VALUE)
						.addComponent(btnNewButton_1).addGap(21))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(155, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addGap(149)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(22)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(btnNewButton)
						.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE).addComponent(btnNewButton_1)
						.addContainerGap()));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "ID Socio", "Instalación", "Fecha", "Coste", "Pago Realizado" }));
		table.setEnabled(true);
		scrollPane.setColumnHeaderView(table);

		frmCancelarReservaSocio.getContentPane().setLayout(gl_panel);
	}

	public void tablaRellenada(JTable tabla) {
		// Obtenemos una lista con los pagos del socio
		listaPagos = modeloPagos.getPagosCliente(dni);
		// pasamos esa lista a un array
		arrayPagos = new String[listaPagos.size()];
		reservasPagadas = new Vector<String>();
		reservasCancelables = new Vector<String>();
		for (int i = 0; i < listaPagos.size(); i++) {
			arrayPagos[i] = listaPagos.get(i)[0].toString();
			reservasPagadas.add(modeloPagos.getReserva(arrayPagos[i]));
		}

		// Obtenemos una lista con las reservas del socio
		listaReservas = modeloReservas.todasReservasSocio(id_socio);
		// si la lista no está vacia, mostramos los elementos
		if (!listaReservas.isEmpty()) {
			matriz = new Object[listaReservas.size()][5];
			it = listaReservas.iterator();
			i = 0;
			while (it.hasNext()) {
				// el vector es el siguiente elemento de la lista (una reserva en concreto del
				// cliente)
				vector = it.next();
				try {
					if (hoy + (principal.getDiasAntelacion() * 24 * 60 * 60 * 1000) < sdf.parse(vector[1].toString())
							.getTime()) {
						reservasCancelables.add(vector[0].toString());
						// bucle para recorer el vector
						for (int j = 0; j < 3; j++) {
							if (j == 0)// si es el id
								matriz[i][j] = vector[j];
							else if (j == 1) {// la fecha hay que separarla
								String[] a = (vector[j].toString()).split("T");
								matriz[i][j] = a[0];
								matriz[i][j + 1] = a[1];
							} else// ahora la j va atrasada
								matriz[i][j + 1] = vector[j];
						}
						if (reservasPagadas.contains(vector[0].toString()))
							matriz[i][4] = "Sí";
						else
							matriz[i][4] = "No";
						// modificamos el índice para movernos por las filas de la matriz
						i++;
					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// copia de las primeras i filas de matriz a matriz2 para que no queden filas en
			// blanco
			matriz2 = new String[i][5];
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < 5; k++) {
					matriz2[j][k] = matriz[j][k].toString();
				}
			}

		}
		// sino, indicamos que no hay reservas
		else {
			sinReservasLabel.setVisible(true);
		}

		table.setModel(
				new DefaultTableModel(matriz2, new String[] { "Id", "Fecha", "Hora", "Instalación", "Pagada", }));

	}
}
