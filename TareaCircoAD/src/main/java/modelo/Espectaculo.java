package modelo;

import java.time.LocalDate;
import java.io.Serializable;

public class Espectaculo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private long id;
	private String nombre;
	private LocalDate fechaInic;
	private LocalDate fechaFin;
	private long idCoordinador; // Tuve que añadirlo despues porque lo vi en la rubrica

	// CONSTRUCTORES
	/**
	 * Contructor Default
	 */
	public Espectaculo() {
		super();
	}

	/**
	 * Constructor parametrizado con todos las variables/atributos
	 * 
	 * @param id
	 * @param nombre
	 * @param fechaInic
	 * @param fechaFin
	 */
	public Espectaculo(long id, String nombre, LocalDate fechaInic, LocalDate fechaFin, long idCoordinador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInic = fechaInic;
		this.fechaFin = fechaFin;
		this.idCoordinador = idCoordinador;
	}

	// GETTERS & SETTERS

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

	public long getIdCoordinador() {
		return idCoordinador;
	}

	public void setIdCoordinador(int idCoordinador) {
		this.idCoordinador = idCoordinador;
	}

	// METODOS
	@Override
	public String toString() {
		return "Espectaculo: " + "/n - Id Espectaculo: " + id + "/n - Nombre: " + nombre + "/n - Fecha Inicio: "
				+ fechaInic + "/n - Fecha Fin: " + fechaFin + "/n - Id Admininistrador: " + idCoordinador;

	}
}
