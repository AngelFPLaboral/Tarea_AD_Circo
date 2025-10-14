package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Espectaculo implements Serializable{
	private int id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int idCoordinador;
    private List<Numero> numeros = new ArrayList<>();
    
    //Gets && Sets
    // !! NO CREO QUE HAGA FALTA EN UNA APP LOS SETT
	public int getId() {
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
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getIdCoordinador() {
		return idCoordinador;
	}
	public void setIdCoordinador(int idCoordinador) {
		this.idCoordinador = idCoordinador;
	}
	public List<Numero> getNumeros() {
		return numeros;
	}
	public void setNumeros(List<Numero> numeros) {
		this.numeros = numeros;
	}
    
    
}
