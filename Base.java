import java.util.ArrayList;

public class Base{

    ArrayList<Frase> Frases;

    public Base() {
        Frases = new ArrayList<>();
    }

    public Base(String nombre, Boolean negado, String argumento) {
        this.Frases = new ArrayList<>();
        this.Frases.add(new Frase(nombre, negado, argumento));
    }

}