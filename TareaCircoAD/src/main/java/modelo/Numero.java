package modelo;

import java.util.List;

public class Numero {
	//VARIABLES
	private long id;
	private String nombre;
	private int orden;
	private double duracion;
	private List<Artista> artistas;
	
	//CONSTRUCTORES
	
	/**
	 * Construtor Default
	 */
	public Numero() {
		super();
	}
	
	/**
	 * Constructor parametrizado con todos las variables
	 * @param id
	 * @param nombre
	 * @param orden
	 * @param duracion
	 * @param artistas
	 */
	public Numero(long id, String nombre, int orden, double duracion, List<Artista> artistas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.orden = orden;
		this.duracion = duracion;
		this.artistas = artistas;
	}

	//GETTERS Y SETTERS
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

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	//METODOS
	
	//HACER EL ToString
}
