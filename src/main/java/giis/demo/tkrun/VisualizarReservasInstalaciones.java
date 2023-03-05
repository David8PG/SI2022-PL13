package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VisualizarReservasInstalaciones {

	private JFrame frameVisualizarReservas;
	private JTable tableHorario;
	private InstalacionesModel modeloInstalaciones = new InstalacionesModel();
	private ReservasModel modeloReservas = new ReservasModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarReservasInstalaciones window = new VisualizarReservasInstalaciones();
					window.frameVisualizarReservas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualizarReservasInstalaciones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameVisualizarReservas = new JFrame();
		frameVisualizarReservas.setTitle("Visualizar Reservas");
		frameVisualizarReservas.setBounds(100, 100, 888, 403);
		frameVisualizarReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Obtenemos los nombres de las instalaciones
		List<Object[]> lInstalaciones = modeloInstalaciones.getNombreInstalaciones();
		String[] nombre_instalaciones = new String[lInstalaciones.size()];
		Iterator<Object[]> iterador = lInstalaciones.iterator();
		int i = 0;
		while (iterador.hasNext()) {
			nombre_instalaciones[i] = iterador.next()[0].toString();
			i++;
		}

		JPanel panel = new JPanel();
		frameVisualizarReservas.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblInstalacion = new JLabel("Instalación:");
		lblInstalacion.setBounds(10, 9, 64, 21);
		panel.add(lblInstalacion);

		JComboBox comboBoxInstalacion = new JComboBox();
		comboBoxInstalacion.setModel(new DefaultComboBoxModel(nombre_instalaciones));
		comboBoxInstalacion.setBounds(81, 9, 116, 21);
		panel.add(comboBoxInstalacion);

		JLabel lblTextoFechaActual = new JLabel("Fecha Actual:");
		lblTextoFechaActual.setBounds(315, 13, 85, 17);
		panel.add(lblTextoFechaActual);

		JLabel lblFechaActual = new JLabel(getFechaActual());
		lblFechaActual.setBounds(412, 17, 80, 13);
		panel.add(lblFechaActual);

		JLabel lblTextoDiasMostrados = new JLabel("Días Mostrados:");
		lblTextoDiasMostrados.setBounds(315, 40, 119, 13);
		panel.add(lblTextoDiasMostrados);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(422, 37, 30, 20);
		spinner.setValue(leerCSV(0));
		panel.add(spinner);

		JButton btnMostrarReservas = new JButton("Mostrar Reservas");
		btnMostrarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Fecha inicio

				completarTabla(tableHorario, tratarFechaInicio(getFechaActual()),
						tratarFechaFin(sumarDiasFechaActual((int) spinner.getValue())), (int) spinner.getValue(),
						(String) comboBoxInstalacion.getSelectedItem());

			}
		});
		btnMostrarReservas.setBounds(179, 70, 116, 21);
		panel.add(btnMostrarReservas);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 111, 837, 180);
		panel.add(scrollPane);

		tableHorario = new JTable();
		scrollPane.setColumnHeaderView(tableHorario);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameVisualizarReservas.dispose();
			}
		});
		btnAceptar.setBounds(389, 301, 85, 21);
		panel.add(btnAceptar);

	}

	public Window getVisualizarReservas() {
		return this.frameVisualizarReservas;
	}

	public static String getFechaActual() {
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return fechaActual.format(formato);
	}

//Método que devuelve el int de un csv que esté en la posición que se le pase
	public static int leerCSV(int posicion) {
		int primerValor = 0;
		String ruta = "src/main/resources/parametros.csv";
		File file = new File(ruta);
		try {
			BufferedReader lector = new BufferedReader(new FileReader(file));
			String linea = lector.readLine(); // lee la primera línea del archivo
			String[] valor = linea.split(";"); // separa los valores por el punto y coma
			lector.close();
			primerValor = Integer.parseInt(valor[posicion]); // asigna el primer valor de la línea a la variable
																// primerValor
		} catch (Exception e) {
			System.out.println("Error al leer el archivo CSV: " + e.getMessage());
		}
		return primerValor; // retorna el primer valor del archivo CSV como un string vacío si ocurre un
							// error
	}

	// Metodo para sumar dias en un int a la fecha actual que retorna la fecha como
	// string
	public static String sumarDiasFechaActual(int diasAdicionales) {
		// Obtener la fecha actual
		LocalDate fechaActual = LocalDate.now();

		// Agregar los días adicionales a la fecha actual
		LocalDate fechaConDiasAdicionales = fechaActual.plusDays(diasAdicionales);

		// Formatear la fecha como un String
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaFormateada = fechaConDiasAdicionales.format(formatoFecha);

		return fechaFormateada;
	}

	// Metodos para convertir las fechas de formato yyyy-mm-dd a formato yyyy-mm-dd
	// hora inicio/fin:00:00 para filtrar en la BBDD
	public static String tratarFechaInicio(String dateString) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(dateString + " 10:00:00", inputFormatter);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return dateTime.format(outputFormatter);

	}

	public static String tratarFechaFin(String dateString) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(dateString + " 22:00:00", inputFormatter);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return dateTime.format(outputFormatter);
	}

	// Metodo para convertir fecha de formato "yyyy-MM-dd HH:mm:ss" a "yyyy-MM-dd"
	public static String reducirFecha(String fechaHora) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(fechaHora, inputFormatter);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return dateTime.format(outputFormatter);
	}

	// Metodo para sumar un dia a la fecha en formato "yyyy-MM-dd"
	public static String sumarUnDia(String fechaString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha = LocalDate.parse(fechaString, formatter);
		fecha = fecha.plusDays(1);
		return fecha.format(formatter);
	}

	public void completarTabla(JTable tabla, String inicio, String fin, int diferenciaDias, String instalacion) {

		List<Object[]> listaReservas = modeloReservas.getReservasInstalacionPeriodo(instalacion, inicio, fin);
		Object[][] matrizDatos = new Object[diferenciaDias][11];

		DefaultTableModel modelo = new DefaultTableModel();
		tabla.setModel(modelo);
		// Cabecera horas
		String[] horarios = new String[13];
		for (int i = 10; i <= 22; i++) {
			horarios[i - 10] = i + ":00";
		}
		// Cabecera fechas
		modelo.addColumn("Hora", horarios);
		for (int i = 0; i < diferenciaDias; i++) {
			modelo.addColumn("");
		}
		String fechaCabecera = reducirFecha(inicio);
		for (int i = 1; i < diferenciaDias + 1; i++) {
			tabla.setValueAt(fechaCabecera, 0, i);
			fechaCabecera = sumarUnDia(fechaCabecera);
		}

	}
}
