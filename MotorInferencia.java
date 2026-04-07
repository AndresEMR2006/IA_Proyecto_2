import java.util.ArrayList;
// Importa la clase ArrayList, que se utiliza para almacenar colecciones dinámicas de objetos.
// En este caso se usa para almacenar las cláusulas, frases generadas y frases ya usadas.

import java.util.Map;
// Importa la interfaz Map, que se utiliza para almacenar pares clave-valor.
// En este caso se usa para trabajar con las sustituciones generadas en la unificación.

/**
 * Motor de Inferencia basado en Resolución por Refutación.
 * 
 * Este motor implementa un algoritmo de resolución que busca demostrar teoremas
 * mediante contradicción. La estrategia aplicada es la siguiente:
 * 
 * 1. Siempre utiliza la cláusula más reciente generada como punto de partida.
 * 2. Intenta resolver esta cláusula con las cláusulas no usadas, comenzando por
 *    la más reciente.
 * 3. Cuando se genera una nueva cláusula resolvente, esta se convierte en la 
 *    más reciente y el proceso se repite.
 * 4. Si se genera una cláusula vacía (contradicción), el teorema se considera
 *    demostrado.
 * 5. Si no se puede generar más cláusulas nuevas, el algoritmo termina sin
 *    demostrar el teorema.
 */
public class MotorInferencia {


    /**
     * Ejecuta el algoritmo principal de resolución por refutación.
     * 
     * Este método implementa el ciclo iterativo que:
     * - Toma la cláusula más reciente de la base de conocimiento
     * - Intenta encontrar resolventes con las cláusulas no utilizadas
     * - Agrega nuevas cláusulas a la base según sea necesario
     * - Continúa hasta encontrar una contradicción o agotar las posibilidades
     * 
     * base Objeto Base que contiene la lista de cláusulas (frases) a resolver.
     *             Las cláusulas se encuentran en el atributo base.Frases.
     */
    public static void resolver(Base base) {
        ArrayList<Frase> clausulas = base.Frases;
        // Obtiene la lista de cláusulas de la base de conocimiento.
        // Esta lista se irá ampliando conforme se generen nuevas cláusulas resolventes.

        int paso = 1;
        // Contador que lleva registro del número de pasos en el proceso de resolución.
        // Se incrementa cada vez que se genera una nueva cláusula resolvente.

        ArrayList<Frase> yaUsadas = new ArrayList<>();
        // Lista que mantiene registro de las cláusulas ya utilizadas en resoluciones.
        // Se usa para evitar resolver la misma cláusula más veces si no es necesario.

        System.out.println("--- INICIO RESOLUCIÓN POR REFUTACIÓN ---");
        // Imprime el encabezado al inicio del algoritmo.

        if (clausulas.isEmpty()) {
            // Verifica si la base de conocimiento contiene al menos una cláusula.
            System.out.println("La base de conocimiento está vacía.");
            // Si está vacía, no hay nada que resolver.
            return;
            // Termina la ejecución del método.
        }

        boolean seGeneroNueva = true;
        // Bandera que controla si en el último ciclo se generó una nueva cláusula.
        // El algoritmo continúa mientras siga generando nuevas cláusulas.

        while (seGeneroNueva) {
            // Inicia el ciclo principal que se repite mientras haya nuevas cláusulas generadas.
            
            seGeneroNueva = false;
            // Reinicia la bandera al inicio de cada ciclo.
            // Si se genera una nueva cláusula dentro del ciclo, se volverá true.

            // Siempre tomar la cláusula más reciente
            Frase clausulaReciente = clausulas.get(clausulas.size() - 1);
            // Obtiene la última cláusula agregada a la lista, que es la más reciente.
            // Esta es la primera cláusula que se intentará resolver con las demás.

            System.out.println("\nCláusula más reciente:");
            // Encabezado informativo.
            
            System.out.println("  C" + clausulas.size() + ": " + clausulaReciente.imprimirString());
            // Muestra la cláusula más reciente con su número identificador.

            System.out.println("Cláusulas no usadas (de más reciente a más antigua):");
            // Encabezado que indica el listado de cláusulas candidatas para resolución.
            
            for (int i = clausulas.size() - 2; i >= 0; i--) {
                // Itera desde la penúltima cláusula (size-2) hasta la primera (0).
                // Esto asegura que se muestren de más reciente a más antigua.
                
                if(!(yaUsadas.contains(clausulas.get(i)))){
                    // Verifica que la cláusula actual no haya sido usada antes.
                    // Esto evita mostrar cláusulas que ya fueron aplicadas en resoluciones.
                    
                    System.out.println("  C" + (i + 1) + ": " + clausulas.get(i).imprimirString());
                    // Muestra el identificador de la cláusula (i+1) y su representación textual.
                }
            }

            // Resolver siempre con las anteriores, empezando por la más reciente
            for (int i = clausulas.size() - 2; i >= 0; i--) {
                // Itera sobre todas las cláusulas anteriores a la cláusula más reciente.
                // El orden es de más reciente a más antigua (de size-2 hacia 0).
                
                Frase candidata = clausulas.get(i);
                // Obtiene la cláusula candidata en la iteración actual.

                Frase resolvente = intentarResolver(clausulaReciente, candidata);
                // Intenta resolver la cláusula más reciente con la cláusula candidata.
                // Retorna un resolvente si es posible encontrar literales complementarios unificables,
                // o null si no es posible la resolución.

                if (resolvente != null) {
                    // Si se encontró un resolvente válido.
                    
                    if (!contieneFrase(clausulas, resolvente)) {
                        // Verifica que la cláusula resolvente no esté ya en la lista.
                        // Esto evita agregar duplicados.
                        
                        System.out.println("\nPaso " + paso + ": Resolviendo con la más reciente");
                        // Encabezado que indica el paso actual del algoritmo.
                        
                        System.out.println("  Clausula reciente: " + clausulaReciente.imprimirString());
                        // Muestra la cláusula más reciente utilizada.
                        
                        System.out.println("  Clausula elegida:  " + candidata.imprimirString());
                        // Muestra la cláusula candidata con la que se resolvió.
                        
                        System.out.println("  => Resultado:      " + resolvente.imprimirString());
                        // Muestra el resolvente generado de la resolución.

                        yaUsadas.add(candidata);
                        // Marca la cláusula candidata como usada.
                        
                        yaUsadas.add(clausulaReciente);
                        // Marca la cláusula más reciente como usada también.

                        paso++;
                        // Incrementa el contador de pasos.

                        if (resolvente.Proposiciones.isEmpty()) {
                            // Verifica si el resolvente es una cláusula vacía (sin proposiciones).
                            // Una cláusula vacía indica que se encontró una contradicción.
                            
                            System.out.println("\n¡CONTRADICCIÓN ENCONTRADA! (Cláusula vacía []).");
                            // Notifica que se encontró una contradicción.
                            
                            System.out.println("El teorema ha sido DEMOSTRADO con éxito.");
                            // El teorema se considera demostrado cuando se alcanza contradicción.
                            
                            return;
                            // Termina la ejecución del algoritmo de forma exitosa.
                        }

                        clausulas.add(resolvente);
                        // Agrega el resolvente a la lista de cláusulas.
                        // Este se convierte en la nueva cláusula más reciente.
                        
                        seGeneroNueva = true;
                        // Marca que se generó una nueva cláusula, lo que requiere continuar iterando.
                        
                        break; 
                        // Sale del bucle for actual.
                        // Importante: la nueva cláusula agregada ahora será la más reciente en el siguiente
                        // ciclo while, siguiendo la estrategia del algoritmo.
                    }
                }
            }
        }

        System.out.println("\nEl algoritmo terminó sin encontrar contradicción.");
        // El algoritmo finalizó sin generar más nuevas cláusulas.
        
        System.out.println("Teorema NO demostrado.");
        // Indica que no fue posible demostrar el teorema.
    }

    /**
     * Intenta resolver dos cláusulas buscando literales complementarios unificables.
     * 
     * Un literal complementario es un par de proposiciones donde una es la negación
     * de la otra (p.ej., P(X) y ¬P(Y)). Para que puedan resolverse, estos literales
     * deben ser unificables, es decir, debe existir una sustitución de variables que
     * los haga idénticos.
     * 
     * f1 Primera cláusula a comparar.
     * f2 Segunda cláusula a comparar.
     * Una nueva cláusula resolvente si se encuentra una resolución válida,
     *         o null si no es posible resolver las dos cláusulas.
     */
    private static Frase intentarResolver(Frase f1, Frase f2) {
        for (Proposicion p1 : f1.Proposiciones) {
            // Itera sobre cada proposición de la primera cláusula.
            
            for (Proposicion p2 : f2.Proposiciones) {
                // Itera sobre cada proposición de la segunda cláusula.
                // Se busca un par (p1, p2) que sean complementarios y unificables.

                Map<String, String> sustitucion = Unificador.unificar(p1, p2);
                // Intenta unificar las dos proposiciones.
                // Si son unificables, retorna un mapa con las sustituciones necesarias.
                // Si no, retorna null.

                if (sustitucion != null) {
                    // Si la unificación fue exitosa.
                    
                    return generarResolvente(f1, f2, p1, p2, sustitucion);
                    // Genera y retorna la cláusula resolvente eliminando los literales
                    // complementarios y aplicando las sustituciones encontradas.
                }
            }
        }
        return null;
        // Si no se encontró ningún par de literales complementarios unificables,
        // retorna null indicando que las dos cláusulas no pueden resolverse.
    }

    /**
     * Genera la nueva cláusula resolvente a partir de dos cláusulas y sus literales resueltos.
     * 
     * El proceso de generación es:
     * 1. Toma todas las proposiciones de f1 excepto la que se resolvió (p1Eliminar).
     * 2. Toma todas las proposiciones de f2 excepto la que se resolvió (p2Eliminar).
     * 3. Aplica la sustitución encontrada durante la unificación a todas estas proposiciones.
     * 4. Combina todas estas proposiciones en una nueva cláusula.
     * 
     * f1 Primera cláusula participante en la resolución.
     * f2 Segunda cláusula participante en la resolución.
     * p1Eliminar La proposición de f1 que será eliminada (fue resuelta).
     * p2Eliminar La proposición de f2 que será eliminada (fue resuelta).
     * sustitucion Mapa de sustituciones de variables a aplicar al resolvente.
     * Una nueva cláusula resolvente con los literales complementarios eliminados
     *         y las sustituciones aplicadas.
     */
    private static Frase generarResolvente(Frase f1, Frase f2,
                                           Proposicion p1Eliminar,
                                           Proposicion p2Eliminar,
                                           Map<String, String> sustitucion) {
        Frase resolvente = new Frase();
        // Crea una nueva cláusula vacía que contendrá el resolvente.

        for (Proposicion p : f1.Proposiciones) {
            // Itera sobre cada proposición de la primera cláusula.
            
            if (p != p1Eliminar) {
                // Verifica que no sea la proposición que se resolió.
                
                resolvente.añadir(p.aplicarSustitucion(sustitucion));
                // Aplica la sustitución a la proposición y la agrega al resolvente.
                // Si no hay sustituciones relevantes, la proposición se mantiene sin cambios.
            }
        }

        for (Proposicion p : f2.Proposiciones) {
            // Itera sobre cada proposición de la segunda cláusula.
            
            if (p != p2Eliminar) {
                // Verifica que no sea la proposición que se resolvió.
                
                resolvente.añadir(p.aplicarSustitucion(sustitucion));
                // Aplica la sustitución a la proposición y la agrega al resolvente.
            }
        }

        return resolvente;
        // Retorna la cláusula resolvente generada.
    }

    /**
     * Verifica si una cláusula ya existe en la lista para evitar redundancias.
     * 
     * Compara la representación textual de la cláusula con todas las cláusulas
     * existentes en la lista. Si encuentra una coincidencia exacta, retorna true.
     * Esta comparación previene agregar cláusulas duplicadas al conjunto.
     * 
     * lista La lista de cláusulas a verificar.
     * f La cláusula a buscar.
     * true si la cláusula ya existe en la lista, false en caso contrario.
     */
    private static boolean contieneFrase(ArrayList<Frase> lista, Frase f) {
        for (Frase existente : lista) {
            // Itera sobre cada cláusula en la lista.
            
            if (existente.imprimirString().equals(f.imprimirString())) {
                // Compara la representación textual de la cláusula existente con la nueva.
                // Si son iguales, significa que la cláusula ya está en la lista.
                
                return true;
                // Retorna true indicando que la cláusula existe.
            }
        }
        return false;
        // Si no se encontró ninguna coincidencia, retorna false.
    }
}
