package dam.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

public class VPrincipal extends JFrame {
	private static final int ANCHO = 600;
	private static final int ALTO = 350;

	public VPrincipal() {
		init();
	}

	private void init() {
		setTitle("Ventana Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAcceso = new JLabel("¡Acceso Conseguido!");
		lblAcceso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcceso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblAcceso, BorderLayout.CENTER);
		
		setSize(ANCHO, ALTO);
		centrarVentana();
	}
	
	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO, ALTO)); 
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		Dimension ventana = this.getPreferredSize();               
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}

}
