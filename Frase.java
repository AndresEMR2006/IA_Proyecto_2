import java.util.ArrayList;

public class Frase {

    ArrayList<Proposicion> Proposiciones;

    public Frase(){
        this.Proposiciones = new ArrayList<>();
    }

    public Frase(String nombre, Boolean negado, String argumento) {
        this.Proposiciones = new ArrayList<>();
        this.Proposiciones.add(new Proposicion(nombre, negado, argumento));
    }

}
