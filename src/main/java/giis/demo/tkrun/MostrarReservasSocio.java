package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class MostrarReservasSocio {

	private JFrame MostrarReservasSocio;
	private JTable table;
	private ReservasModel ModeloReservas = new ReservasModel();
	private InicioSesion vInicioSesion;
	private String[] titulos = { "Instalación", "Fecha", "Hora" };
	private int idSocio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarReservasSocio window = new MostrarReservasSocio();
					window.MostrarReservasSocio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MostrarReservasSocio() {
		initialize();
	}

	public MostrarReservasSocio(InicioSesion vInicioSesion) {
		initialize();
		idSocio = vInicioSesion.getId_socio();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MostrarReservasSocio = new JFrame();
		MostrarReservasSocio.setTitle("Mostrar Reservas");
		MostrarReservasSocio.setBounds(100, 100, 450, 300);
		MostrarReservasSocio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		MostrarReservasSocio.getContentPane().add(panel, BorderLayout.CENTER);

		JDateChooser dateChooserInicio = new JDateChooser();

		JDateChooser dateChooserFin = new JDateChooser();

		JLabel lblNewLabel = new JLabel("Fecha inicio:");

		JLabel lblFechaFin = new JLabel("Fecha fin:");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnMostrarReservas = new JButton("Mostrar reservas");
		btnMostrarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaInicio = dateChooserInicio.getDate();
				Date fechaFin = dateChooserFin.getDate();

				if ((fechaInicio == null) || (fechaFin == null)) {
					JOptionPane.showMessageDialog(MostrarReservasSocio,
							"Selecciona una fecha final y una fecha inicial", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (fechaFin.getTime() - fechaInicio.getTime() <= 0) {
						JOptionPane.showMessageDialog(MostrarReservasSocio,
								"Selecciona una fecha final posterior a la inicial", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// fechaInicio
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:01");
						String fechaInicioS = sdf.format(dateChooserInicio.getDate());
						String Inicio = fechaInicioS;

						// fechaFin
						SimpleDateFormat sdfk = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
						String fechaFinS = sdfk.format(dateChooserFin.getDate());
						String Fin = fechaFinS;

						completarTabla(table, Inicio, Fin, idSocio);
					}
				}

			}
		});

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarReservasSocio.dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap(350, Short.MAX_VALUE)
								.addComponent(btnAceptar).addGap(29))
						.addGroup(Alignment.LEADING,
								gl_panel.createSequentialGroup().addContainerGap().addGroup(gl_panel
										.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
												.createSequentialGroup()
												.addComponent(
														scrollPane, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
												.addContainerGap())
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 87,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(dateChooserInicio, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
												.addComponent(lblFechaFin, GroupLayout.PREFERRED_SIZE, 87,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(dateChooserFin, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(23))))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup().addGap(148)
								.addComponent(btnMostrarReservas).addContainerGap(177, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblFechaFin)
								.addComponent(dateChooserFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel).addComponent(dateChooserInicio, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(btnMostrarReservas).addGap(11)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(btnAceptar).addContainerGap(20, Short.MAX_VALUE)));

		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		panel.setLayout(gl_panel);
	}

	public Window getMostrarReservasSocio() {
		return this.MostrarReservasSocio;
	}

	public void completarTabla(JTable tabla, String Inicio, String Fin, int idSocio) {
		List<Object[]> listaReservas = ModeloReservas.getReservasPeriodo(idSocio, Inicio, Fin);

		System.out.println("El ID socio es: " + idSocio);
		System.out.println("Logitud lista del model: " + listaReservas.size());
		imprimirLista(procesarFechas(listaReservas));
		listaReservas = procesarFechas(listaReservas);

		Object[][] matrizDatos = new Object[listaReservas.size() + 1][3];
		Iterator<Object[]> iterador = listaReservas.iterator();
		int i = 1;
		while (iterador.hasNext()) {
			Object[] vector = new Object[3];
			vector = iterador.next();
			for (int j = 0; j < 3; j++) {
				matrizDatos[i][j] = vector[j];
			}
			i++;
		}

		matrizDatos[0][0] = "Instalación";
		matrizDatos[0][1] = "Fecha";
		matrizDatos[0][2] = "Hora";

		construirTabla(matrizDatos, tabla, titulos);

	}

	public static void imprimirLista(List<Object[]> lista) {
		for (Object[] objArray : lista) {
			for (Object obj : objArray) {
				System.out.print(obj + " ");
			}
			System.out.println();
		}
	}

	public static List<Object[]> procesarFechas(List<Object[]> listaReservas) {
		List<Object[]> listaProcesada = new ArrayList<>();

		SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat formatoFechaSalida = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatoHoraSalida = new SimpleDateFormat("HH:mm");

		for (Object[] reserva : listaReservas) {
			try {
				Date fecha = formatoEntrada.parse(reserva[1].toString());

				Object[] reservaProcesada = new Object[3];
				reservaProcesada[0] = reserva[0];
				reservaProcesada[1] = formatoFechaSalida.format(fecha);
				reservaProcesada[2] = formatoHoraSalida.format(fecha);

				listaProcesada.add(reservaProcesada);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return listaProcesada;
	}

	public void construirTabla(Object[][] matriz, JTable tabla, String[] nombresColumnas) {
		DefaultTableModel modelo = new DefaultTableModel(matriz, nombresColumnas);
		tabla.setModel(modelo);
	}
}
