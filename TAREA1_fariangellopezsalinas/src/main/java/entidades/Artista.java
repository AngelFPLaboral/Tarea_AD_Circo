package entidades;

import java.util.ArrayList;
import java.util.List;

public class Artista extends Persona{
	 private String apodo; 
	 private List<String> especialidades = new ArrayList<>();
	
	 //CONSTRUCROR HEREDADO PERSONA:
	 
	 
	 
	 //Gets && Sets
	 public String getApodo() {
		 return apodo;
	 }
	 public void setApodo(String apodo) {
		 this.apodo = apodo;
	 }
	 public List<String> getEspecialidades() {
		 return especialidades;
	 }
	 public void setEspecialidades(List<String> especialidades) {
		 this.especialidades = especialidades;
	 }
	 
	 
}
