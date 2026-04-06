import java.util.ArrayList;

/**
 * Base de conocimiento que almacena el conjunto de cláusulas (Frases).
 */
public class Base {

    public ArrayList<Frase> Frases;
    public String[] Variables;

    public Base() {
        Frases = new ArrayList<>();
        Variables = new String[]{};
    }

    public Base(String nombre, Boolean negado, String[] argumento) {
        Variables = new String[]{};
        this.Frases = new ArrayList<>();
        this.Frases.add(new Frase(nombre, negado, argumento));
    }

    public void añadir(Frase F){
        this.Frases.add(F);
    }

    public void setVariables(String[] Variables){
        this.Variables = Variables;
    }
}
