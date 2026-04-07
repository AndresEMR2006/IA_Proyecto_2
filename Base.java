import java.util.ArrayList; 
// Importa la clase ArrayList del paquete util de Java.
// ArrayList permite almacenar una lista dinámica de objetos.

// Base de conocimiento que almacena el conjunto de cláusulas (Frases).
// Esta clase representa una base lógica donde se guardan varias frases
// que pueden ser usadas posteriormente para procesos de inferencia.

public class Base {

    public ArrayList<Frase> Frases; 
    // Lista pública que almacena objetos de tipo Frase.
    // Cada elemento representa una cláusula o expresión lógica dentro de la base de conocimiento.

    /**
     * Constructor por defecto.
     * Inicializa la base de conocimiento creando una lista vacía
     * donde posteriormente se podrán almacenar diferentes frases.
     */
    public Base() {
        Frases = new ArrayList<>(); 
        // Se crea una nueva lista vacía donde se almacenarán las frases.
    }

    /**
     * Constructor con parámetros.
     * Crea una base de conocimiento inicializando la lista de frases
     * y agregando una frase inicial construida a partir de los parámetros dados.
     * "nombre" nombre del predicado o de la frase lógica
     * "negado" indica si la frase está negada o no
     * "argumento" arreglo de argumentos asociados al predicado
     */
    public Base(String nombre, Boolean negado, String[] argumento) {
        this.Frases = new ArrayList<>(); 
        // Inicializa la lista de frases vacía.

        this.Frases.add(new Frase(nombre, negado, argumento)); 
        // Crea una nueva frase con los parámetros dados y la añade a la base de conocimiento.
    }

    /**
     * Método para añadir una nueva frase a la base de conocimiento.
     * Recibe un objeto de tipo Frase y lo agrega a la lista de frases almacenadas.
     */
    public void añadir(Frase F){
        this.Frases.add(F); 
        // Agrega la frase recibida como parámetro al ArrayList de frases.
    }

}