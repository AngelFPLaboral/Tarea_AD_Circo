package running;

import java.util.List;

import modelo.Perfil;
import modelo.Persona;
import modelo.Sesion;
import utils.FicherosUtils;

public class GestionSesion {

	// VARIABLE
	private Sesion sesion;

	// GETTES
	public Sesion getSesion() {
		return sesion;
	}

	// CONSTRUCTOR:
	public GestionSesion() {
		super();
		this.sesion = new Sesion();
	}

	// METODOS
	/**
	 * Metodo: 'hijo' de comprobarActividad, devuelve el estado de la sesion sin que
	 * haga falta crear o usar la clase SESION, emplea su defaultdel constructor
	 * 
	 * @return
	 */
	public boolean haySesionActiva() {
		return sesion.comprobadorActiva();
	}

	public boolean login(String nombre, String contraseña) {
		if (sesion.comprobadorActiva()) {
			System.out.println("Ya hay una sesion activa. Cierrala primero.");
			return false;
		}

		if (nombre == null || nombre.trim().isEmpty() || contraseña == null || contraseña.trim().isEmpty()) {
			System.out.println("El nombre y la contraseña no pueden estar vacias");
			return false;
		}

		// Comprueba el usuario Admin
		if (GestionAdmin.esAdmin(nombre, contraseña)) {
			// Crear persona Admin ficticia para la sesión
			Persona admin = new Persona(0, "Administrador", "admin@circo.es", "España");
			sesion.iniciarSesion(admin, Perfil.ADMIN);
			System.out.println("\n¡Bienvenido, Administrador!");
			return true;
		}

		List<String[]> credenciales = FicherosUtils.leerCredenciales();

		for (String[] credencial : credenciales) {
			String nombreUsuario = credencial[1];
			String contraseñaUsuario = credencial[2];

			if (nombreUsuario.equals(nombre) && contraseñaUsuario.equals(contraseña)) {
				int id = Integer.parseInt(credencial[0]);
				String email = credencial[3];
				String nombreCredencial = credencial[4];
				String nacionalidad = credencial[5];
				String perfilStr = credencial[6];

				// Determinar el perfil
				Perfil perfil;
				if (perfilStr.equalsIgnoreCase("coordinacion")) {
					perfil = Perfil.CORDINACION;
				} else if (perfilStr.equalsIgnoreCase("artista")) {
					perfil = Perfil.ARTISTA;
				} else {
					perfil = Perfil.ARTISTA; // Por defecto
				}

				// Crear la persona y iniciar sesión
				Persona persona = new Persona(id, nombreCredencial, email, nacionalidad);
				sesion.iniciarSesion(persona, perfil);

				System.out.println("Bienvenido, " + nombreCredencial + "!");
				System.out.println("Perfil: " + perfil);
				return true;
			}
		}

		System.out.println("Nombre Cuenta/Credencial o Contraseña incorrecta. Intentelo de nuevo.");
		return false;
	}

	// !!!PREGUNTAR PORQUE NO FUNCIONA CON STATIC
	public void logout() {
		if (sesion.comprobadorActiva()) {
			System.out.println("Cerrando sesion de: " + sesion.getPersona().getNombre());
			sesion.cerrarSesion();
			System.out.println("Sesion cerrada");
			// El caso del else no creo que salte al estar controlado por los menus, pero
			// por si acaso
		} else {
			System.out.println("No hay ninguna sesion activa");
		}
	}

	/**
	 * 
	 * @return
	 */
	public Perfil getPerfilActual() {
		if (sesion == null) {
			// Esto no debería pasar, pero por seguridad
			return Perfil.INVITADO;
		}
		return sesion.getPerfil();
	}
}
