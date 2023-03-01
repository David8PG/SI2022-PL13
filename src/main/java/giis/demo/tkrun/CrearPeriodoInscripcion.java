package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CrearPeriodoInscripcion {

	private JFrame frmCrearPeriodoDe;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private PeriodosInscripcionModel PeriodosModel = new PeriodosInscripcionModel();
	private PlanificarActividad ventanaCrearActividad;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPeriodoInscripcion window = new CrearPeriodoInscripcion();
					window.frmCrearPeriodoDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrearPeriodoInscripcion() {
		initialize();
	}
	public JFrame getfrmCrearPeriodoDe() {
		return this.frmCrearPeriodoDe;
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrearPeriodoDe = new JFrame();
		frmCrearPeriodoDe.setTitle("Crear Periodo de Inscripción");
		frmCrearPeriodoDe.setBounds(100, 100, 294, 300);
		frmCrearPeriodoDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmCrearPeriodoDe.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Nombre");
		lblNewLabel.setBounds(22, 11, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(22, 36, 59, 14);
		panel.add(lblDescripcin);
		
		textField = new JTextField();
		textField.setBounds(83, 8, 159, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(83, 33, 159, 77);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha Inicio Socios");
		lblNewLabel_1.setBounds(22, 132, 90, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha Fin Socios");
		lblNewLabel_1_1.setBounds(22, 157, 90, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha Fin no Socios");
		lblNewLabel_1_1_1.setBounds(22, 182, 104, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton = new JButton("Crear Periodo ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Tengo que implementar la fecha
				Date FechaInicioSocio = dateChooser.getDate();
				Date FechaFinSocio = dateChooser_1.getDate();
				Date FechaFinNoSocio = dateChooser_2.getDate();
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				//fecha de hoy
				Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,"El periodo de inscripción no se ha podido realizar. Debes introducir un nombre.","Error",JOptionPane.ERROR_MESSAGE);
				}	
				else if(textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,"El periodo de inscripción no se ha podido realizar. Debes introducir una descripción.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(FechaInicioSocio==null) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,"El periodo de inscripción no se ha podido realizar. Debes introducir una fecha inicial de socios.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(FechaFinSocio==null) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,"El periodo de inscripción no se ha podido realizar. Debes introducir una fecha final de socios.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(FechaFinNoSocio==null) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,"El periodo de inscripción no se ha podido realizar. Debes introducir una fecha final de no socios","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(FechaFinSocio.getTime()-FechaInicioSocio.getTime()<0) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,"El periodo de inscripción no se ha podido realizar. La fecha final no puede ser anterior a la inicial.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(FechaFinNoSocio.getTime()-FechaFinSocio.getTime()<0) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,"El periodo de inscripción no se ha podido realizar. La fecha final de no socios no puede ser anterior a la final de socios.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(FechaInicioSocio.getTime()-dateHoy.getTime()<0) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,"El periodo de inscripción no se ha podido realizar. La fecha inicial de socios no puede ser anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(FechaFinSocio.getTime()-dateHoy.getTime()<0) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,"El periodo de inscripción no se ha podido realizar. La fecha final de socios no puede ser anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(FechaFinNoSocio.getTime()-dateHoy.getTime()<0) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,"El periodo de inscripción no se ha podido realizar. La fecha final de no socios no puede ser anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					String nombre=textField.getText();
					String descripcion=textField_1.getText();
					String FechaInicioSocio1=formatoFecha.format(FechaInicioSocio);
					String FechaFinSocio1=formatoFecha.format(FechaFinSocio);
					String FechaFinNoSocio1=formatoFecha.format(FechaFinNoSocio);
					try {
						PeriodosModel.nuevoPeriodoInscripcion(nombre, descripcion, FechaInicioSocio1, FechaFinSocio1, FechaFinNoSocio1);
						JOptionPane.showMessageDialog(frmCrearPeriodoDe,"Periodo de inscripción creado correctamente","Creado",JOptionPane.INFORMATION_MESSAGE);	
						if(ventanaCrearActividad!=null) {
							ventanaCrearActividad.getPeriodosInscripcion();
						}
						frmCrearPeriodoDe.dispose();
					} catch (Exception ePeriodoIns) {
						JOptionPane.showMessageDialog(frmCrearPeriodoDe,"No se ha podido crear el periodo.\nEl nombre de periodo ya existe.","Error.",JOptionPane.ERROR_MESSAGE);
					}		
				}
				
			}
		});
		btnNewButton.setBounds(83, 227, 101, 23);
		panel.add(btnNewButton);
		
		
		textField_2 = new JTextField();
		textField_2.setBounds(131, 129, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(131, 154, 86, 20);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(131, 179, 86, 20);
		panel.add(textField_4);
	}
	public Window getfrmCrearActividad() {
		return this.getfrmCrearPeriodoDe();
	}


}
