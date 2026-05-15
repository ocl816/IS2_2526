package es.unican.is2;

/*
	WMC = 9 (5 + 1 + 1 + 1 + 1)
 	WMCn = 9 / 5 = 1.8
 	CCog = 4 (4 + 0 + 0 + 0 + 0)
 	CCogn = 4 / 5 = 0.8
*/

/* Clase abstracta que representa un transporte realizado por un conductor */
public abstract class Transporte {

	private double horas;

	/**
	 * Constructor de la clase Transporte
	 * 
	 * @param horas Horas que ha durado el transporte
	 */

	// CC = 5 || CCog = 4
	public Transporte(double horas) throws IllegalArgumentException { // CC + 1
		if (horas <= 0)
			throw new IllegalArgumentException();
		this.horas = horas;
	}

	// CC = 1 || CCog = 0
	public double getHoras() { // CC + 1
		return horas;
	}

	public abstract double getSueldoExtra();
}
