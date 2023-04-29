package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

public class InformeInstalaciones {

	private JFrame InformeInstalaciones;
	private JTable tabla;
	private ReservasModel ModeloReservas = new ReservasModel();
	private String[] titulos = { "Nombre Instalación", "Nº Reservas", "Nº Actividades", "Porcentaje ocupación" };
	private Object[][] matrizDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeInstalaciones window = new InformeInstalaciones();
					window.InformeInstalaciones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InformeInstalaciones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		InformeInstalaciones = new JFrame();
		InformeInstalaciones.setTitle("Informe instalaciones");
		InformeInstalaciones.setBounds(100, 100, 450, 300);
		InformeInstalaciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		InformeInstalaciones.getContentPane().add(panel, BorderLayout.CENTER);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeInstalaciones.dispose();
			}
		});

		JDateChooser dateChooserInicio = new JDateChooser();

		JDateChooser dateChooserFin = new JDateChooser();

		JButton btnMostrarInforme = new JButton("Mostrar Informe");
		btnMostrarInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaInicio = dateChooserInicio.getDate();
				Date fechaFin = dateChooserFin.getDate();

				if ((fechaInicio == null) || (fechaFin == null)) {
					JOptionPane.showMessageDialog(InformeInstalaciones,
							"Selecciona una fecha final y una fecha inicial", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (fechaFin.getTime() - fechaInicio.getTime() <= 0) {
						JOptionPane.showMessageDialog(InformeInstalaciones,
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

						completarTabla(tabla, Inicio, Fin, 100);
					}
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Inicio periodo:");

		JLabel lblNewLabel_1 = new JLabel("Fin periodo:");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnGenerarInforme = new JButton("Generar Informe");
		btnGenerarInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creaTxt(matrizDatos);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(dateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(dateChooserFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(32))
				.addGroup(gl_panel.createSequentialGroup().addGap(168).addComponent(btnMostrarInforme)
						.addContainerGap(161, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(258, Short.MAX_VALUE)
						.addComponent(btnGenerarInforme).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSalir).addContainerGap())
				.addGroup(gl_panel.createSequentialGroup().addGap(19)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(23, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(25)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(dateChooserInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(dateChooserFin, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnMostrarInforme)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalir).addComponent(btnGenerarInforme))
				.addContainerGap()));

		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		panel.setLayout(gl_panel);
	}

	public Window getVisualizarInformeInstalaciones() {
		return this.InformeInstalaciones;
	}

	public void completarTabla(JTable tabla, String Inicio, String Fin, int horasMaximas) {
		List<Object[]> listaInformeInstalaciones = ModeloReservas.getInformeInstalaciones(horasMaximas, Inicio, Fin);

		matrizDatos = new Object[listaInformeInstalaciones.size()][4];
		Iterator<Object[]> iterador = listaInformeInstalaciones.iterator();
		int i = 0;
		while (iterador.hasNext()) {
			Object[] vector = new Object[4];
			vector = iterador.next();
			for (int j = 0; j < 4; j++) {
				matrizDatos[i][j] = vector[j];
			}
			i++;
		}

		construirTabla(matrizDatos, tabla, titulos);

	}

	public void creaTxt(Object[][] matrizDatos) {
		try {
			FileWriter writer = new FileWriter("Informe instalaciones.txt");
			writer.write("Nombre instalación" + ";" + "Número de reservas" + ";" + "Número de actividades" + ";"
					+ "Porcentaje de ocupación" + ";");
			writer.write("\n");
			for (int i = 0; i < matrizDatos.length; i++) {
				for (int j = 0; j < matrizDatos[i].length; j++) {
					writer.write(matrizDatos[i][j].toString() + ";");
				}
				writer.write("\n");
			}
			writer.close();
			System.out.println("Matriz exportada correctamente en Informe instalaciones.txt");
		} catch (IOException e) {
			System.out.println(
					"Ha ocurrido un error al exportar la matriz a Informe instalaciones.txt: " + e.getMessage());
		}
	}

	public void construirTabla(Object[][] matriz, JTable tabla, String[] nombresColumnas) {
		DefaultTableModel modelo = new DefaultTableModel(matriz, nombresColumnas);
		tabla.setModel(modelo);
	}
}
