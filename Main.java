public class Main {

    public static void main(String[] args) {

        Base base = new Base();

        // Listas

        String[] Marco = {"Marco"};
        String[] X = {"X"};
        String[] XY = {"X", "Y"};
        String[] Cesar = {"Cesar"};
        String[] X_Cesar = {"X", "Cesar"};
        String[] Marco_Cesar = {"Marco", "Cesar"};

        // --------------- Clausulas

        // C1: Marco es un hombre
        Frase F1 = new Frase();
        F1.añadir(new Proposicion("Hombre", false, Marco));

        // C2: Marco es ponpeyano
        Frase F2 = new Frase();
        F2.añadir(new Proposicion("Pompeyano", false, Marco));

        // C3: Todos los pompeyanos son romanos ( Pompeyano -> Romano  =  ¬Pompeyano o Romano)
        Frase F3 = new Frase();
        F3.añadir(new Proposicion("Pompeyano", true, X));
        F3.añadir(new Proposicion("Romano", false, X));

        // C4: Cesar es un gobernante
        Frase F4 = new Frase();
        F4.añadir(new Proposicion("Gobernante", false, Cesar));

        // C5: Todos los romanos son o leales a cesar o odian a cesar (Romano -> (ama(Cesar) o odia(cesar))) = (¬Romano o ama(Cesar) o Odia)
        Frase F5 = new Frase();
        F5.añadir(new Proposicion("Romano", true, X));
        F5.añadir(new Proposicion("Ama", false, X_Cesar));
        F5.añadir(new Proposicion("Odia", false, X_Cesar));

        // C6: La gente sólo intenta asesinar a los gobernantes a los que no es leal. (Odia(X,Y) -> IntentaMatar(X,Y)) = (¬odia(X,Y) o IntentaMatar(X,Y))
        Frase F6 = new Frase();
        F6.añadir(new Proposicion("Odia", true, XY));
        F6.añadir(new Proposicion("IntentaMatar", false, XY));

        // C7: marco intento asesinar a cesar. (IntentoAsesinar(Marco,Cesar))
        Frase F7 = new Frase();
        F7.añadir(new Proposicion("IntentaMatar", false, Marco_Cesar));

        // --------------- Fin clausulas

        // Pregunta: ¿Marco odia a Cesar?
        Frase Pregunta = new Frase();
        Pregunta.añadir(new Proposicion("Odia", false, Marco_Cesar));

        // Carga de informacino a la base

        base.añadir(F1);
        base.añadir(F2);
        base.añadir(F3);
        base.añadir(F4);
        base.añadir(F5);
        base.añadir(F6);
        base.añadir(F7);
        base.añadir(Pregunta);
        base.setVariables(XY);

    }
}