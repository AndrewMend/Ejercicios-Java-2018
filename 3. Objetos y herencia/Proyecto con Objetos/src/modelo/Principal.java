/*
 * @Author: Andr�s Mendoza Moreno
 * */
package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {
	//M�todo p�blico est�tico principal que no devuelve nada y recibe por argumentos un arreglo de tipo cadena
	public static void main(String[] argumentos) {		
		//Variables principales
		boolean bandera = true; //Mientras sea verdadera, el programa continuar� funcionando
		double lado; //Variable que recibir� el valor del lado
		int opcion; //Variable que determina la opci�n ingresada en el men�
		String menu;
		Cubo cubo ;
		Cuadrado cuadrado;
		
		//Bucle para validar el status del programa
		while(bandera) {
			//Se inicia con un try-catch para validar el programa de posibles errores de entrada
			try {
			imprime("Por favor, ingrese el valor de un lado: \n");
			lado = leerNumero(); //Se obtiene el valor de un lado
			opcion = -1; //Se asigna -1 a opci�n para no acceder a ning�n elemento del men� y permitir que el usuario decida
			while (opcion != 5 && bandera) {			//Mientras la bandera sea verdadera y la opci�n no sea la 5 (La cual regresar� a cambiar el valor de un lado)...
				menu = "\n\n\n- - - - MEN� - - - -\n\n";
				menu += "Valor del lado: "+lado + "\n";
				menu += "\n1. Calcular per�metro de un cuadrado \n";
				menu += "2. Calcular per�metro de un cubo \n";
				menu += "3. Calcular �rea de un cuadrado \n";
				menu += "4. Calcular volumen de un cubo\n";			
				menu += "5. Cambiar el valor del lado \n";			
				menu += "6. Salir del programa \n";			
				
				cubo = new Cubo(lado); //Se inicializa un cubo
				cuadrado = new Cuadrado(lado); //Se inicializa un cuadrado
				
				/*Se crean ambos objetos, ya que algebra�camente hablando el per�metro de un cubo y el de un cuadrado, corresponden a la suma de las ar�stas,
				 * mientras que el cubo tiene 12, el cuadrado solo tiene 4*/
				
				imprime(menu); //Imprime el men�			
				opcion = leerEntero();//Lee la opci�n del usuario
				switch (opcion) { //Seg�n sea la opci�n realiza cada una de las siguientes acciones:
					case 1: //Imprimir el per�metro de un cuadrado
							imprime("\nValor del lado: "+lado+"\n\n");
							imprime("Perimetro de un cuadrado: "+cuadrado.calcularPerimetro() + " unidades");				
							/*Las siguientes instrucciones, hasta el break son concurrentes en los siguientes casos del switch*/
							//Opciones para decidir si regresar al men� o cambiar el valor del lado o ar�sta
							imprime("\n1. Regresar al menu");
							imprime("2. Cambiar valor del lado");
							opcion= leerEntero(); //Se recicla la variable opci�n para determinar la decisi�n del usuario
							if (opcion == 2) {
								opcion = 5;
							}
							
							break;							
					case 2://Imprimir el per�metro de un cubo
						imprime("\nValor del lado: "+lado+"\n\n");
						imprime("Perimetro de un cubo: "+cubo.calcularPerimetro() + " unidades");
						imprime("\n1. Regresar al menu");
						imprime("2. Cambiar valor del lado");
						opcion= leerEntero();
						if (opcion == 2) {
							opcion = 5;
						}
							break;
					case 3://Imprimir el �rea de un cuadrado
						imprime("\nValor del lado: "+lado+"\n\n");
						imprime("�rea de un cuadrado: "+ cuadrado.calcularArea() + " unidades cuadradas");				
						imprime("\n1. Regresar al menu");
						imprime("2. Cambiar valor del lado");
						opcion= leerEntero();
						if (opcion == 2) {
							opcion = 5;
						}
							break;
					case 4://Imprimir el volumen de un cubo
						imprime("\nValor del lado: "+lado+"\n\n");
						imprime("Volumen de un cubo: "+ cubo.calcularVolumen() + " unidades cubicas");								
						imprime("\n1. Regresar al menu");
						imprime("2. Cambiar valor del lado");
						opcion= leerEntero();
						if (opcion == 2) {
							opcion = 5;
						}
							break;
					case 6:
						bandera = false;
							break;

					default:
							break;
			}		
		}
			} catch (Exception e) {
				imprime("Tipo de dato erroneo.\nVuelve a intentarlo");
			}
		}
		imprime("* * * Programa finalizado * * *");
	}
	
	public static void imprime(String texto) {
		System.out.println(texto);
	}	
	//M�todo est�tico para leer un n�mero. Devuelve un tipo Double
	public static double leerNumero() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		try {
			double numero = Double.parseDouble( br.readLine());
			return numero;
		} catch (IOException e) {
			imprime("Excepciones dice: Error de entrada o salida");
			return -1;
		}
	}
	//M�todo est�tico para leer un n�mero entero
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
