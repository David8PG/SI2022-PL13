package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class CancelarActividadAdmin {

	private JFrame frmCancelarActividadAdmin;
	private JComboBox comboBox;
	private ActividadesModel modeloActividades=new ActividadesModel();
	private ReservasModel modeloReservas=new ReservasModel();
	private SesionesModel modeloSesiones=new SesionesModel();
	private InscripcionesModel modeloInscripciones=new InscripcionesModel();
	private JTextArea textArea, txtArea2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelarActividadAdmin window = new CancelarActividadAdmin();
					window.frmCancelarActividadAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CancelarActividadAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancelarActividadAdmin = new JFrame();
		frmCancelarActividadAdmin.setTitle("Cancelar Actividad");
		frmCancelarActividadAdmin.setBounds(100, 100, 700, 500);
		frmCancelarActividadAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCancelarActividadAdmin.setVisible(false);
			}
		});
		
		JButton btnCancelarActividadAdmin = new JButton("Cancelar Actividad");
		btnCancelarActividadAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarActividadAdmin();
			}
		});
		
		textArea= new JTextArea();
		
		JLabel lblMensaje = new JLabel("Mensaje:");
		
		JLabel lblNewLabel = new JLabel("Selecciona una actividad:");
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				avisar();
			}
		});
		rellenaComboBox();
		
		txtArea2 = new JTextArea();
		txtArea2.setEditable(false);
		avisar();
		JLabel lblNewLabel_1 = new JLabel("Socios:");
		
		GroupLayout groupLayout = new GroupLayout(frmCancelarActividadAdmin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(205)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(260, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(95)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMensaje, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))))
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
								.addComponent(btnCancelarActividadAdmin)
								.addGap(18))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(txtArea2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(50, Short.MAX_VALUE)))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addComponent(lblNewLabel)
					.addGap(13)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMensaje)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(txtArea2)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelarActividadAdmin)
						.addComponent(btnNewButton))
					.addGap(19))
		);
		frmCancelarActividadAdmin.getContentPane().setLayout(groupLayout);
	}
	
	public void rellenaComboBox() {
		List<Object[]> lista=modeloActividades.getNombreActividades();
		
		String[] actividades=new String[lista.size()];
		
		Iterator<Object[]> iterador = lista.iterator();
		
		int i=0;
		while(iterador.hasNext()) {
			actividades[i]=iterador.next()[0].toString();
			i++;
		}
		
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(actividades));
	}
	public void CancelarActividadAdmin(){
		String actividad=comboBox.getSelectedItem().toString();
		long id_actividad=modeloActividades.getIdActividad(actividad);
		modeloSesiones.eliminarSesiones(id_actividad);
		modeloInscripciones.eliminarInscripciones(id_actividad);
		modeloReservas.eliminarReservaPorActividad(id_actividad);
		modeloActividades.eliminarActividad(id_actividad);
		generaTXT(actividad, id_actividad);
		// tendríamos que generar un txt diciendo que a los no socios habría que devolverles el dinero en mano
		// aquí devolveríamos el dinero a los socios sumándoselo a su cuota (hecho en ventana de david CancelarReservaSocio)
		textArea.setText("");
		JOptionPane.showMessageDialog(frmCancelarActividadAdmin, "Se ha eliminado la actividad '"+ actividad + "'.");
		rellenaComboBox();
	}
	
	public void avisar() {
		String actividad=comboBox.getSelectedItem().toString();
		long id_actividad=modeloActividades.getIdActividad(actividad);
		List<Object[]> lista = modeloInscripciones.getPersonasActividad(id_actividad);
		String mensaje = "";
		for(int i=0; i<lista.size(); i++) {
			mensaje+=lista.get(i)[0].toString() + "\n";
		}
		txtArea2.setText(mensaje);
		
	}
	
	public JFrame getFrmCancelarActividadAdmin() {
		return frmCancelarActividadAdmin;
	}
	public void generaTXT(String actividad, long id_actividad) {
		try {
            String ruta = "src/main/resources/CancelActividad/ActividadesCanceladas"+id_actividad;
            String contenido = "Se ha cancelado la actividad: "+ id_actividad +"\nMotivo:\n"+textArea.getText();
            File file = new File(ruta);
            // Si el archivo no existe es creado
            //if (!file.exists()) {
                file.createNewFile();
            //}
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
		    bw.close();
		    
        } catch (Exception e1) {
            e1.printStackTrace();
        }
	}
}
