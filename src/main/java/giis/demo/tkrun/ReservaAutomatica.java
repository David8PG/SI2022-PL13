package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

public class ReservaAutomatica {

	private JFrame ReservaAutomaticaActividad;
	
	// MODELOS
	private ReservasModel modeloReservas = new ReservasModel();
	private InstalacionesModel modeloInstalaciones = new InstalacionesModel();
	private ActividadesModel modeloActividades = new ActividadesModel();
	private SesionesModel modeloSesiones = new SesionesModel();
	
	// FORMATOS
	SimpleDateFormat formato_fecha = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formato_fecha_hora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// FECHA ACTUAL
	Date fecha_actual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaAutomatica window = new ReservaAutomatica();
					window.ReservaAutomaticaActividad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReservaAutomatica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ReservaAutomaticaActividad = new JFrame();
		ReservaAutomaticaActividad.setBounds(100, 100, 362, 185);
		ReservaAutomaticaActividad.setTitle("Reserva automática de actividad");
		ReservaAutomaticaActividad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		ReservaAutomaticaActividad.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		// Almacenamos todas las actividades en un vector para el comboBox
		List<Object[]> lActividades = modeloActividades.getNombreActividades();
		String[] vActividades=new String[lActividades.size()];	
		Iterator<Object[]> itActividades = lActividades.iterator();	
		int i=0;
		while(itActividades.hasNext()) {
			vActividades[i]=itActividades.next()[0].toString();
			i++;
		}
		
		JLabel lblListado = new JLabel("Listado de actividades: ");
		lblListado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblListado.setBounds(10, 10, 133, 13);
		panel.add(lblListado);
		
		JComboBox comboBoxListaActividades = new JComboBox();
		comboBoxListaActividades.setModel(new DefaultComboBoxModel(vActividades));
		comboBoxListaActividades.setBounds(153, 6, 187, 21);
		panel.add(comboBoxListaActividades);
		
		JButton btnReservaAutomatica = new JButton("Reservar automáticamente");
		btnReservaAutomatica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Almacenamos en una lista las sesiones disponibles creadas en la base
				List<Object[]> lSesiones = modeloSesiones.getSesiones2(Long.toString(modeloActividades.getIdActividad(comboBoxListaActividades.getSelectedItem().toString())));
				String fInicio = modeloActividades.getFechaInicioActividad(comboBoxListaActividades.getSelectedItem().toString());
				String fFin = modeloActividades.getFechaFinActividad(comboBoxListaActividades.getSelectedItem().toString());
				
				// Operaciones con fechas
				Date inicio = null;
				Date finale = null;
				try {
					inicio = formato_fecha.parse(fInicio);
					finale = formato_fecha.parse(fFin);
				} catch (ParseException e1) {}
				
				Calendar calendario_inicio = Calendar.getInstance();
				Calendar calendario_horas = Calendar.getInstance();
				Locale es = new Locale("es", "ES");
				boolean existe_sesion = false;
				String dia_Hora = null;
				String hora_Fin = null;
				Date hora_inicio = null;
				Date hora_final = null;
				String fecha_inicio_reserva = null;
				String mensaje = "- Colisiones:\n";
				String id_socio = "";
				String nombre_actividad = "";

				while(inicio.getTime() - finale.getTime() <= 0) {
					String diaDeLaSemana = devuelveFormatos(inicio,es);
					Iterator<Object[]> iterador = lSesiones.iterator();
					Object vSesiones[];
					while(iterador.hasNext()) {
						existe_sesion=false;
						vSesiones = (Object[])iterador.next();
						if (vSesiones[0].equals("miercoles")) {
							vSesiones[0]="miércoles";
						}
						if (vSesiones[0].equals("sabado")) {
							vSesiones[0]="sábado";
						}
						if (vSesiones[0].equals(diaDeLaSemana)) {
							dia_Hora=formato_fecha.format(inicio)+" "+ vSesiones[1];
							hora_Fin=formato_fecha.format(inicio)+" "+ vSesiones[2];
							try {
								hora_inicio=formato_fecha_hora.parse(dia_Hora);
								hora_final=formato_fecha_hora.parse(hora_Fin);
							} catch (ParseException e1) {
							}
							existe_sesion=true;
						}
						if (existe_sesion){
							while(hora_inicio.getTime() < hora_final.getTime()) {
								fecha_inicio_reserva = formato_fecha_hora.format(hora_inicio.getTime());
								String nombreInstalacion = modeloActividades.getInstalacionActividad(comboBoxListaActividades.getSelectedItem().toString());
								int auxDisponibilidad = modeloReservas.comprobarDisponibilidadActividad(nombreInstalacion, fecha_inicio_reserva);
								if (auxDisponibilidad == -1) {
									nombre_actividad=modeloActividades.getNombreActividad_porID(modeloReservas.getActividadReserva(fecha_inicio_reserva,nombreInstalacion));
									mensaje = mensaje + "No se ha podido reservar la fecha '" + fecha_inicio_reserva
											+ "'. Está ocupada por la actividad '" + nombre_actividad + "'.\n";
								}
								else if (auxDisponibilidad == 0){
									modeloReservas.nuevaReserva(0, Integer.parseInt(nombreInstalacion), formato_fecha.format(fecha_actual), fecha_inicio_reserva, "0", modeloActividades.getIdActividad(comboBoxListaActividades.getSelectedItem().toString()));
								}
								else {
									id_socio=modeloReservas.getSocioDef(fecha_inicio_reserva,nombreInstalacion);
									mensaje=mensaje+"Se ha cancelado una reserva del socio con id '"+id_socio+"' en la fecha '"+fecha_inicio_reserva+"' y ha sido reservada para esta actividad.\n";
									try {
							            String ruta = "src/main/resources/Reserva"+modeloReservas.get_idreserva_hora_instalacion(fecha_inicio_reserva,nombreInstalacion)+"Socio"+id_socio+".txt";
							            String contenido = "Se le ha cancelado la reserva con fecha '"+ fecha_inicio_reserva +"' en la instalación '"+modeloInstalaciones.getNombre_Instalacion(nombreInstalacion)+"' por causas administrativas.\n";
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
									modeloReservas.eliminarReserva(Integer.parseInt(nombreInstalacion), fecha_inicio_reserva);
									modeloReservas.nuevaReserva(0, Integer.parseInt(nombreInstalacion), formato_fecha.format(fecha_actual), fecha_inicio_reserva, "0", modeloActividades.getIdActividad(comboBoxListaActividades.getSelectedItem().toString()));
								}
								//SUMAMOS UNA HORA
								calendario_horas.setTime(hora_inicio);
								calendario_horas.add(Calendar.HOUR, 1);
								try {
									hora_inicio = formato_fecha_hora.parse(formato_fecha_hora.format(calendario_horas.getTime()));
									//System.out.println(calendario_horas);
								} catch (ParseException e1) {	
								}	
							}
						}
					}
					calendario_inicio.setTime(inicio);
					calendario_inicio.add(Calendar.DATE, 1);
					try {
						inicio = formato_fecha.parse(formato_fecha.format(calendario_inicio.getTime()));
						//System.out.println(devuelveFormatos(ini,es));
					} catch (ParseException e1) {	
					}
				}
				if(mensaje.equals("- Colisiones:\n")) {
					mensaje="No hay ninguna colisión.\n";
				}
				JOptionPane.showMessageDialog(ReservaAutomaticaActividad,  mensaje, "Colisiones", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnReservaAutomatica.setBounds(153, 37, 187, 21);
		panel.add(btnReservaAutomatica);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmReservaAutomaticaActividad().setVisible(false);
			}
		});
		btnCancelar.setBounds(10, 114, 85, 21);
		panel.add(btnCancelar);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmReservaAutomaticaActividad().dispose();
			}
		});
		btnAtras.setBounds(255, 114, 85, 21);
		panel.add(btnAtras);
	}
	
	public static String devuelveFormatos(Date fecha, Locale sitio) {
		DateFormat formatter = new SimpleDateFormat("EEEE", sitio);
		return formatter.format(fecha);
	}
	
	public Window getFrmReservaAutomaticaActividad() {
		return this.ReservaAutomaticaActividad;
	}
	
}
