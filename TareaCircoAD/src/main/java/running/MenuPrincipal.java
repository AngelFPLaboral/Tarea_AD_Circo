package running;

import java.util.Scanner;

import modelo.Perfil;

public class MenuPrincipal {
	//VARIBALES
	private Scanner sc;
    private GestionSesion gestionSesion;
    private GestionEspectaculo gestionEspectaculo;
    private GestionPersona gestionPersona;
    
    //CONSTRUCTOR
    public MenuPrincipal() {
		super();
		this.sc = new Scanner(System.in);
		this.gestionSesion = new GestionSesion();
		this.gestionEspectaculo = new GestionEspectaculo();
		this.gestionPersona = new GestionPersona();
	}
    
    //METODOS
    public void iniciar() {
        mostrarBienvenida();
        
        boolean salir = false;
        
        while (!salir) {
            // Mostrar el menú según el estado de autenticación
        	Perfil perfilActual = gestionSesion.getPerfilActual();
            
            if (perfilActual == Perfil.INVITADO) {
                salir = mostrarMenuInvitado();
            } else {
                salir = mostrarMenuSesionActiva();
            }
        }
        System.out.println("¡Hasta pronto!");
    }
    
    
	/**
     * Muestra el mensaje de bienvenida
     */
    private void mostrarBienvenida() {
        System.out.println("=======");
        System.out.println("BIENVENIDO AL CIRCO");
        System.out.println("=======");

    }
    

    private boolean mostrarMenuInvitado() {
        System.out.println("=== ===");
        System.out.println("Menu Invitado");
        System.out.println("=== ===");
        System.out.println("1. Ver espectáculos");
        System.out.println("2. Iniciar sesión (Login)");
        System.out.println("0. Salir");
        System.out.println("___");
        System.out.print("Seleccione una opción: ");
        
        try {
            int opc = leerEnteroSeguro();     
            switch (opc) {
                case 1:
                    gestionEspectaculo.verEspectaculos();
                    break;                   
                case 2:
                    realizarLogin();
                    break;
                    
                case 0:
                    return true; 
                    
                default:
                    System.out.println("Opcion no valida. Escriba un numero entero entre 1-3");
                    break;
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Opcion no valida. Escriba un numero entero entre 1-3");
        } catch (Exception e) {
            System.out.println("Opcion no valida. Escriba un numero entero entre 1-3"+ e.getMessage());
        }
        
        return false;
    }
    

    private boolean mostrarMenuSesionActiva() {
        Perfil perfil = gestionSesion.getPerfilActual();
        
        System.out.println("=== ===");
        System.out.println(" MENU " + perfil);
        System.out.println("=== ===");
        System.out.println("1. Ver espectáculos");
        
        // Opciones según el perfil
        switch (perfil) {
            case ADMIN:
                mostrarOpcionesAdmin();
                break;
            case CORDINACION:
                mostrarOpcionesCoordinacion();
                break;
            case ARTISTA:
                mostrarOpcionesArtista();
                break;
            default:
                System.out.println("Error: Perfil no reconocido");
                break;
        }
        
        System.out.println("0. Cerrar sesión (Logout)");
        System.out.println("__");
        System.out.print("Seleccione una opción: ");
        
        try {
            int opcion = leerEnteroSeguro();
            
            // Procesar opción
            return getSeleccionOpcMenuPerfil(opcion, perfil);
            
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Debe ingresar un número valido.");
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
        
        return false;
    }
    
    private void mostrarOpcionesAdmin() {
        System.out.println("2. Registrar nueva persona");
        System.out.println("3. Crear espectáculo");
        System.out.println("4. Ver datos completos de espectáculo (No implementado)");
    }
    
    private void mostrarOpcionesCoordinacion() {
        System.out.println("2. Crear espectaculo");
        System.out.println("3. Ver datos completos de espectáculo (No implementado)");
    }
    
    private void mostrarOpcionesArtista() {
        System.out.println("2. Ver mi ficha (No tiene funcionalidad, no se pidieron)");
        System.out.println("3. Ver datos completos de espectáculo (No tiene funcionalidad, no se pidieron)");
    }
    

    private boolean getSeleccionOpcMenuPerfil(int opcion, Perfil perfil) {
        try {
            if (opcion == 1) {
                gestionEspectaculo.verEspectaculos();
                return false;
            }
            
            if (opcion == 0) {
                gestionSesion.logout();
                return false;
            }
            
            switch (perfil) {
                case ADMIN:
                    return getOpcionAdmin(opcion);
                    
                case CORDINACION:
                    return getOpcionCordinacion(opcion);
                    
                case ARTISTA:
                    return getOpcionArtista(opcion);
                    
                default:
                    System.out.println("ERROR: Perfil no reconocido.");
                    return false;
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: fallo en la administracion de opciones: " + e.getMessage());
            return false;
        }
    }
    

    private boolean getOpcionAdmin(int opcion) {
        try {
            switch (opcion) {
                case 2:
                    gestionPersona.registrarPersona();
                    break;
                    
                case 3:
                    gestionEspectaculo.crearEspectaculo(Perfil.ADMIN, 0);
                    break;
                    
                case 4:
                    System.out.println("AVISO: Esta funcionalidad todavia no ha sido pedida, futuras entregas");
                    break;
                    
                default:
                    System.out.println("Opcion no valida, debe ser un numero entero entre 0-4");
                    break;
            }
        } catch (Exception e) {
            System.out.println("ERROR: fallo en las opciones ADMIN: " + e.getMessage());
        }
        return false;
    }
    

    private boolean getOpcionCordinacion(int opcion) {
        try {
            switch (opcion) {
                case 2:
                	long idCoordinador =  gestionSesion.getSesion().getPersona().getId();
                    gestionEspectaculo.crearEspectaculo(Perfil.CORDINACION, idCoordinador);
                    break;
                    
                case 3:
                    System.out.println("AVISO: Esta funcionalidad todavia no ha sido pedida, futuras entregas");
                    break;
                    
                default:
                	System.out.println("Opcion no valida, debe ser un numero entero entre 0-3");
                    break;
            }
        } catch (Exception e) {
            System.out.println("ERROR: fallo en las opciones COORDINACIÓN: " + e.getMessage());
        }
        return false;
    }
    
 
    private boolean getOpcionArtista(int opcion) {
        try {
            switch (opcion) {
                case 2:
                case 3:
                    System.out.println("AVISO: Esta funcionalidad todavia no ha sido pedida, futuras entregas");
                    break;
                    
                default:
                    System.out.println("Opcion no valida, debe ser un numero entero entre 0-3");
                    break;
            }
        } catch (Exception e) {
            System.out.println("ERROR: fallo en las opciones ARTISTA: " + e.getMessage());
        }
        return false;
    }
    

    private void realizarLogin() {
        System.out.println("=== INICIAR SESION ===");
        
        System.out.print("Nombre de usuario: ");
        String usuarioCredencial = sc.nextLine().trim();
        
        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine().trim();
        
        // Intentar autenticar
        gestionSesion.login(usuarioCredencial, contraseña);
    }
    
    /**
     * Lee un número entero con manejo robusto de errores
     * Reemplaza al método leerEntero() anterior con mejor manejo de excepciones
     * @return Número entero leído
     * @throws NumberFormatException si no se puede convertir a entero
     */
    private int leerEnteroSeguro() throws NumberFormatException {
        try {
            String entrada = sc.nextLine().trim();
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El valor ingresado no es un número válido.");
        } catch (Exception e) {
            throw new NumberFormatException("Error al leer la entrada.");
        }
    }
}
