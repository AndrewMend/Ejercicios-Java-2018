package modelo;

public class Cuadrado {
	private double lado;
	
	public Cuadrado(double lado) {
		this.lado = lado;
	}
	
	public double calcularArea() {
		return Math.abs(lado*lado);
	}
	public double calcularPerimetro() {
		return Math.abs(lado * 4);
	}
}
