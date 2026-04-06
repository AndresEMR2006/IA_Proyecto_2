import java.util.Map;

/**
 * Representa un literal o proposición atómica (ej. Romano(X) o ¬Romano(Marco)).
 */
public class Proposicion {

    public String nombre;
    public Boolean negado;
    public String[] argumento;

    public Proposicion(String nombre, Boolean negado, String[] argumento) {
        this.nombre = nombre;
        this.negado = negado;
        this.argumento = argumento;
    }

    public Proposicion(){}

    /**
     * Aplica la sustitución de variables encontrada en la unificación.
     */
    public Proposicion aplicarSustitucion(Map<String, String> sustitucion) {
        String[] nuevoArgumento = new String[argumento.length];
        for (int i = 0; i < argumento.length; i++) {
            // Si el argumento está en el mapa, lo sustituye; si no, lo deja igual
            nuevoArgumento[i] = sustitucion.getOrDefault(argumento[i], argumento[i]);
        }
        return new Proposicion(this.nombre, this.negado, nuevoArgumento);
    }

    /**
     * Retorna la representación en String de la proposición.
     */
    public String imprimirString() {
        String negacion = this.negado ? "¬" : "";
        return negacion + this.nombre + "(" + String.join(", ", this.argumento) + ")";
    }

    public void imprimir(){
        System.out.print(imprimirString() + " ");
    }
}
