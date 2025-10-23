package modelo;

public class Credencial {
	// VARIABLES
	private String nombreUsuario;
	private String contraseña;
	private Perfil perfil;

	// CONTRUCTORES

	/**
	 * Metodo Default
	 */
	public Credencial() {
		super();
	}

	/**
	 * Metodo parametrizado con todos los atributos/variables
	 * 
	 * @param nombreUsuario
	 * @param contraseña
	 * @param perfil
	 */
	public Credencial(String nombreUsuario, String contraseña, Perfil perfil) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.perfil = perfil;
	}

	// GETTERS & SETTERS
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	// METODOS
	public boolean verificar(String usuario, String contraseña) {
		return this.nombreUsuario.equals(usuario) && this.contraseña.equals(contraseña);
	}

	//!!!HACER EL toString
}
