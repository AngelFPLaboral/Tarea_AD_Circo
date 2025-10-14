package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Coordinacion extends Persona{
	private boolean senior;
    private LocalDate seniorDesde;
    private List<Integer> espectaculosDirigidos = new ArrayList<>();
  
    //CONSTRUCROR HEREDADO PERSONA:
	 
	 
    //Gets && Sets
	public boolean isSenior() {
		return senior;
	}
	public void setSenior(boolean senior) {
		this.senior = senior;
	}
	public LocalDate getSeniorDesde() {
		return seniorDesde;
	}
	public void setSeniorDesde(LocalDate seniorDesde) {
		this.seniorDesde = seniorDesde;
	}
	public List<Integer> getEspectaculosDirigidos() {
		return espectaculosDirigidos;
	}
	public void setEspectaculosDirigidos(List<Integer> espectaculosDirigidos) {
		this.espectaculosDirigidos = espectaculosDirigidos;
	}
    
    
}
