import java.util.ArrayList;
// Importa ArrayList para almacenar listas dinámicas de cláusulas.

import java.util.Map;
// Importa Map para manejar las sustituciones generadas durante la unificación.

/**
 * Motor de Inferencia basado en Resolución por Refutación.
 * Esta clase implementa el algoritmo que intenta demostrar un teorema
 * utilizando resolución lógica entre cláusulas de una base de conocimiento.
 */
public class MotorInferencia {

    /**
     * Ejecuta el algoritmo principal de resolución.
     * Recorre todas las combinaciones posibles de cláusulas intentando resolverlas.
     * Si se genera la cláusula vacía ([]), se encuentra una contradicción
     * y el teorema queda demostrado.
     */
    public static void resolver(Base base) {

        ArrayList<Frase> clausulas = base.Frases;
        // Obtiene todas las cláusulas almacenadas en la base de conocimiento.

        boolean seAgregaronNuevas = true;
        // Controla si se generaron nuevas cláusulas durante una iteración.

        int paso = 1;
        // Contador de pasos para mostrar el proceso de resolución.

        System.out.println("--- INICIO RESOLUCIÓN POR REFUTACIÓN ---");
        // Mensaje inicial del proceso de inferencia.

        while (seAgregaronNuevas) {
            // Mientras se sigan generando nuevas cláusulas, el algoritmo continúa.

            seAgregaronNuevas = false;
            // Se reinicia el indicador en cada iteración.

            ArrayList<Frase> nuevasClausulas = new ArrayList<>();
            // Lista temporal donde se almacenarán las nuevas cláusulas generadas.

            // Comparar todos los pares de cláusulas posibles
            for (int i = 0; i < clausulas.size(); i++) {
                // Recorre la primera cláusula del par.

                for (int j = i + 1; j < clausulas.size(); j++) {
                    // Recorre la segunda cláusula del par evitando repetir combinaciones.

                    Frase f1 = clausulas.get(i);
                    // Obtiene la primera cláusula.

                    Frase f2 = clausulas.get(j);
                    // Obtiene la segunda cláusula.

                    Frase resolvente = intentarResolver(f1, f2);
                    // Intenta resolver las dos cláusulas.

                    if (resolvente != null) {
                        // Si se generó una resolvente válida.

                        // Evitar agregar cláusulas que ya existen
                        if (!contieneFrase(clausulas, resolvente) && !contieneFrase(nuevasClausulas, resolvente)) {

                            System.out.println("Paso " + paso + ": Resolviendo");
                            // Imprime el número de paso del proceso.

                            System.out.println("  C1: " + f1.imprimirString());
                            // Muestra la primera cláusula usada.

                            System.out.println("  C2: " + f2.imprimirString());
                            // Muestra la segunda cláusula usada.

                            System.out.println("  => Resultado: " + resolvente.imprimirString() + "\n");
                            // Muestra la cláusula resolvente obtenida.

                            paso++;
                            // Incrementa el contador de pasos.

                            // Si la resolvente está vacía, encontramos la contradicción
                            if (resolvente.Proposiciones.isEmpty()) {

                                System.out.println("¡CONTRADICCIÓN ENCONTRADA! (Cláusula Vacía []).");
                                // Indica que se encontró la cláusula vacía.

                                System.out.println("El teorema ha sido DEMOSTRADO con éxito.");
                                // Confirma que el teorema fue probado.

                                return;
                                // Termina el algoritmo.
                            }

                            nuevasClausulas.add(resolvente);
                            // Agrega la nueva cláusula generada a la lista temporal.
                        }
                    }
                }
            }

            if (!nuevasClausulas.isEmpty()) {
                // Si se generaron nuevas cláusulas.

                clausulas.addAll(nuevasClausulas);
                // Se añaden a la base de cláusulas existente.

                seAgregaronNuevas = true;
                // Se indica que hubo cambios y el algoritmo debe continuar.
            }
        }

        System.out.println("El algoritmo terminó sin encontrar contradicción. Teorema NO demostrado.");
        // Si no se encontró la cláusula vacía, el teorema no pudo demostrarse.
    }

    /**
     * Intenta resolver dos cláusulas buscando literales que puedan unificarse.
     * Si encuentra dos proposiciones unificables (una positiva y otra negativa),
     * genera una nueva cláusula resolvente.
     */
    private static Frase intentarResolver(Frase f1, Frase f2) {

        for (Proposicion p1 : f1.Proposiciones) {
            // Recorre cada proposición de la primera cláusula.

            for (Proposicion p2 : f2.Proposiciones) {
                // Recorre cada proposición de la segunda cláusula.

                Map<String, String> sustitucion = Unificador.unificar(p1, p2);
                // Intenta unificar ambas proposiciones.

                if (sustitucion != null) {
                    // Si la unificación fue exitosa.

                    return generarResolvente(f1, f2, p1, p2, sustitucion);
                    // Genera y devuelve la cláusula resolvente.
                }
            }
        }

        return null;
        // Si no se encontró ninguna unificación posible, retorna null.
    }

    /**
     * Genera la cláusula resolvente a partir de dos cláusulas originales.
     * Elimina los literales que se resolvieron y aplica la sustitución
     * al resto de proposiciones.
     */
    private static Frase generarResolvente(Frase f1, Frase f2,
                                           Proposicion p1Eliminar,
                                           Proposicion p2Eliminar,
                                           Map<String, String> sustitucion) {

        Frase resolvente = new Frase();
        // Crea una nueva cláusula que contendrá el resultado de la resolución.

        for (Proposicion p : f1.Proposiciones) {
            // Recorre las proposiciones de la primera cláusula.

            if (p != p1Eliminar)
                resolvente.añadir(p.aplicarSustitucion(sustitucion));
            // Añade las proposiciones excepto la que fue eliminada,
            // aplicando la sustitución generada por la unificación.
        }

        for (Proposicion p : f2.Proposiciones) {
            // Recorre las proposiciones de la segunda cláusula.

            if (p != p2Eliminar)
                resolvente.añadir(p.aplicarSustitucion(sustitucion));
            // Añade las proposiciones restantes aplicando la sustitución.
        }

        return resolvente;
        // Devuelve la nueva cláusula generada.
    }

    /**
     * Verifica si una cláusula ya existe dentro de una lista de cláusulas.
     * Se usa para evitar duplicados durante el proceso de resolución.
     */
    private static boolean contieneFrase(ArrayList<Frase> lista, Frase f) {

        for (Frase existente : lista) {
            // Recorre cada cláusula existente en la lista.

            if (existente.imprimirString().equals(f.imprimirString()))
                return true;
            // Compara la representación textual de las cláusulas.
            // Si son iguales, significa que ya existe.
        }

        return false;
        // Si no se encontró ninguna coincidencia, la cláusula no está en la lista.
    }
}