package running;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GestionAdmin {
	// ATRIBUTOS
    private static Properties properties;
    private static boolean cargado = false;
    private static boolean errorCarga = false;
    private static final String ficheroAdministrador = "/application.properties";
    
    //METODOS
    private static void cargarConfiguracion() {
        if (cargado) {
            return; 
        }
        
        properties = new Properties();
        
        try (InputStream input = GestionSesion.class.getResourceAsStream(ficheroAdministrador)) {
            if (input == null) {
            	System.out.println("ERROR: No se puede ENCONTRAR el ARCHIVO autenticar Administrados");
                errorCarga = true;
                cargado = true;
                return;
            }
            
            properties.load(input);
            
            if (!properties.containsKey("admin.usuarioNombre") || !properties.containsKey("admin.contraseña")) {
            	System.out.println("ERROR: No se puede ENCONTRAR los ATRIBUTOS CLAVE de autenticar Administrados");
                errorCarga = true;
                cargado = true;
                return;
            }
            
            cargado = true;
            errorCarga = false;
            
        } catch (IOException e) {
            
            
            errorCarga = true;
            cargado = true;
        }
    }
    
    /**
     * Metodo: Verifica si la configuracion cargo
     * @return true si se cargó sin errores, false si hubo problemas
     */
    public static boolean esConfiguracionValida() {
        cargarConfiguracion();
        return !errorCarga;
    }
    
    /**
     * Metodo: Obtiene el nombre de usuario del Admin 
     * @return Nombre de usuario del Admin, o null si hay error
     */
    public static String getAdminUsername() {
        cargarConfiguracion();
        
        if (errorCarga) {
            return null; 
        }
        
        String username = properties.getProperty("admin.usuarioNombre");
        
        if (username == null || username.trim().isEmpty()) {
            System.out.println("AVISO: el nombre del Administrador no existe");
            return null;
        }
        
        return username.trim();
    }
    
    /**
     * Metodo: Obtiene la contraseña de usuario del Admin 
     * @return 
     */
    public static String getAdminPassword() {
        cargarConfiguracion();
        
        if (errorCarga) {
            return null; 
        }
        
        String contraseña = properties.getProperty("admin.contraseña");
        
        if (contraseña == null || contraseña.trim().isEmpty()) {
            System.out.println("AVISO: la contraseña del Administrador no existe");
            return null;
        }
        
        return contraseña;
    }
    
 
    public static String getProperty(String key, String defaultValue) {
        cargarConfiguracion();
        
        if (errorCarga) {
            return defaultValue;
        }
        
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Metodo: que verifica si es Admin
     * @param usuarioNombre
     * @param contraseñaUsuario
     * @return
     */
    public static boolean esAdmin(String usuarioNombre, String contraseñaUsuario) {
        // Si hay error de configuración, NO permitir acceso como Admin
        if (!esConfiguracionValida()) {
            System.out.println("No se puede autenticar como Administrados");
            return false;
        }
        
        // Obtener credenciales del archivo
        String adminUser = getAdminUsername();
        String adminPass = getAdminPassword();
        
        // Si alguna es null, no permitir acceso
        if (adminUser == null || adminPass == null) {
            System.out.println("No se puede autenticar como Administrados");
            return false;
        }
        
        // Validar que las entradas no sean nulas
        if (usuarioNombre == null || contraseñaUsuario == null) {
            return false;
        }
        
        // Comparar credenciales
        return adminUser.equals(usuarioNombre) && adminPass.equals(contraseñaUsuario);
    }
}
