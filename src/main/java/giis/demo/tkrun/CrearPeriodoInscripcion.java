package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.toedter.calendar.JDateChooser;

public class CrearPeriodoInscripcion {

	private JFrame frmCrearPeriodoDe;
	private JTextField textField;
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

	public CrearPeriodoInscripcion(PlanificarActividad vSM) {
		this.ventanaCrearActividad = vSM;
		initialize();
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
		frmCrearPeriodoDe.setBounds(100, 100, 378, 324);
		frmCrearPeriodoDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmCrearPeriodoDe.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(" Nombre");
		lblNewLabel.setBounds(22, 11, 90, 14);
		panel.add(lblNewLabel);

		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(22, 36, 90, 14);
		panel.add(lblDescripcin);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(145, 137, 103, 19);
		panel.add(dateChooser);

		JTextPane textField_1 = new JTextPane();
		textField_1.setBounds(135, 36, 159, 90);
		panel.add(textField_1);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(145, 167, 103, 19);
		panel.add(dateChooser_1);

		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(145, 197, 103, 19);
		panel.add(dateChooser_2);

		textField = new JTextField();
		textField.setBounds(135, 8, 159, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fecha Inicio Socios");
		lblNewLabel_1.setBounds(22, 142, 118, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Fecha Fin Socios");
		lblNewLabel_1_1.setBounds(22, 172, 118, 14);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha Fin no Socios");
		lblNewLabel_1_1_1.setBounds(22, 202, 118, 14);
		panel.add(lblNewLabel_1_1_1);

		JButton btnNewButton = new JButton("Crear Periodo ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tengo que implementar la fecha
				Date FechaInicioSocio = dateChooser.getDate();
				Date FechaFinSocio = dateChooser_1.getDate();
				Date FechaFinNoSocio = dateChooser_2.getDate();
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				// fecha de hoy
				Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,
							"El periodo de inscripción no se ha podido realizar. Debes introducir un nombre.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,
							"El periodo de inscripción no se ha podido realizar. Debes introducir una descripción.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (FechaInicioSocio == null) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,
							"El periodo de inscripción no se ha podido realizar. Debes introducir una fecha inicial de socios.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (FechaFinSocio == null) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,
							"El periodo de inscripción no se ha podido realizar. Debes introducir una fecha final de socios.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (FechaFinNoSocio == null) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,
							"El periodo de inscripción no se ha podido realizar. Debes introducir una fecha final de no socios",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (FechaFinSocio.getTime() - FechaInicioSocio.getTime() < 0) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,
							"El periodo de inscripción no se ha podido realizar. La fecha final no puede ser anterior a la inicial.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (FechaFinNoSocio.getTime() - FechaFinSocio.getTime() < 0) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,
							"El periodo de inscripción no se ha podido realizar. La fecha final de no socios no puede ser anterior a la final de socios.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (FechaInicioSocio.getTime() - dateHoy.getTime() < 0) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,
							"El periodo de inscripción no se ha podido realizar. La fecha inicial de socios no puede ser anterior a la actual.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (FechaFinSocio.getTime() - dateHoy.getTime() < 0) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,
							"El periodo de inscripción no se ha podido realizar. La fecha final de socios no puede ser anterior a la actual.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (FechaFinNoSocio.getTime() - dateHoy.getTime() < 0) {
					JOptionPane.showMessageDialog(frmCrearPeriodoDe,
							"El periodo de inscripción no se ha podido realizar. La fecha final de no socios no puede ser anterior a la actual.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String nombre = textField.getText();
					String descripcion = textField_1.getText();
					String FechaInicioSocio1 = formatoFecha.format(FechaInicioSocio);
					String FechaFinSocio1 = formatoFecha.format(FechaFinSocio);
					String FechaFinNoSocio1 = formatoFecha.format(FechaFinNoSocio);

					PeriodosModel.nuevoPeriodoInscripcion(nombre, descripcion, FechaInicioSocio1, FechaFinSocio1,
							FechaFinNoSocio1);
					JOptionPane.showMessageDialog(frmCrearPeriodoDe, "Periodo de inscripción creado correctamente",
							"Creado", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Periodo Inscripción creado");
					if (ventanaCrearActividad != null) {
						ventanaCrearActividad.getPeriodosInscripcion();
					}
					frmCrearPeriodoDe.dispose();

				}

			}
		});
		btnNewButton.setBounds(168, 247, 126, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCrearPeriodoDe.dispose();
			}
		});
		btnNewButton_1.setBounds(51, 247, 89, 23);
		panel.add(btnNewButton_1);

	}

	public Window getfrmCrearActividad() {
		return this.getfrmCrearPeriodoDe();
	}
}
