import java.util.ArrayList;
import java.util.Map;

/**
 * Motor de Inferencia basado en Resolución por Refutación.
 */
public class MotorInferencia {

    /**
     * Ejecuta el algoritmo principal de resolución.
     * @param base Base de conocimiento con las cláusulas.
     */
    public static void resolver(Base base) {
        ArrayList<Frase> clausulas = base.Frases;
        boolean seAgregaronNuevas = true;
        int paso = 1;

        System.out.println("--- INICIO RESOLUCIÓN POR REFUTACIÓN ---");

        while (seAgregaronNuevas) {
            seAgregaronNuevas = false;
            ArrayList<Frase> nuevasClausulas = new ArrayList<>();

            // Comparar todos los pares de cláusulas posibles
            for (int i = 0; i < clausulas.size(); i++) {
                for (int j = i + 1; j < clausulas.size(); j++) {
                    Frase f1 = clausulas.get(i);
                    Frase f2 = clausulas.get(j);

                    Frase resolvente = intentarResolver(f1, f2);

                    if (resolvente != null) {
                        // Evitar agregar cláusulas que ya existen
                        if (!contieneFrase(clausulas, resolvente) && !contieneFrase(nuevasClausulas, resolvente)) {
                            System.out.println("Paso " + paso + ": Resolviendo");
                            System.out.println("  C1: " + f1.imprimirString());
                            System.out.println("  C2: " + f2.imprimirString());
                            System.out.println("  => Resultado: " + resolvente.imprimirString() + "\n");
                            paso++;

                            // Si la resolvente está vacía, encontramos la contradicción
                            if (resolvente.Proposiciones.isEmpty()) {
                                System.out.println("¡CONTRADICCIÓN ENCONTRADA! (Cláusula Vacía []).");
                                System.out.println("El teorema ha sido DEMOSTRADO con éxito.");
                                return;
                            }

                            nuevasClausulas.add(resolvente);
                        }
                    }
                }
            }
            if (!nuevasClausulas.isEmpty()) {
                clausulas.addAll(nuevasClausulas);
                seAgregaronNuevas = true;
            }
        }
        System.out.println("El algoritmo terminó sin encontrar contradicción. Teorema NO demostrado.");
    }

    private static Frase intentarResolver(Frase f1, Frase f2) {
        for (Proposicion p1 : f1.Proposiciones) {
            for (Proposicion p2 : f2.Proposiciones) {
                Map<String, String> sustitucion = Unificador.unificar(p1, p2);
                if (sustitucion != null) {
                    return generarResolvente(f1, f2, p1, p2, sustitucion);
                }
            }
        }
        return null; 
    }

    private static Frase generarResolvente(Frase f1, Frase f2, Proposicion p1Eliminar, Proposicion p2Eliminar, Map<String, String> sustitucion) {
        Frase resolvente = new Frase();
        for (Proposicion p : f1.Proposiciones) {
            if (p != p1Eliminar) resolvente.añadir(p.aplicarSustitucion(sustitucion));
        }
        for (Proposicion p : f2.Proposiciones) {
            if (p != p2Eliminar) resolvente.añadir(p.aplicarSustitucion(sustitucion));
        }
        return resolvente;
    }

    private static boolean contieneFrase(ArrayList<Frase> lista, Frase f) {
        for (Frase existente : lista) {
            if (existente.imprimirString().equals(f.imprimirString())) return true;
        }
        return false;
    }
}
