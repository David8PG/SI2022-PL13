package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InscripcionActividadSocio {

	private JFrame frame;
	private JTextField tfSocio;
	private JTextField tfFechafinins;
	private JTextField tfPrecio;
	private JTextField tfIniAct;
	private JTextField tfFinAct;
	private JButton bCancelar;
	private JButton bIncribirse;
	private JComboBox<Object> cActividades;
	private ActividadesModel actividadesmodel = new ActividadesModel();
	private InscripcionesModel insmodel = new InscripcionesModel();
	private ClientesModel clientesmodel = new ClientesModel();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	DateFormat horaform = new SimpleDateFormat("HH:mm:ss");
	Date hora = new Date();
	String fecha = sdf.format(dateHoy);
	String horas = horaform.format(hora);
	String hoy = sdf.format(dateHoy) + " " + horaform.format(hora);
	private int id_socio;
	InicioSesion login;
	private ReservasModel reservasmodel = new ReservasModel();
	private PeriodosInscripcionModel perinsmodel = new PeriodosInscripcionModel();
	List<String> todasAct = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscripcionActividadSocio window = new InscripcionActividadSocio();
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
	public InscripcionActividadSocio() {
		initialize();
	}

	public InscripcionActividadSocio(InicioSesion l) {
		login=l;
		id_socio=l.getId_socio();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Inscripción a un actividad");
		frame.setBounds(100, 100, 565, 280);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nº de Socio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(195, 21, 76, 15);
		frame.getContentPane().add(lblNewLabel);

		tfSocio = new JTextField();
		tfSocio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfSocio.setText(""+id_socio);
		tfSocio.setBounds(267, 19, 48, 19);
		frame.getContentPane().add(tfSocio);
		tfSocio.setEditable(false);
		tfSocio.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Actividad:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(28, 54, 58, 15);
		frame.getContentPane().add(lblNewLabel_1);


		List<Object[]> plazos=perinsmodel.getFechasAbiertasSocio(fecha);
		Iterator<Object[]> iteradorPer = plazos.iterator();
		while(iteradorPer.hasNext()) {
			List<Object[]> modAct=actividadesmodel.getActividadesPeriodoIns(iteradorPer.next()[0].toString());
			Iterator<Object[]> iteradorAct = modAct.iterator();
			while(iteradorAct.hasNext()) {
				todasAct.add(iteradorAct.next()[0].toString());
			}
		}

		String [] actividades=new String[todasAct.size()];
		Iterator<String> iteradorTodas = todasAct.iterator();
		int iTodas=0;
		while(iteradorTodas.hasNext()) {
			actividades[iTodas]=iteradorTodas.next();	
			iTodas++;
		}
		

		JComboBox cActividades = new JComboBox<>();
		cActividades.setEditable(true);
		cActividades.setModel(new DefaultComboBoxModel(actividades));
		cActividades.setBounds(86, 52, 119, 21);
		frame.getContentPane().add(cActividades);


		JLabel lblNewLabel_2 = new JLabel("Fecha fin de inscripción:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(27, 79, 136, 15);
		frame.getContentPane().add(lblNewLabel_2);

		tfFechafinins = new JTextField();
		tfFechafinins.setBounds(161, 78, 103, 19);
		int hola = actividadesmodel.getPeriodoActividad((String)cActividades.getSelectedItem());
		tfFechafinins.setText(perinsmodel.getFechaFinSocio(hola));
		frame.getContentPane().add(tfFechafinins);
		tfFechafinins.setEditable(false);
		tfFechafinins.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Total de aforo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(28, 104, 85, 15);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lAforoT = new JLabel("New label");
		lAforoT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lAforoT.setText(actividadesmodel.getAforo((String)cActividades.getSelectedItem()));
		lAforoT.setBounds(118, 106, 45, 13);
		frame.getContentPane().add(lAforoT);

		JLabel lblNewLabel_4 = new JLabel("Plazas restantes:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(28, 129, 89, 15);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lAforoA = new JLabel("New label");
		lAforoA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lAforoA.setText(actividadesmodel.getPlazasActividad((String)cActividades.getSelectedItem()));
		lAforoA.setBounds(128, 131, 45, 13);
		frame.getContentPane().add(lAforoA);

		JLabel lblNewLabel_5 = new JLabel("Precio:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(28, 154, 45, 13);
		frame.getContentPane().add(lblNewLabel_5);

		tfPrecio = new JTextField();
		tfPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfPrecio.setBounds(67, 152, 45, 19);
		tfPrecio.setText(actividadesmodel.getPrecioSocio((String)cActividades.getSelectedItem()).toString());
		frame.getContentPane().add(tfPrecio);
		tfPrecio.setEditable(false);
		tfPrecio.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Inicio de actividad:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(320, 54, 103, 15);
		frame.getContentPane().add(lblNewLabel_6);

		tfIniAct = new JTextField();
		tfIniAct.setBounds(423, 53, 96, 19);
		tfIniAct.setText(actividadesmodel.getFechaInicioActividad((String)cActividades.getSelectedItem()));
		frame.getContentPane().add(tfIniAct);
		tfIniAct.setEditable(false);
		tfIniAct.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Fin de actividad:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(320, 81, 89, 15);
		frame.getContentPane().add(lblNewLabel_7);

		tfFinAct = new JTextField();
		tfFinAct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfFinAct.setBounds(411, 78, 96, 19);
		tfFinAct.setText(actividadesmodel.getFechaFinActividad((String)cActividades.getSelectedItem()));
		frame.getContentPane().add(tfFinAct);
		tfFinAct.setEditable(false);
		tfFinAct.setColumns(10);

		bCancelar = new JButton("Cancelar");
		bCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bCancelar.setBounds(78, 199, 85, 21);
		frame.getContentPane().add(bCancelar);

		bIncribirse = new JButton("Incribirse");
		bIncribirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (insmodel.personaInscritaenActividad(actividadesmodel.getIdActividad(cActividades.getSelectedItem().toString()), clientesmodel.getDNI(""+id_socio))) {
					JOptionPane.showMessageDialog(frame,"No puedes inscribirte a la actividad porque ya estás incrito.","Error",JOptionPane.ERROR_MESSAGE);				
				}
				else {
					if (Integer.parseInt(actividadesmodel.getPlazasActividad(cActividades.getSelectedItem().toString())) > 0) {
						Long actividad = actividadesmodel.getIdActividad(cActividades.getSelectedItem().toString());
						long id_nuevainscripcion = insmodel.nuevaInscripcionRetornaId(clientesmodel.getDNI(""+id_socio), actividad.toString(), hoy);
						actividadesmodel.restarPlaza(cActividades.getSelectedItem().toString());
						double cuota = reservasmodel.nuevaCuota(id_socio);
						cuota = cuota + Double.parseDouble(actividadesmodel.getPrecioSocio((String)cActividades.getSelectedItem()).toString());
						reservasmodel.añadeacuota(cuota, id_socio);
						JOptionPane.showMessageDialog(frame,"Te has inscrito en esta actividad.\nID de la reserva: "+id_nuevainscripcion+"\nImporte: "+tfPrecio.getText()+" €\nSe añadirá el importe a tu próxima cuota.","Inscrito",JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
						
						try {
				            String ruta = "D://Descargas HDD/Ticket.txt";
				            String contenido1 = "Te has inscrito en "+ cActividades.getSelectedItem().toString();
				            String contenido2 = "\nID de la reserva: "+id_nuevainscripcion;
				            String contenido3 = "\nImporte: "+tfPrecio.getText();
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
				            bw.close();
				        } catch (Exception u) {
				            u.printStackTrace();
				        }
					}
					else {
						JOptionPane.showMessageDialog(frame,"No te has podido inscribir, la actividad ya no tienen plazas libres.","Error",JOptionPane.ERROR_MESSAGE);				
					}
				}
			}

		});
		bIncribirse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bIncribirse.setBounds(381, 200, 85, 21);
		frame.getContentPane().add(bIncribirse);

		cActividades.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//tfFechafinins.setText(actividadesmodel.);
				tfIniAct.setText(actividadesmodel.getFechaInicioActividad((String)cActividades.getSelectedItem()));
				tfFinAct.setText(actividadesmodel.getFechaFinActividad((String)cActividades.getSelectedItem()));
				tfPrecio.setText(actividadesmodel.getPrecioSocio((String)cActividades.getSelectedItem()).toString());
				lAforoA.setText(actividadesmodel.getPlazasActividad((String)cActividades.getSelectedItem()));
				lAforoT.setText(actividadesmodel.getAforo((String)cActividades.getSelectedItem()));
				int num = actividadesmodel.getPeriodoActividad((String)cActividades.getSelectedItem());
				tfFechafinins.setText(perinsmodel.getFechaFinSocio(hola));
			}
		});
	}

	public JFrame getFrame() { return this.frame; }
	public JButton getBIncribirse() { return this.bIncribirse; }
	public JButton getBCancelar() { return this.bCancelar; }
	public void setId_Socio(String n) {this.tfSocio.setText(n);}
	public JComboBox<Object> getListaActividades() { return this.cActividades; }
}
