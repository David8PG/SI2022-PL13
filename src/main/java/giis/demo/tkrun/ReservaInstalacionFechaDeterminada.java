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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
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
	private String precio_instalacion ="";
	Date actual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

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
			i++;}
		
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
		comboBoxHoraInicioReserva.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
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
		comboBoxHoraFinReserva.setModel(new DefaultComboBoxModel(new String[] {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		comboBoxHoraFinReserva.setBounds(176, 167, 111, 21);
		panel.add(comboBoxHoraFinReserva);
		
		// El botón reservar lo añadimos el último porque necesita información del resto de componentes de la interfaz
		JButton btnReservar = new JButton("Reservar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_instalacion;
				
				// Creamos una lista con los Id de las instalaciones que se encuentren en el comboBox
				List<Object[]> lista_idsInstalaciones = modeloInstalaciones.getId_Instalacion((String) comboBoxInstalacion.getSelectedItem());
				// Creamos un array de strings de tanto tamaño como instalaciones haya en el comboBox
				String[] vNombres_Instalaciones = new String[lista_idsInstalaciones.size()];
				Iterator<Object[]> iterador = lista_idsInstalaciones.iterator();

				// Metemos dentro del array de strings los nombres de las instalaciones que hay en el comboBox
				int i = 0;
				while (iterador.hasNext()) {
					vNombres_Instalaciones[i] = iterador.next()[0].toString();
					i++;
				}
				
				// Fecha
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				String dia = formato.format(dateChooser_FechaReserva.getDate());
				String hora_inicio = (String)comboBoxHoraInicioReserva.getSelectedItem();
				String hora_fin = (String)comboBoxHoraFinReserva.getSelectedItem();
				String diaYhora_inicio = dia+" "+hora_inicio;
				String diaYhora_fin = dia+" "+hora_fin;
				
				LocalTime hora1 = LocalTime.parse(hora_inicio);
				int hora_de_inicioReserva = hora1.getHour();
				
				LocalTime hora2 = LocalTime.parse(hora_fin);
				int hora_de_finReserva = hora2.getHour();
				
				//Creamos una variable entera para la hora de inicio de la reserva
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
				precio_instalacion = modeloInstalaciones.getPrecio((String)comboBoxInstalacion.getSelectedItem());
				
				// Establecemos el id del socio que hace la reserva
				String id_socio;
				id_socio = textFieldIdSocio.getText();
				
				// Pasamos a comprobar la disponibilidad de la instalación
				if (modeloReservas.comprobarDisponibilidad(id_instalacion, diaYhora_inicio)) {
					if (modeloClientes.validarId(id_socio)) {
						if (diferencia_en_dias >= 0 && diferencia_en_años >= 0) {
							if (diferencia_en_dias <= 15 || diferencia_en_años > 0) {
								if (hora_de_inicioReserva < hora_de_finReserva) {
									modeloReservas.nuevaReserva(Integer.parseInt(id_socio),
											Integer.parseInt(id_instalacion), formato.format(fecha_actual),
											diaYhora_inicio, precio_instalacion, 0);
									JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
											"La reserva se ha hecho correctamente.\n" + "  Coste: " + precio_instalacion
													+ "\n  Nº Socio solicitante: " + id_socio
													+ "\n  Nombre de la instalación reservada: " + id_instalacion
													+ "\n  Reservada para el: " + diaYhora_inicio);
								} else {
									JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
											"No puedes seleccionar una hora de fin de reserva anterior a la de inicio",
											"Ha ocurrido un error al hacer la reserva",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(frmReservarInstalacionFechaDet,
										"No se puede reservar con más de 15 días de antelación (periodo mínimo estipulado).",
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
