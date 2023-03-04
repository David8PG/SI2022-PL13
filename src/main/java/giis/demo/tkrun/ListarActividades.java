package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class ListarActividades {

	private JFrame frameListarActividades;
	private JTable tablaActividades;
	private ActividadesModel ModeloActividades = new ActividadesModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarActividades window = new ListarActividades();
					window.frameListarActividades.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListarActividades() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frameListarActividades = new JFrame();
		frameListarActividades.setTitle("Listar Actividades");
		frameListarActividades.setBounds(100, 100, 716, 525);
		frameListarActividades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frameListarActividades.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setToolTipText("");
		dateChooserInicio.setBounds(132, 20, 95, 19);
		panel.add(dateChooserInicio);

		JDateChooser dateChooserFin = new JDateChooser();
		dateChooserFin.setToolTipText("");
		dateChooserFin.setBounds(388, 20, 95, 19);
		panel.add(dateChooserFin);

		JLabel textoInicioPeriodo = new JLabel("Fecha inicio del periodo:");
		textoInicioPeriodo.setBounds(10, 26, 112, 13);
		textoInicioPeriodo.setHorizontalAlignment(SwingConstants.LEFT);
		textoInicioPeriodo.setVerticalAlignment(SwingConstants.TOP);
		panel.add(textoInicioPeriodo);

		JLabel textoFinPeriodo = new JLabel("Fecha fin del periodo:");
		textoFinPeriodo.setBounds(278, 26, 100, 13);
		panel.add(textoFinPeriodo);

		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setBounds(31, 102, 619, 317);
		panel.add(panelTabla);

		tablaActividades = new JTable();
		panelTabla.setColumnHeaderView(tablaActividades);

		JButton botonMostrarActividades = new JButton("Mostrar actividades");
		botonMostrarActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaInicio = dateChooserInicio.getDate();
				Date fechaFin = dateChooserFin.getDate();

				if ((fechaInicio == null) || (fechaFin == null)) {
					JOptionPane.showMessageDialog(frameListarActividades,
							"Selecciona una fecha final y una fecha inicial", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (fechaFin.getTime() - fechaInicio.getTime() <= 0) {
						JOptionPane.showMessageDialog(frameListarActividades,
								"Selecciona una fecha final posterior a la inicial", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// fechaInicio
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String fechaInicioS = sdf.format(dateChooserInicio.getDate());
						String Inicio = fechaInicioS;

						// fechaFin
						SimpleDateFormat sdfk = new SimpleDateFormat("yyyy-MM-dd");
						String fechaFinS = sdfk.format(dateChooserFin.getDate());
						String Fin = fechaFinS;

						completarTabla(tablaActividades, Inicio, Fin);
					}
				}
			}
		});

		botonMostrarActividades.setBounds(221, 62, 184, 29);
		panel.add(botonMostrarActividades);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameListarActividades.dispose();
			}
		});
		botonAceptar.setBounds(552, 442, 85, 21);
		panel.add(botonAceptar);

	}

	public Window getListarActividades() {
		return this.frameListarActividades;
	}

	public void completarTabla(JTable tabla, String Inicio, String Fin) {

		List<Object[]> listaActividades = ModeloActividades.getActividadesPeriodo(Inicio, Fin);
		Object[][] matrizDatos = new Object[listaActividades.size()][8];
		Iterator<Object[]> iterador = listaActividades.iterator();

		int i = 0;
		while (iterador.hasNext()) {
			Object[] vector = new Object[8];
			vector = iterador.next();
			for (int j = 0; j < 8; j++) {
				matrizDatos[i][j] = vector[j];
			}
			i++;
		}
		tablaActividades.setModel(new DefaultTableModel(matrizDatos, new String[] { "ID Actividad", "Deporte",
				"Plazas Disponibles", "Fecha Inicio", "Fecha Fin", "Aforo", "Precio Socios", "Precio No Socios" }

		));
	}

	/*
	 * public void RellenarTablas(JTable tabla, String Inicio, String Fin) {
	 * 
	 * List<Object[]> listaActividades = modeloReservas.getActividadPeriodo(Inicio,
	 * Fin);
	 * 
	 * Object[][] matriz = new Object[listaActividades.size()][7];
	 * Iterator<Object[]> iterador = listaActividades.iterator(); int i = 0; while
	 * (iterador.hasNext()) { Object[] vector = new Object[7]; vector =
	 * iterador.next();
	 * 
	 * for (int j = 0; j < 7; j++) {
	 * 
	 * matriz[i][j] = vector[j];
	 * 
	 * } i++; } table.setModel(new DefaultTableModel(
	 * 
	 * matriz
	 * 
	 * , new String[] { "ID Actividad", "Deporte", "Plazas disponibles",
	 * "Fecha inicio", "Fecha fin", "Horas", "Precio Socios", "Precio No Socios" }
	 * 
	 * ));
	 * 
	 * }
	 */
}
