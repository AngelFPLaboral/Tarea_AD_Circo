package utils;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PaisesUtils {
	private static final String rutaPaises="paises.xml";
	
	
	private static Map<String, String> leerPaises(){
		
		Map<String, String> paises= new HashMap<>();
		
		try {
			File paisesXML= new File(rutaPaises);
			if (!paisesXML.exists()) {
                System.out.println("ERROR: No se encontrO el archivo ");
                return paises;
            }
			
			//Elementos necesarios para leer un xml
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true); // ignora espacios
            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(paisesXML);
            documento.getDocumentElement().normalize();

            // Obtenemos todos los nodos <pais>
            NodeList listaPaises = documento.getElementsByTagName("pais");

            for (int i = 0; i < listaPaises.getLength(); i++) {
                Node nodoPais = listaPaises.item(i);

                if (nodoPais.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoPais = (Element) nodoPais;

                    String id = getTextoDeEtiqueta("id", elementoPais);
                    String nombre = getTextoDeEtiqueta("nombre", elementoPais);

                    if (id != null && nombre != null) {
                        paises.put(id.trim().toUpperCase(), nombre.trim());
                    }
                }
            }
			
		}
		catch (Exception e) {
           System.out.println("Error al LEER el fichero");
        }
        
        return paises;
	}
	
	public static void mostrarPaises() {
        Map<String, String> paises = leerPaises();
        
        System.out.println("___PAÍSES DISPONIBLES___");
        for (Map.Entry<String, String> pais : paises.entrySet()) {
            System.out.println(pais.getKey() + " - " + pais.getValue());
        }
        System.out.println("______");
    }
	
	private static String getTextoDeEtiqueta(String etiqueta, Element elemento) {
        NodeList nodos = elemento.getElementsByTagName(etiqueta);
        if (nodos != null && nodos.getLength() > 0) {
            return nodos.item(0).getTextContent();
        }
        return null;
    }
	
	public static boolean existePais(String idPais) {
        Map<String, String> paises = leerPaises();
        return paises.containsKey(idPais.toUpperCase());
    }
	
	public static String obtenerNombrePais(String idPais) {
        Map<String, String> paises = leerPaises();
        return paises.get(idPais.toUpperCase());
    }
}
