import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de ejecutar el Algoritmo de Unificación de Variables.
 */
public class Unificador {

    /**
     * Verifica si una cadena representa una variable simbólica.
     * Asumimos que las variables son cadenas de una letra mayúscula (ej. "X", "Y").
     */
    public static boolean esVariable(String arg) {
        return arg.length() == 1 && Character.isUpperCase(arg.charAt(0));
    }

    /**
     * Intenta unificar dos proposiciones para la resolución.
     * @param p1 Proposición de la primera cláusula.
     * @param p2 Proposición de la segunda cláusula.
     * @return Un mapa con las sustituciones si unifican, o null si fallan.
     */
    public static Map<String, String> unificar(Proposicion p1, Proposicion p2) {
        // Para resolver por refutación, deben tener el mismo nombre y signos opuestos
        if (!p1.nombre.equals(p2.nombre) || p1.negado == p2.negado) {
            return null; 
        }
        
        // Deben tener la misma cantidad de argumentos (aridad)
        if (p1.argumento.length != p2.argumento.length) {
            return null;
        }

        Map<String, String> sustitucion = new HashMap<>();

        // Recorrer los argumentos para encontrar el unificador más general (MGU)
        for (int i = 0; i < p1.argumento.length; i++) {
            String arg1 = p1.argumento[i];
            String arg2 = p2.argumento[i];

            if (arg1.equals(arg2)) continue;

            if (esVariable(arg1)) {
                sustitucion.put(arg1, arg2);
            } else if (esVariable(arg2)) {
                sustitucion.put(arg2, arg1);
            } else {
                // Si ambas son constantes diferentes, la unificación falla
                return null; 
            }
        }
        return sustitucion;
    }
}
