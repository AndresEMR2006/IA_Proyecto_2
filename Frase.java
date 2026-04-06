import java.util.ArrayList;

/**
 * Representa una cláusula, que es una disyunción de proposiciones (literales).
 */
public class Frase {

    public ArrayList<Proposicion> Proposiciones;

    public Frase(){
        this.Proposiciones = new ArrayList<>();
    }

    public Frase(String nombre, Boolean negado, String[] argumento) {
        this.Proposiciones = new ArrayList<>();
        this.Proposiciones.add(new Proposicion(nombre, negado, argumento));
    }

    /**
     * Añade una proposición a la frase, evitando duplicados.
     */
    public void añadir(Proposicion prop){
        for(Proposicion p : this.Proposiciones) {
            if(p.imprimirString().equals(prop.imprimirString())) return;
        }
        this.Proposiciones.add(prop);
    }

    /**
     * Retorna la representación en String de la cláusula completa.
     */
    public String imprimirString() {
        if(Proposiciones.isEmpty()) return "[]"; // Cláusula vacía
        ArrayList<String> props = new ArrayList<>();
        for(Proposicion p : Proposiciones) props.add(p.imprimirString());
        return String.join(" v ", props);
    }
}
