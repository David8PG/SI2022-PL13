package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ConsultaPagosSocio {

	private JFrame frame;
	private JTextField tfSocio;
	private InicioSesion login;
	private int id_socio;
	private JTable table;
	private ReservasModel reservasmodel = new ReservasModel();
	private PagosModel pagosmodel = new PagosModel();
	private ClientesModel clientesmodel = new ClientesModel();
	private InscripcionesModel inscripcionesmodel = new InscripcionesModel();
	private List<Object[]> listaReservas;
	private List<Object[]> listaInscripciones;
	private List<Object[]> listaPagos;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private InstalacionesModel instalacionesmodel = new InstalacionesModel();
	private ActividadesModel actividadesmodel = new ActividadesModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaPagosSocio window = new ConsultaPagosSocio();
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
	public ConsultaPagosSocio() {
		initialize();
	}

	public ConsultaPagosSocio(InicioSesion l) {
		login=l;
		id_socio=login.getId_socio();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 577, 401);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nº Socio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(196, 29, 61, 15);
		frame.getContentPane().add(lblNewLabel);

		tfSocio = new JTextField();
		tfSocio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfSocio.setText(""+id_socio);
		tfSocio.setBounds(253, 27, 40, 19);
		tfSocio.setEditable(false);
		frame.getContentPane().add(tfSocio);
		tfSocio.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Pagos desde:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(50, 65, 82, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JDateChooser dFechaD = new JDateChooser();
		dFechaD.setBounds(128, 61, 113, 19);
		frame.getContentPane().add(dFechaD);

		JDateChooser dFechaH = new JDateChooser();
		dFechaH.setBounds(372, 65, 113, 19);
		frame.getContentPane().add(dFechaH);

		JLabel lblNewLabel_2 = new JLabel("Hasta:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(330, 65, 61, 15);
		frame.getContentPane().add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 94, 499, 143);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Cantidad", "Fecha", "Motivo", "Pagado"
				}
				));
		table.getColumnModel().getColumn(0).setPreferredWidth(18);
		table.getColumnModel().getColumn(1).setPreferredWidth(38);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(18);
		scrollPane.setViewportView(table);



		JButton bBuscar = new JButton("Buscar");
		bBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((dFechaD.getDate() != null) && (dFechaH.getDate() != null)) {
					String dateD = sdf.format(dFechaD.getDate());
					String dateH = sdf.format(dFechaH.getDate());
					System.out.println(dateD);
					System.out.println(dateH);
					if (dFechaH.getDate().after(dFechaD.getDate())) {
						Calendar cal=Calendar.getInstance();
						cal.setTime(dFechaH.getDate());
						cal.add(Calendar.DATE, 1);
						String ini=sdf.format(dFechaD.getDate());
						String fin = sdf.format(cal.getTime());
						
						System.out.println(ini);
						System.out.println(fin);

						RellenarTabla(table,ini,fin);
					}
					else {
						JOptionPane.showMessageDialog(frame,
								"La fecha mas mayor es mas pequeña que la otra",
								"Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(frame,
							"No has selecionado las 2 fechas",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		bBuscar.setBounds(443, 298, 85, 21);
		frame.getContentPane().add(bBuscar);

		JButton bSalir = new JButton("Salir");
		bSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		bSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bSalir.setBounds(29, 297, 85, 21);
		frame.getContentPane().add(bSalir);

	}

	public void RellenarTabla(JTable tabla, String Inicio, String Fin) {
		Calendar cal = Calendar.getInstance();
		listaReservas=reservasmodel.getReservasSocioTodo(id_socio, Inicio, Fin);
		System.out.println(listaReservas);
		String dni=clientesmodel.getDNI(id_socio+"");
		listaInscripciones=inscripcionesmodel.getTodasInscripcionesSocio(dni, Inicio, Fin);
		System.out.println(listaReservas);
		System.out.println("Hola");
		System.out.println(Fin);
		String[][] datosTabla=new String[listaReservas.size()+listaInscripciones.size()][4];
		String[] auxDatos=new String[4];
		Object[][] mRes = new Object[listaReservas.size()][7];
		Iterator<Object[]> iRes = listaReservas.iterator();	
		Object[][] mIns = new Object[listaInscripciones.size()][4];
		Iterator<Object[]> iIns = listaInscripciones.iterator();
		String instal;
		Date[] orden =new Date[listaReservas.size()+listaInscripciones.size()]; 
		Date aux=null;
		boolean pagado=false;
		int i=0;
		while(iRes.hasNext()) {
			Object[] vector = new Object[7]; 
			vector=iRes.next();
			for(int j=0;j<7;j++) {	
			  mRes[i][j]= vector[j];
			}
			i++;
		}
		int y=0;
		while(iIns.hasNext()) {
			Object[] vector = new Object[4]; 
			vector=iIns.next();
			for(int j=0;j<4;j++) {	
			  mIns[y][j]= vector[j];
			}
			y++;
		}
		for(int k=0;k<i;k++) {
			//Precio
			datosTabla[k][0]=mRes[k][5].toString()+" €";
			//Fecha
			Date fe=null;
			try {
		        fe = new Date(sdf.parse(mRes[k][3].toString()).getTime());
				orden[k]=fe;
			} catch (ParseException e) {
			}
			String f=sdf.format(fe);
			datosTabla[k][1]=f;
			//Instalacion
			instal=mRes[k][2].toString();
			String instalacion="Reserva instalación: "+instalacionesmodel.getNombre_Instalacion(instal);
			datosTabla[k][2]=instalacion;
			//Estado	
			pagado=pagosmodel.getPagoReserva(mRes[k][0].toString());
			if(pagado) {
				datosTabla[k][3]="Pagado";
			}
			else {
				datosTabla[k][3]="Pendiente";
			}
			//System.out.println(datosTabla[k][3]);
			//System.out.println("FOR 1");
		}
		for(int k=i;k<y+i;k++) {
			//Precio 
			System.out.println(mIns[k-i][2].toString());
			datosTabla[k][0]=actividadesmodel.getPrecioSocioActividad(mIns[k-i][2].toString())+" €";
			//Fecha
			Date fe=null;
			try {
				fe = new Date(sdf.parse(mIns[k-i][3].toString()).getTime());
				orden[k]=fe;
			} catch (ParseException e) {
			}
			String f=sdf.format(fe);
			datosTabla[k][1]=f;
			//Actividad 
			String actividad="Inscripción actividad: "+actividadesmodel.getNombreActividad_porID(mIns[k-i][2].toString());
			datosTabla[k][2]=actividad;
			//Estado 
			pagado=pagosmodel.getPagoInscripcion(mIns[k-i][0].toString());
			if(pagado) {
				datosTabla[k][3]="Pagado";
			}
			else {
				datosTabla[k][3]="Pendiente";
			}
			//System.out.println(datosTabla[k][3]);
			//System.out.println("FOR 2");
		}
		//Ordenar por fecha
		for(int k=0; k<y+i-1; k++) {
			for (int f=k+1; f<y+i; f++) {
				if(orden[k].getTime()<orden[f].getTime()) {
					aux=orden[k];
					auxDatos=datosTabla[k];
					orden[k]=orden[f];
					datosTabla[k]=datosTabla[f];
					orden[f]=aux;
					datosTabla[f]=auxDatos;
				}
			}
		}
		/*
		for(int k=0; k<y+i; k++) {
			System.out.printf("%s | %s | %s | %s \n", datosTabla[k][0],datosTabla[k][1],datosTabla[k][2],datosTabla[k][3]);
		}
		*/
		
		table.setModel(new DefaultTableModel(
				
				datosTabla,
				new String[] {
					"Cantidad", "Fecha", "Motivo", "Estado"
				}
				
			));
		table.getColumnModel().getColumn(0).setPreferredWidth(18);
		table.getColumnModel().getColumn(1).setPreferredWidth(38);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(18);
	}

	public JFrame getFrame() {
		return this.frame;
		}
}
