package es.unican.is2;

/*
	WMC = 4 
 	WMCn = 4 / 4 = 1
 	CCog = 1
 	CCogn = 1 / 4 = 0.25
*/

/* Clase abstracta que representa un transporte realizado por un conductor */
public abstract class Transporte {

	private double horas;

	/**
	 * Constructor de la clase Transporte
	 * 
	 * @param horas Horas que ha durado el transporte
	 */
	public Transporte(double horas) throws IllegalArgumentException { // CC + 1
		if (horas <= 0) // CC + 1 || CCog + 1
			throw new IllegalArgumentException();
		this.horas = horas;
	}

	// CC = 1 || CCog = 0
	public double getHoras() { // CC + 1
		return horas;
	}

	public abstract double getSueldoExtra(); // CC = 1
}
