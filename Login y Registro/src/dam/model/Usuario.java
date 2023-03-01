package dam.model;

public class Usuario {
	
	private String nomUser;
	private String pwd;
	
	public Usuario(String nomUser, String pwd) {
		this.nomUser = nomUser;
		this.pwd = pwd;
	}

	public String getNomUser() {
		return nomUser;
	}

	public String getPwd() {
		return pwd;
	}
	
	
	
}
