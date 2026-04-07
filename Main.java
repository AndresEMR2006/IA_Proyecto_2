// Clase principal para ejecutar la validación del Motor de Inferencia.
// Aquí se construye manualmente la base de conocimiento con varias cláusulas
// y luego se ejecuta el motor de inferencia para verificar si una afirmación
// puede deducirse a partir de esas cláusulas.

public class Main {

    /**
     * Método principal del programa.
     * Construye una base de conocimiento con varias cláusulas de lógica de predicados
     * relacionadas con Marco y César. Luego se formula una pregunta que se niega
     * (para aplicar el método de resolución por refutación) y finalmente se ejecuta
     * el motor de inferencia para determinar si la conclusión se puede demostrar.
     */
    public static void main(String[] args) {

        Base base = new Base();
        // Se crea una nueva base de conocimiento donde se almacenarán todas las cláusulas.

        // Listas de argumentos
        String[] Marco = {"Marco"};
        // Arreglo que representa el argumento "Marco".

        String[] X = {"X"};
        // Variable lógica utilizada en reglas generales.

        String[] XY = {"X", "Y"};
        // Variables usadas en predicados con dos argumentos.

        String[] Cesar = {"Cesar"};
        // Arreglo que representa el argumento "Cesar".

        String[] X_Cesar = {"X", "Cesar"};
        // Arreglo usado en predicados donde el segundo argumento es César.

        String[] Marco_Cesar = {"Marco", "Cesar"};
        // Arreglo que representa la relación entre Marco y César.

        // --------------- Cláusulas

        // C1: Marco es un hombre
        Frase F1 = new Frase();
        // Se crea una nueva cláusula vacía.

        F1.añadir(new Proposicion("Hombre", false, Marco));
        // Se añade la proposición Hombre(Marco).
        // false indica que no está negada.

        // C2: Marco es pompeyano
        Frase F2 = new Frase();
        // Se crea una nueva cláusula.

        F2.añadir(new Proposicion("Pompeyano", false, Marco));
        // Se añade la proposición Pompeyano(Marco).

        // C3: Todos los pompeyanos son romanos
        // Forma lógica: Pompeyano(X) -> Romano(X)
        // Forma en cláusula: ¬Pompeyano(X) v Romano(X)
        Frase F3 = new Frase();

        F3.añadir(new Proposicion("Pompeyano", true, X));
        // Se añade la proposición negada ¬Pompeyano(X).

        F3.añadir(new Proposicion("Romano", false, X));
        // Se añade la proposición Romano(X).

        // C4: Cesar es un gobernante
        Frase F4 = new Frase();

        F4.añadir(new Proposicion("Gobernante", false, Cesar));
        // Se añade la proposición Gobernante(Cesar).

        // C5: Todos los romanos aman a César o lo odian
        // Romano(X) -> (Ama(X,Cesar) v Odia(X,Cesar))
        // Forma en cláusula: ¬Romano(X) v Ama(X,Cesar) v Odia(X,Cesar)
        Frase F5 = new Frase();

        F5.añadir(new Proposicion("Romano", true, X));
        // ¬Romano(X)

        F5.añadir(new Proposicion("Ama", false, X_Cesar));
        // Ama(X,Cesar)

        F5.añadir(new Proposicion("Odia", false, X_Cesar));
        // Odia(X,Cesar)

        // C6: Si alguien intenta matar a alguien, lo odia
        // IntentaMatar(X,Y) -> Odia(X,Y)
        // Forma en cláusula: ¬IntentaMatar(X,Y) v Odia(X,Y)
        Frase F6 = new Frase();

        F6.añadir(new Proposicion("IntentaMatar", true, XY));
        // ¬IntentaMatar(X,Y)

        F6.añadir(new Proposicion("Odia", false, XY));
        // Odia(X,Y)

        // C7: Marco intentó asesinar a César
        // IntentaMatar(Marco,Cesar)
        Frase F7 = new Frase();

        F7.añadir(new Proposicion("IntentaMatar", false, Marco_Cesar));
        // Se añade el hecho IntentaMatar(Marco,Cesar)

        // --------------- Fin cláusulas

        // Pregunta: ¿Marco odia a César?
        // Para usar resolución por refutación, la pregunta se NIEGA:
        // ¬Odia(Marco, Cesar)
        Frase Pregunta = new Frase();

        Pregunta.añadir(new Proposicion("Odia", true, Marco_Cesar));
        // Se añade la proposición negada ¬Odia(Marco,Cesar).

        // Carga de información a la base de conocimiento
        base.añadir(F1);
        // Se agrega la cláusula 1 a la base.

        base.añadir(F2);
        // Se agrega la cláusula 2.

        base.añadir(F3);
        // Se agrega la cláusula 3.

        base.añadir(F4);
        // Se agrega la cláusula 4.

        base.añadir(F5);
        // Se agrega la cláusula 5.

        base.añadir(F6);
        // Se agrega la cláusula 6.

        base.añadir(F7);
        // Se agrega la cláusula 7.

        base.añadir(Pregunta);
        // Se agrega la pregunta negada para aplicar refutación.

        // --- INICIAR EL MOTOR DE INFERENCIA ---
        MotorInferencia.resolver(base);
        // Se ejecuta el motor de inferencia usando la base de conocimiento
        // para intentar demostrar si la afirmación original es verdadera.
    }
}