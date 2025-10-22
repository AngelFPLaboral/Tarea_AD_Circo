package modelo;

import java.time.LocalDate;
import java.io.Serializable;

public class Espectaculo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//VARIABLES
	private long id;
	private String nombre;
	private LocalDate fechaInic;
	private LocalDate fechaFin;
	
	
	//CONSTRUCTORES
	/**
	 * Contructor Default
	 */
	public Espectaculo() {
		super();
	}
	
	/**
	 * Constructor parametrizado con todos las variables/atributos
	 * @param id
	 * @param nombre
	 * @param fechaInic
	 * @param fechaFin
	 */
	public Espectaculo(long id, String nombre, LocalDate fechaInic, LocalDate fechaFin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInic = fechaInic;
		this.fechaFin = fechaFin;
	}
	
	//GETTERS & SETTERS

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

	public LocalDate getFechaInic() {
		return fechaInic;
	}

	public void setFechaInic(LocalDate fechaInic) {
		this.fechaInic = fechaInic;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	//METODOS
	@Override
	public String toString() {
		return "Espectaculo: "+
	"/n - Id Espectaculo: "+id+
	"/n - Nombre: "+nombre+
	"/n - Fecha Inicio: "+fechaInic+
	"/n - Fecha Fin: "+fechaFin;
		
	}
}
