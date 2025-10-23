package modelo;

import java.util.ArrayList;
import java.util.List;

public class Artista extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables
	private String apodo;
	private List<Especialidad> especialidades;

	// Constructores
	/**
	 * Constructor Default
	 */
	public Artista() {
		super();
	}

	/**
	 * Constructor con todos las Variables
	 * 
	 * @param apodo
	 * @param especialidades
	 * @param numeros
	 */
	public Artista(String apodo, List<Especialidad> especialidades, List<Numero> numeros) {
		super();
		this.apodo = apodo;
		this.especialidades = new ArrayList<>();
	}

	// Set && Get
	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}

	// Metodos

	// METODOS A FUTURO: EDITAR ARTISTA
	/**
	 * Metodo para AÑADIR una especialidad a un Artista
	 * 
	 * @param especialidad
	 */
	public void añadirEspecialidad(Especialidad especialidad) {
		if (!this.especialidades.contains(especialidad)) {
			especialidades.add(especialidad);
			System.out.println("Se ha AÑADIDO correctamento la Especialidad: " + especialidad);
			System.out.println("A la Persona: " + super.getNombre());
		} else {
			System.out.println("La Persona: " + super.getNombre() + ", YA tiene la Especialidad: " + especialidad);
		}
	}

	/**
	 * Metodo para ELIMINAR una especialidad a un Artista
	 * 
	 * @param especialidad
	 */
	public void eliminarEspecialidad(Especialidad especialidad) {
		if (!this.especialidades.contains(especialidad)) {
			especialidades.remove(especialidad);
			System.out.println("Se ha ELIMINADO correctamento la Especialidad: " + especialidad);
			System.out.println("A la Persona: " + super.getNombre());
		} else {
			System.out.println("La Persona: " + super.getNombre() + ", NO tiene la Especialidad: " + especialidad);
		}
	}

	// AÑADIR EL TO STRING:

	/**
	 * 
	 */
	@Override
	public String toString() {
		return super.toString() + "/n - Apodo: " + apodo + "/n - Especialidades: " + especialidades;
	}
}
