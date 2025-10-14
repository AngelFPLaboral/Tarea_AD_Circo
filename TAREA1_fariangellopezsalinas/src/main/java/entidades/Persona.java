package entidades;

import java.io.Serializable;

public class Persona implements Serializable{
	protected int id;
	protected String nombre;
	protected String email;
	protected String nacionalidad;
	protected Credencial credencial;
	
	//Constructor
	public Persona(int id, String nombre, String email, String nacionalidad,
			Credencial credencial) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.nacionalidad = nacionalidad;
		this.credencial = credencial;
	}

	//Gets && Sets
	public int getId() {
		return id;
	}
	
	// !! NO CREO QUE HAGA FALTA EN UNA APP COMO TAL (NUNCA CAMBIA O ES DADA AUTOMATICAMENTE)
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
	
	
}
