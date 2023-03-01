package dam.main;

import java.awt.EventQueue;

import dam.control.LoginControl;
import dam.db.persistencia.UsuariosPersistencia;
import dam.view.VLogin;
import dam.view.VPrincipal;
import dam.view.VRegistro;

public class InicioLogin {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			
			@Override
			public void run() {
			VLogin vLogin = new VLogin();
			VRegistro vReg = new VRegistro();
			VPrincipal vPpal = new VPrincipal();
			
			UsuariosPersistencia up = new UsuariosPersistencia();
			
			LoginControl control = new LoginControl(vLogin, vPpal, vReg, up);
			
			vLogin.setControlador(control);
			vReg.setControlador(control);
			
			vLogin.hacerVisible();
			}
		});
		
	}

}
