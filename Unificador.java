import java.util.HashMap;
// Importa la clase HashMap, una implementación de Map que permite
// almacenar pares clave-valor. Se usará para guardar sustituciones
// de variables durante el proceso de unificación.

import java.util.Map;
// Importa la interfaz Map, que define la estructura para almacenar
// asociaciones entre variables y sus valores sustituidos.

/**
 * Clase encargada de ejecutar el Algoritmo de Unificación de Variables.
 * La unificación es un proceso fundamental en lógica de predicados que
 * intenta encontrar sustituciones de variables para hacer coincidir
 * dos proposiciones.
 */
public class Unificador {

    /**
     * Determina si un argumento es una variable lógica.
     * En este sistema se considera variable a cualquier cadena
     * formada por una sola letra mayúscula (ej. X, Y).
     *
     * arg argumento que se desea evaluar
     * true si es una variable, false si es constante
     */
    public static boolean esVariable(String arg) {

        return arg.length() == 1 && Character.isUpperCase(arg.charAt(0));
        // Verifica dos condiciones:
        // 1. Que la cadena tenga exactamente un carácter.
        // 2. Que ese carácter sea una letra mayúscula.
        // Si ambas se cumplen, se considera una variable lógica.
    }

    /**
     * Intenta unificar dos proposiciones para el proceso de resolución.
     * Para que dos proposiciones puedan resolverse deben:
     * - Tener el mismo predicado.
     * - Tener signos opuestos (una negada y otra no).
     * - Tener la misma cantidad de argumentos.
     * Si es posible unificarlas, se genera un mapa de sustituciones
     * llamado Unificador Más General (MGU).
     *
     * p1 Proposición de la primera cláusula.
     * p2 Proposición de la segunda cláusula.
     * mapa con las sustituciones de variables o null si falla la unificación.
     */
    public static Map<String, String> unificar(Proposicion p1, Proposicion p2) {

        // Para resolver por refutación, deben tener el mismo nombre y signos opuestos
        if (!p1.nombre.equals(p2.nombre) || p1.negado == p2.negado) {
            return null;
            // Si los predicados son diferentes o tienen el mismo signo,
            // no se pueden resolver y la unificación falla.
        }

        // Deben tener la misma cantidad de argumentos (aridad)
        if (p1.argumento.length != p2.argumento.length) {
            return null;
            // Si las proposiciones tienen diferente número de argumentos,
            // tampoco pueden unificarse.
        }

        Map<String, String> sustitucion = new HashMap<>();
        // Se crea un mapa para almacenar las sustituciones encontradas
        // durante la unificación.

        // Recorrer los argumentos para encontrar el unificador más general (MGU)
        for (int i = 0; i < p1.argumento.length; i++) {

            String arg1 = p1.argumento[i];
            // Obtiene el argumento de la primera proposición.

            String arg2 = p2.argumento[i];
            // Obtiene el argumento correspondiente de la segunda proposición.

            if (arg1.equals(arg2)) continue;
            // Si ambos argumentos son iguales, no se necesita sustitución.

            if (esVariable(arg1)) {
                // Si el primer argumento es una variable,
                // se puede sustituir por el valor del segundo argumento.

                sustitucion.put(arg1, arg2);

            } else if (esVariable(arg2)) {
                // Si el segundo argumento es una variable,
                // se sustituye por el valor del primero.

                sustitucion.put(arg2, arg1);

            } else {
                // Si ambos argumentos son constantes diferentes,
                // la unificación es imposible.

                return null;
            }
        }

        return sustitucion;
        // Devuelve el mapa con las sustituciones encontradas
        // que permiten unificar ambas proposiciones.
    }
}