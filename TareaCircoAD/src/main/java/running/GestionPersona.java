package running;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Especialidad;
import modelo.Persona;
import utils.FicherosUtils;
import utils.PaisesUtils;
import utils.ValidacionesUtils;

public class GestionPersona {
	public final Scanner sc =new Scanner(System.in);
	
	public void registrarPersona() {
		System.out.println("=== ===");
        System.out.println("Registrar Nueva Persona");
        System.out.println("=== ===");
		System.out.println("Escriba 'CANCELAR' en cualquier momento para salir");
		try {
			// VARIABLES NECESARIAS 
			//son usadas en todos los procesos son las que podemos encontrar en las clases: PERSONA, ARTISTA, COORDINACION
            String nombre = null;
            String email = null;
            String nacionalidad = null;
            int opcionPerfil = 0;
            String perfil = null;
            boolean esSenior = false;
            LocalDate fechaSenior = null;
            String apodo = null;
            List<Especialidad> especialidades = new ArrayList<>();
            String nombreUsuario = null;
            String contraseña = null;
			System.out.println("--- Datos Personales ---");
			
			//!!!DATOS PERSONA:
			
			//Nombre Usuario
			boolean nombreValido = false;
			do {
				System.out.print("- Nombre completo: ");
				nombre = sc.nextLine().trim();
				
				if (verificarCancelar(nombre)) return;
				
				if(ValidacionesUtils.validarNoVacio(nombre, "nombre")) {
					nombreValido = true;
				}else {
                    System.out.println("Por favor, intente de nuevo");
                }
			} while (!nombreValido);
			
			
			//Email
			boolean emailValido = false;
            do {
                System.out.print("- Email: ");
                email = sc.nextLine().trim();
                
                if (verificarCancelar(email)) return;
                
                if (!ValidacionesUtils.validarEmail(email)) {
                    System.out.println("Por favor, intente de nuevo");
                    continue;
                }
                
                if (FicherosUtils.existeEmail(email)) {
                    System.out.println("ERROR: Este email ya existe");
                    System.out.println("Use otro email");
                    continue;
                }
                
                emailValido = true;
            } while (!emailValido);
			
			//Nacionalidad
            boolean nacionalidadValida = false;
            do {
                nacionalidad = seleccionarNacionalidad();
                if (nacionalidad == null) {
                    System.out.println("Debe seleccionar una nacionalidad");
                } else {
                    nacionalidadValida = true;
                }
            } while (!nacionalidadValida);
			
			//!!!DATOS CLASES HIJA (ARTISTA7COORDINACION)
            
            System.out.println("--- Datos Especializados ---");
            boolean perfilValido = false;
            do {
                System.out.println("- Seleccione el perfil:");
                System.out.println("1. Coordinacipn");
                System.out.println("2. Artista");
                System.out.print("Opcion: ");
                String opc = sc.nextLine();
                if (verificarCancelar(opc)) return;
                
                try {
                    opcionPerfil = Integer.parseInt(opc);
                    
                    switch (opcionPerfil) {
                        case 1:
                            // COORDINACIÓN
                            perfil = "coordinacion";
                            System.out.println("- Perfil COORDINACION seleccionado:");
                            boolean respuestaValidaSenior = false;
                            
                            do {
                                System.out.print("¿Es senior? (S/N): ");
                                String respuesta = sc.nextLine().trim().toUpperCase();
                                
                                if (verificarCancelar(respuesta)) return;
                                
                                switch (respuesta) {
                                    case "S":
                                        esSenior = true;
                                        respuestaValidaSenior = true;
                                        
                                        boolean fechaValida = false;
                                        do {
                                            System.out.print("Fecha desde que es senior (DD-MM-AAAA): ");
                                            String fecha = sc.nextLine().trim();

                                            if (verificarCancelar(fecha)) return;

                                            try {  
                                            	//DateTimeFormatter aprendio en Interfaces
                                            	//Soy conciente que existe un LocalDate.parse(), pero creo que ese funiona con el yyyy-MM-dd, que para mi es un sistema/formado incomodo (opinion propia), si hay algun apunte o cambio puedo editarlo
                                                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                                fechaSenior = LocalDate.parse(fecha, formato);

                                                fechaValida = true;
                                                System.out.println("Fecha senior registrada: " + fechaSenior);

                                            } catch (Exception e) {
                                                System.out.println("ERROR: Formato de fecha inválido.");
                                                System.out.println("Use DD-MM-AAAA (ejemplo: 23-10-2025)");
                                            }

                                        } while (!fechaValida);
                                        break;
                                        
                                    case "N":
                                        esSenior = false;
                                        respuestaValidaSenior = true;
                                        System.out.println("No es senior");
                                        break;
                                        
                                    default:
                                        System.out.println("ERROR: Opción invalida. Debe responder S o N");
                                        break;
                                }
                            } while (!respuestaValidaSenior);
                            
                            perfilValido = true;
                            
                            break;
                            
                        case 2:
                            // ARTISTA
                            perfil = "artista";
                            System.out.println("- Perfil ARTISTA seleccionado");
                            
                            boolean respuestaValidaApodo = false;
                            do {
                                System.out.print("¿Tiene apodo? (S/N): ");
                                String respuesta = sc.nextLine().trim().toUpperCase();
                                
                                if (verificarCancelar(respuesta)) return;
                                
                                switch (respuesta) {
                                    case "S":
                                        respuestaValidaApodo = true;
                                        boolean apodoValido = false;
                                        do {
                                            System.out.print("Apodo profesional: ");
                                            apodo = sc.nextLine().trim();
                                            
                                            if (verificarCancelar(apodo)) return;
                                            
                                            if (!apodo.isEmpty()) {
                                                apodoValido = true;
                                                System.out.println("Apodo: " + apodo);
                                            } else {
                                                System.out.println("ERROR: El apodo no puede estar vacio");
                                            }
                                        } while (!apodoValido);
                                        break;
                                        
                                    case "N":
                                        respuestaValidaApodo = true;
                                        apodo = null;
                                        System.out.println("Sin apodo");
                                        break;
                                        
                                    default:
                                        System.out.println("ERROR: Opcion invalida. Debe responder S o N");
                                        break;
                                }
                            } while (!respuestaValidaApodo);
                            
                            // Seleccionar especialidades
                            boolean especialidadesValidas = false;
                            do {
                                especialidades = seleccionarEspecialidades();
                                if (!especialidades.isEmpty()) {
                                    especialidadesValidas = true;
                                } else {
                                    System.out.println("ERROR: Debe seleccionar al menos una especialidad");
                                }
                            } while (!especialidadesValidas);
                            
                            perfilValido = true;
                            
                            break;
 
                        default:
                            System.out.println("ERROR: Opción inválida. Debe ser 1 o 2.");
                            break;
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: Por favor, ingrese un número entero valido (1 o 2)");
                }
            } while (!perfilValido);
			
            
            //!!! DATOS CREDENCIALES
            System.out.println("--- Datos Credenciales ---");
            
            boolean usuarioValido = false;
            do {
            	//Yo esto me lo imagine como en una app que el nombre de usuario y la de la cuenta pueden ser diferentes
                System.out.print("Nombre de usuario (credenciales/cuenta): ");
                nombreUsuario = sc.nextLine().trim();
                
                if (verificarCancelar(nombreUsuario)) return;
                
                if (!ValidacionesUtils.validarNombreUsuarioCredencial(nombreUsuario)) {
                    System.out.println("Por favor, intente de nuevo.");
                    continue;
                }
                
                if (FicherosUtils.existeUsuario(nombreUsuario)) {
                    System.out.println("ERROR: Este nombre de usuario ya existe");
                    System.out.println("Por favor, elija otro nombre para sus credenciales");
                    continue;
                }
                
                usuarioValido = true;
            } while (!usuarioValido);
            
            
            boolean contraseñaValida = false;
            do {
                System.out.print("Contraseña: ");
                contraseña = sc.nextLine().trim();
                
                if (verificarCancelar(contraseña)) return;
                
                if (ValidacionesUtils.validarContraseñaCredencial(contraseña)) {
                    contraseñaValida = true;
                } else {
                    System.out.println("Por favor, intente de nuevo.");
                }
            } while (!contraseñaValida);
            
            System.out.println("____");
            System.out.println("Credenciales finales: ");
            System.out.println("Nombre de usuario "+nombreUsuario.toLowerCase());
            System.out.println("Contraña: "+contraseña);
            System.out.println("____");
            
          //!!! GUARDADO DE DATOS
            
    		long nuevoId = FicherosUtils.incrementarId(); //Aqui ocurre el una escala de tipos numéricos primitivos, donde el porpio programa pasa el int a long cuando entra a Persona
    		Persona persona = new Persona(nuevoId, nombre, email, nacionalidad);
    		
    		boolean guardarPersona= FicherosUtils.guardarCredencial(persona, nombreUsuario, contraseña, perfil);
    		
    		if (guardarPersona) {
                System.out.println("=== ===");
                System.out.println("Persona Resigtrada");
                System.out.println("=== ===");
                System.out.println("- ID asignado: " + nuevoId);
                System.out.println("- Nombre: " + nombre);
                System.out.println("- Email: " + email);
                System.out.println("- Nacionalidad: " + nacionalidad);
                System.out.println("- Nombre de usuario: " + nombreUsuario.toLowerCase());
                System.out.println("- Perfil: " + perfil.toUpperCase());
                
                if (perfil.equals("coordinacion") && esSenior) {
                    System.out.println("Senior desde: " + fechaSenior);
                } else if (perfil.equals("artista")) {
                    if (apodo != null) {
                        System.out.println("Apodo: " + apodo);
                    }
                    System.out.println("Especialidades: " + especialidades);
                }
                
                System.out.println("======");
            } else {
                System.out.println("Error al registrar la persona");
            }
		} catch (Exception e) {
            System.out.println("Error durante el registro: " + e.getMessage());
        }
			
	}
	
	private String seleccionarNacionalidad() {
		String nacionalidad = null;
        boolean nacionalidadValida = false;
        
        do {
        	PaisesUtils.mostrarPaises();
            
            System.out.print("Ingrese el ID del pais: ");
            String idPais = sc.nextLine().trim().toUpperCase();
            
            if (verificarCancelar(idPais)) {
                return null;
            }
            
            if (PaisesUtils.existePais(idPais)) {
                nacionalidad = PaisesUtils.obtenerNombrePais(idPais);
                nacionalidadValida = true;
                System.out.println("Pais seleccionado: "+nacionalidad);
            } else {
                System.out.println("ERROR: El ID del pais no es valido");
                System.out.println("Por favor, intente de nuevo");
            }
        } while (!nacionalidadValida);
        
        return nacionalidad;
	}
	
	private List<Especialidad> seleccionarEspecialidades() {
        List<Especialidad> especialidades = new ArrayList<>();
        boolean seleccionValida = false;
        
        do {
            System.out.println("Especialidades disponibles:");
            Especialidad[] todasEspecialidades = Especialidad.values();
            for (int i = 0; i < todasEspecialidades.length; i++) {
                System.out.println((i + 1) + ". " + todasEspecialidades[i]);
            }
            
            System.out.println("Ingrese los números de las especialidades separados por comas");
            System.out.print("(Ejemplo: 1,3,5) o CANCELAR: ");
            String entrada = sc.nextLine().trim();
            
            if (verificarCancelar(entrada)) {
                // Si cancela, asignar especialidad por defecto
                especialidades.add(Especialidad.ACROBACIA);
                System.out.println("Se asignara ACROBACIA por defecto.");
                return especialidades;
            }
            
            especialidades.clear(); // Limpiar lista por si hay reintentos
            String[] numeros = entrada.split(",");
            
            for (String num : numeros) {
                try {
                    int indice = Integer.parseInt(num.trim()) - 1;
                    if (indice >= 0 && indice < todasEspecialidades.length) {
                        if (!especialidades.contains(todasEspecialidades[indice])) {
                            especialidades.add(todasEspecialidades[indice]);
                        }
                    }
                } catch (NumberFormatException e) {
                    // Ignorar valores no validos
                }
            }
            
            if (especialidades.isEmpty()) {
                System.out.println("ERROR: No se seleccionaron especialidades válidas.");
                System.out.println("Por favor, intente de nuevo.");
            } else {
                seleccionValida = true;
                System.out.println("Especialidades seleccionadas: " + especialidades);
            }
            
        } while (!seleccionValida);
        
        return especialidades;
    }
	
	private boolean verificarCancelar(String entrada) {
        if (entrada != null && entrada.equalsIgnoreCase("CANCELAR")) {
            System.out.println("REGISTRO CANCELADO: por el propio usuario");
            return true;
        }
        return false;
    }
}
