package modelo;

public class Sesion {
	// ATRIBUTOS
	private Persona persona;
	private Perfil perfil;
	private boolean activa;

	// CONSTRUCTORES

	/**
	 * Constructor Default Usada para sesion vacia y secion invitado
	 */
	public Sesion() {
		this.persona = null;
		this.perfil = Perfil.INVITADO;
		this.activa = false;
	}

	/**
	 * Constructor parametrizado con todos los atributos/variables
	 * 
	 * @param persona
	 * @param perfil
	 * @param activa
	 */
	public Sesion(Persona persona, Perfil perfil, boolean activa) {
		this.persona = persona;
		this.perfil = perfil;
		this.activa = activa;
	}

	// GETTERS
	// Para devolver estos datos si son necesarios visualizarlos, nadie puede dar
	// datos asi que omito los setters

	public Persona getPersona() {
		return persona;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	// METODOS
	/**
	 * Metodo para iniciar Sesion
	 * 
	 * @param persona
	 * @param perfil
	 */
	public void iniciarSesion(Persona persona, Perfil perfil) {
		this.persona = persona;
		this.perfil = perfil;
		this.activa = true;
	}

	/**
	 * Metodo para finalizar Sesion Limpia Sesion restableciendo todos sus valores
	 * de dentro (== null), menos el invitado que esa sobreentiendo que siempre
	 * estara activa
	 */
	public void cerrarSesion() {
		this.persona = null;
		this.perfil = Perfil.INVITADO;
		this.activa = false;
	}

	/**
	 * Metodo que devuelve el estado de la variable Activa
	 * 
	 * @return
	 */
	public boolean comprobadorActiva() {
		return activa;
	}

	//!!!HACER EL toString
	@Override
	public String toString() {
		if (activa) {
			return "Sesion: " + "/n - Persona" + persona + "/n - Perfil: " + perfil;
		} else
			return "Sesion: " + activa;
	}

}
