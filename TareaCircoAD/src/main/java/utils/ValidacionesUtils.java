package utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValidacionesUtils {
	
	public static boolean validarNoVacio(String texto, String nombreVariable) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println("El campo '" + nombreVariable + "' no puede estar vacío.");
            return false;
        }
        return true;
    }
	
	/**
	 * Metodo: Valida si sigue las normas que se deben seguir al registrar el nombre de un usuario nuevo
	 * @param nombreUsuario
	 * @return
	 */
	public static boolean validarNombreUsuarioCredencial(String nombreUsuario) {
		if(nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
			System.out.println("El nombre del usuario no puede estar vacio");
			return false;
		}
		
		if(nombreUsuario.length() <= 2) {
			System.out.println("El nombre de usuario debe de tener más de 2 caracteres");
			return false;
		}
		
		if (nombreUsuario.length() > 50) {
            System.out.println("El nombre del usuario debe de tener menos de 25 caracteres.");
            return false;
        }
		
		if (!nombreUsuario.matches("[a-zA-Z]+")) {
            System.out.println("El nombre de usuario solo puede contener letras sin tildes");
            return false;
        }
		
		 if (nombreUsuario.contains(" ")) {
			 //para que se asemeje al ejemplo visto en el pdf
            System.out.println("El nombre de usuario no puede contener espacios.");
            return false;
         }

		
		return true;
	}
	
	
	/**
	 * Metodo: Valida si sigue las normas que se deben seguir al registrar la contraseña de un usuario nuevo
	 * @param contraseña
	 * @return
	 */
	public static boolean validarContraseñaCredencial(String contraseña) {
		if (contraseña == null || contraseña.trim().isEmpty()) {
			System.out.println("La contraseña no puede estar vacio");
            return false;   
        }
        
        if (contraseña.length() <= 2) {
            System.out.println("La contraseña debe tener más de 2 caracteres.");
            return false;
        }
        
        if (contraseña.contains(" ")) {
            System.out.println("La contraseña no puede contener espacios.");
            return false;
        }
        
        return true;
	}
	
	/**
	 * Metodo: Valida si sigue las normas que se deben seguir al registrar el email de un usuario nuevo
	 * @param email
	 * @return
	 */
	public static boolean validarEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			System.out.println("El email del usuario no puede estar vacio");
            return false;
        }
        
        // Standar Email:
        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("El formato del email no es válido.");
            return false;
        }
        
        return true;
	}
	
	/**
	 * Metodo: Valida si sigue las normas que se deben seguir al registrar el nombre de un espectaculo nuevo
	 * @param nombre
	 * @return
	 */
	public static boolean validarNombreEspectaculo(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre del espectáculo no puede estar vacio");
            return false;
        }
        
        if (nombre.length() > 25) {
            System.out.println("El nombre del espectaculo no puede superar los 25 caracteres");
            return false;
        }
        
        return true;
    }
	
	public static boolean validarPeriodoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            System.out.println("Las fechas no pueden ser nulas");
            return false;
        }
        
        // Verificar que la fecha de inicio no sea posterior a la del fin
        if (fechaInicio.isAfter(fechaFin)) {
            System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin");
            return false;
        }
        
     // Verificar que no haya más de 365 dias entre la fecah inicio y fin
        long diasDiferencia = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        if (diasDiferencia > 365) {
            System.out.println("ERROR: El periodo de fechas no puede superar 1 año (365 días).");
            System.out.println("Duración actual: "+ diasDiferencia);
            return false;
        }
        
        return true;
    }
}
