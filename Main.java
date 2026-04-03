public class Main {

    public static void main(String[] args) {

        Base base = new Base();

        // Crear primera cláusula: ¬P ∨ Q
        Frase F1 = new Frase();
        F1.Proposiciones.add(new Proposicion("P", true, null));
        F1.Proposiciones.add(new Proposicion("Q", false, null));
        F1.Proposiciones.add(new Proposicion("R", false, null));

        // Crear segunda cláusula: P
        Frase F2 = new Frase();
        F2.Proposiciones.add(new Proposicion("P", false, null));
        F2.Proposiciones.add(new Proposicion("R", true, null));

    }
}