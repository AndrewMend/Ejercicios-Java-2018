/*
 * 
 * @Author: Andr�s Mendoza Moreno
 * 
 */

package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Principal {
//M�todo principal. Inicializa el programa y cuenta con validaciones para evitar que falle
	public static void main(String[] argumentos) {
		String entrada = "";
		imprime("Intrucciones: Escriba el n�mero correspondiente a la opci�n\nque desea ejecutar y presione ENTER para acceder a ella");
		String menu = "";
		menu += "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n";			
		menu += "|\tTraductor de C�digo Morse\t\t\t\t| \n|\t\t\t\t\t\t\t\t\t|\n";
		menu += "|\t1. Traducir texto a c�digo morse\t\t\t|\n";
		menu += "|\t2. Traducir c�digo morse a texto\t\t\t|\n";
		menu += "|\t3. Salir del programa\t\t\t\t\t|\n";		
		menu += "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n";			
		int opcion = -1;
		//Bucle para evaluar la opci�n del usuario. Finaliza hasta que la opci�n es igual a 3
		while(opcion != 3) {			
			try {
				imprime(menu);	
				opcion = leerEntero();				
			} catch (Exception e) {
				//Mediante une excepci�n. Se valida que se ingrese un n�mero correctamente. En caso contrario, se captura la excepci�n y se vuelve a intentar.
				imprime("Ha ocurrido un error, por favor vuelve a intentar\n");
			}

			//Casos de selecci�n por entrada de usuario
			switch (opcion) {
			/*El primer caso, solicita al usuario texto para traducirlo a morse. Cualquier caract�r que no pertenezca a la lista proporcionada
			 * no ser� tomado en cuenta al devolver el resultado.
			 */
			case 1:
				imprime("Ingresa el texto a traducir: \n");
				//Se llama al m�todo traducirTextoAMorse para evaluar dicha funci�n
				entrada = traducirTextoAMorse(leerCadena());					
				//Se imprime el resultado en c�digo morse
				imprime("Traducci�n: \n" + entrada + "\n");
				opcion = -1; //Reinicia la opci�n actual
				break;

			/*Para el segundo caso, se solicitan unicamente guiones y puntos. Cualquier caract�r que no sea un punto (.) 
			 * o un gui�n (-) no ser� tomado en cuenta en el resultado. */
			case 2:

				imprime("Ingresa el c�digo morse a traducir: \n");
				//Se llama al m�todo traducirMorseATexto para evaluar dicha funci�n
				entrada = traducirMorseATexto(leerCadena());
				//Se imprime el resultado en texto, en may�sculas
				imprime("\nTraducci�n: \n" + entrada + "\n");
				opcion = -1; //Reinicia la opci�n actual
				break;
			case 3:
				imprime("\n- - - - - - - PROGRAMA FINALIZADO - - - - - - -");
				break;				
				
			default:
				//El caso por defecto proporciona al usuario informaci�n sobre como utilizar el men�
				imprime("Intenta con un n�mero v�lido [1-3]");
				break;
		}		
		//Se limpiar una parte de la pantalla para mantener un mejor orden al mostrar los datos
		limpiarPantalla();
		
		}

	}
	/*
	 * M�todos utilizados para la clase principal
	 */	
	//M�todo que permite imprimir texto en consola
	public static void imprime(String texto) {
		System.out.println(texto);
	}	
	//M�todo que imprime tres saltos de l�nea para mejorar la visibilidad cuando es requerido
	public static void limpiarPantalla() {
		imprime("\n\n\n");		
	}
	//M�todo que permite el an�lisis de texto para devolver una cadena con su respectiva traducci�n en morse
	public static String traducirTextoAMorse(String entrada){				
		//Validaci�n de acentos, para evitar perdida de informaci�n
		entrada = entrada.toUpperCase();
		entrada = entrada.replace("�", "A");
		entrada = entrada.replace("�", "E");
		entrada = entrada.replace("�", "I");
		entrada = entrada.replace("�", "O");
		entrada = entrada.replace("�", "U");
		
		String salida = ""; //Variable que ser� retornada al finalizar el m�todo
		StringTokenizer separador  = new StringTokenizer(entrada);		//Clase perteneciente a java.util que permite el an�lisis l�xico por token		
		ArrayList<Lista> lista = new Lista().obtenerLista(); //M�todo para obtener 
		
		while(separador.hasMoreTokens()) { //Bucle que analiza todas las palabras disponibles
				String palabra = separador.nextToken(); //se asigna el siguiente token a una cadena de texto
				char[] caracteres = palabra.toCharArray(); //Se separa la palabra actual en un arreglo de caract�res
				for (int i = 0; i < caracteres.length; i++) { //Ciclo for para verificar si alg�n caracter coincide con la lista 
					for (int j = 0; j < lista.size(); j++) {	  //Segundo ciclo for el cual compara cada una de las palabras de la lista
						if( lista.get(j).getValorTexto().contains(""+caracteres[i]) ){ //Si la lista contiene el valor textual del caracter...
							salida += lista.get(j).getValorMorse() + " ";							//A salida se le asigna el valor morse de dicho token
							break; //En caso de entrar a este if, finaliza el for para pasar a la siguiente letra
						} 						
					}										
				}
				salida+= "  "; //A la cadena de salida se le asignan tres espacios vacios para cumplir con las reglas de traducci�n
			}
				
		return salida;
	}
	//M�todo que permite el an�lisis de un c�digo morse para devolver su respectiva traducci�n a texto
	public static String traducirMorseATexto(String entrada){		
		String salida = ""; //Se declara una variable para retornar la salida
		entrada = entrada.replace("   ", "%"); //Se reemplazan los espacios vac�os triples con el s�mbolo porcentaje para facilitar el an�lisis
		StringTokenizer separador  = new StringTokenizer(entrada, "%"); //El porcentual permite separar las palabras en morse		
		ArrayList<Lista> lista = new Lista().obtenerLista(); //Se obtiene la lista de texto y equivalencia morse
		
		while(separador.hasMoreTokens()) {			
				String palabra = separador.nextToken(); //Obtener la palabra a analizar				
				palabra = palabra.replace( " " ,"#");	//Remplazar los espacios vacios con un gato (#)
				StringTokenizer caracteres = new StringTokenizer(palabra, "#"); //Separar la palabra por espacios gracias al simbolo gato(#)
				while(caracteres.hasMoreTokens()) { //Mientras tenga m�s tokens
					String siguienteToken = caracteres.nextToken(); //Se analiza el conjunto de puntos y guiones para determinar si coincide con su valor textual
					for (int j = 0; j < lista.size(); j++) { //Analizar lineas de la lista
						if( lista.get(j).getValorMorse().equals(siguienteToken) ){//Si el valor coincide con alg�n valor de la lista...					
							salida += lista.get(j).getValorTexto();							//A salida se le asigna el valor textual
							break;
						} 						
					}									
				}
				salida+= " "; //Se concatena un espacio para darle sentido a la oraci�n
			}
				
		return salida;
	}
	//M�todo que permite la lectura de una cadena de texto para devolverla en un String
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
	//M�todo que permite la lectura de un entero para devolverlo en un dato entero
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
