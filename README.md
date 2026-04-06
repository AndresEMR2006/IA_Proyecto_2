# IA_Proyecto_2: Motor de Inferencia Basado en Resolución 
**Materia:** Introducciòn a la Inteligencia Artificial

## Descripción del Proyecto
Este proyecto implementa un motor de inferencia basado en resolución por refutación utilizando Lógica de Primer Orden (LPO). El programa incluye un algoritmo de unificación de variables simbólicas y es capaz de demostrar teoremas a partir de una Base de Conocimiento dada.

El caso de validación implementado resuelve el clásico problema lógico: *"¿Odia Marco a César?"* partiendo de las premisas de que Marco es pompeyano, los pompeyanos son romanos, los romanos aman u odian a los gobernantes, y Marco intentó asesinar al gobernante César.

## Estructura de Clases
- `Proposicion.java`: Representa un literal atómico (con su nombre, estado de negación y argumentos).
- `Frase.java`: Representa una cláusula en forma normal conjuntiva (una disyunción de proposiciones).
- `Base.java`: Contenedor de la Base de Conocimiento (conjunto de Frases).
- `Unificador.java`: Implementa el Algoritmo de Unificación (encuentra el MGU).
- `MotorInferencia.java`: Ejecuta el algoritmo de resolución cruzando las cláusulas hasta hallar la cláusula vacía.
- `Main.java`: Contiene la carga del teorema de validación y ejecuta el programa.

## Cómo ejecutar el proyecto
1. Asegúrate de tener instalado Java (JDK 8 o superior).
2. Abre una terminal y navega hasta la carpeta raíz del proyecto.
3. Compila los archivos ejecutando: `javac *.java`
4. Ejecuta el programa principal con: `java Main`
5. La consola mostrará la traza paso a paso del proceso de resolución y la unificación de variables hasta llegar a la cláusula vacía.
