package entidades;

public class Credencial {
	 private int idPersona;
	 private String nombreUsuario;
	 private String password;
	 private String perfil;
	 //AÑADIR VARIABLES? datos persona?
	 
	 
	 //Gets && Sets
	 public int getIdPersona() {
		return idPersona;
	 }
	 public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	 }
	 public String getNombreUsuario() {
		return nombreUsuario;
	 }
	 public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	 }
	 public String getPerfil() {
		return perfil;
	 }
	 public void setPerfil(String perfil) {
		this.perfil = perfil;
	 }
	 public String getPassword() {
		return password;
	 }
	 public void setPassword(String password) {
		this.password = password;
	 }
	 
	 //CREAR UN OVERRIDE PARA GENERAR CREDENCIALES
}
