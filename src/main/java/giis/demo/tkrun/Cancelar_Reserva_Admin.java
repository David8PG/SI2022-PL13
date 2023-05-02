package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;

public class Cancelar_Reserva_Admin {

	private JFrame frame;
	private JTextField tfSocio;
	private JTable table;
	private ReservasModel reservasmodel = new ReservasModel();
	private InstalacionesModel instalacionesmodel = new InstalacionesModel();
	private int id_socio;
	private String dni;
	private ModificarParametros param = new ModificarParametros();
	private ClientesModel clientesmodel = new ClientesModel();

	private List<Object[]> listaReservas;
	private List<Object[]> listaPagos;
	private String[] arrayPagos;
	private Vector<String> reservasPagadas;
	private Vector<String> reservastodas = new Vector<String>();
	private Object[][] matriz;
	private String[][] matriz2;
	private Iterator<Object[]> it;
	private int i;
	private Object[] vector = new Object[3];
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField idTxtField;
	private long hoy;
	private JLabel lblNewLabel_1;
	private JTextField IdReserva;
	private JButton bCancelarReserva;
	private JButton bCancelar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cancelar_Reserva_Admin window = new Cancelar_Reserva_Admin();
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
	public Cancelar_Reserva_Admin() {
		Calendar cal=Calendar.getInstance();
		hoy = cal.getTime().getTime();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Cancelar reservas");
		frame.setBounds(100, 100, 567, 336);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nº Socio:");
		lblNewLabel.setBounds(210, 26, 45, 13);
		frame.getContentPane().add(lblNewLabel);

		tfSocio = new JTextField();
		tfSocio.setBounds(261, 23, 51, 19);
		frame.getContentPane().add(tfSocio);
		tfSocio.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 58, 499, 143);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);

		lblNewLabel_1 = new JLabel("Id de la reserva que se quere cancelar:");
		lblNewLabel_1.setBounds(29, 211, 181, 13);
		frame.getContentPane().add(lblNewLabel_1);

		IdReserva = new JTextField();
		IdReserva.setBounds(210, 208, 37, 19);
		frame.getContentPane().add(IdReserva);
		IdReserva.setColumns(10);

		JButton bCargarReservas = new JButton("Cargar reservas");
		bCargarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientesmodel.validarId(tfSocio.getText())) {
					id_socio=Integer.parseInt(tfSocio.getText());
					RellenarTabla(table);
				}
				else 
					JOptionPane.showMessageDialog(frame,
							"El ID no existe",
							"Error",
							JOptionPane.ERROR_MESSAGE);	
			}
		});
		bCargarReservas.setBounds(421, 22, 107, 21);
		frame.getContentPane().add(bCargarReservas);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Ignora parametro");
		chckbxNewCheckBox.setBounds(421, 207, 107, 21);
		frame.getContentPane().add(chckbxNewCheckBox);


		bCancelarReserva = new JButton("Borrar reserva");
		bCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fechareserva = null;
				String inst = null;
				String fechars = null;
				String horars = null;
				String precio = null;
				boolean hay = false;
				for (int i=0;i<matriz.length;i++) {
					if (IdReserva.getText().equals(matriz[i][0].toString())) {
						hay = true;
						fechareserva  = matriz[i][2].toString();
						inst= matriz[i][1].toString();
						fechars= matriz[i][2].toString();
						horars= matriz[i][3].toString();
						precio=matriz2[i][0];
					}
				}
				if (hay == true) {
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					Date datehoy = new Date(hoy);
					String hoy = formato.format(datehoy);



					java.util.Date fechareservad = null;
					try {
						fechareservad = formato.parse(fechareserva);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					java.util.Date fechahoy = null;
					try {
						fechahoy = formato.parse(hoy);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(fechahoy);
					System.out.println(fechareservad);
					if (!fechareservad.before(fechahoy)) {
						if (!chckbxNewCheckBox.isSelected()) {
							if (!fechareservad.equals(fechahoy)) {
								Calendar c = Calendar.getInstance();
								c.setTime(fechahoy);
								c.add(Calendar.DAY_OF_YEAR, param.getCancelar());
								fechahoy = c.getTime();
								System.out.println(fechahoy);
								if (!fechareservad.before(fechahoy)) {
									System.out.println("Puedes cancelar la reserva");

									double k= reservasmodel.nuevaCuota(id_socio); 
									System.out.println(precio);
									k = k - Double.parseDouble(precio);
									System.out.println(Double.parseDouble(precio));
									reservasmodel.añadeacuota(k, id_socio);

									reservasmodel.eliminarReserva(IdReserva.getText());



									JOptionPane.showMessageDialog(frame, "  Has cancelado la reserva.\n"
											+ "  ID de la reserva cancelada: "+IdReserva.getText()
											+"\n  Socio que lo solicita: "+id_socio
											+"\n  ID instalación a cancelar: "+ inst
											+"\n  Fecha de la reserva: "+ fechars
											+"\n  Hora de la reserva: "+ horars);


									try {
										String ruta = "D://Descargas HDD/Ticket.txt";
										String contenido1 = "ID de la reserva cancelada: "+IdReserva.getText();
										String contenido2 = "\nSocio que lo solicita: "+id_socio;
										String contenido3 = "\nID instalación a cancelar: "+ inst;
										String contenido4 = "\nFecha de la reserva: "+ fechars;
										String contenido5 = "\nHora de la reserva: "+ horars;
										File file = new File(ruta);
										// Si el archivo no existe es creado
										if (!file.exists()) {
											file.createNewFile();
										}
										FileWriter fw = new FileWriter(file);
										BufferedWriter bw = new BufferedWriter(fw);
										bw.write(contenido1);
										bw.write(contenido2);
										bw.write(contenido3);
										bw.write(contenido4);
										bw.write(contenido5);
										bw.close();
									} catch (Exception u) {
										u.printStackTrace();
									}
								}
								else 
									JOptionPane.showMessageDialog(frame,
											"Tienes que reservar con "+ param.getCancelar()+" días de antelación",
											"No puedes cancelar la reservar",
											JOptionPane.ERROR_MESSAGE);	
							}
							else {
								System.out.println("Al ser la fecha hoy no se puede cancelar la reserva");
								JOptionPane.showMessageDialog(frame,
										"No puedes cancelar la reserva en el mismo dia que la misma",
										"No puedes cancelar la reservar",
										JOptionPane.ERROR_MESSAGE);	
							}
						}
						else {
							System.out.println("Puedes cancelar la reserva");

							double k= reservasmodel.nuevaCuota(id_socio); 
							System.out.println(precio);
							k = k - Double.parseDouble(precio);
							System.out.println(Double.parseDouble(precio));
							reservasmodel.añadeacuota(k, id_socio);

							reservasmodel.eliminarReserva(IdReserva.getText());



							JOptionPane.showMessageDialog(frame, "  Has cancelado la reserva.\n"
									+ "  ID de la reserva cancelada: "+IdReserva.getText()
									+"\n  Socio que lo solicita: "+id_socio
									+"\n  ID instalación a cancelar: "+ inst
									+"\n  Fecha de la reserva: "+ fechars
									+"\n  Hora de la reserva: "+ horars);


							try {
								String ruta = "D://Descargas HDD/Ticket.txt";
								String contenido1 = "ID de la reserva cancelada: "+IdReserva.getText();
								String contenido2 = "\nSocio que lo solicita: "+id_socio;
								String contenido3 = "\nID instalación a cancelar: "+ inst;
								String contenido4 = "\nFecha de la reserva: "+ fechars;
								String contenido5 = "\nHora de la reserva: "+ horars;
								File file = new File(ruta);
								// Si el archivo no existe es creado
								if (!file.exists()) {
									file.createNewFile();
								}
								FileWriter fw = new FileWriter(file);
								BufferedWriter bw = new BufferedWriter(fw);
								bw.write(contenido1);
								bw.write(contenido2);
								bw.write(contenido3);
								bw.write(contenido4);
								bw.write(contenido5);
								bw.close();
							} catch (Exception u) {
								u.printStackTrace();
							}
						}
					}
					else
						JOptionPane.showMessageDialog(frame,
								"La fecha de la reserva ya pasó",
								"No puedes cancelar la reservar",
								JOptionPane.ERROR_MESSAGE);	
				}
				else {
					JOptionPane.showMessageDialog(frame,
							"El Id de la reserva indicado es incorrecto",
							"No puedes cancelar la reservar",
							JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		bCancelarReserva.setBounds(414, 257, 114, 21);
		frame.getContentPane().add(bCancelarReserva);

		bCancelar = new JButton("Cancelar");
		bCancelar.setBounds(29, 257, 85, 21);
		frame.getContentPane().add(bCancelar);










	}

	public void RellenarTabla(JTable tabla) {
		//Obtenemos una lista con las reservas del socio
		listaReservas=reservasmodel.todasReservasSocio(id_socio);	
		//si la lista no está vacia, mostramos los elementos
		if(!listaReservas.isEmpty()) {
			matriz = new Object[listaReservas.size()][4];		
			matriz2 = new String[listaReservas.size()][1];
			it = listaReservas.iterator();				
			i=0;
			while(it.hasNext()) {
				//el vector es el siguiente elemento de la lista (una reserva en concreto del cliente)
				vector=it.next();
				reservastodas.add(vector[0].toString());
				System.out.println("hola");
				System.out.println(vector[0]);
				System.out.println(vector[1]);
				System.out.println(vector[2]);
				System.out.println(vector[3]);
				System.out.println(vector[4]);

				matriz[i][0] = vector[0];
				matriz[i][1] = instalacionesmodel.getNombre_Instalacion(vector[2].toString());
				String[] a = (vector[3].toString()).split("T");
				matriz[i][2] = a[0];
				matriz[i][3] = a[1];
				matriz2[i][0] = vector[4].toString();
				i++;
			}

		}

		//Modelo de la tabla
		tabla.setModel(new DefaultTableModel(matriz,new String[] {
				"Id", 
				"Instalación", 
				"Fecha", 
				"Hora" 
		}));
		tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(38);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(15);
	}

	public JFrame getFrame() {
		return this.frame;
	}
}
