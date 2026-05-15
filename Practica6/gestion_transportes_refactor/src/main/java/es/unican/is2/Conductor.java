package es.unican.is2;

import java.util.ArrayList;
/*
WMC = 13
WMCn = 13/9 = 1.44
CCOg = 3
CCogn = 3/9 = 0.33

*/

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado.
 */
public class Conductor {

	private static final double SUELDO_BASE = 700.0;
	private static final double PRECIO_HORA = 5.0;

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

	public String getDireccion() { // CC +1 || CCog +0
		return dire;
	}

	public double sueldo() { // CC+1 || CCog +0
		double sueldoTransportes = 0;
		for (Transporte t : transportes) { // CC +1 || CCog +1
			sueldoTransportes += (t.getHoras() * PRECIO_HORA) + t.getSueldoExtra();
		}

		return SUELDO_BASE + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte t) { // CC +1 || CCog +0
		transportes.add(t);
	}

}
