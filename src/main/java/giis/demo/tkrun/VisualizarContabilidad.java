package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class VisualizarContabilidad {

	private JFrame VisualizarContabilidad;
	private JTable table;
	private ClientesModel ModeloClientes = new ClientesModel();
	private String[] titulos = { "Nombre", "Dni", "Precio Actividades", "Precio Reservas", "Total" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarContabilidad window = new VisualizarContabilidad();
					window.VisualizarContabilidad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualizarContabilidad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		VisualizarContabilidad = new JFrame();
		VisualizarContabilidad.setTitle("Visualizador de la contabilidad");
		VisualizarContabilidad.setBounds(100, 100, 450, 300);
		VisualizarContabilidad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		VisualizarContabilidad.getContentPane().add(panel, BorderLayout.CENTER);

		JButton btnContabilidad = new JButton("Mostar la contabilidad");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnAceptar = new JButton("Aceptar");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(151).addComponent(btnContabilidad))
								.addGroup(gl_panel.createSequentialGroup().addGap(47).addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(51, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(339, Short.MAX_VALUE)
						.addComponent(btnAceptar).addGap(30)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(btnContabilidad).addGap(55)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE).addComponent(btnAceptar)
						.addContainerGap()));

		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		panel.setLayout(gl_panel);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarContabilidad.dispose();
			}
		});

		btnContabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completarTabla(table, obtenerFechaInicial(), obtenerFechaFinal());

			}
		});
	}

	public Window getVisualizarContabilidad() {
		return this.VisualizarContabilidad;
	}

	public static void imprimirLista(List<Object[]> lista) {
		for (Object[] objArray : lista) {
			for (Object obj : objArray) {
				System.out.print(obj + " ");
			}
			System.out.println();
		}
	}

	public void completarTabla(JTable tabla, String Inicio, String Fin) {
		List<Object[]> listaContabilidadReservas = ModeloClientes.getContabilidadReservas(Inicio, Fin);
		List<Object[]> listaContabilidadActividades = ModeloClientes.getContabilidadActividades(Inicio, Fin);
		List<Object[]> listaContabilidad = sumaContabilidades(
				combinaObjetos(listaContabilidadReservas, listaContabilidadActividades));

		/*
		 * System.out.println("Logitud lista reservas: " +
		 * listaContabilidadReservas.size()); imprimirLista(listaContabilidadReservas);
		 * 
		 * System.out.println("Logitud lista actividades: " +
		 * listaContabilidadActividades.size());
		 * imprimirLista(listaContabilidadActividades);
		 * 
		 * System.out.println("Logitud lista CONTABILIDAD: " +
		 * listaContabilidad.size()); imprimirLista(listaContabilidad);
		 */

		Object[][] matrizDatos = new Object[listaContabilidad.size() + 1][5];
		Iterator<Object[]> iterador = listaContabilidad.iterator();
		int i = 1;
		while (iterador.hasNext()) {
			Object[] vector = new Object[5];
			vector = iterador.next();
			for (int j = 0; j < 5; j++) {
				matrizDatos[i][j] = vector[j];
			}
			i++;
		}

		matrizDatos[0][0] = "Nombre";
		matrizDatos[0][1] = "DNI";
		matrizDatos[0][2] = "Precio actividades";
		matrizDatos[0][3] = "Precio reservas";
		matrizDatos[0][4] = "Total";

		construirTabla(matrizDatos, tabla, titulos);

		creaTxt(matrizDatos);

	}

	public static List<Object[]> combinaObjetos(List<Object[]> list1, List<Object[]> list2) {
		List<Object[]> result = new ArrayList<>();

		for (Object[] obj1 : list1) {
			for (Object[] obj2 : list2) {
				if (obj1[0].equals(obj2[0])) { // Si los primeros strings coinciden
					Object[] mergedObj = new Object[4];
					mergedObj[0] = obj1[0];
					mergedObj[1] = obj1[1];
					mergedObj[2] = obj1[2];
					mergedObj[3] = obj2[2];
					result.add(mergedObj);
				}
			}
		}

		for (Object[] obj1 : list1) {
			boolean foundMatch = false;
			for (Object[] obj2 : list2) {
				if (obj1[0].equals(obj2[0])) {
					foundMatch = true;
					break; // Si ya se encontró una coincidencia, salir del bucle interno
				}
			}
			if (!foundMatch) {
				Object[] newObj = new Object[4];
				newObj[0] = obj1[0];
				newObj[1] = obj1[1];
				newObj[2] = obj1[2];
				newObj[3] = 0;
				result.add(newObj);
			}
		}

		for (Object[] obj2 : list2) {
			boolean foundMatch = false;
			for (Object[] obj1 : list1) {
				if (obj1[0].equals(obj2[0])) {
					foundMatch = true;
					break; // Si ya se encontró una coincidencia, salir del bucle interno
				}
			}
			if (!foundMatch) {
				Object[] newObj = new Object[4];
				newObj[0] = obj2[0];
				newObj[1] = obj2[1];
				newObj[2] = 0;
				newObj[3] = obj2[2];
				result.add(newObj);
			}
		}

		return result;
	}

	public static List<Object[]> sumaContabilidades(List<Object[]> inputList) {
		List<Object[]> outputList = new ArrayList<>();
		for (Object[] obj : inputList) {
			String str1 = (String) obj[0];
			String str2 = (String) obj[1];
			double i1 = 0;
			double i2 = 0;
			if (obj[2] instanceof String) { // si es un String
				i1 = Double.parseDouble((String) obj[2]);
			} else {
				i1 = ((Number) obj[2]).doubleValue();
			}
			if (obj[3] instanceof String) { // si es un String
				i2 = Double.parseDouble((String) obj[3]);
			} else {
				i2 = ((Number) obj[3]).doubleValue();
			}

			double i3 = i1 + i2;
			Object[] newObj = new Object[] { str1, str2, i1, i2, i3 };
			outputList.add(newObj);
		}
		return outputList;
	}

	public void construirTabla(Object[][] matriz, JTable tabla, String[] nombresColumnas) {
		DefaultTableModel modelo = new DefaultTableModel(matriz, nombresColumnas);
		tabla.setModel(modelo);
	}

	public void creaTxt(Object[][] matrizDatos) {
		try {
			FileWriter writer = new FileWriter("contabilidad.txt");
			for (int i = 0; i < matrizDatos.length; i++) {
				for (int j = 0; j < matrizDatos[i].length; j++) {
					writer.write(matrizDatos[i][j].toString() + ";");
				}
				writer.write("\n");
			}
			writer.close();
			System.out.println("Matriz exportada correctamente en contabilidad.txt");
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al exportar la matriz a contabilidad.txt: " + e.getMessage());
		}
	}

	public static String obtenerFechaInicial() {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaCalculada;

		if (fechaActual.getDayOfMonth() < 25) {
			fechaCalculada = fechaActual.minusMonths(1).withDayOfMonth(25);
		} else {
			fechaCalculada = fechaActual.withDayOfMonth(1);
		}

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaFormateada = fechaCalculada.format(formato);

		return fechaFormateada;
	}

	public static String obtenerFechaFinal() {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaCalculada;

		if (fechaActual.getDayOfMonth() < 25) {
			fechaCalculada = fechaActual.withDayOfMonth(25);
		} else {
			fechaCalculada = fechaActual.plusMonths(1).withDayOfMonth(1);
		}

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaFormateada = fechaCalculada.format(formato);

		return fechaFormateada;
	}

}
