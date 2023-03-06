package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;

public class ReservarInstalacionSocio {

	private JFrame frame;
	private JTextField tSocio;
	private JTextField tCoste;
	private InicioSesion sesion;
	private int id_socio;
	private String precio="";
	private String date;
	private InstalacionesModel instalacionesModel = new InstalacionesModel();
	private ReservasModel reservasModel = new ReservasModel();
	private ClientesModel clientesModel = new ClientesModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservarInstalacionSocio window = new ReservarInstalacionSocio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReservarInstalacionSocio() {
		initialize();
	}

	public ReservarInstalacionSocio(InicioSesion login) {
		initialize();
		this.sesion= login;
		this.id_socio=this.sesion.getId_socio();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		List<Object[]> lista=instalacionesModel.getNombreInstalaciones();
		String[] instalaciones=new String[lista.size()];
		Iterator<Object[]> iterador = lista.iterator();
		int i=0;
		while(iterador.hasNext()) {
			instalaciones[i]=iterador.next()[0].toString();
			i++;
		}




		frame = new JFrame();
		frame.setTitle("Reservar Instalación");
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.setBounds(100, 100, 450, 322);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lInstalacion = new JLabel("Instalación:");
		lInstalacion.setBounds(44, 54, 106, 13);
		lInstalacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lInstalacion);

		JLabel lblNewLabel = new JLabel("Nº de socio:");
		lblNewLabel.setBounds(44, 22, 84, 13);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel);

		JComboBox cInstalaciones = new JComboBox();
		cInstalaciones.setModel(new DefaultComboBoxModel(instalaciones));
		cInstalaciones.setBounds(121, 52, 113, 21);
		frame.getContentPane().add(cInstalaciones);

		tSocio = new JTextField();
		tSocio.setBounds(122, 21, 96, 19);
		frame.getContentPane().add(tSocio);
		tSocio.setText(id_socio+"");
		tSocio.setEditable(false);
		tSocio.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fecha de reserva:");
		lblNewLabel_1.setBounds(44, 88, 110, 17);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Hora de inicio:");
		lblNewLabel_2.setBounds(44, 119, 91, 17);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel_2);

		JComboBox cHoraInicio = new JComboBox();
		cHoraInicio.setBounds(143, 119, 113, 21);
		cHoraInicio.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
		frame.getContentPane().add(cHoraInicio);

		JLabel lblNewLabel_3 = new JLabel("Hora de fin:");
		lblNewLabel_3.setBounds(44, 152, 75, 17);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel_3);

		JComboBox cHoraFin = new JComboBox();
		cHoraFin.setBounds(125, 152, 93, 21);
		cHoraFin.setModel(new DefaultComboBoxModel(new String[] {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		frame.getContentPane().add(cHoraFin);

		JLabel lblNewLabel_4 = new JLabel("Coste:");
		lblNewLabel_4.setBounds(44, 188, 40, 17);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel_4);

		tCoste = new JTextField();
		tCoste.setBounds(94, 189, 96, 19);
		frame.getContentPane().add(tCoste);
		tCoste.setEditable(false);
		tCoste.setColumns(10);

		JDateChooser dFecha = new JDateChooser();
		dFecha.setBounds(164, 86, 113, 19);
		frame.getContentPane().add(dFecha);

		JButton bCancelar = new JButton("Cancelar");
		bCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bCancelar.setBounds(44, 243, 85, 25);
		frame.getContentPane().add(bCancelar);

		JButton bReservar = new JButton("Reservar");
		bReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id;

				List<Object[]> lista=instalacionesModel.getId_Instalacion((String)cInstalaciones.getSelectedItem());
				String[] nombre=new String[lista.size()];
				Iterator<Object[]> iterador = lista.iterator();

				int i=0;
				while(iterador.hasNext()) {
					nombre[i]=iterador.next()[0].toString();
					i++;
				}

				id = nombre[0];

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(dFecha.getDate());
				String hora = (String)cHoraInicio.getSelectedItem();
				String diaHora = date+" "+hora;

				int indiceinicio = cHoraInicio.getSelectedIndex();
				int indicefin = cHoraFin.getSelectedIndex();

				Date d1 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

				long diferencia_dias= 0;
				long diferencia_años= 0;
				try {
					//pasar de string a fecha
					Date d2 = sdf.parse(date);

					//Calcular la diferencia en milisegundos
					long difference_In_Time
					= d2.getTime() - d1.getTime();

					// Calcular la diferencia en segundos, minutos, horas, años y dias
					long difference_In_Seconds
					= (difference_In_Time
							/ 1000)
					% 60;

					long difference_In_Minutes
					= (difference_In_Time
							/ (1000 * 60))
					% 60;

					long difference_In_Hours
					= (difference_In_Time
							/ (1000 * 60 * 60))
					% 24;

					long difference_In_Years
					= (difference_In_Time
							/ (1000l * 60 * 60 * 24 * 365));

					long difference_In_Days
					= (difference_In_Time
							/ (1000 * 60 * 60 * 24))
					% 365;
					diferencia_dias = difference_In_Days;
					diferencia_años = difference_In_Years;

				}

				// Catch the Exception
				catch (ParseException excepcion) {
					excepcion.printStackTrace();
				}


				int id_socioS = sesion.getId_socio();
				String id_socio = String.valueOf(id_socioS);
				String fecha_ini=hora;
				String[] vector1=fecha_ini.split(":"); 
				fecha_ini=vector1[0].split(":")[0];
				int horaS= Integer.parseInt(fecha_ini);

				switch(horaS) {
				case 8:horaS=horaS+1;
				break;
				}

				int horaT=horaS+1;
				switch(horaT) {
				case 22: horaT=horaT-1;
				break;
				}

				int horaJ=horaS-1;
				switch(horaJ) {
				case 8:horaJ=horaJ+1;
				break;
				}

				double prix;
				prix = Double.parseDouble(instalacionesModel.getPrecio((String)cInstalaciones.getSelectedItem()));										

				double k= reservasModel.nuevaCuota(id_socioS);     
				k = k + prix;
				reservasModel.añadeacuota(k, id_socioS);

				hora = (String)cHoraInicio.getSelectedItem();
				List<Object[]> I= reservasModel.getListaReservasUsuario(id_socioS, id);
				int contador=0;

				for(int j=0; k<I.size();j++){
					if(getFecha((I.get(j)[0]).toString()).equals(date)) {
						if( ( (getHora((I.get(j)[0]).toString())) <= ((getHora1(hora))+sesion.getHora_Max())) && ( (getHora((I.get(j)[0]).toString())) >= ((getHora1(hora))-sesion.getHora_Max()))) {								
							//System.out.println("\nHora Bucle"+(getHora((I.get(k)[0]).toString())));
							// System.out.println("\nHoraIni"+(getHora1(hora)-Hora_Max));
							// System.out.println("\nHoraFin"+(getHora1(hora)+Hora_Max));
							contador++;
							//System.out.println("\nContador"+contador);

						}
					}		
				}

				boolean seguidas=true;

				if(contador>=sesion.getHora_Max()) {
					seguidas=false;
				}
				else 
					seguidas=true;

				String Date0 = date+" "+"00:00:00";
				String Date11 = date+" "+"23:00:00";
				String Date00 = sdf.format(d1)+" "+"00:00:00";


				if(reservasModel.getListaReservasUsuario2(id_socioS, Date0, Date11).size() < sesion.getHorasDiaMax()) {	

					if(reservasModel.getListaReservasUsuario1(id_socioS,Date00).size() < sesion.getHorasPeriodoMax()) {
						if(seguidas) {
							if (reservasModel.comprobarDisponibilidad(id, diaHora)) {
								//obtener el precio de la instalacion seleccionada
								precio = instalacionesModel.getPrecio((String)cInstalaciones.getSelectedItem());										
								tCoste.setText(precio);

								if (clientesModel.validarId(id_socio)) {
									if (diferencia_dias >= 0 && diferencia_años >= 0) {
										if (diferencia_dias <= 15 || diferencia_años>0) {	
											// System.out.printf("%d",);

											//CheckBoxPuedesReservar.setSelected(true);
											JOptionPane.showMessageDialog(frame, "  Has reservado.\n"
													+ "  Precio de la reserva: "+precio
													+"\n  Socio que lo solicita: "+id_socio
													+"\n  Instalación a reservar: "+id
													+"\n  Fecha de reserva: "+diaHora);
											reservasModel.nuevaReserva1(Integer.parseInt(id_socio), Integer.parseInt(id), sdf.format(d1), diaHora, precio ,0);
											bReservar.setSelected(true);
										}								
										else {
											JOptionPane.showMessageDialog(frame,
													"No puedes reservar con más de 15 días de antelación.",
													"No puedes reservar",
													JOptionPane.ERROR_MESSAGE);								
										}	
									}
									else {
										JOptionPane.showMessageDialog(frame,
												"No puedes reservar para una fecha ya pasada.",
												"No puedes reservar",
												JOptionPane.ERROR_MESSAGE);							
									}
								}
								else {
									JOptionPane.showMessageDialog(frame,
											"Introduce un número de socio válido.",
											"No puedes reservar",
											JOptionPane.ERROR_MESSAGE);						
								}											
							}
							else {
								JOptionPane.showMessageDialog(frame,
										"Está ocupado.",
										"No puedes reservar",
										JOptionPane.ERROR_MESSAGE);

							}

						}
						else {
							JOptionPane.showMessageDialog(frame,
									"No puedes reservar más de "+Integer.toString(sesion.getHorasDiaMax()-1)+"h seguidas",
									"Error Reservando",
									JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(frame,
								"No puedes reservar más de "+Integer.toString(sesion.getHorasPeriodoMax())+"h en el periodo de 15 días",
								"Error Reservando",
								JOptionPane.ERROR_MESSAGE);

					}
				}
				else {
					JOptionPane.showMessageDialog(frame,
							"No puedes reservar más de "+Integer.toString(sesion.getHorasDiaMax())+"h el mismo dia",
							"Error Reservando",
							JOptionPane.ERROR_MESSAGE);

				}
				bReservar.setEnabled(true);
				contador=0;
				//System.out.println(contador);
				seguidas=true;


			}
		});


		bReservar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bReservar.setBounds(308, 247, 87, 25);
		frame.getContentPane().add(bReservar);
	}

	public String getFecha (String date) {
		String[] vector=date.split("T");
		String fecha = vector[0];

		return fecha;
	}


	public void setFecha(String date) {
		this.date = date;
	}

	public int getHora (String date) {
		String[] vector=date.split("T");
		date=vector[1].split(":")[0];
		int hora=Integer.parseInt(date);


		return hora;
	}

	public int getHora1 (String date) {

		String[] vector1=date.split(":"); 
		date=vector1[0].split(":")[0];
		int horaS= Integer.parseInt(date);

		return horaS;
	}
}
