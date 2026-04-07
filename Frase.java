import java.util.ArrayList;
// Importa la clase ArrayList del paquete util de Java.
// Se usa para almacenar dinámicamente una colección de proposiciones.

// Representa una cláusula, que es una disyunción de proposiciones (literales).
// En lógica proposicional o de predicados, una frase es una expresión formada
// por varias proposiciones unidas por el operador OR lógico (v).

public class Frase {

    public ArrayList<Proposicion> Proposiciones;
    // Lista pública que almacena las proposiciones que forman la cláusula.
    // Cada elemento es un literal que puede ser afirmado o negado.

    /**
     * Constructor por defecto.
     * Inicializa una frase creando una lista vacía de proposiciones
     * que posteriormente podrán agregarse.
     */
    public Frase(){
        this.Proposiciones = new ArrayList<>();
        // Se crea una lista vacía donde se almacenarán las proposiciones de la frase.
    }

    /**
     * Constructor con parámetros.
     * Crea una frase inicial con una proposición generada a partir
     * del nombre del predicado, su estado de negación y sus argumentos.
     * 
     * "nombre" nombre del predicado de la proposición
     * "negado" indica si la proposición está negada
     * "argumento" arreglo de argumentos asociados al predicado
     */
    public Frase(String nombre, Boolean negado, String[] argumento) {
        this.Proposiciones = new ArrayList<>();
        // Inicializa la lista donde se guardarán las proposiciones.

        this.Proposiciones.add(new Proposicion(nombre, negado, argumento));
        // Crea una nueva proposición con los parámetros dados
        // y la añade a la lista de proposiciones de la frase.
    }

    /**
     * Añade una proposición a la frase evitando duplicados.
     * Recorre las proposiciones existentes y compara su representación
     * en texto para evitar que se agregue una proposición idéntica.
     * 
     * "prop" proposición que se desea añadir a la frase
     */
    public void añadir(Proposicion prop){

        for(Proposicion p : this.Proposiciones) {
            // Recorre cada proposición existente en la frase.

            if(p.imprimirString().equals(prop.imprimirString())) return;
            // Compara la representación en texto de la proposición actual
            // con la proposición que se quiere agregar.
            // Si son iguales, significa que ya existe y se cancela la inserción.
        }

        this.Proposiciones.add(prop);
        // Si no se encontró un duplicado, se agrega la nueva proposición a la lista.
    }

    /**
     * Retorna la representación en String de la cláusula completa.
     * Une todas las proposiciones de la frase usando el operador lógico OR (v)
     * para representar la disyunción de literales.
     * Retorna un String con la representación textual de la cláusula
     */
    public String imprimirString() {

        if(Proposiciones.isEmpty()) return "[]";
        // Si la lista de proposiciones está vacía,
        // se retorna una representación de cláusula vacía.

        ArrayList<String> props = new ArrayList<>();
        // Lista temporal donde se almacenarán las representaciones en texto
        // de cada proposición.

        for(Proposicion p : Proposiciones) props.add(p.imprimirString());
        // Recorre cada proposición de la frase y guarda su representación
        // en texto dentro de la lista temporal.

        return String.join(" v ", props);
        // Une todas las proposiciones usando " v " como separador,
        // representando la disyunción lógica entre ellas.
    }
}