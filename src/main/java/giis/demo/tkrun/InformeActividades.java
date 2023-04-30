package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
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

public class InformeActividades {

	private JFrame InformeActividades;
	private JTable tabla;
	private ActividadesModel ModeloActividades = new ActividadesModel();
	private String[] titulos = { "Nombre Actividad", "Plazas", "Inscritos", "Inscritos en lista de espera",
			"% de ocupación", "Número de edición" };
	private Object[][] matrizDatos;
	private List<String> listaActividades = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeActividades window = new InformeActividades();
					window.InformeActividades.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InformeActividades() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		InformeActividades = new JFrame();
		InformeActividades.setTitle("Informe Actividades");
		InformeActividades.setBounds(100, 100, 450, 300);
		InformeActividades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		InformeActividades.getContentPane().add(panel, BorderLayout.CENTER);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeActividades.dispose();
			}
		});

		JDateChooser dateChooserInicio = new JDateChooser();

		JDateChooser dateChooserFin = new JDateChooser();

		JScrollPane scrollPane = new JScrollPane();

		JButton btnGenerarInforme = new JButton("Generar informe");
		btnGenerarInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creaTxt(matrizDatos);
			}
		});

		JButton btnMostrarInforme = new JButton("Mostrar informe");
		btnMostrarInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaInicio = dateChooserInicio.getDate();
				Date fechaFin = dateChooserFin.getDate();

				if ((fechaInicio == null) || (fechaFin == null)) {
					JOptionPane.showMessageDialog(InformeActividades, "Selecciona una fecha final y una fecha inicial",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (fechaFin.getTime() - fechaInicio.getTime() <= 0) {
						JOptionPane.showMessageDialog(InformeActividades,
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

						completarTabla(tabla, Inicio, Fin);
						// System.out.println(obtenerDiferenciaFechas(Inicio, Fin));
					}
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Inicio periodo:");

		JLabel lblNewLabel_1 = new JLabel("Fin periodo:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel.createSequentialGroup().addContainerGap()
										.addGroup(gl_panel
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(btnGenerarInforme)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(btnSalir).addContainerGap())
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
																		105, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(
																		dateChooserInicio, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, 55,
																		Short.MAX_VALUE)
																.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE,
																		85, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(dateChooserFin,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(33))
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(btnMostrarInforme).addGap(161)))
												.addGroup(Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
																		403, GroupLayout.PREFERRED_SIZE)
																.addContainerGap()))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(dateChooserFin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(dateChooserInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnMostrarInforme).addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalir).addComponent(btnGenerarInforme))
				.addContainerGap()));

		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		panel.setLayout(gl_panel);
	}

	public Window getVisualizarInformeActividades() {
		return this.InformeActividades;
	}

	public void completarTabla(JTable tabla, String Inicio, String Fin) {
		List<Object[]> listaInformeActividades = ModeloActividades.getInformeActividades(Inicio, Fin);

		matrizDatos = new Object[listaInformeActividades.size()][6];
		Iterator<Object[]> iterador = listaInformeActividades.iterator();
		int i = 0;
		while (iterador.hasNext()) {
			Object[] vector = new Object[6];
			vector = iterador.next();
			for (int j = 0; j < 6; j++) {
				matrizDatos[i][j] = vector[j];
			}
			i++;
		}
		listaActividades = obtenerUltimosNumeros(obtenerPrimeraColumna(matrizDatos));
		reemplazarColumna(matrizDatos, listaActividades);
		eliminarNumerosPrimeraFila(matrizDatos);
		construirTabla(matrizDatos, tabla, titulos);

	}

	public void creaTxt(Object[][] matrizDatos) {
		try {
			FileWriter writer = new FileWriter("Informe actividades.txt");
			writer.write("Nombre actividad" + ";" + "Número de plazas" + ";" + "Inscripciones" + ";" + "Lista de espera"
					+ ";" + "Porcentaje de ocupación" + ";" + "Número de edición" + ";");
			writer.write("\n");
			for (int i = 0; i < matrizDatos.length; i++) {
				for (int j = 0; j < matrizDatos[i].length; j++) {
					writer.write(matrizDatos[i][j].toString() + ";");
				}
				writer.write("\n");
			}
			writer.close();
			System.out.println("Matriz exportada correctamente en Informe actividades.txt");
		} catch (IOException e) {
			System.out
					.println("Ha ocurrido un error al exportar la matriz a Informe actividades.txt: " + e.getMessage());
		}
	}

	public void construirTabla(Object[][] matriz, JTable tabla, String[] nombresColumnas) {
		DefaultTableModel modelo = new DefaultTableModel(matriz, nombresColumnas);
		tabla.setModel(modelo);
	}

	public static List<String> obtenerPrimeraColumna(Object[][] matriz) {
		List<String> lista = new ArrayList<>();
		for (int i = 0; i < matriz.length; i++) {
			lista.add((String) matriz[i][0]);
		}
		return lista;
	}

	public static List<String> obtenerUltimosNumeros(List<String> lista) {
		List<String> resultado = new ArrayList<String>();
		for (String cadena : lista) {
			String[] palabras = cadena.split(" ");
			String ultimoNumero = "";
			for (int i = palabras.length - 1; i >= 0; i--) {
				String palabra = palabras[i];
				if (palabra.matches("\\d+")) {
					ultimoNumero = palabra;
					break;
				}
			}
			resultado.add(ultimoNumero);
		}
		return resultado;
	}

	public static void reemplazarColumna(Object[][] matriz, List<String> lista) {
		// Verificar que la matriz y la lista tengan la misma longitud
		if (matriz.length != lista.size()) {
			throw new IllegalArgumentException("La matriz y la lista deben tener la misma longitud");
		}
		// Recorrer la matriz y reemplazar los valores de la sexta columna con los de la
		// lista
		for (int i = 0; i < matriz.length; i++) {
			matriz[i][5] = lista.get(i);
		}
	}

	public static void eliminarNumerosPrimeraFila(Object[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			String str = matriz[i][0].toString();
			int espacioIndex = str.lastIndexOf(" ");
			if (espacioIndex != -1) {
				String newStr = str.substring(0, espacioIndex + 1);
				matriz[i][0] = newStr;
			}
		}
	}

}
