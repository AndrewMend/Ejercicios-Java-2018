/*
 * @Author: Andrés Mendoza Moreno
 * */
package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {
	//Método público estático principal que no devuelve nada y recibe por argumentos un arreglo de tipo cadena
	public static void main(String[] argumentos) {		
		//Variables principales
		boolean bandera = true; //Mientras sea verdadera, el programa continuará funcionando
		double lado; //Variable que recibirá el valor del lado
		int opcion; //Variable que determina la opción ingresada en el menú
		String menu;
		Cubo cubo ;
		Cuadrado cuadrado;
		
		//Bucle para validar el status del programa
		while(bandera) {
			//Se inicia con un try-catch para validar el programa de posibles errores de entrada
			try {
			imprime("Por favor, ingrese el valor de un lado: \n");
			lado = leerNumero(); //Se obtiene el valor de un lado
			opcion = -1; //Se asigna -1 a opción para no acceder a ningún elemento del menú y permitir que el usuario decida
			while (opcion != 5 && bandera) {			//Mientras la bandera sea verdadera y la opción no sea la 5 (La cual regresará a cambiar el valor de un lado)...
				menu = "\n\n\n- - - - MENÚ - - - -\n\n";
				menu += "Valor del lado: "+lado + "\n";
				menu += "\n1. Calcular perímetro de un cuadrado \n";
				menu += "2. Calcular perímetro de un cubo \n";
				menu += "3. Calcular área de un cuadrado \n";
				menu += "4. Calcular volumen de un cubo\n";			
				menu += "5. Cambiar el valor del lado \n";			
				menu += "6. Salir del programa \n";			
				
				cubo = new Cubo(lado); //Se inicializa un cubo
				cuadrado = new Cuadrado(lado); //Se inicializa un cuadrado
				
				/*Se crean ambos objetos, ya que algebraícamente hablando el perímetro de un cubo y el de un cuadrado, corresponden a la suma de las arístas,
				 * mientras que el cubo tiene 12, el cuadrado solo tiene 4*/
				
				imprime(menu); //Imprime el menú			
				opcion = leerEntero();//Lee la opción del usuario
				switch (opcion) { //Según sea la opción realiza cada una de las siguientes acciones:
					case 1: //Imprimir el perímetro de un cuadrado
							imprime("\nValor del lado: "+lado+"\n\n");
							imprime("Perimetro de un cuadrado: "+cuadrado.calcularPerimetro() + " unidades");				
							/*Las siguientes instrucciones, hasta el break son concurrentes en los siguientes casos del switch*/
							//Opciones para decidir si regresar al menú o cambiar el valor del lado o arísta
							imprime("\n1. Regresar al menu");
							imprime("2. Cambiar valor del lado");
							opcion= leerEntero(); //Se recicla la variable opción para determinar la decisión del usuario
							if (opcion == 2) {
								opcion = 5;
							}
							
							break;							
					case 2://Imprimir el perímetro de un cubo
						imprime("\nValor del lado: "+lado+"\n\n");
						imprime("Perimetro de un cubo: "+cubo.calcularPerimetro() + " unidades");
						imprime("\n1. Regresar al menu");
						imprime("2. Cambiar valor del lado");
						opcion= leerEntero();
						if (opcion == 2) {
							opcion = 5;
						}
							break;
					case 3://Imprimir el área de un cuadrado
						imprime("\nValor del lado: "+lado+"\n\n");
						imprime("Área de un cuadrado: "+ cuadrado.calcularArea() + " unidades cuadradas");				
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
	//Método estático para leer un número. Devuelve un tipo Double
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
	//Método estático para leer un número entero
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
