import java.util.Map;
// Importa la interfaz Map, que se utiliza para almacenar pares clave-valor.
// En este caso se usa para guardar las sustituciones generadas durante la unificación.

/**
 * Representa un literal o proposición atómica (ej. Romano(X) o ¬Romano(Marco)).
 * Una proposición está formada por:
 * - Un nombre de predicado.
 * - Un indicador de negación.
 * - Una lista de argumentos.
 */
public class Proposicion {

    public String nombre;
    // Nombre del predicado de la proposición (ej. Romano, Odia, Ama).

    public Boolean negado;
    // Indica si la proposición está negada (true = negado, false = afirmado).

    public String[] argumento;
    // Arreglo que contiene los argumentos del predicado.
    // Pueden ser constantes (Marco, Cesar) o variables (X, Y).

    public Proposicion(String nombre, Boolean negado, String[] argumento) {
        // Constructor que inicializa completamente una proposición.

        this.nombre = nombre;
        // Asigna el nombre del predicado.

        this.negado = negado;
        // Asigna el estado de negación de la proposición.

        this.argumento = argumento;
        // Asigna el arreglo de argumentos asociados al predicado.
    }

    public Proposicion(){}
    // Constructor vacío que permite crear una proposición sin inicializarla
    // inmediatamente (puede completarse posteriormente).

    /**
     * Aplica la sustitución de variables encontrada durante la unificación.
     * Recorre cada argumento de la proposición y lo reemplaza si existe
     * una sustitución correspondiente en el mapa.
     */
    public Proposicion aplicarSustitucion(Map<String, String> sustitucion) {

        String[] nuevoArgumento = new String[argumento.length];
        // Se crea un nuevo arreglo para almacenar los argumentos ya sustituidos.

        for (int i = 0; i < argumento.length; i++) {
            // Recorre cada argumento original de la proposición.

            // Si el argumento está en el mapa, lo sustituye; si no, lo deja igual
            nuevoArgumento[i] = sustitucion.getOrDefault(argumento[i], argumento[i]);
            // getOrDefault busca el argumento en el mapa de sustituciones.
            // Si existe una sustitución, la aplica; de lo contrario mantiene el valor original.
        }

        return new Proposicion(this.nombre, this.negado, nuevoArgumento);
        // Devuelve una nueva proposición con los argumentos sustituidos
        // manteniendo el mismo predicado y estado de negación.
    }

    /**
     * Genera una representación en texto de la proposición lógica.
     * Incluye el símbolo de negación (¬) si corresponde y los argumentos
     * separados por comas.
     */
    public String imprimirString() {

        String negacion = this.negado ? "¬" : "";
        // Si la proposición está negada se agrega el símbolo ¬,
        // en caso contrario se deja vacío.

        return negacion + this.nombre + "(" + String.join(", ", this.argumento) + ")";
        // Construye la representación textual de la proposición.
        // Ejemplo: ¬Romano(X) o Odia(Marco, Cesar).
    }

    /**
     * Imprime la proposición directamente en consola.
     * Utiliza el método imprimirString para obtener su representación textual.
     */
    public void imprimir(){

        System.out.print(imprimirString() + " ");
        // Muestra la proposición en consola seguida de un espacio.
    }
}