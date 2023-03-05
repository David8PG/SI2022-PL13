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

public class ModificarParametros {

	private JFrame frameModificarParametros;
	private JSpinner spinnerPeriodoReservas;

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
		frameModificarParametros.setBounds(100, 100, 450, 300);
		frameModificarParametros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frameModificarParametros.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblTextoPeriodoReservas = new JLabel("Periodo de visualización de reservas:");
		lblTextoPeriodoReservas.setBounds(10, 10, 294, 13);
		panel.add(lblTextoPeriodoReservas);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarParametros();
				frameModificarParametros.dispose();
			}
		});
		btnAceptar.setBounds(230, 232, 85, 21);
		panel.add(btnAceptar);

		spinnerPeriodoReservas = new JSpinner();
		spinnerPeriodoReservas.setBounds(240, 7, 30, 20);
		spinnerPeriodoReservas.setValue(leerCSV(0));
		panel.add(spinnerPeriodoReservas);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameModificarParametros.dispose();
			}
		});
		btnCancelar.setBounds(325, 232, 85, 21);
		panel.add(btnCancelar);
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
			String contenido = Integer.toString((int) spinnerPeriodoReservas.getValue());

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
}
