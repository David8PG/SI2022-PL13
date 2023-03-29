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

public class MostrarReservasSocio {

	private JFrame MostrarReservasSocio;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarReservasSocio window = new MostrarReservasSocio();
					window.MostrarReservasSocio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MostrarReservasSocio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MostrarReservasSocio = new JFrame();
		MostrarReservasSocio.setTitle("Mostrar Reservas");
		MostrarReservasSocio.setBounds(100, 100, 450, 300);
		MostrarReservasSocio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		MostrarReservasSocio.getContentPane().add(panel, BorderLayout.CENTER);

		JDateChooser dateChooser = new JDateChooser();

		JDateChooser dateChooser_1 = new JDateChooser();

		JLabel lblNewLabel = new JLabel("Fecha inicio:");

		JLabel lblFechaFin = new JLabel("Fecha fin:");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnMostrarReservas = new JButton("Mostrar reservas");

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarReservasSocio.dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap(350, Short.MAX_VALUE)
								.addComponent(btnAceptar).addGap(29))
						.addGroup(Alignment.LEADING,
								gl_panel.createSequentialGroup().addContainerGap().addGroup(gl_panel
										.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
												.createSequentialGroup()
												.addComponent(
														scrollPane, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
												.addContainerGap())
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 87,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
												.addComponent(lblFechaFin, GroupLayout.PREFERRED_SIZE, 87,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(23))))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup().addGap(148)
								.addComponent(btnMostrarReservas).addContainerGap(177, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblFechaFin)
								.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel).addComponent(dateChooser, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(btnMostrarReservas).addGap(11)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(btnAceptar).addContainerGap(20, Short.MAX_VALUE)));

		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		panel.setLayout(gl_panel);
	}

	public Window getMostrarReservasSocio() {
		return this.MostrarReservasSocio;
	}
}
