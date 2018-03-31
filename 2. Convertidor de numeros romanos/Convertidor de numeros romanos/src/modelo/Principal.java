/*
 * @Author: Andr�s Mendoza Moreno
 * */
package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class Principal {
	//Se declaran las variables principales del programa como globales
	private static int numero;
	private static int millares;
	private static int centenas;
	private static int decenas ;

	//M�todo main
	public static void main(String[] argumentos) {
		//La variable validar determina si el programa debe terminar o no
		int validar = -1;
		//Bucle while que determina si el usuario desea evaluar otro n�mero o salir del programa java
		while(validar != 2) {
			imprime("- - - - - - - - - - - - - - - - - - - - - -\nIngrese un n�mero del 1 al 1000\t\t\t\t\n");
			//Mediante un try/catch se hace la captura de excepci�n por datos incompatibles para garantizar que el programa contin�e su flujo
			try {
				//Le see un n�mero entero
				numero = leerEntero();	
				//El resultado se ver� reflejado en la cadena salida
				String salida = "";		
				//Se evalua si el n�mero cumple con las condiciones descritas del ejercicio: tener un valor entre 1 y 1000				
				if (numero > 0 && numero <= 1000  ) {
					//En esta serie de condicionales se determinan los millares, centenas, decenas y unidades para facilitar la conversi�n
					if (numero > 999) {
						millares = numero/1000;//Determina los millares por medio de una simple divisi�n que retorna la cantidad entera
						numero = numero - (millares*1000);//Se resta la cantidad de millares al n�mero le�do
						}
					
					if (numero > 99) {
						centenas = numero/100;//De igual forma, determina las centenas con una divisi�n
						numero = numero - (centenas*100);//Se resta la cantidad de centenas al n�mero le�do
					}
					
					if (numero > 9) {
						decenas = numero/10;//Determina las decenas con una divisi�n
						numero = numero - (decenas*10);//Se resta la cantidad de decenas al n�mero le�do
					}
					salida += convertirUnidades(convertirARomanos(millares),4); //Se concatena la conversi�n de millares
					salida += convertirUnidades(convertirARomanos(centenas),3);//Se concatena la conversi�n de centenas
					salida += convertirUnidades(convertirARomanos(decenas),2);//Se concatena la conversi�n de decenas
					salida += convertirUnidades(convertirARomanos(numero),1);//Se concatena la conversi�n de unidades
					
					imprime("Equivalente en romano:\n" + salida);			//Se imprime el resultado
					/*
					 * Aqu� se reinician las variables para evitar un futuro mal funcionamiento (En caso de ingresar una nueva conversi�n)
					 * */
					millares = 0;
					centenas = 0;
					decenas = 0;
					numero = 0;
					/*La siguiente serie de condicionales y catch son los que muestran al usuario informaci�n sobre los posibles errores cometidos		 
					 */
				}else {
					imprime("\nEl n�mero debe ser mayor que 0 y menor o igual que 1000 \n");
				}	
				imprime("�Deseas convertir otro n�mero?\n1.Si\n2.No");
				validar = leerEntero();
			} catch (Exception e) {
				imprime("Has ingresado un dato erroneo. Vuelve a intentarlo");

			}



		}
		imprime("** Programa finalizado **");
	}
	 
	/*M�todo est�tico que devuelve una cadena de texto con la correspondiente conversi�n de un n�mero. Es generico, es decir,
	 devuelve solamente la equivalencia con caract�res ! $ y #, los cuales posteriormente pueden ser interpretados
	 * */ 
	public static String convertirARomanos(int numero){
		String romano = "";
		switch (numero) {
		case 1:
			romano ="!";
			break;
		case 2:
			romano ="!!";			
			break;
		case 3:
			romano ="!!!";
			break;
		case 4:
			romano ="!%";
			break;
		case 5:
			romano ="%";
			break;
		case 6:
			romano ="%!";
			break;
		case 7:
			romano ="%!!";
			break;
		case 8:
			romano ="%!!!";
			break;
		case 9:
			romano ="!#";
			break;
		}

		return romano;
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
		//M�todo que permite imprimir texto en consola
	public static void imprime(String texto) {
			System.out.println(texto);
		}
	//M�todo est�tico que convierte el texto gen�rico generado, de acuerdo a las indicaciones del tipo de conversi�n, determinado por la variable t
	public static String convertirUnidades(String numero, int t) {
		String conversion = numero;
		switch (t) {
		case 1: //Caso 1 para unidades
			conversion = conversion.replace("!", "I"); 
			conversion = conversion.replace("%", "V"); 
			conversion = conversion.replace("#", "X"); 
			break;
		case 2: //Caso 2 para decenas
			conversion = conversion.replace("!", "X"); 
			conversion = conversion.replace("%", "L"); 
			conversion = conversion.replace("#", "C"); 			
			break;
		case 3: //Caso 2 para centenas
			conversion = conversion.replace("!", "C"); 
			conversion = conversion.replace("%", "D"); 
			conversion = conversion.replace("#", "M"); 			
			break;	
		case 4: //Caso 4 para millar
			conversion = conversion.replace("!", "M"); 
			break;

		default:
			break;
		}
		return conversion;
	}
}
