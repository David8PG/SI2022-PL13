package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.Window;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReservaInstalacionActividadPeriodoDet {

	private JFrame frmReservaInstalacionActividadPeriodoDet;
	
	// Modelos con los que se va a trabajar
	private InstalacionesModel modeloInstalaciones = new InstalacionesModel();	
	private ReservasModel modeloReservas = new ReservasModel();
	private ClientesModel modeloClientes = new ClientesModel();
	private ActividadesModel modeloActividades = new ActividadesModel();
	private SesionesModel modeloSesiones = new SesionesModel();
	// A utilizar mas adelante
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	Date actual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	private boolean ejecutado = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaInstalacionActividadPeriodoDet window = new ReservaInstalacionActividadPeriodoDet();
					window.frmReservaInstalacionActividadPeriodoDet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReservaInstalacionActividadPeriodoDet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaInstalacionActividadPeriodoDet = new JFrame();
		frmReservaInstalacionActividadPeriodoDet.setTitle("Reserva para una Actividad");
		frmReservaInstalacionActividadPeriodoDet.setBounds(100, 100, 667, 431);
		frmReservaInstalacionActividadPeriodoDet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReservaInstalacionActividadPeriodoDet.getContentPane().setLayout(new BorderLayout(0, 0));
		
		// Obtenemos los nombres de las instalaciones
		List<Object[]> lInstalaciones = modeloInstalaciones.getNombreInstalaciones();
		String[] nombre_instalaciones = new String[lInstalaciones.size()];
		Iterator<Object[]> iterador_I = lInstalaciones.iterator();
		int i = 0;
		while (iterador_I.hasNext()) {
			nombre_instalaciones[i] = iterador_I.next()[0].toString();
			i++;
		}
		
		// Debemos obtener también el nombre de las actividades
		List<Object[]> lActividades = modeloActividades.getNombreActividades();
		String[] nombre_actividades = new String[lActividades.size()];
		Iterator<Object[]> iterador_A = lActividades.iterator();
		i = 0;
		while (iterador_A.hasNext()) {
			nombre_actividades[i] = iterador_A.next()[0].toString();
			i++;
		}
		
		JPanel panel = new JPanel();
		frmReservaInstalacionActividadPeriodoDet.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblInstalacion = new JLabel("Seleccione la Instalación:");
		lblInstalacion.setBounds(68, 129, 202, 13);
		panel.add(lblInstalacion);
		
		JLabel lblActividad = new JLabel("Seleccione la Actividad:");
		lblActividad.setBounds(68, 59, 202, 13);
		panel.add(lblActividad);
		
		JLabel lblFecha_Inicio = new JLabel("Fecha Inicio");
		lblFecha_Inicio.setBounds(68, 198, 252, 13);
		panel.add(lblFecha_Inicio);
		
		JLabel lblFecha_Fin = new JLabel("Fecha Final:");
		lblFecha_Fin.setBounds(68, 248, 252, 13);
		panel.add(lblFecha_Fin);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmReservaInstalacionActividadPeriodoDet.dispose();
			}
		});
		btnCancelar.setBounds(68, 346, 85, 21);
		panel.add(btnCancelar);
		
		JComboBox comboBoxInstalacion = new JComboBox();
		comboBoxInstalacion.setModel(new DefaultComboBoxModel(nombre_instalaciones));
		comboBoxInstalacion.setBounds(225, 125, 345, 21);
		panel.add(comboBoxInstalacion);
		
		JComboBox comboBoxActividad = new JComboBox();
		comboBoxActividad.setModel(new DefaultComboBoxModel(nombre_actividades));
		comboBoxActividad.setBounds(225, 55, 345, 21);
		panel.add(comboBoxActividad);
		
		JDateChooser dateChooser_FechaInicio = new JDateChooser();
		dateChooser_FechaInicio.setBounds(414, 192, 156, 19);
		dateChooser_FechaInicio.setDate(actual);
		panel.add(dateChooser_FechaInicio);
		
		JDateChooser dateChooser_FechaFin = new JDateChooser();
		dateChooser_FechaFin.setBounds(414, 248, 156, 19);
		dateChooser_FechaFin.setDate(actual);
		panel.add(dateChooser_FechaFin);
		
		JDateChooser dateChooser_FechaPeriodo = new JDateChooser();
		dateChooser_FechaPeriodo.setVisible(false);
		dateChooser_FechaPeriodo.setDate(actual);
		dateChooser_FechaPeriodo.setBounds(414,192,156,19);
		panel.add(dateChooser_FechaPeriodo);
		
		JComboBox comboBoxHoraIni = new JComboBox();
		comboBoxHoraIni.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
		comboBoxHoraIni.setVisible(false);
		comboBoxHoraIni.setBounds(348, 245, 106, 19);
		panel.add(comboBoxHoraIni);
		
		JComboBox comboBoxHoraFin = new JComboBox();
		comboBoxHoraFin.setModel(new DefaultComboBoxModel(new String[] {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		comboBoxHoraFin.setVisible(false);
		comboBoxHoraFin.setBounds(464, 245, 106, 19);
		panel.add(comboBoxHoraFin);
		
		JButton btnReservar = new JButton("Tramitar Reserva");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comprobacion1 = formato.format(dateChooser_FechaInicio.getDate());
				String comprobacion2 = formato.format(dateChooser_FechaFin.getDate());
				String vacio = "";
				if ((comprobacion1 == vacio) || (comprobacion2 == vacio)) {
					JOptionPane.showMessageDialog(frmReservaInstalacionActividadPeriodoDet,
							"Los campos Fecha Inicio y Fecha Fin no pueden estar vacíos.",
							"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
				}
				// Instalacion que se va a reservar
				String id_instalacion;

				// Creamos una lista con los Id de las instalaciones que se encuentren en el
				// comboBox
				List<Object[]> lIds_Instalaciones = modeloInstalaciones
						.getId_Instalacion((String) comboBoxInstalacion.getSelectedItem());
				// Creamos un array de strings de tanto tamaño como instalaciones haya en el
				// comboBox
				String[] vNombres_Instalaciones = new String[lIds_Instalaciones.size()];
				Iterator<Object[]> iterador = lIds_Instalaciones.iterator();
				List<String[]> lista_reservasCliente_eliminadas = new ArrayList<String[]>();

				// Metemos dentro del array de strings los nombres de las instalaciones que hay
				// en el comboBox
				int i = 0;
				while (iterador.hasNext()) {
					vNombres_Instalaciones[i] = iterador.next()[0].toString();
					i++;
				}

				id_instalacion = vNombres_Instalaciones[0];

				Date fecha_actual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				String diaYhora = "";
				
				if (ejecutado) {
					String fecha_inicioS = formato.format(dateChooser_FechaInicio.getDate());
					String fecha_finS = formato.format(dateChooser_FechaFin.getDate());

					// A continuación creamos las variables necesarias para hacer los cálculos de
					// fechas
					long diferencia_en_dias = 0;
					long diferencia_en_años = 0;
					Date fecha_inicio = new Date();
					Date fecha_fin = new Date();

					try {
						// Convertimos en formato fecha los string almacenados en el elemento calendario
						// de la interfaz
						fecha_inicio = formato.parse(fecha_inicioS);
						fecha_fin = formato.parse(fecha_finS);

						// Calculamos la diferencia entre fecha inicio del comboBox y fecha actual en
						// milisegundos
						long difference_In_Time = fecha_fin.getTime() - fecha_inicio.getTime();

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

					// Iniciamos el proceso de ver si la instalación está ocupada por una actividad
					Date fecha_inicioAct = dateChooser_FechaInicio.getDate();
					Calendar calendario = Calendar.getInstance();
					calendario.setTime(fecha_inicioAct);
					boolean sin_actividad = false;
					int reserva_socio = 0;
					LocalDate fecha_local = Instant.ofEpochMilli(fecha_inicioAct.getTime()).atZone(ZoneId.systemDefault())
							.toLocalDate();
					if (diferencia_en_dias >= 0 && diferencia_en_años >= 0) {
						sin_actividad = true;
						for (i = 0; i < diferencia_en_dias + diferencia_en_años * 365; i++) {
							// Debe recorrer las horas de posible reserva
							for (int j = 9; j <= 21; j++) {
								diaYhora = formato.format(calendario.getTime()) + " " + Integer.toString(j) + ":00:00";
								// -1, 0 o 1
								int dispo_instalacionAct = modeloReservas.comprobarDisponibilidadActividad(id_instalacion,
										diaYhora);
								if (dispo_instalacionAct == -1) {
									JOptionPane.showMessageDialog(frmReservaInstalacionActividadPeriodoDet,
											"La instalación ya está ocupada por otra actividad",
											"Ha ocurrido un error al hacer la reserva", JOptionPane.ERROR_MESSAGE);
									sin_actividad = false;
									continue;
								} else if (dispo_instalacionAct == 1) {
									reserva_socio = 1;
								}
							}
							// Añadimos 1 a la fecha de la instancia de calendario
							calendario.add(calendario.DATE, 1);
						}
					} else {
						JOptionPane.showMessageDialog(frmReservaInstalacionActividadPeriodoDet,
								"No puedes realizar una reserva para una fecha que ya ha pasado.",
								"Ha ocurrido un error al hacer la reserva", JOptionPane.ERROR_MESSAGE);
					}
					if (sin_actividad == true) {
						calendario.setTime(fecha_inicioAct);
						for (i = 0; i < diferencia_en_dias + diferencia_en_años * 365; i++) {
							for (int j = 9; j <= 21; j++) {
								diaYhora = formato.format(calendario.getTime()) + " " + Integer.toString(j) + ":00:00";
								// La instalacion está reservada pero sin actividad asignada, administrador
								// tiene prioridad, se elimina la reserva del socio
								if (reserva_socio == 1
										&& !modeloReservas.comprobarDisponibilidad(id_instalacion, diaYhora)) {
									lista_reservasCliente_eliminadas.add(new String[] { id_instalacion, diaYhora });
									modeloReservas.eliminarReserva(Integer.parseInt(id_instalacion), diaYhora);
								}
								// Almacenamos la nueva reserva que ha suplantado a la del socio sin actividad
								modeloReservas.nuevaReservaAct(0, Integer.parseInt(id_instalacion),
										formato.format(fecha_actual), diaYhora, "0",
										modeloActividades.getIdActividad((String) comboBoxActividad.getSelectedItem()));
							}
							// Añadimos 1 a la fecha de la instancia de calendario
							calendario.add(calendario.DATE, 1);
						}
						String mensaje = "Alguna hora estaba reservada por un cliente pero la administración tiene prioridad.\n"
								+ "Se han eliminado las siguientes reservas:\n";
						Iterator<String[]> iterador2 = lista_reservasCliente_eliminadas.iterator();
						String[] vector_iterador;
						while (iterador2.hasNext()) {
							vector_iterador = iterador2.next();
							mensaje += "\nInstalación: " + vector_iterador[0] + "\nDía y hora: " + vector_iterador[1];
						}
						if (reserva_socio == 1) {
							JOptionPane.showMessageDialog(frmReservaInstalacionActividadPeriodoDet, mensaje,
									"La reserva se ha realizado con éxito", JOptionPane.INFORMATION_MESSAGE);
						} else
							JOptionPane.showMessageDialog(frmReservaInstalacionActividadPeriodoDet, "Reservado.\n");
					}
				} else {
					int inicio = Integer.parseInt(comboBoxHoraIni.getSelectedItem().toString().split(":")[0]);
					int fin = Integer.parseInt(comboBoxHoraFin.getSelectedItem().toString().split(":")[0]);
					String dia_reserva = formato.format(dateChooser_FechaPeriodo.getDate());
					String hora_reserva = comboBoxHoraIni.getSelectedItem().toString();
					int seleccion_hora = comboBoxHoraIni.getSelectedIndex();
					int cliente_conReserva = 0;
					int cliente = 0;
					if (fin > inicio) {
						// Bucle para comprobar si todas las horas están disponibles
						for (int j = 0; j < fin - inicio; j++) {
							diaYhora = dia_reserva + " " + hora_reserva;
							cliente = modeloReservas.comprobarDisponibilidadActividad(id_instalacion, diaYhora);
							if (cliente == -1) {
								JOptionPane.showMessageDialog(frmReservaInstalacionActividadPeriodoDet,
										"Está ocupado por otra actividad.", "Error al reservar",
										JOptionPane.ERROR_MESSAGE);
								break;
							} else if (cliente == 1)
								cliente_conReserva = 1;
							hora_reserva = comboBoxHoraIni.getItemAt(seleccion_hora + j).toString();
						}
						if (cliente != -1) {

							hora_reserva = comboBoxHoraIni.getSelectedItem().toString();
							// Bucle para reservar todas las horas
							for (int j = 0; j < fin - inicio; j++) {
								hora_reserva = comboBoxHoraIni.getItemAt(seleccion_hora + j).toString();
								diaYhora = dia_reserva + " " + hora_reserva;
								modeloReservas.nuevaReservaAct(0, Integer.parseInt(id_instalacion), formato.format(fecha_actual), diaYhora,
										"0", modeloActividades.getIdActividad((String) comboBoxActividad.getSelectedItem()));
							}
							// Mensajes de éxito de reserva
							if (cliente_conReserva == 1) {
								JOptionPane.showMessageDialog(frmReservaInstalacionActividadPeriodoDet,
										"Alguna hora estaba reservada por un cliente pero la administración tiene prioridad.\n"
												+ "Se han eliminado las siguientes reservas:\n" + "Cliente: "
												+ lista_reservasCliente_eliminadas.get(0)[3] + "\nInstalación: "
												+ lista_reservasCliente_eliminadas.get(0)[3] + "\nDía y hora: "
												+ lista_reservasCliente_eliminadas.get(0)[2],
										"Éxito al reservar", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(frmReservaInstalacionActividadPeriodoDet, "Reservado.",
										"Éxito al reservar", JOptionPane.INFORMATION_MESSAGE);
							}

						}
					} else {
						JOptionPane.showMessageDialog(frmReservaInstalacionActividadPeriodoDet,
								"La hora de fin es anterior a la de inicio.", "Error al reservar",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		btnReservar.setBounds(423, 304, 147, 21);
		panel.add(btnReservar);
		
		JButton btnReservarPeriodo = new JButton("Reserva Parcial");
		btnReservarPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!ejecutado) {
					dateChooser_FechaInicio.setVisible(false);
					dateChooser_FechaFin.setVisible(false);
					dateChooser_FechaPeriodo.setVisible(true);
					btnReservarPeriodo.setText("Reservar Días");
					comboBoxHoraIni.setVisible(true);
					comboBoxHoraFin.setVisible(true);
					lblFecha_Inicio.setText("Seleccione el día a reservar:");
					lblFecha_Fin.setText("Seleccione las horas de reserva:");
					ejecutado = true;
				}
				else {
					dateChooser_FechaInicio.setVisible(true);
					dateChooser_FechaFin.setVisible(true);
					dateChooser_FechaPeriodo.setVisible(false);
					btnReservarPeriodo.setText("Reservar Horas");
					comboBoxHoraIni.setVisible(false);
					comboBoxHoraFin.setVisible(false);
					lblFecha_Inicio.setText("Fecha inicial:");
					lblFecha_Fin.setText("Fecha final:");
					ejecutado = false;
				}
			}
		});
		btnReservarPeriodo.setBounds(423, 346, 147, 21);
		panel.add(btnReservarPeriodo);
		
	}
	
	public Window getFrmReservaInstalacionActividadPeriodoDet() {
		return frmReservaInstalacionActividadPeriodoDet;
	}
}