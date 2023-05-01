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
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class InformesMenu {

	private JFrame InformesMenu;
	private InformeSocios ventanaInformeSocios;
	private InformeActividades ventanaInformeActividades;
	private InformeInstalaciones ventanaInformeInstalaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformesMenu window = new InformesMenu();
					window.InformesMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InformesMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		InformesMenu = new JFrame();
		InformesMenu.setTitle("Panel Informes");
		InformesMenu.setBounds(100, 100, 450, 338);
		InformesMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		InformesMenu.getContentPane().add(panel, BorderLayout.CENTER);

		JButton btnInformeSocios = new JButton("Informe Socios");
		btnInformeSocios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaInformeSocios = new InformeSocios();
				ventanaInformeSocios.getVisualizarInformeSocios().setVisible(true);
			}
		});

		JButton btnInformeActividades = new JButton("Informe Actividades");
		btnInformeActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaInformeActividades = new InformeActividades();
				ventanaInformeActividades.getVisualizarInformeActividades().setVisible(true);
			}
		});

		JButton btnInformeInstalaciones = new JButton("Informe Instalaciones");
		btnInformeInstalaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaInformeInstalaciones = new InformeInstalaciones();
				ventanaInformeInstalaciones.getVisualizarInformeInstalaciones().setVisible(true);
			}
		});

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformesMenu.dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(111)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInformeInstalaciones, GroupLayout.PREFERRED_SIZE, 221,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInformeActividades, GroupLayout.PREFERRED_SIZE, 221,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInformeSocios, GroupLayout.PREFERRED_SIZE, 221,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(104, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(38)
				.addComponent(btnInformeSocios, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE).addGap(31)
				.addComponent(btnInformeActividades, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
				.addGap(32)
				.addComponent(btnInformeInstalaciones, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
				.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE).addGap(27)));
		panel.setLayout(gl_panel);
	}

	public Window getVisualizarInformesMenu() {
		return this.InformesMenu;
	}
}
