package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import modelo.Espectaculo;
import modelo.Persona;

public class FicherosUtils {
	private static final String rutaCredenciales = "credenciales.txt";
	private static final String rutaEspectaculos = "espectaculo.dat";

	// FICHEROS CREDENCIALES METODOS:
	/**
	 * METODO PARA LEER CREEDENCIALES Sirve tambien como list de transporte de datos
	 * para ser comparados en futuros metoods
	 * 
	 * @return
	 */
	// !!!PRESUPONGO QUE TODOS LAS CREDENCIALES TIENEN LA MISMA LOGINTUD, sinedo un
	// standar
	// (CAMBIAR A FUTURO SI EL EJERCICIO LO RREQUIERE)

	public static List<String[]> leerCredenciales() {
		List<String[]> credenciales = new ArrayList<>();
		File file = new File(rutaCredenciales);

		// Si el archivo NO existe o no es encontrado en la ruta, crea uno (usado para
		// crear el fichero Credencial por primera vez)
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Error al CREAR/ENCONTRAR el archivo credenciales");
			}
			// Devuelde credenciales aunque este vacio
			return credenciales;
		}

		// Si el archivo ES encontrado, devuelve y lee las credenciales
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split("\\|");

				// !!!PUNTO CRITICO, aqui se refleja mi creencia que las credenciales estan
				// standarizadas y estan no varian en tamaño y datos
				if (datos.length == 7) {
					// Devuelde credenciales con la linea de datos añadida
					credenciales.add(datos);
				}
			}
		} catch (IOException e) {
			System.out.println("Error al LEER el archivo credenciales");
		}

		// Credenciales ya listas para elevarse hasta el metodo en el que sea requerido
		// Vacias si no hay o con las lineas leidas si hay y las que existan
		return credenciales;
	}

	/**
	 * Metodo para saber si el nombre de usuario ya existe
	 * 
	 * @param nombreUsuario
	 * @return
	 */
	public static boolean existeUsuario(String nombreUsuario) {
		List<String[]> credenciales = leerCredenciales();

		for (String[] credencial : credenciales) {
			if (credencial[1].equalsIgnoreCase(nombreUsuario)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo para saber si el email de usuario ya existe
	 * 
	 * @param emailUsuario
	 * @return
	 */
	public static boolean existeEmail(String emailUsuario) {
		List<String[]> credenciales = leerCredenciales();

		for (String[] credencial : credenciales) {
			if (credencial[3].equalsIgnoreCase(emailUsuario)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo: para incremental del id Perosona/Usuario
	 * 
	 * @return
	 */
	public static long incrementarId() {
		List<String[]> credenciales = leerCredenciales();

		// Si esta vacio empeiza en 1
		// El 0 reservado para ADMIN
		if (credenciales.isEmpty()) {
			return 1;
		}

		long maxId = 0;

		for (String[] credencial : credenciales) {
			try {
				long id = Integer.parseInt(credencial[0]);
				if (id > maxId) {
					maxId = id;
				}
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Ha habido un erro al intentar encontrar el núm más alto");
			}
		}
		return maxId + 1;
	}

	public static boolean guardarCredencial(Persona persona, String nombreUsuario, String contraseña, String perfil) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaCredenciales, true))) {
			String linea = String.format("%d|%s|%s|%s|%s|%s|%s", persona.getId(), nombreUsuario.toLowerCase(),
					contraseña, persona.getEmail(), persona.getNombre(), persona.getNacionalidad(),
					perfil.toLowerCase());
			bw.write(linea);
			bw.newLine();
			return true;

		} catch (IOException e) {
			System.out.println("ERROR: Ha habido un erro al guardar las Credenciales");
			return false;
		}
	}

	// FICHEROS ESPECATCULOS METODOS:
	/**
	 * Metodo para leer todos los espectaculos en el .dat
	 * 
	 * @return
	 */
	public static List<Espectaculo> leerEspectaculos() {
		List<Espectaculo> espectaculos = new ArrayList<>();
		File file = new File(rutaEspectaculos);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Error al CREAR/ENCONTRAR el archivo espectaculo");
			}
			// Devuelde Espectaclos aunque este vacio
			return espectaculos;
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			while (true) {
				try {
					Espectaculo espectaculo = (Espectaculo) ois.readObject();
					espectaculos.add(espectaculo);
				} catch (EOFException e) {
					// Evista un bucle infinito o paron de aplicacion al dejar de haber objetos
					// nuevos en el fichero
					break;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al LEER espectaculos");
		}

		return espectaculos;
	}

	/**
	 * Metodo para añadir un nuevo Espectaculo a la lista y al propio .dat
	 * 
	 * @param espectaculo
	 * @return
	 */
	public static boolean añadirEspectaculo(Espectaculo espectaculo) {
		File file = new File(rutaEspectaculos);
		List<Espectaculo> espectaculos = leerEspectaculos();

		espectaculos.add(espectaculo);

		// Guarda el espectaculo nuevo
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			for (Espectaculo e : espectaculos) {
				oos.writeObject(e);
			}
			return true;

		} catch (FileNotFoundException e) {
			System.out.println("Error al GUARDAR el nuevo Espectaculo");
			return false;
		}

		catch (IOException e) {
			System.out.println("Error al GUARDAR el nuevo Espectaculo");
			return false;
		}
	}

	/**
	 * Metodo: Comprueba si ya hay un espectaculo existente igual Lo hace por el
	 * nombre al no tener más valores comparables Tampoco sale en el pdf si hay más
	 * restricciones con esto al respecto de que existan varios espectaculos o
	 * similares
	 * 
	 * @param nombre
	 * @return
	 */
	public static boolean existeEspectaculo(String nombre) {
		List<Espectaculo> espectaculos = leerEspectaculos();
		for (Espectaculo e : espectaculos) {
			if (e.getNombre().equalsIgnoreCase(nombre)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo: Genera un id auto-incremental para todos los espectaculos que se
	 * vayan a registrar
	 * 
	 * @return
	 */
	public static long obtenerSiguienteIdEspectaculo() {
		List<Espectaculo> espectaculos = leerEspectaculos();
		long maxId = 0;

		for (Espectaculo e : espectaculos) {
			if (e.getId() > maxId) {
				maxId = e.getId();
			}
		}

		return maxId + 1;
	}

	public static String obtenerNombrePersonaPorId(long idPersona) {
		List<String[]> credenciales = leerCredenciales();

		for (String[] credencial : credenciales) {
			try {
				int id = Integer.parseInt(credencial[0]);
				if (id == idPersona) {
					return credencial[4]; // nombre_persona
				}
			} catch (NumberFormatException e) {
				// Ignorar líneas con ID inválido
			}
		}

		return null;
	}

	public static List<String[]> obtenerCoordinadores() {
		List<String[]> coordinadores = new ArrayList<>();
		List<String[]> credenciales = leerCredenciales();

		for (String[] credencial : credenciales) {
			String perfil = credencial[6];

			if (perfil.equalsIgnoreCase("coordinacion")) {
				String[] coordinador = new String[3];
				coordinador[0] = credencial[0];
				coordinador[1] = credencial[4];
				coordinador[2] = credencial[3];
				coordinadores.add(coordinador);
			}
		}

		return coordinadores;
	}

	public static boolean esCoordinador(int idPersona) {
		List<String[]> credenciales = leerCredenciales();

		for (String[] credencial : credenciales) {
			try {
				int id = Integer.parseInt(credencial[0]);
				String perfil = credencial[6];

				if (id == idPersona && perfil.equalsIgnoreCase("coordinacion")) {
					return true;
				}
			} catch (NumberFormatException e) {
				// Continuan el programa para que pase el return false
			} catch (ArrayIndexOutOfBoundsException e) {
				// Continuan el programa para que pase el return false
			}
		}

		return false;
	}
}
