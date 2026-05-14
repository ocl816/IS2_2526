package es.unican.is2;

import java.util.ArrayList;
import java.util.List;

/*
 	WMC = 6 (3 + 2 + 1)
	WMCn = 6 / 3 = 2.0
	CCog = 4 (3 + 1 + 0)
	CCogn = 4 / 3 = 1.33
*/
public class gestionTransportes {

	private ArrayList<Conductor> cs = new ArrayList<Conductor>();
	
	// CC = 3 || CCog = 3
	public Conductor buscaConductor(String DNI) { //CC + 1	
		for(Conductor c: cs) // CC + 1 || CCog + 1
			if (c.dni().equals(DNI)) // CC + 1  || CCog + 2 (por nivel de anidamiento)
				return c;
		
		return null;
	}
	
	//CC = 2 || CCog = 1
	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) { // CC + 1
		if (buscaConductor(dni) != null) // CC + 1 || CCog + 1
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
	}

	//CC = 1 || CCog = 0
	public List<Conductor> conductores() {
		return cs;
	}
	
}
