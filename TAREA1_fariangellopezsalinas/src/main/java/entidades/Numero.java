package entidades;

import java.util.ArrayList;
import java.util.List;

public class Numero {
	private int id;
	private int orden; // 1,2,3...
	private String nombre;
	private double duracion; // minutos (x.y) where y in {0,5}
	private List<Integer> artistasIds = new ArrayList<>();
	   
	//Gets && Sets
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getDuracion() {
		return duracion;
	}
	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}
	public List<Integer> getArtistasIds() {
		return artistasIds;
	}
	public void setArtistasIds(List<Integer> artistasIds) {
		this.artistasIds = artistasIds;
	}
    
 
}
