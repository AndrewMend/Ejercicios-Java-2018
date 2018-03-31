/*
 * 
 * @Author: Andrés Mendoza Moreno
 * 
 */

package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Principal {
//Método principal. Inicializa el programa y cuenta con validaciones para evitar que falle
	public static void main(String[] argumentos) {
		String entrada = "";
		imprime("Intrucciones: Escriba el número correspondiente a la opción\nque desea ejecutar y presione ENTER para acceder a ella");
		String menu = "";
		menu += "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n";			
		menu += "|\tTraductor de Código Morse\t\t\t\t| \n|\t\t\t\t\t\t\t\t\t|\n";
		menu += "|\t1. Traducir texto a código morse\t\t\t|\n";
		menu += "|\t2. Traducir código morse a texto\t\t\t|\n";
		menu += "|\t3. Salir del programa\t\t\t\t\t|\n";		
		menu += "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n";			
		int opcion = -1;
		//Bucle para evaluar la opción del usuario. Finaliza hasta que la opción es igual a 3
		while(opcion != 3) {			
			try {
				imprime(menu);	
				opcion = leerEntero();				
			} catch (Exception e) {
				//Mediante une excepción. Se valida que se ingrese un número correctamente. En caso contrario, se captura la excepción y se vuelve a intentar.
				imprime("Ha ocurrido un error, por favor vuelve a intentar\n");
			}

			//Casos de selección por entrada de usuario
			switch (opcion) {
			/*El primer caso, solicita al usuario texto para traducirlo a morse. Cualquier caractér que no pertenezca a la lista proporcionada
			 * no será tomado en cuenta al devolver el resultado.
			 */
			case 1:
				imprime("Ingresa el texto a traducir: \n");
				//Se llama al método traducirTextoAMorse para evaluar dicha función
				entrada = traducirTextoAMorse(leerCadena());					
				//Se imprime el resultado en código morse
				imprime("Traducción: \n" + entrada + "\n");
				opcion = -1; //Reinicia la opción actual
				break;

			/*Para el segundo caso, se solicitan unicamente guiones y puntos. Cualquier caractér que no sea un punto (.) 
			 * o un guión (-) no será tomado en cuenta en el resultado. */
			case 2:

				imprime("Ingresa el código morse a traducir: \n");
				//Se llama al método traducirMorseATexto para evaluar dicha función
				entrada = traducirMorseATexto(leerCadena());
				//Se imprime el resultado en texto, en mayúsculas
				imprime("\nTraducción: \n" + entrada + "\n");
				opcion = -1; //Reinicia la opción actual
				break;
			case 3:
				imprime("\n- - - - - - - PROGRAMA FINALIZADO - - - - - - -");
				break;				
				
			default:
				//El caso por defecto proporciona al usuario información sobre como utilizar el menú
				imprime("Intenta con un número válido [1-3]");
				break;
		}		
		//Se limpiar una parte de la pantalla para mantener un mejor orden al mostrar los datos
		limpiarPantalla();
		
		}

	}
	/*
	 * Métodos utilizados para la clase principal
	 */	
	//Método que permite imprimir texto en consola
	public static void imprime(String texto) {
		System.out.println(texto);
	}	
	//Método que imprime tres saltos de línea para mejorar la visibilidad cuando es requerido
	public static void limpiarPantalla() {
		imprime("\n\n\n");		
	}
	//Método que permite el análisis de texto para devolver una cadena con su respectiva traducción en morse
	public static String traducirTextoAMorse(String entrada){				
		//Validación de acentos, para evitar perdida de información
		entrada = entrada.toUpperCase();
		entrada = entrada.replace("Á", "A");
		entrada = entrada.replace("É", "E");
		entrada = entrada.replace("Í", "I");
		entrada = entrada.replace("Ó", "O");
		entrada = entrada.replace("Ú", "U");
		
		String salida = ""; //Variable que será retornada al finalizar el método
		StringTokenizer separador  = new StringTokenizer(entrada);		//Clase perteneciente a java.util que permite el análisis léxico por token		
		ArrayList<Lista> lista = new Lista().obtenerLista(); //Método para obtener 
		
		while(separador.hasMoreTokens()) { //Bucle que analiza todas las palabras disponibles
				String palabra = separador.nextToken(); //se asigna el siguiente token a una cadena de texto
				char[] caracteres = palabra.toCharArray(); //Se separa la palabra actual en un arreglo de caractéres
				for (int i = 0; i < caracteres.length; i++) { //Ciclo for para verificar si algún caracter coincide con la lista 
					for (int j = 0; j < lista.size(); j++) {	  //Segundo ciclo for el cual compara cada una de las palabras de la lista
						if( lista.get(j).getValorTexto().contains(""+caracteres[i]) ){ //Si la lista contiene el valor textual del caracter...
							salida += lista.get(j).getValorMorse() + " ";							//A salida se le asigna el valor morse de dicho token
							break; //En caso de entrar a este if, finaliza el for para pasar a la siguiente letra
						} 						
					}										
				}
				salida+= "  "; //A la cadena de salida se le asignan tres espacios vacios para cumplir con las reglas de traducción
			}
				
		return salida;
	}
	//Método que permite el análisis de un código morse para devolver su respectiva traducción a texto
	public static String traducirMorseATexto(String entrada){		
		String salida = ""; //Se declara una variable para retornar la salida
		entrada = entrada.replace("   ", "%"); //Se reemplazan los espacios vacíos triples con el símbolo porcentaje para facilitar el análisis
		StringTokenizer separador  = new StringTokenizer(entrada, "%"); //El porcentual permite separar las palabras en morse		
		ArrayList<Lista> lista = new Lista().obtenerLista(); //Se obtiene la lista de texto y equivalencia morse
		
		while(separador.hasMoreTokens()) {			
				String palabra = separador.nextToken(); //Obtener la palabra a analizar				
				palabra = palabra.replace( " " ,"#");	//Remplazar los espacios vacios con un gato (#)
				StringTokenizer caracteres = new StringTokenizer(palabra, "#"); //Separar la palabra por espacios gracias al simbolo gato(#)
				while(caracteres.hasMoreTokens()) { //Mientras tenga más tokens
					String siguienteToken = caracteres.nextToken(); //Se analiza el conjunto de puntos y guiones para determinar si coincide con su valor textual
					for (int j = 0; j < lista.size(); j++) { //Analizar lineas de la lista
						if( lista.get(j).getValorMorse().equals(siguienteToken) ){//Si el valor coincide con algún valor de la lista...					
							salida += lista.get(j).getValorTexto();							//A salida se le asigna el valor textual
							break;
						} 						
					}									
				}
				salida+= " "; //Se concatena un espacio para darle sentido a la oración
			}
				
		return salida;
	}
	//Método que permite la lectura de una cadena de texto para devolverla en un String
	public static String leerCadena() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		try {
			String cadena = br.readLine();
			return cadena;
		} catch (IOException e) {
			imprime("Excepciones dice: Error de entrada o salida");
			return "";
		}


	}
	//Método que permite la lectura de un entero para devolverlo en un dato entero
	public static int leerEntero() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		try {
			int numero = Integer.parseInt( br.readLine());
			return numero;
		} catch (IOException e) {
			imprime("Excepciones dice: Error de entrada o salida");
			return -1;
		}
	}

}
