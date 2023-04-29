package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class InformeSocios {

	private JFrame InformeSocios;
	private JTable tabla;
	private ClientesModel ModeloClientes = new ClientesModel();
	private String[] titulos = { "Nombre socio", "Reservas realizadas", "Total gastado", "Instalación más reservada",
			"% de las reservas en ella" };
	private Object[][] matrizDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeSocios window = new InformeSocios();
					window.InformeSocios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InformeSocios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		InformeSocios = new JFrame();
		InformeSocios.setTitle("Informe Socios");
		InformeSocios.setBounds(100, 100, 450, 300);
		InformeSocios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		InformeSocios.getContentPane().add(panel, BorderLayout.CENTER);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeSocios.dispose();
			}
		});

		JDateChooser dateChooserInicio = new JDateChooser();

		JDateChooser dateChooserFin = new JDateChooser();

		JLabel lblNewLabel = new JLabel("Inicio periodo:");

		JLabel lblNewLabel_1 = new JLabel("Fin periodo:");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnMostrarInforme = new JButton("Mostrar informe");
		btnMostrarInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaInicio = dateChooserInicio.getDate();
				Date fechaFin = dateChooserFin.getDate();

				if ((fechaInicio == null) || (fechaFin == null)) {
					JOptionPane.showMessageDialog(InformeSocios, "Selecciona una fecha final y una fecha inicial",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (fechaFin.getTime() - fechaInicio.getTime() <= 0) {
						JOptionPane.showMessageDialog(InformeSocios,
								"Selecciona una fecha final posterior a la inicial", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// fechaInicio
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:01");
						String fechaInicioS = sdf.format(dateChooserInicio.getDate());
						String Inicio = fechaInicioS;

						// fechaFin
						SimpleDateFormat sdfk = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
						String fechaFinS = sdfk.format(dateChooserFin.getDate());
						String Fin = fechaFinS;

						completarTabla(tabla, Inicio, Fin);
					}
				}

			}
		});

		JButton btnGenerarInforme = new JButton("Generar informe");
		btnGenerarInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creaTxt(matrizDatos);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(175).addComponent(btnMostrarInforme)
						.addContainerGap(154, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(22).addComponent(scrollPane,
								GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 86,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(dateChooserInicio, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
												.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 87,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(dateChooserFin, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnGenerarInforme))
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSalir)))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(21)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dateChooserInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(dateChooserFin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnMostrarInforme).addGap(9)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalir).addComponent(btnGenerarInforme))
				.addContainerGap()));

		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		panel.setLayout(gl_panel);
	}

	public Window getVisualizarInformeSocios() {
		return this.InformeSocios;
	}

	public void completarTabla(JTable tabla, String Inicio, String Fin) {
		List<Object[]> listaInformeSocios = ModeloClientes.getInformeSocios(Inicio, Fin);

		/*
		 * System.out.println("Logitud lista reservas: " +
		 * listaContabilidadReservas.size()); imprimirLista(listaContabilidadReservas);
		 * 
		 * System.out.println("Logitud lista actividades: " +
		 * listaContabilidadActividades.size());
		 * imprimirLista(listaContabilidadActividades);
		 * 
		 * System.out.println("Logitud lista CONTABILIDAD: " +
		 * listaContabilidad.size()); imprimirLista(listaContabilidad);
		 */

		matrizDatos = new Object[listaInformeSocios.size()][5];
		Iterator<Object[]> iterador = listaInformeSocios.iterator();
		int i = 0;
		while (iterador.hasNext()) {
			Object[] vector = new Object[5];
			vector = iterador.next();
			for (int j = 0; j < 5; j++) {
				matrizDatos[i][j] = vector[j];
			}
			i++;
		}

		construirTabla(matrizDatos, tabla, titulos);

	}

	public void construirTabla(Object[][] matriz, JTable tabla, String[] nombresColumnas) {
		DefaultTableModel modelo = new DefaultTableModel(matriz, nombresColumnas);
		tabla.setModel(modelo);
	}

	public void creaTxt(Object[][] matrizDatos) {
		try {
			FileWriter writer = new FileWriter("Informe socios.txt");
			writer.write("Nombre socio" + ";" + "Reservas realizadas" + ";" + "Total gastado" + ";"
					+ "Instalación más reservada" + ";" + "% de las reservas en ella" + ";");
			writer.write("\n");
			for (int i = 0; i < matrizDatos.length; i++) {
				for (int j = 0; j < matrizDatos[i].length; j++) {
					writer.write(matrizDatos[i][j].toString() + ";");
				}
				writer.write("\n");
			}
			writer.close();
			System.out.println("Matriz exportada correctamente en Informe socios.txt");
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al exportar la matriz a Informe socios.txt: " + e.getMessage());
		}
	}
}
