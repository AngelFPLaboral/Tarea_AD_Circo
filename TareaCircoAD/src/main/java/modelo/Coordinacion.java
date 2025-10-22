package modelo;

import java.time.LocalDate;
import java.util.List;

public class Coordinacion extends Persona{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//VARIABLES
	private boolean esSenior;
	private LocalDate fechaSenior;
	
	//CONSTRUCTORES
	/**
	 * Constructor Default
	 */
	public Coordinacion() {
		super();
	}
	
	
	/**
	 * Constructor con todos las Variables
	 * Se pone en false el valor es Senior por lo planteado en el ejercicio
	 * La fecha en default null tambien ya que sino lo es como podria tenerla (preguntar?)
	 * @param senior
	 * @param fechaSenior
	 * @param espectaculos
	 */
	public Coordinacion(boolean senior, LocalDate fechaSenior, List<Espectaculo> espectaculos) {
		super();
		this.esSenior = false;
		this.fechaSenior = null;
	}


	// GETTERS Y SETTERS
	public boolean isSenior() {
		return esSenior;
	}


	public void setSenior(boolean senior) {
		this.esSenior = senior;
	}


	public LocalDate getFechaSenior() {
		return fechaSenior;
	}


	public void setFechaSenior(LocalDate fechaSenior) {
		this.fechaSenior = fechaSenior;
	}

	//METODOS
	
	public void promoverASenior(LocalDate fecha) {
		this.esSenior=true;
		this.fechaSenior=fecha;
	}
	
	//HACER EL toString
	@Override
	public String toString() {
		return super.toString()+
				"/n Es Senior: "+esSenior+
				"/n Fecha Sernior: "+fechaSenior;
	}
}
