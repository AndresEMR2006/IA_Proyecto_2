public class Proposicion {

    // Atributos de la clase Proposicion
    public String nombre;
    public Boolean negado;
    public String argumento;

    // Constructor de la clase Proposicion
    public Proposicion(String nombre, Boolean negado, String argumento) {
        this.nombre = nombre;
        this.negado = negado;
        this.argumento = argumento;
    }

    public Proposicion(){
    }

    public void imprimir(){
        String negacion;

        if(this.negado){
            negacion = "¬";
        }else{
            negacion = "";
        }
        System.out.print(negacion + this.nombre + "(" + this.argumento + ") ");
    }

}