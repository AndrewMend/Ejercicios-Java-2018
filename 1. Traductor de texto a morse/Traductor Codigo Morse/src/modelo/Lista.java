package modelo;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Lista {
	//Objeto que funciona como interfaz para traducir. Cuenta con las equivalencias entre texto y morse 	
	private String valorTexto;
	private String valorMorse;
	
	
	public String getValorTexto() {
		return valorTexto;
	}
	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}
	public String getValorMorse() {
		return valorMorse;
	}
	public void setValorMorse(String valorMorse) {
		this.valorMorse = valorMorse;
	}
	@Override
	public String toString() {
		return "Token \nValor textual: " + valorTexto + "\nValor morse: " + valorMorse;
	}
	
	public ArrayList<Lista> obtenerLista(){		
		ArrayList<String> lineas = new ArrayList<String>();
		lineas.add("A|.-");
		lineas.add("B|-...");
		lineas.add("C|-.-.");
		lineas.add("D|-..");
		lineas.add("E|.");
		lineas.add("F|..-.");
		lineas.add("G|--.");
		lineas.add("H|....");
		lineas.add("I|..");
		lineas.add("J|.---");
		lineas.add("K|-.-");
		lineas.add("L|.-..");
		lineas.add("M|--");
		lineas.add("N|-.");
		lineas.add("O|---");
		lineas.add("P|.--.");
		lineas.add("Q|--.-");
		lineas.add("R|.-.");
		lineas.add("S|...");
		lineas.add("T|-");
		lineas.add("U|..-");
		lineas.add("V|...-");
		lineas.add("W|.--");
		lineas.add("X|-..-");
		lineas.add("Y|-.--");
		lineas.add("Z|--..");
		lineas.add("1|.----");
		lineas.add("2|..---");
		lineas.add("3|...--");
		lineas.add("4|....-");
		lineas.add("5|.....");
		lineas.add("6|-....");
		lineas.add("7|--...");
		lineas.add("8|---..");
		lineas.add("9|----.");
		lineas.add("0|-----");
		
		ArrayList<Lista> lista = new ArrayList<Lista>();
		StringTokenizer tokenizer ;
		for (int i = 0; i < lineas.size(); i++) {
			tokenizer = new StringTokenizer(lineas.get(i), "|");
			Lista token = new Lista();
			token.setValorTexto(tokenizer.nextToken());			
			token.setValorMorse(tokenizer.nextToken());			
			lista.add(token);			
		}	    
		return lista;
	}
	
	
	
	
}
