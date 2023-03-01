package dam.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dam.db.persistencia.UsuariosPersistencia;
import dam.model.Usuario;
import dam.view.VLogin;
import dam.view.VPrincipal;
import dam.view.VRegistro;

public class LoginControl implements ActionListener {
	
	//TODO
	private static final int TOTAL_INTENTOS = 3;
	private VLogin vLogin;
	private VPrincipal vPpal;
	private UsuariosPersistencia up;
	private VRegistro vReg;
	private int contIntentos;
	

	public LoginControl(VLogin vLogin, VPrincipal vPpal, VRegistro vReg, UsuariosPersistencia up) {
		this.vLogin = vLogin;
		this.vPpal = vPpal;
		this.vReg = vReg;
		this.up = up;
		contIntentos = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() instanceof JButton) {
			if(e.getActionCommand().equals(VLogin.BTN_ACCEDER)) {
				intentarAcceder();
			} else if(e.getActionCommand().equals(VLogin.BTN_REGISTRAR)) {
				accederRegistro();
			} else if(e.getActionCommand().equals(VRegistro.BTN_ACEPTAR)) {
				registrarUsuario();
			} else if(e.getActionCommand().equals(VRegistro.BTN_CANCELAR)) {
				volverLogin();
			}
		}	
	}



	private void registrarUsuario() {
		Usuario user = vReg.obtenerUsuario();
		
		if(user != null) {
			String pwd = up.ConsultarPasswordPorUsuario(user.getNomUser());
			
			if(pwd == null) {
				int res = up.insertarUsuario(user);
				if(res == 1) {
					vReg.mostrarInformacion("El usuario se ha registrado correctamente");
					volverLogin();
					vLogin.cargarUsuario(user.getNomUser());
				} else {
					vReg.mostrarError("No se ha podido registrar el usuario");
				}
			} else {
				vReg.mostrarError("El usuario introducido ya existe");
			}
		}
		
	}



	private void volverLogin() {
		vReg.dispose(); // Cerrar la ventana
		vReg.limpiarDatos(); // Limpiar Datos
		vLogin.hacerVisible();
	}



	private void accederRegistro() {
		vLogin.dispose(); //Cerrar una ventana
		vLogin.limpiarDatos(); // Limpiar datos
		vReg.hacerVisible();
	}



	private void intentarAcceder() {
		Usuario user = vLogin.obtenerUsuario();
		
		if(user != null) {
			contIntentos++;
			boolean accede = false;
			String error = "";
			
			
			String pwd = up.ConsultarPasswordPorUsuario(user.getNomUser());
			
			if(pwd != null) {
				// Comprobar si coincide con la password introducida
				if(pwd.equals(user.getPwd())) {
					// Permiso para acceder
					accede = true;
					vLogin.dispose(); // Cerrar la ventana
					vLogin.limpiarDatos(); // Limpiar Datos
					vPpal.hacerVisible();
				} else {
					error = "La password introducida es incorrecta";
				}
			} else {
				error = "El usuario introducido no existe";
			}
			
			if(!accede) {
				if(contIntentos < 3) {
				error += "\nIntentos restantes: " + (TOTAL_INTENTOS - 2);
				vLogin.mostrarError(error);	
			} else {
				vLogin.mostrarError("Se han agotado los " + TOTAL_INTENTOS + " intentos "
						+ "\nSe va a cerrar la aplicación");
				System.exit(0);
			}
		}
	}
}
}
