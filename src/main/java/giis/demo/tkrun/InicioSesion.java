package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InicioSesion {

	private JFrame frmInicio_de_sesion;
	private JTextField textFieldUsuario;
	private JPasswordField passFieldContraseña;
	private pantallaInicialAdmin vAdmin;
	private ClientesModel modelClientes = new ClientesModel();
	private int id_socio;
	private pantallaInicialSocio vSocio;
	
	
	//Variables parametrizables
	private int diasAntelacion;
	private int dia_comprobar;
	private int Hora_Max;
	private int horasDiaMax;
  	private int horasPeriodoMax;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion window = new InicioSesion();
					window.frmInicio_de_sesion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InicioSesion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInicio_de_sesion = new JFrame();
		frmInicio_de_sesion.setTitle("Inicio de Sesión");
		frmInicio_de_sesion.setBounds(100, 100, 248, 181);
		frmInicio_de_sesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInicio_de_sesion.getContentPane().setLayout(null);
		
		// Inicializamos pantallas admin y socio
		vAdmin = new pantallaInicialAdmin(this);
		
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(38, 25, 45, 13);
		frmInicio_de_sesion.getContentPane().add(lblUsuario);
		
		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setBounds(24, 73, 66, 13);
		frmInicio_de_sesion.getContentPane().add(lblContraseña);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(103, 22, 96, 19);
		frmInicio_de_sesion.getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		passFieldContraseña = new JPasswordField();
		passFieldContraseña.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					entrada_en_sesion();
				}
			}
		});
		passFieldContraseña.setBounds(103, 70, 96, 19);
		frmInicio_de_sesion.getContentPane().add(passFieldContraseña);
		
		JButton btnInicioSesion = new JButton("Inicia Sesión");
		btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrada_en_sesion();
			}
		});
		btnInicioSesion.setBounds(69, 113, 102, 21);
		frmInicio_de_sesion.getContentPane().add(btnInicioSesion);
		
	}
	
	public pantallaInicialAdmin getvInicialAdmin() {
		return vAdmin;
	}

	public void setpantallaInicialAdmin(pantallaInicialAdmin vAdmin) {
		this.vAdmin = vAdmin;
	}
	
	public pantallaInicialSocio getvInicialSocio() {
		return vSocio;
	}

	public void setpantallaInicialSocio(pantallaInicialSocio vSocio) {
		this.vSocio= vSocio;
	}
	
	public int getId_socio() {
		return id_socio;
	}

	public void setId_socio(int id_socio) {
		this.id_socio = id_socio;
	}

	public Window getFrmInicioSesion() {
		return frmInicio_de_sesion;
	}
	
	public int getDiasAntelacion() {
		return diasAntelacion;
	}

	public void setDiasAntelacion(int diasAntelacion) {
		this.diasAntelacion = diasAntelacion;
	}

	public int getDia_comprobar() {
		return dia_comprobar;
	}

	public void setDia_comprobar(int dia_comprobar) {
		this.dia_comprobar = dia_comprobar;
	}

	public int getHora_Max() {
		return Hora_Max;
	}

	public void setHora_Max(int hora_Max) {
		Hora_Max = hora_Max;
	}

	public int getHorasDiaMax() {
		return horasDiaMax;
	}

	public void setHorasDiaMax(int horasDiaMax) {
		this.horasDiaMax = horasDiaMax;
	}

	public int getHorasPeriodoMax() {
		return horasPeriodoMax;
	}

	public void setHorasPeriodoMax(int horasPeriodoMax) {
		this.horasPeriodoMax = horasPeriodoMax;
	}

	// Método de inicio de sesión
	public void entrada_en_sesion() {
		String contraseña;
		if (!textFieldUsuario.getText().equals("")
				&& !Arrays.equals(passFieldContraseña.getPassword(), "".toCharArray())) {
			if (textFieldUsuario.getText().equals("admin")
					&& Arrays.equals(passFieldContraseña.getPassword(), "1234".toCharArray())) {
				vAdmin.getFrmvAdmin().setVisible(true);
				frmInicio_de_sesion.setVisible(false);
			} else {
				try {
					setId_socio(Integer.parseInt(textFieldUsuario.getText()));
					List<Object[]> LContraseñas = modelClientes.getContraseña(Integer.toString(id_socio));
					contraseña = LContraseñas.get(0)[0].toString();
					if (Arrays.equals(passFieldContraseña.getPassword(), contraseña.toCharArray())) {
						vSocio = new pantallaInicialSocio(this);
						vSocio.getFrmvSocio().setVisible(true);
						frmInicio_de_sesion.setVisible(false);

					} else {
						JOptionPane.showMessageDialog(frmInicio_de_sesion,
								"Contraseña de socio incorrecta.", "Introduzca los datos de forma correcta.",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frmInicio_de_sesion, "En el campo usuario debe introducir su número de socio.",
							"Credenciales incorrectas", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frmInicio_de_sesion, "Debes rellenar los campos usuario y contraseña.",
					"Introduzca los datos necesarios.", JOptionPane.ERROR_MESSAGE);
		}
	}
}
