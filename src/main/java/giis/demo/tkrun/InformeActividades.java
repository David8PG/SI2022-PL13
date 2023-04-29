package giis.demo.tkrun;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JDateChooser;

public class InformeActividades {

	private JFrame InformeActividades;
	private JTable tabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeActividades window = new InformeActividades();
					window.InformeActividades.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InformeActividades() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		InformeActividades = new JFrame();
		InformeActividades.setTitle("Informe Actividades");
		InformeActividades.setBounds(100, 100, 450, 300);
		InformeActividades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		InformeActividades.getContentPane().add(panel, BorderLayout.CENTER);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeActividades.dispose();
			}
		});

		JDateChooser dateChooser = new JDateChooser();

		JDateChooser dateChooser_1 = new JDateChooser();

		JScrollPane scrollPane = new JScrollPane();

		JButton btnGenerarInforme = new JButton("Generar informe");

		JButton btnMostrarInforme = new JButton("Mostrar informe");

		JLabel lblNewLabel = new JLabel("Inicio periodo:");

		JLabel lblNewLabel_1 = new JLabel("Fin periodo:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel.createSequentialGroup().addContainerGap()
										.addGroup(gl_panel
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(btnGenerarInforme)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(btnSalir).addContainerGap())
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
																		105, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, 55,
																		Short.MAX_VALUE)
																.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE,
																		85, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(33))
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(btnMostrarInforme).addGap(161)))
												.addGroup(Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
																		403, GroupLayout.PREFERRED_SIZE)
																.addContainerGap()))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(dateChooser_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnMostrarInforme).addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalir).addComponent(btnGenerarInforme))
				.addContainerGap()));

		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		panel.setLayout(gl_panel);
	}

	public Window getVisualizarInformeActividades() {
		return this.InformeActividades;
	}
}
