package running;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import modelo.Espectaculo;
import modelo.Perfil;
import utils.FicherosUtils;
import utils.ValidacionesUtils;

public class GestionEspectaculo {
	// VARIABLES NECESARIAS
	private Scanner sc;
	private DateTimeFormatter formatterEntrada; // Para leer fechas del usuario: dd-MM-yyyy
	private DateTimeFormatter formatterSalida; // Para mostrar fechas: dd/MM/yyyy

	// CONSTRUCTOR
	public GestionEspectaculo() {
		this.sc = new Scanner(System.in);
		this.formatterEntrada = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		this.formatterSalida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}

	// METODOS:
	public void verEspectaculos() {
		System.out.println("=== ===");
		System.out.println("ESPECTACULOS");
		System.out.println("=== ===");

		try {
			
			List<Espectaculo> espectaculos = FicherosUtils.leerEspectaculos();

			// Controlar que el fichero no este vacio
			if (espectaculos.isEmpty()) {
				System.out.println("Todavia no hay espectaculos guardados");
				return;
			}

			// Mostrar cada espectaculo
			for (Espectaculo espectaculo : espectaculos) {
				mostrarEspectaculoBasico(espectaculo);
			}

			// Mostrar total para saber si coinciden con los creados
			System.out.println("==== ");
			System.out.println("Total de espectaculos: " + espectaculos.size());
			System.out.println("====");

		} catch (Exception e) {
			System.out.println("ERROR al intentar leer los espectaculos" + e.getMessage());
		}
	}

	/**
	 * Metodo: para creae espectaculos
	 */
	public void crearEspectaculo(Perfil perfilActual, long idUsuarioActual) {
		System.out.println("=== ===");
		System.out.println("Crear Espectaculo");
		System.out.println("=== ===");
		System.out.println("(Escriba 'CANCELAR' en cualquier momento para salir)");

		try {
			String nombre = null;
			LocalDate fechaInicio = null;
			LocalDate fechaFin = null;
			long idCoordinador = 0;

			System.out.println("--- Datos del Espectáculo ---");
			nombre = solicitarNombreEspectaculo();
			if (nombre == null)
				return;

			// Solicita Fechas
			System.out.println("--- Periodo de Fechas ---");
			System.out.println("- Formato: dd-MM-yyyy (ejemplo: 23-12-2025)");

			boolean fechasValidas = false;
			do {

				fechaInicio = solicitarFechaInicio();
				if (fechaInicio == null)
					return;

				fechaFin = solicitarFechaFin();
				if (fechaFin == null)
					return;

				if (ValidacionesUtils.validarPeriodoFechas(fechaInicio, fechaFin)) {
					fechasValidas = true;
					
					System.out.println("Periodo de fechas valido");
					System.out.println("___");
				} else {
					
					System.out.println("El periodo de fechas no es valido");
					System.out.println("___");
				}

			} while (!fechasValidas);

			System.out.println("-- Coordinador Responsable ---");
			System.out.println(perfilActual);
			if (perfilActual == Perfil.CORDINACION) {
				
				idCoordinador = idUsuarioActual;
				String nombreCoordinador = FicherosUtils.obtenerNombrePersonaPorId(idCoordinador);
				System.out.println("Coordinador asignado: " + nombreCoordinador + " (ID: " + idCoordinador + ")");

			} else if (perfilActual == Perfil.ADMIN) {
				idCoordinador = seleccionarCoordinador();
				if (idCoordinador == 0) System.out.println("Debe haber Coordinadores para hacer un Espectaculo");
					return;
			}

			// Guarda los espectaculos
			guardarNuevoEspectaculo(nombre, fechaInicio, fechaFin, idCoordinador);

		} catch (Exception e) {
			System.out.println("ERROR en la CREACION del especatculo");
			System.out.println("   " + e.getMessage());
			e.printStackTrace();
		}
	}

	// !!! METODO QUE PODRIA ELIMINAR
	public int contarEspectaculos() {
		try {
			List<Espectaculo> espectaculos = FicherosUtils.leerEspectaculos();
			return espectaculos.size();
		} catch (Exception e) {
			System.out.println("Error:  no se ha podido leer los espectaculos" + e.getMessage());
			return 0;
		}
	}

	/**
	 * Metodo: Muestra los datos de un espectaculo similar al toString
	 * 
	 * @param
	 */
	private void mostrarEspectaculoBasico(Espectaculo espectaculo) {
		System.out.println("=== Especatculo " + espectaculo.getId() + " ===");
		System.out.println("- ID: " + espectaculo.getId());
		System.out.println("- Nombre: " + espectaculo.getNombre());
		System.out.println("- Fecha Incio: " + espectaculo.getFechaInic().format(formatterSalida));
		System.out.println("- Fecha Fin: " + espectaculo.getFechaFin().format(formatterSalida));
		System.out.println("======");
	}

	/**
	 * Metodo: para solicitar el nombre del Espectaculo que queremos crear
	 * 
	 * @return
	 */
	private String solicitarNombreEspectaculo() {
		boolean nombreValido = false;
		String nombre = null;

		do {
			System.out.print("- Nombre del espectáculo: ");
			nombre = sc.nextLine().trim();

			if (verificarCancelar(nombre))
				return null;

			if (!ValidacionesUtils.validarNombreEspectaculo(nombre)) {
				System.out.println("Por favor, intente de nuevo.");
				continue;
			}

			if (FicherosUtils.existeEspectaculo(nombre)) {
				System.out.println("ERROR: Ya existe un espectaculo con el mismo nombre.");
				System.out.println("Introduzca/Elija otro nombre");
				continue;
			}

			nombreValido = true;
			System.out.println("- Nombre: " + nombre);

		} while (!nombreValido);

		return nombre;
	}

	/**
	 * Metodo:
	 * 
	 * @return
	 */
	private LocalDate solicitarFechaInicio() {
		boolean fechaValida = false;
		LocalDate fecha = null;

		do {
			System.out.print("Fecha de inicio (dd-MM-yyyy): ");
			String fechaStr = sc.nextLine().trim();

			if (verificarCancelar(fechaStr))
				return null;

			try {
				fecha = LocalDate.parse(fechaStr, formatterEntrada);
				fechaValida = true;
				System.out.println("- Fecha de inicio: " + fecha.format(formatterSalida));
			} catch (DateTimeParseException e) {
				System.out.println("ERROR: Formato de fecha invalido. (00-00-0000)");
				System.out.println("Use el formato dd-MM-yyyy (ejemplo: 23-12-2025)");
			}

		} while (!fechaValida);

		return fecha;
	}

	/**
	 * 
	 * @return
	 */
	private LocalDate solicitarFechaFin() {
		boolean fechaValida = false;
		LocalDate fecha = null;

		do {
			System.out.print("Fecha de fin (dd-MM-yyyy): ");
			String fechaStr = sc.nextLine().trim();

			if (verificarCancelar(fechaStr))
				return null;

			try {
				fecha = LocalDate.parse(fechaStr, formatterEntrada);
				fechaValida = true;
				System.out.println(" Fecha de fin: " + fecha.format(formatterSalida));
			} catch (DateTimeParseException e) {
				System.out.println("ERROR: Formato de fecha inválido.");
				System.out.println("Use el formato dd-MM-yyyy (ejemplo: 23-12-2025)");
			}

		} while (!fechaValida);

		return fecha;
	}

	/**
	 * Metodo:
	 * 
	 * @param nombre
	 * @param fechaInicio
	 * @param fechaFin
	 */
	private void guardarNuevoEspectaculo(String nombre, LocalDate fechaInicio, LocalDate fechaFin, long idCoordinador) {
		try {

			long nuevoId = FicherosUtils.obtenerSiguienteIdEspectaculo();

			Espectaculo nuevoEspectaculo = new Espectaculo(nuevoId, nombre, fechaInicio, fechaFin, idCoordinador);

			boolean guardado = FicherosUtils.añadirEspectaculo(nuevoEspectaculo);

			if (guardado) {
				// Mostrar mensaje de éxito
				mostrarMensajeExito(nuevoEspectaculo);
			} else {
				System.out.println("ERROR: No se pudo guardar el espectaculo");
			}

		} catch (Exception e) {
			System.out.println("ERROR al guardar el espectaculo:");
			System.out.println("   " + e.getMessage());
		}
	}

	/**
	 * Metodo: Muestra el Especatculo creado
	 * 
	 * @param espectaculo
	 */
	private void mostrarMensajeExito(Espectaculo espectaculo) {
		System.out.println("=== ====");
		System.out.println("Espectaculo Creado Exitosamente");
		System.out.println("=== ===");
		System.out.println("- ID asignado: " + espectaculo.getId());
		System.out.println("- Nombre: " + espectaculo.getNombre());
		System.out.println("- Fecha inicio: " + espectaculo.getFechaInic().format(formatterSalida));
		System.out.println("- Fecha fin: " + espectaculo.getFechaFin().format(formatterSalida));

		String nombreCoord = FicherosUtils.obtenerNombrePersonaPorId(espectaculo.getIdCoordinador());
		System.out.println("Coordinador: " + nombreCoord + ", Su ID: " + espectaculo.getIdCoordinador());
		System.out.println("=== ===");
	}

	private boolean verificarCancelar(String entrada) {
		if (entrada != null && entrada.equalsIgnoreCase("CANCELAR")) {
			System.out.println("La creacion de espectaculo ha sido parada");
			System.out.println("Causa: el propio Usuario");
			return true;
		}
		return false;
	}

	private int seleccionarCoordinador() {

		List<String[]> coordinadores = FicherosUtils.obtenerCoordinadores();

		if (coordinadores.isEmpty()) {
			System.out.println("ERROR: No hay personas con perfil Coordinador");
			System.out.println("Debe exisitir al menos un coordinador antes de crear un espectaculo");
			return 0;
		}

		// Mostrar lista de coordinadores
		System.out.println("___Seleccione el coordinador responsable del espectaculo___");
		System.out.println("__");
		for (String[] coordinador : coordinadores) {
			System.out.println("- ID: " + coordinador[0] + " - " + coordinador[1] + " (" + coordinador[2] + ")");
		}
		System.out.println("__");

		boolean seleccionValida = false;
		int idCoordinador = 0;

		do {
			System.out.print("- Ingrese el ID del coordinador: ");
			String entrada = sc.nextLine().trim();

			if (verificarCancelar(entrada))
				return 0;

			try {
				idCoordinador = Integer.parseInt(entrada);

				// Verificar que el ID sea de un coordinador
				if (FicherosUtils.esCoordinador(idCoordinador)) {
					seleccionValida = true;
					String nombreCoord = FicherosUtils.obtenerNombrePersonaPorId(idCoordinador);
					System.out.println("Coordinador elegido: " + nombreCoord + " (Su Id:" + idCoordinador + ")");
				} else {
					System.out.println("ERROR: El ID ingresado no existe o no es de ningun coordinador");
					System.out.println("Vuelva ha intentarlo");
				}

			} catch (NumberFormatException e) {
				System.out.println("ERROR: Debe ingresar un número entero");
			}

		} while (!seleccionValida);

		return idCoordinador;
	}
}
