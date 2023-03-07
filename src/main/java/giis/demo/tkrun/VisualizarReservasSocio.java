package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VisualizarReservasSocio {

	private JFrame frame;
	private InstalacionesModel instalacionesModel = new InstalacionesModel();
	private ReservasModel reservasModel = new ReservasModel();
	private InicioSesion sesion;
	int id_socio;
	private Object[][] contenidos;
	private DefaultTableModel tableModel;
	private String titulos[] = new String[31];
	
	Calendar cal=Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	final long tiempo_actual=cal.getTime().getTime();
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarReservasSocio window = new VisualizarReservasSocio();
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
	public VisualizarReservasSocio() {
		initialize();
	}

	public VisualizarReservasSocio(InicioSesion login) {
		initialize();
		this.sesion = login;
		this.id_socio=this.sesion.getId_socio();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 440);
		frame.setTitle("Visualizar Reservas");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
JPanel panel = new JPanel();
		
		JSeparator separator = new JSeparator();
		
		JLabel LabelPeriodo = new JLabel("Instalación:");
		LabelPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		List<Object[]> lista=instalacionesModel.getNombreInstalaciones();
		
		String[] instalaciones=new String[lista.size()];
		
		Iterator<Object[]> iterador = lista.iterator();
		
		int i=0;
		while(iterador.hasNext()) {
			instalaciones[i]=iterador.next()[0].toString();
			i++;
		}
		
		JComboBox comboBoxInstalacion = new JComboBox();
		
		comboBoxInstalacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxInstalacion.setModel(new DefaultComboBoxModel(instalaciones));
		
		JButton ButtonCancelar = new JButton("Salir");
		ButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		
		table_1 = new JTable();
		contenidos=generaContenido(comboBoxInstalacion.getSelectedItem().toString());
		generaTitulos();
		tableModel = new DefaultTableModel(contenidos, titulos) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }};
		table_1.setModel(tableModel);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane_1 = new JScrollPane(table_1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportView(table_1);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollPane_1.setColumnHeaderView(scrollBar_1);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
		);
		
		JButton ButtonRecargar = new JButton("Recargar Tabla");
		
		ButtonRecargar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(ButtonCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, 466, Short.MAX_VALUE)
							.addComponent(ButtonRecargar, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(LabelPeriodo, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(comboBoxInstalacion, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(LabelPeriodo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxInstalacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(ButtonCancelar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(ButtonRecargar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(20))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	
		comboBoxInstalacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaModelo(comboBoxInstalacion);
			}
		});
		ButtonRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaModelo(comboBoxInstalacion);
			}
		});
	}
	
	
	
	public void actualizaModelo(JComboBox comboBoxInstalacion) {
		contenidos=generaContenido(comboBoxInstalacion.getSelectedItem().toString());

		tableModel= new DefaultTableModel(contenidos, titulos) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}};
			table_1.setModel(tableModel);
	}

	/* 																*/
	/* 																*/
	/* FALTA QUE IDENTIFIQUE SI ES ACTIVIDAD O EL SOCIO EN CUESTION	*/
	/* 																*/
	/* 																*/
	public Object[][] generaContenido(String instalacion) {
		Object[][] contenido= new Object[13][31];
		for(int i=0; i<13; i++) {
			if(i==1 || i==0)contenido[i][0]="0"+Integer.toString(i+9) + ":00";
			contenido[i][0]=Integer.toString(i+9) + ":00";
		}

		for(int i=0; i<13;i++) {
			for(int j=1; j<31; j++) {
				contenido[i][j]="LIBRE";
			}
		}

		List<Object[]> lista = instalacionesModel.getId_Instalacion(instalacion);
		long id_instalacion=(long)lista.get(0)[0];


		lista = reservasModel.getReservasInstalaciones(id_instalacion);


		Iterator<Object[]> iterador = lista.iterator();
		Object vector[];
		String[] fechayhora;
		String diaTotal, horaTotal, nombre_actividad="prueba";
		Date fecha_reserva;
		int dias,dia_afectado;
		long persona=0, actividad;

		while(iterador.hasNext()) {
			vector=iterador.next();
			if(vector[0]!=null)persona=(long)vector[0];
			boolean esReservaPropia=false;
			if(persona==id_socio) esReservaPropia=true;
			actividad= (long)vector[2];
			boolean esActividad=false;
			if(actividad!=0) {
				nombre_actividad=reservasModel.getActividad(actividad).get(0)[0].toString();
				esActividad=true;
			}
			fechayhora=vector[1].toString().split("T");
			diaTotal=fechayhora[0];
			horaTotal=fechayhora[1];
			try {
				fecha_reserva = sdf.parse(diaTotal);
				long diferencia= fecha_reserva.getTime()-tiempo_actual;
				dias = (int)Math.ceil(diferencia*1.0/(1000*3600*24));
				if(dias <0) continue;
				else {
					dia_afectado = dias+1;
				}
				if(esActividad) {
					switch(horaTotal) {
					case "9:00":
						contenido[0][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "10:00":
						contenido[1][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "11:00":
						contenido[2][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "12:00":
						contenido[3][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "13:00":
						contenido[4][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "14:00":
						contenido[5][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "15:00":
						contenido[6][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "16:00":
						contenido[7][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "17:00":
						contenido[8][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "18:00":
						contenido[9][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "19:00":
						contenido[10][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "20:00":
						contenido[11][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					case "21:00":
						contenido[12][dia_afectado]="Reservado("+nombre_actividad+")"; break;
					}
				}
				else if(esReservaPropia){
					switch(horaTotal) {
					case "9:00":
						contenido[0][dia_afectado]="Reservado(Usted)"; break;
					case "10:00":
						contenido[1][dia_afectado]="Reservado(Usted)"; break;
					case "11:00":
						contenido[2][dia_afectado]="Reservado(Usted)"; break;
					case "12:00":
						contenido[3][dia_afectado]="Reservado(Usted)"; break;
					case "13:00":
						contenido[4][dia_afectado]="Reservado(Usted)"; break;
					case "14:00":
						contenido[5][dia_afectado]="Reservado(Usted)"; break;
					case "15:00":
						contenido[6][dia_afectado]="Reservado(Usted)"; break;
					case "16:00":
						contenido[7][dia_afectado]="Reservado(Usted)"; break;
					case "17:00":
						contenido[8][dia_afectado]="Reservado(Usted)"; break;
					case "18:00":
						contenido[9][dia_afectado]="Reservado(Usted)"; break;
					case "19:00":
						contenido[10][dia_afectado]="Reservado(Usted)"; break;
					case "20:00":
						contenido[11][dia_afectado]="Reservado(Usted)"; break;
					case "21:00":
						contenido[12][dia_afectado]="Reservado(Usted)"; break;
					}
				}
				else {
					switch(horaTotal) {
					case "9:00":
						contenido[0][dia_afectado]="Reservado"; break;
					case "10:00":
						contenido[1][dia_afectado]="Reservado"; break;
					case "11:00":
						contenido[2][dia_afectado]="Reservado"; break;
					case "12:00":
						contenido[3][dia_afectado]="Reservado"; break;
					case "13:00":
						contenido[4][dia_afectado]="Reservado"; break;
					case "14:00":
						contenido[5][dia_afectado]="Reservado"; break;
					case "15:00":
						contenido[6][dia_afectado]="Reservado"; break;
					case "16:00":
						contenido[7][dia_afectado]="Reservado"; break;
					case "17:00":
						contenido[8][dia_afectado]="Reservado"; break;
					case "18:00":
						contenido[9][dia_afectado]="Reservado"; break;
					case "19:00":
						contenido[10][dia_afectado]="Reservado"; break;
					case "20:00":
						contenido[11][dia_afectado]="Reservado"; break;
					case "21:00":
						contenido[12][dia_afectado]="Reservado"; break;
					}
				}



			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} //fin while iterador
		return contenido;
	}

	public void generaTitulos(){
		titulos[0]="Horas";
		titulos[1]=new SimpleDateFormat("dd-MM").format(cal.getTime());
		for(int i=2; i<titulos.length;i++) {
			cal.add(cal.DATE, 1);
			titulos[i]=new SimpleDateFormat("dd-MM").format(cal.getTime());
		}
	}
}
