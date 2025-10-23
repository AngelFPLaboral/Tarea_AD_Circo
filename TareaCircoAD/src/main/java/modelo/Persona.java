package modelo;

import java.io.Serializable;

public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables:
	private long id; // Tuve un problema y es que este dato lo puse como id al inciar el proyecto y luego lo cambie, creo que no afecta igualmente pq en java un long si que puede contener a un int por la escala de tipos numéricos primitivos
	private String nombre;
	private String email;
	private String nacionalidad;
	private Credencial credencial;

	// Constructores:

	/**
	 * Constructor default
	 */
	public Persona() {

	}

	/**
	 * Constructor con todos las Variables
	 * 
	 * @param id
	 * @param nombre
	 * @param email
	 * @param nacionalidad
	 * @param credencial
	 */
	public Persona(long id, String nombre, String email, String nacionalidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.nacionalidad = nacionalidad;
	}

	// Get && Set
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Credencial getCredencial() {
		return credencial;
	}

	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}

	// Metodos
	/**
	 * Metodo para ver datos exactos de una persona No pondra datos de las clases
	 * hij@s
	 */
	@Override
	public String toString() {
		return "-> Persona:" + "/n - Id Persona: " + id + "/n - Nombre: " + nombre + "/n - Email: " + email
				+ "/n - Nacionalidad: " + nacionalidad;
	}

}
