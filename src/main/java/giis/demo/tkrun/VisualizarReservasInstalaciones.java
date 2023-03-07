package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
import javax.swing.SpinnerNumberModel;
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

		// Model para que el spinner no pueda bajar de -1
		SpinnerNumberModel model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		JSpinner spinner = new JSpinner(model);
		spinner.setBounds(422, 37, 30, 20);
		spinner.setValue(leerCSV(0));
		panel.add(spinner);

		JButton btnMostrarReservas = new JButton("Mostrar Reservas");
		btnMostrarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				actualizaModelo(comboBoxInstalacion);

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
		tableHorario.setEnabled(false);
		tableHorario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		generaTitulos();
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

	// Metodo para convertir las fechas de formato yyyy-mm-dd a formato yyyy-mm-dd
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

	public static int fechaToHora(String dateString) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = (Date) formatter.parse(dateString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	// Metodo para añadir un vector a la primera columna de una matriz
	public String[][] añadeColumna(String[][] matrix, String[] vector) {
		String[][] result = new String[matrix.length][matrix[0].length + 1];

		for (int i = 0; i < matrix.length; i++) {
			result[i][0] = vector[i];
			for (int j = 0; j < matrix[0].length; j++) {
				result[i][j + 1] = matrix[i][j];
			}
		}

		return result;
	}

	public void completarTabla(JTable tabla, String inicio, String fin, int diferenciaDias, String instalacion) {
		List<Object[]> listaReservas = modeloReservas.getReservasInstalacionPeriodo(instalacion, inicio, fin);
		DefaultTableModel modelo = new DefaultTableModel();
		// String[] fechasReservas = getFechasReservas(listaReservas);
		tabla.setModel(modelo);
		// Cabecera horas
		String[] horarios = new String[14];
		for (int i = 10; i <= 22; i++) {
			horarios[i + 1 - 10] = i + ":00";
		}
		modelo.addColumn("Hora", horarios);
		// añadeColumna(matrizHorario, horarios);

		// Cabecera fechas
		for (int i = 0; i < diferenciaDias; i++) {
			modelo.addColumn(i);
		}
		String fechaCabecera = reducirFecha(inicio);
		for (int i = 1; i < diferenciaDias + 1; i++) {
			tabla.setValueAt(fechaCabecera, 0, i);
			// matrizHorario[0][i] = fechaCabecera;
			fechaCabecera = sumarUnDia(fechaCabecera);
		}
		int cont = 0;
		for (int i = 1; i < diferenciaDias + 1; i++) {
			for (int j = 1; j < 14; j++) {
				if (cont < listaReservas.size() && listaReservas.size() > 0) {
					if ((comparar(obtenerHoraDesdeString(listaReservas.get(cont)[0].toString() + ":00"),
							tabla.getValueAt(j, 0).toString()))
							&& comparar(obtenerFechaDesdeString(listaReservas.get(cont)[0].toString()),
									tabla.getValueAt(0, i).toString())) {
						if (listaReservas.get(cont)[1] == null) {
							tabla.setValueAt("null", j, i);
						} else {
							tabla.setValueAt(listaReservas.get(cont)[1].toString(), j, i);
						}
						cont = cont + 1;
					}
				}
				if (tabla.getValueAt(j, i) == null || listaReservas.size() == 0) {
					tabla.setValueAt("Libre", j, i);
				}
			}
		}
	}

	public static boolean comparar(String cadena1, String cadena2) {
		return cadena1.equals(cadena2);
	}

	public static String obtenerHoraDesdeString(String fechaString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime fecha = LocalDateTime.parse(fechaString, formatter);
		int hora = fecha.getHour();
		return String.valueOf(hora) + ":00";
	}

	public String obtenerFechaDesdeString(String dateStr) {
		LocalDateTime datetime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
		LocalDate date = datetime.toLocalDate();
		return date.toString();
	}

	// Método para sacar la fecha de la reserva de la lista de objetos que devueve
	// el model
	public static String[] getFechasReservas(List<Object[]> objectList) {
		String[] result = new String[objectList.size()];
		for (int i = 0; i < objectList.size(); i++) {
			Object[] objArray = objectList.get(i);
			Long objLong = (Long) objArray[1];
			result[i] = objLong.toString();
		}
		return result;
	}
}
