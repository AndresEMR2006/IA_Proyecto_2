import java.util.ArrayList;

public class Base{

    ArrayList<Frase> Frases;
    String[] Variables;

    public Base() {
        Frases = new ArrayList<>();
        Variables = new String[]{};
    }

    public Base(String nombre, Boolean negado, String argumento) {
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