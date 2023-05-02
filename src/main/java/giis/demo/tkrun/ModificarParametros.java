package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ModificarParametros {

	private JFrame frameModificarParametros;
	private JSpinner spinnerPeriodoReservas;
	private JSpinner spinnerDiasAntelacion;
	private JSpinner spinnerHorasMax;
	private JSpinner spinnerHorasMaxDia;
	private JSpinner spinnerCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarParametros window = new ModificarParametros();
					window.frameModificarParametros.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModificarParametros() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameModificarParametros = new JFrame();
		frameModificarParametros.setTitle("Modificar Parámetros");
		frameModificarParametros.setBounds(100, 100, 450, 307);
		frameModificarParametros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frameModificarParametros.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblTextoPeriodoReservas = new JLabel("Periodo de visualización de reservas:");
		lblTextoPeriodoReservas.setBounds(38, 35, 177, 13);
		panel.add(lblTextoPeriodoReservas);

		JLabel lblAntelacion = new JLabel("Días Antelacion: ");
		lblAntelacion.setBounds(38, 70, 165, 13);
		panel.add(lblAntelacion);

		JLabel lblHoraMaxima = new JLabel("Hora máx: ");
		lblHoraMaxima.setBounds(38, 105, 165, 13);
		panel.add(lblHoraMaxima);

		JLabel lblHorasDiaMax = new JLabel("Número de horas máximo por día: ");
		lblHorasDiaMax.setBounds(38, 140, 165, 13);
		panel.add(lblHorasDiaMax);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarParametros();
				frameModificarParametros.dispose();
			}
		});
		btnAceptar.setBounds(230, 232, 85, 21);
		panel.add(btnAceptar);

		SpinnerNumberModel model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		SpinnerNumberModel model2 = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		SpinnerNumberModel model3 = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		SpinnerNumberModel model4 = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		spinnerPeriodoReservas = new JSpinner(model);
		spinnerPeriodoReservas.setBounds(235, 32, 30, 20);
		spinnerPeriodoReservas.setValue(leerCSV(0));
		panel.add(spinnerPeriodoReservas);

		spinnerDiasAntelacion = new JSpinner(model1);
		spinnerDiasAntelacion.setBounds(235, 67, 30, 20);
		spinnerDiasAntelacion.setValue(leerCSV(1));
		panel.add(spinnerDiasAntelacion);

		spinnerHorasMax = new JSpinner(model2);
		spinnerHorasMax.setBounds(235, 102, 30, 20);
		spinnerHorasMax.setValue(leerCSV(2));
		panel.add(spinnerHorasMax);

		spinnerHorasMaxDia = new JSpinner(model3);
		spinnerHorasMaxDia.setBounds(235, 137, 30, 20);
		spinnerHorasMaxDia.setValue(leerCSV(3));
		panel.add(spinnerHorasMaxDia);
		
		spinnerCancelar = new JSpinner(model4);
		spinnerCancelar.setBounds(235, 179, 30, 20);
		spinnerCancelar.setValue(leerCSV(4));
		panel.add(spinnerCancelar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameModificarParametros.dispose();
			}
		});
		btnCancelar.setBounds(325, 232, 85, 21);
		panel.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Dias con antelación para cancelar reserva:");
		lblNewLabel.setBounds(38, 182, 195, 13);
		panel.add(lblNewLabel);
		
	

	}

	public static int leerCSV(int posicion) {
		int primerValor = 0;
		String ruta = "src/main/resources/parametros.csv";
		File file = new File(ruta);
		try {
			BufferedReader lector = new BufferedReader(new FileReader(file));
			String linea = lector.readLine(); // lee la primera línea del archivo
			String[] valor = linea.split(";"); // separa los valores por el punto y coma
			lector.close();
			primerValor = Integer.parseInt(valor[posicion]); // asigna el primer valor de la línea a la variable
																// primerValor
		} catch (Exception e) {
			System.out.println("Error al leer el archivo CSV: " + e.getMessage());
		}
		return primerValor; // retorna el primer valor del archivo CSV como un string vacío si ocurre un
							// error
	}

	private void guardarParametros() {
		try {
			String ruta = "src/main/resources/parametros.csv";
			String contenido = Integer.toString((int) spinnerPeriodoReservas.getValue()) + ";"
					+ Integer.toString((int) spinnerDiasAntelacion.getValue()) + ";"
					+ Integer.toString((int) spinnerHorasMax.getValue()) + ";"
					+ Integer.toString((int) spinnerHorasMaxDia.getValue()) + ";"
					+ Integer.toString((int) spinnerCancelar.getValue());
			File file = new File(ruta);
			// Se crea el archivo si no existe
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

	}

	public Window getModificarParametros() {
		return this.frameModificarParametros;
	}
	
	public int getDiasAntelacion() {
		return (int) spinnerDiasAntelacion.getValue();
	}
	
	public int getHorasMax() {
		return (int) spinnerHorasMax.getValue();
	}
	
	public int getHorasMaxDia() {
		return (int) spinnerHorasMaxDia.getValue();
	}
	
	public int getCancelar() {
		return (int) spinnerCancelar.getValue();
	}
}
