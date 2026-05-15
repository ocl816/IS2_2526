package es.unican.is2;

import java.util.ArrayList;
/*
WMC = 18
WMCn = 18/9 = 2
CCOg = 9
CCogn = 9/9 = 1

*/

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado.
 */
public class Conductor {

	private static final double SUELDO_BASE = 700.0;
	private static final double PRECIO_HORA = 5.0;
	private static final double PRECIO_TONELADA = 2.0;
	private static final double PLUS_PELIGROSIDAD = 50.0;

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dire;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) { // CC +1 || CCog +0
		if (dni == null || nombre == null || apellido1 == null || direccion == null) { // CC +1 +1 +1 +1 || CCog +1 +1
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dire = direccion;
	}

	public String getDni() { // CC +1 || CCog +0
		return dni;
	}

	public String getNombre() { // CC +1 || CCog +0
		return nombre;
	}

	public String getApellido1() { // CC +1 || CCog +0
		return apellido1;
	}

	public String getApellido2() { // CC +1 || CCog +0
		return apellido2;
	}

	public String getDire() { // CC +1 || CCog +0
		return dire;
	}

	public double sueldo() { // CC+1 || CCog +0
		double sueldoTransportes = 0;
		for (Transporte t : transportes) { // CC +1 || CCog +1
			double sueldoExtraTransporte = 0.0;
			switch (t.categoria()) { // CC +1 +1 +1 || CCog +1 +1
				case Mercancias:
					sueldoExtraTransporte = t.ton() * PRECIO_TONELADA;
					break;
				case MercanciasPeligrosas:
					sueldoExtraTransporte = t.ton() * PRECIO_TONELADA + PLUS_PELIGROSIDAD;
					break;
				case Personas:
					if (t.getPersonas() < 10) // CC +1 || CCog +1 +1 +1
						sueldoExtraTransporte = t.getHoras() * 0.5;
					else // CCog +1
						sueldoExtraTransporte = t.getHoras();
					break;
			}
			sueldoTransportes += t.getHoras() * PRECIO_HORA + sueldoExtraTransporte;
		}
		return SUELDO_BASE + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte t) { // CC +1 || CCog +0
		transportes.add(t);
	}

}
