package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.toedter.calendar.JDateChooser;

import java.awt.GridLayout;
import java.awt.Window;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;

public class ReservaInstalacionFechaDeterminada {
	private InstalacionesModel modeloInstalaciones = new InstalacionesModel();
	private ReservasModel modeloReservas = new ReservasModel();
	private ClientesModel modeloClientes = new ClientesModel();
	private JFrame frmReservarInstalacionFechaDet;
	private JTextField textFieldIdSocio;
	private String precio_instalacion = "";
	private ModificarParametros modificaParametros = new ModificarParametros();
	Date actual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaInstalacionFechaDeterminada window = new ReservaInstalacionFechaDeterminada();
					window.frmReservarInstalacionFechaDet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReservaInstalacionFechaDeterminada() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservarInstalacionFechaDet = new JFrame();
		frmReservarInstalacionFechaDet.setTitle("Reservar Instalación");
		frmReservarInstalacionFechaDet.setBounds(100, 100, 552, 436);
		frmReservarInstalacionFechaDet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReservarInstalacionFechaDet.getContentPane().setLayout(new BorderLayout(0, 0));

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
		frmReservarInstalacionFechaDet.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmReservarInstalacionFechaDet.dispose();
			}
		});
		btnCancelar.setBounds(41, 349, 85, 21);
		panel.add(btnCancelar);

		JLabel lblInstalacion = new JLabel("Seleccione una Instalación:");
		lblInstalacion.setBounds(41, 38, 125, 13);
		panel.add(lblInstalacion);

		JComboBox comboBoxInstalacion = new JComboBox();
		comboBoxInstalacion.setModel(new DefaultComboBoxModel(nombre_instalaciones));
		comboBoxInstalacion.setBounds(176, 34, 215, 21);
		panel.add(comboBoxInstalacion);

		JLabel lblHoraInicioReserva = new JLabel("Hora de Inicio de la Reserva:");
		lblHoraInicioReserva.setBounds(41, 129, 133, 13);
		panel.add(lblHoraInicioReserva);

		JLabel lblFechaReserva = new JLabel("Fecha de la Reserva:");
		lblFechaReserva.setBounds(41, 87, 125, 13);
		panel.add(lblFechaReserva);

		JComboBox comboBoxHoraInicioReserva = new JComboBox();
		comboBoxHoraInicioReserva.setModel(new DefaultComboBoxModel(new String[] { "09:00", "10:00", "11:00", "12:00",
				"13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
		comboBoxHoraInicioReserva.setBounds(176, 125, 111, 21);
		panel.add(comboBoxHoraInicioReserva);

		JDateChooser dateChooser_FechaReserva = new JDateChooser();
		dateChooser_FechaReserva.setBounds(176, 81, 156, 19);
		dateChooser_FechaReserva.setDate(actual);
		panel.add(dateChooser_FechaReserva);

		JLabel lblHoraFinReserva = new JLabel("Hora de Fin de Reserva:");
		lblHoraFinReserva.setBounds(41, 171, 125, 13);
		panel.add(lblHoraFinReserva);

		JComboBox comboBoxHoraFinReserva = new JComboBox();
		comboBoxHoraFinReserva.setModel(new DefaultComboBoxModel(new String[] { "10:00", "11:00", "12:00", "13:00",
				"14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00" }));
		comboBoxHoraFinReserva.setBounds(176, 167, 111, 21);
		panel.add(comboBoxHoraFinReserva);

		// El botón reservar lo añadimos el último porque necesita información del resto
		// de componentes de la interfaz
		JButton btnReservar = new JButton("Reservar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_instalacion;

				// Creamos una lista con los Id de las instalaciones que se encuentren en el
				// comboBox
				List<Object[]> lista_idsInstalaciones = modeloInstalaciones
						.getId_Instalacion((String) comboBoxInstalacion.getSelectedItem());
				// Creamos un array de strings de tanto tamaño como instalaciones haya en el
				// comboBox
				String[] vNombres_Instalaciones = new String[lista_idsInstalaciones.size()];
				Iterator<Object[]> iterador = lista_idsInstalaciones.iterator();

				// Metemos dentro del array de strings los nombres de las instalaciones que hay
				// en el comboBox
				int i = 0;
				while (iterador.hasNext()) {
					vNombres_Instalaciones[i] = iterador.next()[0].toString();
					i++;
				}

				// Fecha
				String dia = formato.format(dateChooser_FechaReserva.getDate());
				String hora_inicio = (String) comboBoxHoraInicioReserva.getSelectedItem();
				String hora_fin = (String) comboBoxHoraFinReserva.getSelectedItem();
				String diaYhora_inicio = dia + " " + hora_inicio;
				String diaYhora_fin = dia + " " + hora_fin;

				LocalTime hora1 = LocalTime.parse(hora_inicio);
				int hora_de_inicioReserva = hora1.getHour();

				LocalTime hora2 = LocalTime.parse(hora_fin);
				int hora_de_finReserva = hora2.getHour();

				int diferencia_horas = hora_de_finReserva - hora_de_inicioReserva;

				Date hoy_mismo = new Date();

				Calendar calendario_prueba = Calendar.getInstance();
				calendario_prueba.setTime(hoy_mismo);
				calendario_prueba.add(Calendar.DAY_OF_YEAR, modificaParametros.leerCSV(1));
				Date datedespues = calendario_prueba.getTime();
				String despues = formato.format(datedespues);
				String ahora = formato.format(hoy_mismo);

				// Creamos una variable entera para la hora de inicio de la reserva
				int indice = comboBoxHoraInicioReserva.getSelectedIndex();

				// fecha actual
				Date fecha_actual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

				// Realizamos el cálculo entre la fecha de inicio del comboBox y la fecha actual
				long diferencia_en_dias = 0;
				long diferencia_en_años = 0;
				try {
					// fecha del comboBoxInicio pasada de string a fecha
					Date fecha_inicio_comboBox = formato.parse(dia);

					// Calculamos la diferencia entre fecha inicio del comboBox y fecha actual en
					// milisegundos
					long difference_In_Time = fecha_inicio_comboBox.getTime() - fecha_actual.getTime();

					// Tras calcular esa diferencia, pasamos ese resultado a:
					// segundos
					long difference_In_Seconds = (difference_In_Time / 1000) % 60;

					// minutos
					long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

					// horas
					long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

					// días
					long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
					diferencia_en_dias = difference_In_Days;

					// años
					long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
					diferencia_en_años = difference_In_Years;
				}

				// Catch the Exception
				catch (ParseException excepcion) {
					excepcion.printStackTrace();
				}

				// Establecemos como id de instalacion el primer elemento del comboBox
				id_instalacion = vNombres_Instalaciones[0];

				// Establecemos el precio de la instalación seleccionada en el comboBox
				precio_instalacion = modeloInstalaciones.getPrecio((String) comboBoxInstalacion.getSelectedItem());

				// Establecemos el id del socio que hace la reserva
				String id_socio;
				id_socio = textFieldIdSocio.getText();

				// nInstalación
				String nInstalacion = comboBoxInstalacion.getSelectedItem().toString();

				SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date fecha_inicio = null;
				try {
					fecha_inicio = formato2.parse(diaYhora_inicio);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*
				 * // Pruebas SimpleDateFormat formato2 = new
				 * SimpleDateFormat("yyyy-MM-dd HH:mm"); Date fecha_comienzo_reserva = null; try
				 * { fecha_comienzo_reserva = formato.parse(diaYhora_inicio); } catch
				 * (ParseException e1) { // TODO Auto-generated catch block
				 * e1.printStackTrace(); } Calendar fechaInicio_while = Calendar.getInstance();
				 * fechaInicio_while.setTime(fecha_comienzo_reserva);
				 * 
				 * Date fecha_final_reserva = null; try { fecha_final_reserva =
				 * formato.parse(diaYhora_fin); } catch (ParseException e1) { // TODO
				 * Auto-generated catch block e1.printStackTrace(); } Calendar fechaFin_while =
				 * Calendar.getInstance(); fechaFin_while.setTime(fecha_final_reserva);
				 */

				// Pasamos a comprobar la disponibilidad de la instalación
				if (modeloReservas.comprobarDisponibilidad(id_instalacion, diaYhora_inicio)) {
					if (modeloClientes.validarId(id_socio)) {
						if (diferencia_en_dias >= 0 && diferencia_en_años >= 0) {
							// no se puede con 15 dias antelacion
							// meter que sea parametrizable ese 15
							if (diferencia_en_dias <= modificaParametros.leerCSV(1) || diferencia_en_años > 0) {
								// meter por aquí un if para que haya horas máximas que sean parametrizables
								if (hora_de_inicioReserva < hora_de_finReserva) {
									// if de horas max por dia, comparar si horas max de parámetros >=
									// modeloReservas.horas_reservadas(fechaActual,
									// fechaActualHastaDiasParametrizables, idSocio)
									// meter como un || al siguiente if
									if (diferencia_horas < modificaParametros.leerCSV(3)) {
										int horas_probando = modeloReservas.horas_reservadas(
												ahora, despues, id_socio);
										if (modificaParametros.leerCSV(2) >= horas_probando + diferencia_horas) {
											horas_probando += diferencia_horas;
											System.out.println(modificaParametros.leerCSV(2));
											System.out.println(modeloReservas.horas_reservadas(
												ahora, despues, id_socio) + diferencia_horas);
											//System.out.println(ahora);
											for (int k = 0; k <= hora_de_finReserva - hora_de_inicioReserva; k++) {
												Calendar cal = Calendar.getInstance(); // Crear un objeto Calendar
												cal.setTime(fecha_inicio); // Establecer la fecha y hora de inicio de la
																			// reserva
												cal.add(Calendar.HOUR_OF_DAY, k);
												Date fecha_reserva = cal.getTime();
												modeloReservas.nuevaReservaAct(Integer.parseInt(id_socio),
														Integer.parseInt(id_instalacion), formato.format(fecha_actual),
														formato2.format(fecha_reserva), precio_instalacion, 0);
											}
											JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
													"La reserva se ha hecho correctamente.\n" + "  Coste reserva: "
															+ precio_instalacion + "\n  Nº Socio solicitante: "
															+ id_socio + "\n  Instalación reservada: " + nInstalacion
															+ "\n  Reservada el " + dia + " de " + hora_inicio + " a "
															+ hora_fin + "\n Formulario guardado con éxito.");
											// formulario de reserva exitosa
											try {
												String ruta = "src/main/resources/ReservaSocio" + id_socio
														+ nInstalacion + dia + ".txt";
												String contenido = "Se ha completado la reserva para el día " + dia
														+ " desde las " + hora_inicio + " hasta las " + hora_fin + "\n"
														+ "El socio nº " + id_socio + " ha reservado " + nInstalacion
														+ "\n" + "El coste por hora de la reserva es: "
														+ precio_instalacion;
												File file = new File(ruta);
												if (!file.exists()) {
													file.createNewFile();
												}
												FileWriter fw = new FileWriter(file);
												BufferedWriter bw = new BufferedWriter(fw);
												bw.write(contenido);
												bw.close();

											} catch (Exception e1) {
												e1.printStackTrace();
											}
										} else {
											JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
													"Un socio no puede reservar mas de " + modificaParametros.leerCSV(2)
															+ " horas en total.",
													"No puedes reservar", JOptionPane.ERROR_MESSAGE);
										}
									} else {
										JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
												"No puedes reservar más de " + modificaParametros.leerCSV(3)
														+ " horas seguidas.",
												"Ha ocurrido un error al hacer la reserva", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
											"No puedes seleccionar una hora de fin de reserva anterior o igual a la de inicio",
											"Ha ocurrido un error al hacer la reserva", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
										"No se puede reservar con más de " + modificaParametros.leerCSV(1)
												+ " días de antelación.",
										"Ha ocurrido un error al hacer la reserva", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
									"No puedes realizar una reserva para una fecha que ya ha pasado.",
									"Ha ocurrido un error al hacer la reserva", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
								"No se puede reservar porque el número de socio no es válido.",
								"Ha ocurrido un error al hacer la reserva", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
							"La instalación está ocupada en ese momento.", "Ha ocurrido un error al hacer la reserva",
							JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		btnReservar.setBounds(413, 349, 85, 21);
		panel.add(btnReservar);

		JLabel lblIdSocio = new JLabel("Número de socio:");
		lblIdSocio.setBounds(41, 231, 102, 13);
		panel.add(lblIdSocio);

		textFieldIdSocio = new JTextField();
		textFieldIdSocio.setBounds(176, 228, 111, 19);
		panel.add(textFieldIdSocio);
		textFieldIdSocio.setColumns(10);
	}

	public Window getFrmReservaInstalacionFechaDeterminada() {
		return this.frmReservarInstalacionFechaDet;
	}
}
