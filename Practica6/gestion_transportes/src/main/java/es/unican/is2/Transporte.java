package es.unican.is2;

/*
	WMC = 9 (5 + 1 + 1 + 1 + 1)
 	WMCn = 9 / 5 = 1.8
 	CCog = 3 (3 + 0 + 0 + 0 + 0)
 	CCogn = 3 / 5 = 0.6
 	CBO = 2 (Clases acopladas: CategoriaTransporte, IllegalArgumentException) 
 	DIT = 1 (Hereda de Object)
 	NOC = 0 (No tiene subclases)
*/

/* Clase que representa un transporte realizado por un conductor */
public class Transporte {
	
	private double horas;
	private int ton;
	private int personas;
	private CategoriaTransporte cat;
	
	/**
	 * Constructor de la clase Transporte
	 * @param horas Horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param valor En caso de ser un transporte de tipo Personas, 
	 * representa el numero de personas, en caso de ser de tipo Mercancias 
	 * representa las toneladas
	 */ 

	//CC = 5 || CCog = 3
	public Transporte(double horas, CategoriaTransporte cat, int valor) throws IllegalArgumentException { //CC + 1
		if (horas <= 0 || valor <= 0 || cat == null) { // CC + 1 + 2 || Ccog + 1 + 1
			throw new IllegalArgumentException();
		}
		this.horas = horas;
		this.cat = cat;
		if (cat.equals(CategoriaTransporte.Personas)) { //CC + 1 || Ccog + 1
			this.personas = valor;
		} else  {
			this.ton = valor;
		}
	}
	
	// CC = 1 || CCog = 0
	public double horas() { //CC + 1
		return horas;
	}
	
	// CC = 1 || CCog = 0
	public CategoriaTransporte categoria() { //CC + 1
		return cat;
	}

	// CC = 1 || CCog = 0
	public int ton() { //CC + 1
		return ton;
	}

	// CC = 1 || CCog = 0
	public int getPersonas() { //CC + 1
		return personas;
	}
	
}
