package modelo;

public class Cubo extends Cuadrado{
	private double lado;
	
	public Cubo(double lado) {
		super(lado);		
		this.lado = lado;						
	}	
	public double calcularVolumen(){		
		return Math.abs(this.calcularArea()* lado);
	}
	
	@Override 
	public double calcularPerimetro(){
		return Math.abs(lado*12);
	}

}
