package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTextPane;

public class PlanificarActividad {

	private JFrame frmPlanificarActividad;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private ActividadesModel ModeloActividades = new ActividadesModel();
	private PeriodosInscripcionModel ModeloPeriodo = new PeriodosInscripcionModel();
	private InstalacionesModel ModeloInstalaciones = new InstalacionesModel();
	private SesionesModel ModeloSesiones = new SesionesModel();
	private CrearPeriodoInscripcion ventanaPeriodoInscripcion;
	private List<String[]> listaSesiones;
	
	JComboBox comboBox_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanificarActividad window = new PlanificarActividad();
					window.frmPlanificarActividad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlanificarActividad() {
		initialize();
	}
	public Window getFrmCrearActividad() {
		return this.frmPlanificarActividad;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPlanificarActividad = new JFrame();
		frmPlanificarActividad.setTitle("Planificar Actividad");
		frmPlanificarActividad.setBounds(100, 100, 650, 527);
		frmPlanificarActividad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmPlanificarActividad.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(84, 12, 241, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(21, 14, 56, 17);
		panel.add(lblNewLabel);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(21, 42, 56, 17);
		panel.add(lblDescripcin);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(84, 40, 241, 78);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Instalación");
		lblNewLabel_1.setBounds(21, 137, 56, 14);
		panel.add(lblNewLabel_1);
		
		List<Object[]> modeloInstalaciones=ModeloInstalaciones.getNombreInstalaciones();
		String[] instalaciones=new String[modeloInstalaciones.size()];
		Iterator<Object[]> iteradorIns = modeloInstalaciones.iterator();
		int i=0;
		while(iteradorIns.hasNext()) {
			instalaciones[i]=iteradorIns.next()[0].toString();
			i++;
		}
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(instalaciones));
		comboBox.setBounds(84, 133, 132, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Deporte");
		lblNewLabel_2.setBounds(21, 170, 46, 14);
		panel.add(lblNewLabel_2);
		
		List<Object[]> modeloDeportes=ModeloInstalaciones.getDeportes();
		String[] deportes=new String[modeloDeportes.size()];
		Iterator<Object[]> iteradorDep = modeloDeportes.iterator();
		int iDep=0;
		while(iteradorDep.hasNext()) {
			deportes[iDep]=iteradorDep.next()[0].toString();
			iDep++;
		}
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(deportes));
		comboBox_1.setBounds(84, 166, 132, 22);
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Aforo");
		lblNewLabel_3.setBounds(21, 207, 46, 14);
		panel.add(lblNewLabel_3);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(84, 204, 46, 20);
		panel.add(spinner);
		
		JLabel lblNewLabel_4 = new JLabel("Precio");
		lblNewLabel_4.setBounds(21, 242, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Precio No Socio");
		lblNewLabel_4_1.setBounds(171, 242, 89, 14);
		panel.add(lblNewLabel_4_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(84, 240, 56, 17);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(245, 240, 61, 17);
		panel.add(textField_3);
		
		JLabel lblNewLabel_5 = new JLabel("€");
		lblNewLabel_5.setBounds(142, 242, 46, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("€");
		lblNewLabel_5_1.setBounds(312, 242, 46, 14);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_6 = new JLabel("Periodo Inscripción");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(21, 282, 120, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Fecha Inicio Socios");
		lblNewLabel_7.setBounds(21, 319, 132, 17);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Fecha Fin Socios");
		lblNewLabel_8.setBounds(21, 347, 103, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("Fecha Fin no Socios");
		lblNewLabel_8_1.setBounds(21, 372, 103, 14);
		panel.add(lblNewLabel_8_1);
		
		JButton btnNewButton = new JButton("Crear Actividad");
		btnNewButton.setBounds(186, 454, 120, 23);
		panel.add(btnNewButton);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(129, 317, 114, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(129, 344, 114, 20);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(129, 369, 114, 20);
		panel.add(textField_6);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(444, 267, 148, 157);
		panel.add(textPane);
		
		JLabel lblNewLabel_9 = new JLabel("Duración");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_9.setBounds(367, 102, 76, 14);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Fecha Inicio");
		lblNewLabel_10.setBounds(279, 137, 68, 14);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Fecha Fin");
		lblNewLabel_11.setBounds(279, 170, 46, 14);
		panel.add(lblNewLabel_11);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(341, 137, 103, 19);
		panel.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(340, 165, 103, 19);
		panel.add(dateChooser_1);
		

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Lunes","Martes","Miercoles","Jueves","Viernes","Sábado","Domingo"}));
		comboBox_3.setBounds(502, 134, 89, 21);
		panel.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
		comboBox_4.setBounds(502, 167, 89, 21);
		panel.add(comboBox_4);
		
		JComboBox comboBox_4_1 = new JComboBox();
		comboBox_4_1.setModel(new DefaultComboBoxModel(new String[] {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		comboBox_4_1.setBounds(502, 204, 89, 21);
		panel.add(comboBox_4_1);
		
		Date Fechainicial;
		String FechaInicialS = "02-03-2023";
		Fechainicial = new SimpleDateFormat("dd-MM-yyyy").parse(FechaInicialS);
		dateChooser.setDate(Fechainicial);
		String FechaFinalS = "02-04-2023";
		Date FechaFinal;
		FechaFinal = new SimpleDateFormat("dd-MM-yyyy").parse(FechaFinalS);
		dateChooser_1.setDate(FechaFinal);

		
		JComboBox comboBox_2 = new JComboBox();
		getPeriodosInscripcion();
		String seleccionado=comboBox_2.getSelectedItem().toString();
		List<Object[]> seleccionadoFechas=ModeloPeriodo.getFechas(seleccionado);
		String[][] fecha=new String[seleccionadoFechas.size()][3];
		Iterator<Object[]> iterator = seleccionadoFechas.iterator();
		int ite=0;
		while(iterator.hasNext()) {
			String[] vector = new String[3]; 
			Object[] r=iterator.next();
			vector[0]=r[0].toString();
			vector[1]=r[1].toString();
			vector[2]=r[2].toString();
			
			for(int k=0;k<3;k++) {
				fecha[ite][k]=vector[k];
			}
			ite++;		
		}
		textField_4.setText(fecha[0][0]);
		textField_5.setText(fecha[0][1]);
		textField_6.setText(fecha[0][2]);
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccionado=comboBox_2.getSelectedItem().toString();
				List<Object[]> seleccionadoFechas=ModeloPeriodo.getFechas(seleccionado);
				String[][] fecha=new String[seleccionadoFechas.size()][3];
				Iterator<Object[]> iterator = seleccionadoFechas.iterator();
				int i=0;
				while(iterator.hasNext()) {
					String[] vector = new String[3]; 
					Object[] r=iterator.next();
					vector[0]=r[0].toString();
					vector[1]=r[1].toString();
					vector[2]=r[2].toString();
					
					for(int k=0;k<3;k++) {
						fecha[i][k]=vector[k];
					}
					i++;		
				}
				textField_4.setText(fecha[0][0]);
				textField_5.setText(fecha[0][1]);
				textField_6.setText(fecha[0][2]);
			}
		});
		comboBox_2.setBounds(151, 279, 124, 22);
		panel.add(comboBox_2);
		
		JLabel lblNewLabel_6_1 = new JLabel("Crear Nuevo Periodo Inscripción");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6_1.setBounds(21, 410, 222, 14);
		panel.add(lblNewLabel_6_1);
		
		listaSesiones = new ArrayList<String[]>();
		JButton btnNewButton_2 = new JButton("Crear Sesion");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("aaaa");
				if(comboBox_4_1.getSelectedIndex()>=comboBox_4.getSelectedIndex()+1) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"No se puede añadir esa sesión, la hora de inicio tiene que ser anterior a la posterior.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					String selecciones[] = new String[3]; 
					selecciones[0]=(String) comboBox_3.getModel().getElementAt(comboBox_3.getSelectedIndex());
					selecciones[1]=(String) comboBox_4.getModel().getElementAt(comboBox_4.getSelectedIndex());
					selecciones[2]=(String) comboBox_4_1.getModel().getElementAt(comboBox_4_1.getSelectedIndex());
					listaSesiones.add(selecciones);
					textPane.setText("Los "+textPane.getText()+comboBox_3.getModel().getElementAt(comboBox_3.getSelectedIndex())+
							" desde las "+comboBox_4.getModel().getElementAt(comboBox_4.getSelectedIndex())+
							" hasta las "+comboBox_4_1.getModel().getElementAt(comboBox_4_1.getSelectedIndex())+"\n");	
				}
				
			}
		});
		btnNewButton_2.setBounds(464, 238, 103, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat año = new SimpleDateFormat("yyyy");
				Date fechaActual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date fechaInicio = dateChooser.getDate();
				Date fechaFin = dateChooser_1.getDate();
			
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. Debes introducir un nombre.","Error",JOptionPane.ERROR_MESSAGE);
				}	
				else if(textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. Debes introducir una descripción.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. Debes introducir un precio para socios. ","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_3.getText().equals("")) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. Debes introducir un precio para no socios.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(fechaInicio==null) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. Debes introducir una fecha inicial de periodo.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(fechaFin==null) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. Debes introducir una fecha final de periodo.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!esPrecio(textField_1.getText())) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. Debes introducir un precio de socio válido.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!esPrecio(textField_2.getText())) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. Debes introducir un precio de no socio válido.","Error",JOptionPane.ERROR_MESSAGE);
				}		
				else if(fechaInicio.getTime()-fechaActual.getTime()<0) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. La fecha inicial es anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(fechaFin.getTime()-fechaActual.getTime()<0) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. La fecha final es anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(fechaFin.getTime()-fechaInicio.getTime()<0) {
					JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad no se ha creado. La fecha final es anterior a la inicial.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else{
					String nombre = textField_1.getText();
					String descripcion = textField_2.getText();
					String instalacionN = comboBox.getSelectedItem().toString();
					String deporte = comboBox_1.getSelectedItem().toString();
					String fechaInicioS = formato.format(fechaInicio);
					String fechaFinS = formato.format(fechaFin);
					String aforo = spinner.getModel().getValue().toString();
					String precioSocio = textField_3.getText();
					String precioNoSocio = textField_4.getText();
					
					
					List<Object[]> instalaciones = ModeloInstalaciones.getId_Instalacion(instalacionN);
					String[] listaInstalaciones=new String[instalaciones.size()];
					Iterator<Object[]> iteradorIns = instalaciones.iterator();
					int nInstalaciones=0;
					while(iteradorIns.hasNext()) {
						listaInstalaciones[nInstalaciones]=iteradorIns.next()[0].toString();
						nInstalaciones++;
					}
					
					String instalacion=listaInstalaciones[0];
					String per_ins_nombre=comboBox.getSelectedItem().toString();
					List<Object[]> per_ins_lista=ModeloPeriodo.getIdPeriodoIns(per_ins_nombre);
					String[] p_i=new String[per_ins_lista.size()];
					Iterator<Object[]> iteradorPerIns = per_ins_lista.iterator();
					int iPerIns=0;
					while(iteradorPerIns.hasNext()) {
						p_i[iPerIns]=iteradorPerIns.next()[0].toString();
						iPerIns++;
					}
					String periodoIns=p_i[0];
					try {
						int idActividad=ModeloActividades.nuevaActividadConId(nombre, descripcion, aforo, precioSocio, precioNoSocio, fechaInicioS, fechaFinS, deporte, instalacion, periodoIns);
						Iterator<String[]> iter=listaSesiones.iterator();
						while(iter.hasNext()) {
							String vector[]=iter.next();
							ModeloSesiones.nuevaSesion(vector[0], vector[1]+":00", vector[2]+":00", idActividad);
						}
						JOptionPane.showMessageDialog(frmPlanificarActividad,"La actividad se ha creado correctamente","Creado",JOptionPane.INFORMATION_MESSAGE);	
						frmPlanificarActividad.dispose();
						
					} catch (Exception eActividad) {
						JOptionPane.showMessageDialog(frmPlanificarActividad,"No se ha podido crear la actividad.\n","Error.",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				}
			
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPeriodoInscripcion.getfrmCrearPeriodoDe().setVisible(true);
			}});
		btnNewButton_1.setBounds(217, 407, 89, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_12 = new JLabel("Día");
		lblNewLabel_12.setBounds(444, 138, 61, 13);
		panel.add(lblNewLabel_12);
		
		JLabel lblNewLabel_12_1 = new JLabel("Hora Fin");
		lblNewLabel_12_1.setBounds(444, 208, 61, 13);
		panel.add(lblNewLabel_12_1);
		

		
		JLabel lblNewLabel_12_1_1 = new JLabel("Hora Inicio");
		lblNewLabel_12_1_1.setBounds(444, 171, 61, 13);
		panel.add(lblNewLabel_12_1_1);
		
	
		
		
		

	}

	
	public void getPeriodosInscripcion() {
		List<Object[]> mPeriodo=ModeloPeriodo.getPeriodosInscripcion();
		String[] periodosInscripcion;
		periodosInscripcion=new String[mPeriodo.size()];
		Iterator<Object[]> iteradorPeriodos = mPeriodo.iterator();
		int i=0;
		while(iteradorPeriodos.hasNext()) {
			periodosInscripcion[i]=iteradorPeriodos.next()[0].toString();
			i++;
		}
		comboBox_2.setModel(new DefaultComboBoxModel(periodosInscripcion));
	}
	
	public static boolean esPrecio(String str) {
	    return str.matches("^\\d+(\\.\\d{1,2})?$");
	}
}
